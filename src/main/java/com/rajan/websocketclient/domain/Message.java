package com.rajan.websocketclient.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Message {
    private String from;
    private String text;
}
