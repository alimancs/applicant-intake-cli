package com.applicant_intake.applicant.service;

import java.util.Scanner;
import com.applicant_intake.applicant.model.ApplicationForm;

public class ApplicationService {
    public void createApplication() {
        System.out.println("\n----- PERSONAL INFORMATION -----");
        Scanner scanner = new Scanner(System.in);
        System.out.print("First Name: ");
        String firstName = scanner.nextLine();
        System.out.print("Last Name: ");
        String lastName = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();  

        System.out.println("\n\n----- EDUCATION INFORMATION -----");
        System.out.print("Program: ");
        String program = scanner.nextLine();
        System.out.print("University/College: ");
        String university = scanner.nextLine();
        System.out.print("GPA: ");
        String gpaInput = scanner.nextLine();
        String gpa = gpaInput; // Store GPA as a string

        System.out.println("\n\n----- Guardian Information -----");
        System.out.print("Guardian Name: ");
        String guardianName = scanner.nextLine();
        System.out.print("Guardian Contact: ");
        String guardianContact = scanner.nextLine();   
        System.out.print("Guardian Email: ");
        String guardianEmail = scanner.nextLine();

        System.out.println(firstName +" "+ lastName +" "+ email +" "+ program +" "+ university +" "+ gpa +" "+ guardianName +" "+ guardianContact +" "+ guardianEmail);

        ApplicationForm newApplication = new ApplicationForm(firstName, lastName, email, program, university, gpa, guardianName, guardianContact, guardianEmail);

        System.out.println("\n\n=========================================\nAPPLICATION SUBMITTED SUCCESSFULLY\n\nApplication ID: " + newApplication.getId() + "\nStatus:PENDING" + "\n\nPlease keep your application ID" + "\n=========================================");
    }
}