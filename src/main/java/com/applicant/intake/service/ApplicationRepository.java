package com.applicant.intake.service;

import com.applicant.intake.model.ApplicationForm;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



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
        File parent = file.getParentFile();
        if (parent != null && !parent.exists() && !parent.mkdirs()) {
            throw new IOException("Unable to create data directory: " + parent);
        }
        objectMapper.writeValue(file, applications);
    }

    public void removeById(String id) throws IOException {
        List<ApplicationForm> applications = load();
        applications.removeIf(a -> a != null && id.equals(a.getId()));
        objectMapper.writeValue(file, applications);
    }

    public List<ApplicationForm> load() throws IOException {

        if (!file.exists()) {
            return new ArrayList<>();
        }

        List<ApplicationForm> applications = objectMapper.readValue(
                file,
                new TypeReference<List<ApplicationForm>>() {}
        );
        return applications == null ? new ArrayList<>() : applications;
    }

}