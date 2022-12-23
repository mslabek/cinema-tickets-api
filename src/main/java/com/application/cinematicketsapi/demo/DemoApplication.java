package com.application.cinematicketsapi.demo;

import com.application.cinematicketsapi.demo.model.DemoDataRoot;
import com.application.cinematicketsapi.demo.service.DataGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.IOException;

/**
 * Class for executing operations related to setting up a demo version of the application.
 */
@Service
@Profile("demo")
@RequiredArgsConstructor
public class DemoApplication implements CommandLineRunner {

    private final DataGenerator dataGenerator;
    private final ObjectMapper mapper;

    @Override
    public void run(String... args) throws IOException {
        generateAndPersistData("classpath:democonfig.json");
    }

    /**
     * Generates and persists data from a file with a given path.
     * <p>
     * This is a way of externalising the demo data. The file should be formatted as JSON and should represent a
     * {@link DemoDataRoot} object.
     *
     * @param demoDataFilePath the path of the JSON file containing the demo data
     * @throws IOException if the resource cannot be resolved to a file in the file system
     */
    public void generateAndPersistData(String demoDataFilePath) throws IOException {
        DemoDataRoot demoData = mapper.readValue(ResourceUtils.getFile(demoDataFilePath), DemoDataRoot.class);
        dataGenerator.generateAndPersistRooms(demoData.getCinema());
        dataGenerator.generateAndPersistMovies(demoData.getMovies());
        dataGenerator.generateAndPersistScreenings(demoData.getMovies());
    }

}
