package com.applicant_intake.applicant.model;


public class ApplicationForm {
    private String firstName;
    private String lastName;
    private String email;
    private String program;
    private String university;
    private String gpa;
    private String guardianName;
    private String guardianContact;
    private String guardianEmail;
    private String id;
    private String status;

    public ApplicationForm(String firstName, String lastName, String email, String program, String university, String gpa, String guardianName, String guardianContact, String guardianEmail) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.program = program;
        this.university = university;
        this.gpa = gpa;
        this.guardianName = guardianName;
        this.guardianContact = guardianContact;
        this.guardianEmail = guardianEmail;
        this.id = "APP2026"+email.split("@")[0];
        this.status = "PENDING"; // Default status is PENDING
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

    public String getGpa() {
        return gpa;
    }

    public String setGpa(String newGpa) {
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

    public String getStatus() {
        return status;
    }

    public String setStatus(String newStatus) {
        this.status = newStatus;
        return status;
    }

    public String toString() {
        return "Application{" +
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