package org.example.ratelimiter.utils.rateLimiterAlgorithms;


import org.example.ratelimiter.model.RateLimiterAlgo;
import org.example.ratelimiter.model.User;
import org.example.ratelimiter.utils.RateLimiter;
import org.example.ratelimiter.model.RateLimiterConfig;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

public class SlidingWindowRateLimiter extends RateLimiter {

    ConcurrentHashMap<String, Deque<Long>> window = new ConcurrentHashMap<>();
    public SlidingWindowRateLimiter(RateLimiterConfig rateLimiterConfig) {
        super(rateLimiterConfig, RateLimiterAlgo.SLIDING_WINDOW);
    }

    @Override
    public boolean allowRequest(User user) {
        Long now = System.currentTimeMillis() / 1000;
        String userId = user.getUserId();
        AtomicBoolean allowed = new AtomicBoolean(false);
        window.compute(userId,(id,log)->{
           if(log == null){
               log = new ArrayDeque<>();
           }
           while(!log.isEmpty() && (now - log.getFirst()) > rateLimiterConfig.getWindowInSeconds()){
               log.removeFirst();
           }
           if(log.size() < rateLimiterConfig.getMaxRequestsAllowed()){
               allowed.set(true);
               log.addLast(now);
           }
           return log;
        });
        return allowed.get();
    }
}
