package org.example.ratelimiter.factory;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.ratelimiter.model.RateLimiterAlgo;
import org.example.ratelimiter.utils.RateLimiter;
import org.example.ratelimiter.model.RateLimiterConfig;
import org.example.ratelimiter.utils.rateLimiterAlgorithms.SlidingWindowRateLimiter;
import org.example.ratelimiter.utils.rateLimiterAlgorithms.TokenBucketRateLimiter;

@AllArgsConstructor
@Data
public class RateLimiterFactory {
    public static RateLimiter createRateLimiter(RateLimiterAlgo rateLimiterAlgo, RateLimiterConfig rateLimiterConfig){
        return switch (rateLimiterAlgo) {
            case FIXED_TOKEN_BUCKET -> new TokenBucketRateLimiter(rateLimiterConfig);
            case SLIDING_WINDOW -> new SlidingWindowRateLimiter(rateLimiterConfig);
            default -> throw new IllegalArgumentException("No rate limiter for that algorithm");
        };
    }
}
