package org.example.realSystems.ratelimiter.service;



import org.example.realSystems.ratelimiter.factory.RateLimiterFactory;
import org.example.realSystems.ratelimiter.model.RateLimiterAlgo;
import org.example.realSystems.ratelimiter.model.Tier;
import org.example.realSystems.ratelimiter.model.User;
import org.example.realSystems.ratelimiter.utils.RateLimiter;
import org.example.realSystems.ratelimiter.model.RateLimiterConfig;

import java.util.HashMap;
import java.util.Map;

public class RateLimiterService {
    Map<Tier, RateLimiter> rateLimiterMap = new HashMap<>();

    public RateLimiterService(){
        rateLimiterMap.put(Tier.FREE,
                RateLimiterFactory.createRateLimiter(
                        RateLimiterAlgo.FIXED_TOKEN_BUCKET,
                        new RateLimiterConfig(30L,5L)
                )
        );

        rateLimiterMap.put(Tier.PREMIUM,
                RateLimiterFactory.createRateLimiter(
                        RateLimiterAlgo.SLIDING_WINDOW,
                        new RateLimiterConfig(10L,10L)
                )
        );
    }

    public boolean allowRequest(User user){
        RateLimiter rateLimiter = rateLimiterMap.get(user.getUserTier());
        if(rateLimiter == null){
            throw new IllegalArgumentException("No rate limiter configured for this tier " + user.getUserTier().name());
        }
        return rateLimiter.allowRequest(user);
    }
}
