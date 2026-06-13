package org.example.realSystems.ratelimiter.utils.rateLimiterAlgorithms;



import org.example.realSystems.ratelimiter.model.RateLimiterAlgo;
import org.example.realSystems.ratelimiter.model.User;
import org.example.realSystems.ratelimiter.utils.RateLimiter;
import org.example.realSystems.ratelimiter.model.RateLimiterConfig;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

public class TokenBucketRateLimiter extends RateLimiter {

    // Both maps must be concurrent — they're written from multiple threads
    private final ConcurrentHashMap<String, Long> lastRefillTime = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, Long> tokens = new ConcurrentHashMap<>();

    public TokenBucketRateLimiter(RateLimiterConfig config) {
        super(config, RateLimiterAlgo.FIXED_TOKEN_BUCKET);
    }

    @Override
    public boolean allowRequest(User user) {
        long now = System.currentTimeMillis();
        AtomicBoolean allowed = new AtomicBoolean(false);

        tokens.compute(user.getUserId(), (id, currentTokens) -> {
            // Initialize on first request
            if (currentTokens == null) {
                lastRefillTime.put(id, now);
                currentTokens = (long) rateLimiterConfig.getMaxRequestsAllowed();
            }

            // Refill based on elapsed time — done inside compute() so it's atomic
            long lastRefill = lastRefillTime.getOrDefault(id, now);
            long elapsedSeconds = (now - lastRefill) / 1000;

            if (elapsedSeconds > 0) {
                double tokensPerSecond =
                        (double) rateLimiterConfig.getMaxRequestsAllowed()
                                / rateLimiterConfig.getWindowInSeconds();

                long tokensToAdd = (long) (elapsedSeconds * tokensPerSecond);

                if (tokensToAdd > 0) {
                    currentTokens = Math.min(
                            rateLimiterConfig.getMaxRequestsAllowed(),
                            currentTokens + tokensToAdd
                    );
                    lastRefillTime.put(id, now); // safe: still inside compute()'s lock on this key
                }
            }

            // Consume a token if available
            if (currentTokens > 0) {
                allowed.set(true);
                return currentTokens - 1;
            }
            return currentTokens;
        });

        return allowed.get();
    }
}