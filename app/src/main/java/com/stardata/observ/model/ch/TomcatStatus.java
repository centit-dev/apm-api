package com.stardata.observ.model.ch;

import lombok.Data;

@Data
public class TomcatStatus {
    private Integer threads;
    private Integer activeSessions;
    private Long requestCount;
}
