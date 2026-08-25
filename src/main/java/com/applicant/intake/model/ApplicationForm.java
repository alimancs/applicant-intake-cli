package com.applicant.intake.model;

import com.applicant.intake.types.ApplicationFormStatus;

public class ApplicationForm {
    private String firstName;
    private String lastName;
    private String email;
    private String program;
    private String university;
    private Double gpa;
    private String guardianName;
    private String guardianContact;
    private String guardianEmail;
    private String id;
    private ApplicationFormStatus status;

    public ApplicationForm() {};

    public ApplicationForm(String firstName, String lastName, String email, String program, String university, Double gpa, String guardianName, String guardianContact, String guardianEmail, int index) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.program = program;
        this.university = university;
        this.gpa = gpa;
        this.guardianName = guardianName;
        this.guardianContact = guardianContact;
        this.guardianEmail = guardianEmail;
        this.status = ApplicationFormStatus.PENDING;
        String emailPrefix = email.substring(0, email.indexOf('@'));
        String idPrefix = emailPrefix.substring(0, Math.min(4, emailPrefix.length()));
        this.id = "app2026" + idPrefix + index;
    }

    public String getFirstName() {
        return firstName;
    }

    public String setFirstName(String newFirstName) {
        this.firstName = newFirstName;
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String setLastName(String newLastName) {
        this.lastName = newLastName;
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String setEmail(String newEmail) {
        this.email = newEmail;
        return email;
    }

    public String getProgram() {
        return program;
    }

    public String setProgram(String newProgram) {
        this.program = newProgram;
        return program;
    }

    public String getUniversity() {
        return university;
    }

    public String setUniversity(String newUniversity) {
        this.university = newUniversity;
        return university;
    }

    public Double getGpa() {
        return gpa;
    }

    public Double setGpa(Double newGpa) {
        this.gpa = newGpa;
        return gpa;
    }

    public String getGuardianName() {
        return guardianName;
    }

    public String setGuardianName(String newGuardianName) {
        this.guardianName = newGuardianName;
        return guardianName;
    }

    public String getGuardianContact() {
        return guardianContact;
    }

    public String setGuardianContact(String newGuardianContact) {
        this.guardianContact = newGuardianContact;
        return guardianContact;
    }

    public String getGuardianEmail() {
        return guardianEmail;
    }

    public String setGuardianEmail(String newGuardianEmail) {
        this.guardianEmail = newGuardianEmail;
        return guardianEmail;
    }

    public String getId() {
        return id;
    }

    public ApplicationFormStatus getStatus() {
        return status;
    }

    public ApplicationFormStatus setStatus(ApplicationFormStatus newStatus) {
        this.status = newStatus;
        return status;
    }

    @Override
    public String toString() {
        return "Application {" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", program='" + program + '\'' +
                ", university='" + university + '\'' +
                ", gpa='" + gpa + '\'' +
                ", guardianName='" + guardianName + '\'' +
                ", guardianContact='" + guardianContact + '\'' +
                ", guardianEmail='" + guardianEmail + '\'' +
                ", id='" + id + '\'' +
            '}';
    };
}