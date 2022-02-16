package com.rajan.websocketclient.domain;

import lombok.Data;

@Data
public class OutputMessage {
    private String from;
    private String text;
    private String time;
}
