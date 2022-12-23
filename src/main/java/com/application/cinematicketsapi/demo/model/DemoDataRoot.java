package com.application.cinematicketsapi.demo.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DemoDataRoot {

    private CinemaDemoData cinema;
    private List<MovieDemoData> movies;

}
