package org.example.ratelimiter.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class RateLimiterConfig {
    private Long windowInSeconds;
    private Long maxRequestsAllowed;
}
