package com.skywalking.pojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlarmMessage {
    private int scopeId;
    private String name;
    private int id0;
    private int id1;
    private String alarmMessage;
    private long startTime;
}
