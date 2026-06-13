package org.example.realSystems.ratelimiter.utils;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.realSystems.ratelimiter.model.RateLimiterAlgo;
import org.example.realSystems.ratelimiter.model.RateLimiterConfig;
import org.example.realSystems.ratelimiter.model.User;

@AllArgsConstructor
@Data
public abstract class RateLimiter {
    protected RateLimiterConfig rateLimiterConfig;
    protected RateLimiterAlgo rateLimiterAlgo;

    public abstract boolean allowRequest(User user);
}
