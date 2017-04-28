package com.concepts.domain.time.support;

import java.util.Date;

import com.concepts.domain.time.TimePoint;
import com.concepts.domain.time.TimeSource;


public class SystemClock implements TimeSource {

    public static TimeSource timeSource() {
        return new SystemClock();
    }

    public TimePoint now() {
        return TimePoint.from(new Date());
    }

}
