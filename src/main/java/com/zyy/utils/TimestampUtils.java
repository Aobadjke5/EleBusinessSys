package com.zyy.utils;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class TimestampUtils {

    public Timestamp getTimestamp(long timestamp) {
        return new Timestamp(timestamp);
    }
}
