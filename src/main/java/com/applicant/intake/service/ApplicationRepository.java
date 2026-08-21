package com.applicant.intake.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.applicant.intake.model.ApplicationForm;

public class ApplicationRepository {
    private ObjectMapper objectMapper;
    private File file;

    public ApplicationRepository() {
        objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        file = new File("data/applications.json");
    }

    public void save(ApplicationForm application) throws IOException {
        List<ApplicationForm> applications = load();
        applications.add(application);
        objectMapper.writeValue(file, applications);
    }

    public List<ApplicationForm> load() throws IOException {

        if (!file.exists()) {
            return new ArrayList<>();
        }

        return objectMapper.readValue(
                file,
                new TypeReference<List<ApplicationForm>>() {}
        );
    }

}