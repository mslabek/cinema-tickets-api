package com.application.cinematicketsapi.demo.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class DemoDataRoot {

    private CinemaDemoData cinema;
    private List<MovieDemoData> movies = new ArrayList<>();
    private List<TicketDemoData> tickets = new ArrayList<>();

}
