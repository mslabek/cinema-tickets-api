package com.application.cinematicketsapi.demo.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ScreeningDemoData {

    private MovieDemoData movieDemoData;
    private Long roomId;
    private LocalDateTime beginning;
    private LocalDateTime ending;

}
