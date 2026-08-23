package com.applicant.intake.service;

import com.applicant.intake.model.ApplicationForm;
import com.applicant.intake.util.FormValidator;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;


public class ApplicationService {
    public static void createApplication() {
        System.out.println("\n----- PERSONAL INFORMATION -----");
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("First Name: ");
        String firstName = scanner.nextLine();
        System.out.print("Last Name: ");
        String lastName = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();  
        boolean isValidEmail = FormValidator.isValidEmail(email);
        boolean emailExists = FormValidator.doesEmailExist(email);
        while (!isValidEmail || emailExists) {
            System.out.print(!isValidEmail?"Invalid email address!\nEmail: ":"Application with this email address already exists!\nEmail: ");
            email = scanner.nextLine();  
            isValidEmail = FormValidator.isValidEmail(email.toLowerCase());
            emailExists = FormValidator.doesEmailExist(email.toLowerCase());
        };


        System.out.println("\n\n----- EDUCATION INFORMATION -----");
        System.out.print("Program: ");
        String program = scanner.nextLine();
        System.out.print("University/College: ");
        String university = scanner.nextLine();

        System.out.print("Your GPA: ");
        Double gpaInput = scanner.nextDouble();
        boolean isValidGpa = FormValidator.isValidGpa(gpaInput);
        while (!isValidGpa) {
            System.out.print("Invalid GPA!\nYour GPA: ");
            gpaInput = scanner.nextDouble();  
            isValidGpa = FormValidator.isValidGpa(gpaInput);
        }
        scanner.nextLine();

        System.out.println("\n\n----- Guardian Information -----");
        System.out.print("Guardian Full Name: ");
        String guardianName = scanner.nextLine();
        System.out.print("Guardian Contact: ");
        String guardianContact = scanner.nextLine();   
        System.out.print("Guardian Email: ");
        String guardianEmail = scanner.nextLine();

        ApplicationRepository repo = new ApplicationRepository();
        int applicationIndex = 0;
        try {
            List<ApplicationForm> forms = repo.load();
            applicationIndex = forms.size()+1;
        } catch (IOException exception) {
            System.out.println("Unable to load existing applications: " + exception.getMessage());
        }
        ApplicationForm newApplication = new ApplicationForm(firstName.toLowerCase(), lastName.toLowerCase(), email.toLowerCase(), program.toLowerCase(), university.toLowerCase(), gpaInput, guardianName.toLowerCase(), guardianContact, guardianEmail.toLowerCase(), applicationIndex);

        try {
            repo.save(newApplication);
            System.out.println("\n\n=========================================\nAPPLICATION SUBMITTED SUCCESSFULLY\n\nApplication ID: " + newApplication.getId() + "\nStatus:PENDING" + "\n\nPlease keep your application ID" + "\n=========================================");
        } catch (IOException exception) {
            System.out.println("Unable to save new application: "+ exception.getMessage()+"\nPlease start application again\nNEW APPLICATION FORM STARTED!");
            createApplication();
        }

    }
}