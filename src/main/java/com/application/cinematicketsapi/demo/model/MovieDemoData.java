package com.application.cinematicketsapi.demo.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class MovieDemoData {

    private String title;
    private List<ScreeningDemoData> screenings = new ArrayList<>();

}
