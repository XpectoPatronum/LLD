package org.example.ratelimiter.utils.rateLimiterAlgorithms;


import org.example.ratelimiter.model.RateLimiterAlgo;
import org.example.ratelimiter.model.User;
import org.example.ratelimiter.utils.RateLimiter;
import org.example.ratelimiter.model.RateLimiterConfig;

public class SlidingWindowRateLimiter extends RateLimiter {

    public SlidingWindowRateLimiter(RateLimiterConfig rateLimiterConfig) {
        super(rateLimiterConfig, RateLimiterAlgo.SLIDING_WINDOW);
    }

    @Override
    public boolean allowRequest(User user) {
        return false;
    }
}
