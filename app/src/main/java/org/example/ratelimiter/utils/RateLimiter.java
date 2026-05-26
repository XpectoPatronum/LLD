package org.example.ratelimiter.utils;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.ratelimiter.model.RateLimiterAlgo;
import org.example.ratelimiter.model.RateLimiterConfig;
import org.example.ratelimiter.model.User;

@AllArgsConstructor
@Data
public abstract class RateLimiter {
    protected RateLimiterConfig rateLimiterConfig;
    protected RateLimiterAlgo rateLimiterAlgo;

    public abstract boolean allowRequest(User user);
}
