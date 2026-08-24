package com.applicant.intake.service;

import com.applicant.intake.model.ApplicationForm;
import com.applicant.intake.types.ApplicationFormStatus;
import com.applicant.intake.ui.MainMenu;
import com.applicant.intake.ui.ReviewerModeMenu;
import com.applicant.intake.ui.SearchApplicationMenu;
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

    public static void searchApplicationById() {
        System.out.println("\n\n----- Search Application By ID -----");
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter application ID: ");
        String id = scanner.nextLine();
        ApplicationForm result = null;

        ApplicationRepository repo = new ApplicationRepository();
        try {
            List<ApplicationForm> forms = repo.load();
            for (ApplicationForm form: forms) {
                if (form.getId().equals(id)) {
                    result = form;
                    break;
                }
            };
        } catch (IOException exception) {
            System.out.println("Unable to load existing applications: " + exception.getMessage());
        }

        if (result!=null) {
            System.out.println(
                "Applicant:\n" 
                + result.getFirstName()+" "+result.getLastName() 
                + "\n\nProgram:\n" 
                +result.getProgram()
                + "\n\nAcademic Score:\n" 
                + result.getGpa() 
                + "\n\nStatus: " 
                + result.getStatus()
                + "\n\nGuardian:\n" 
                + result.getGuardianName() 
                + "\n\nGuardian Email:\n" 
                +result.getGuardianEmail()
                + "\n\nGuardian Contact:\n" 
                + result.getGuardianContact()
            );
        } else {
            System.out.println("\n\nApplication with id: "+id+" not found!\nTry again");
        }
        searchApplicationById();
    };

    public static void searchApplicationByEmail() {
        System.out.println("\n\n----- Search Application By Email-----");
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter application email: ");
        String email = scanner.nextLine();
        ApplicationForm result = null;

        ApplicationRepository repo = new ApplicationRepository();
        try {
            List<ApplicationForm> forms = repo.load();
            for (ApplicationForm form: forms) {
                if (form.getEmail().equals(email)) {
                    result = form;
                    break;
                }
            };
        } catch (IOException exception) {
            System.out.println("Unable to load existing applications: " + exception.getMessage());
        }

        if (result!=null) {
            System.out.println(
                "Applicant:\n" 
                + result.getFirstName()+" "+result.getLastName() 
                + "\n\nProgram:\n" 
                +result.getProgram()
                + "\n\nAcademic Score:\n" 
                + result.getGpa() 
                + "\n\nStatus: " 
                + result.getStatus()
                + "\n\nGuardian:\n" 
                + result.getGuardianName() 
                + "\n\nGuardian Email:\n" 
                +result.getGuardianEmail()
                + "\n\nGuardian Contact:\n" 
                + result.getGuardianContact()
            );
        } else {
            System.out.println("\n\nApplication with id: "+email+" not found!\nTry again");
        }
        SearchApplicationMenu searchMenu = new SearchApplicationMenu();
        searchMenu.loadMenu();
    };

    public static void reviewApplication() {
        System.out.println("\n\n>>> Review Application");
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter application ID: ");
        String id = scanner.nextLine();
        ApplicationForm result = null;

        ApplicationRepository repo = new ApplicationRepository();
        try {
            List<ApplicationForm> forms = repo.load();
            for (ApplicationForm form: forms) {
                if (form.getId().equals(id)) {
                    result = form;
                    break;
                }
            };
        } catch (IOException exception) {
            System.out.println("Unable to load existing applications: " + exception.getMessage());
        }

        if (result!=null) {
            System.out.println("Applicant:\n" + result.getFirstName()+" "+result.getLastName() + "\n\nProgram:\n" +result.getProgram() + "\n\nAcademic Score:\n" + result.getGpa() + "\n\nStatus: " + result.getStatus()+ "\n\n1. Accept \n2. Reject \n3. Back \n4. Exit"
            );
            System.out.print("Select an option: ");
            int opt = scanner.nextInt();
            if (opt == 1) {
                result.setStatus(ApplicationFormStatus.APPROVED);
                try {
                    repo.removeById(result.getId());
                    repo.save(result);
                    System.out.println("Successfully approved application");
                    ReviewerModeMenu reviewerMenu = new ReviewerModeMenu();
                    reviewerMenu.loadMenu();
                } catch (IOException exception) {
                    System.out.println("Unable to Accept application: " + exception.getMessage());
                }
                
            } else if (opt == 2) {
                result.setStatus(ApplicationFormStatus.REJECTED);
                try {
                    repo.removeById(result.getId());
                    repo.save(result);
                    System.out.println("Successfully rejected application");
                    ReviewerModeMenu reviewerMenu = new ReviewerModeMenu();
                    reviewerMenu.loadMenu();
                } catch (IOException exception) {
                    System.out.println("Unable to Reject application: " + exception.getMessage());
                }
            } else if (opt == 3) {
                ReviewerModeMenu reviewerMenu = new ReviewerModeMenu();
                reviewerMenu.loadMenu();
            } else if (opt == 4) {
                System.out.println("Exiting the system. Goodbye!");
            } else {
                System.out.println("Invalid option selected. Back to main menu.");
                MainMenu mainMenu = new MainMenu();
                mainMenu.loadMenu();
            }
        } else {
            System.out.println("\n\nApplication with id: "+id+" not found!\nTry again");
            reviewApplication();
        }
    };

    public static void viewSummary() {
        ApplicationRepository repo = new ApplicationRepository();
        try {
            List<ApplicationForm> forms = repo.load();
            int pending = 0;
            int accepted = 0;
            int rejected = 0;

            for (ApplicationForm form: forms) {
                if (form.getStatus()==ApplicationFormStatus.APPROVED) {
                    accepted++;
                } else if (form.getStatus()==ApplicationFormStatus.REJECTED) {
                    rejected++;
                } else if (form.getStatus()==ApplicationFormStatus.PENDING) {
                    pending++;
                }
            };

            int acceptanceRate = (accepted/forms.size())*100;

            System.out.println(
            "========= APPLICATION SUMMARY =========" +
            "\nTotal Applications: "+forms.size()+

            "\nPending Applications: "+pending+
            "\nAccepted Applications: "+accepted+
            "\nRejected Applications: "+rejected+
            "\n\nAcceptance Rate: "+acceptanceRate+"%"
            );
            
        } catch (IOException exception) {
            System.out.println("Unable to load existing applications: " + exception.getMessage());
        }
    };
}