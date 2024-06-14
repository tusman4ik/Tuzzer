package org.example.limiters;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
@Getter
@Slf4j
public final class Limiter {
    private Duration duration;
    private long count;

    private Limiter() {}

    public static Limiter of(final Duration duration) {
        Limiter limiter = new Limiter();
        limiter.duration = duration;
        return limiter;
    }

    public static Limiter of(final long count) {
        if (count < 0) {
            throw new IllegalArgumentException("Count must be a positive number");
        }
        Limiter limiter = new Limiter();
        limiter.count = count;
        return limiter;
    }

    public LimiterType type(){
        if (duration != null) {
            return LimiterType.COUNT;
        }else {
            return LimiterType.DURATION;
        }
    }

}
