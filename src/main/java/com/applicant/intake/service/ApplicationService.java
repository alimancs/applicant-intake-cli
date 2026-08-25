package com.applicant.intake.service;

import com.applicant.intake.model.ApplicationForm;
import com.applicant.intake.types.ApplicationFormStatus;
import com.applicant.intake.util.ConsoleInput;
import com.applicant.intake.util.FormValidator;
import java.io.IOException;
import java.util.List;


public class ApplicationService {
    public static void createApplication() {
        System.out.println("\n----- PERSONAL INFORMATION -----");
        System.out.print("First Name: ");
        String firstName = ConsoleInput.readLine("");
        System.out.print("Last Name: ");
        String lastName = ConsoleInput.readLine("");

        System.out.print("Email: ");
        String email = ConsoleInput.readLine("").toLowerCase();
        boolean isValidEmail = FormValidator.isValidEmail(email);
        boolean emailExists = FormValidator.doesEmailExist(email);
        while (!isValidEmail || emailExists) {
            System.out.print(!isValidEmail?"Invalid email address!\nEmail: ":"Application with this email address already exists!\nEmail: ");
            email = ConsoleInput.readLine("").toLowerCase();
            isValidEmail = FormValidator.isValidEmail(email.toLowerCase());
            emailExists = FormValidator.doesEmailExist(email.toLowerCase());
        };


        System.out.println("\n\n----- EDUCATION INFORMATION -----");
        System.out.print("Program: ");
        String program = ConsoleInput.readLine("");
        System.out.print("University/College: ");
        String university = ConsoleInput.readLine("");

        System.out.print("Your GPA: ");
        Double gpaInput = ConsoleInput.readDouble("");
        boolean isValidGpa = FormValidator.isValidGpa(gpaInput);
        while (!isValidGpa) {
            System.out.print("Invalid GPA!\nYour GPA: ");
            gpaInput = ConsoleInput.readDouble("");
            isValidGpa = FormValidator.isValidGpa(gpaInput);
        }
        System.out.println("\n\n----- Guardian Information -----");
        System.out.print("Guardian Full Name: ");
        String guardianName = ConsoleInput.readLine("");
        System.out.print("Guardian Contact: ");
        String guardianContact = ConsoleInput.readLine("");
        System.out.print("Guardian Email: ");
        String guardianEmail = ConsoleInput.readLine("").toLowerCase();

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
            return;
        }

    }

    public static void searchApplicationById() {
        System.out.println("\n\n----- Search Application By ID -----");
        System.out.print("Enter application ID: ");
        String id = ConsoleInput.readLine("");
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
    }

    public static void searchApplicationByEmail() {
        System.out.println("\n\n----- Search Application By Email-----");
        System.out.print("Enter application email: ");
        String email = ConsoleInput.readLine("").toLowerCase();
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
    }

    public static void reviewApplication() {
        System.out.println("\n\n>>> Review Application");
        System.out.print("Enter application ID: ");
        String id = ConsoleInput.readLine("");
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
            int opt = ConsoleInput.readInt("");
            if (opt == 1) {
                result.setStatus(ApplicationFormStatus.APPROVED);
                try {
                    repo.removeById(result.getId());
                    repo.save(result);
                    System.out.println("Successfully approved application");
                } catch (IOException exception) {
                    System.out.println("Unable to Accept application: " + exception.getMessage());
                }
                
            } else if (opt == 2) {
                result.setStatus(ApplicationFormStatus.REJECTED);
                try {
                    repo.removeById(result.getId());
                    repo.save(result);
                    System.out.println("Successfully rejected application");
                } catch (IOException exception) {
                    System.out.println("Unable to Reject application: " + exception.getMessage());
                }
            } else if (opt == 3) {
            } else if (opt == 4) {
                System.out.println("Exiting the system. Goodbye!");
            } else {
                System.out.println("Invalid option selected. Back to main menu.");
            }
        } else {
            System.out.println("\n\nApplication with id: "+id+" not found!\nTry again");
            return;
        }
    };

    public static void listApplications() {
        final int pageSize = 5;
        ApplicationRepository repo = new ApplicationRepository();
        List<ApplicationForm> applications;

        try {
            applications = repo.load();
        } catch (IOException exception) {
            System.out.println("Unable to load applications: " + exception.getMessage());
            return;
        }

        if (applications.isEmpty()) {
            System.out.println("No applications found.");
            return;
        }

        int page = 0;
        while (true) {
            int firstApplication = page * pageSize;
            int lastApplication = Math.min(firstApplication + pageSize, applications.size());
            int totalPages = (applications.size() + pageSize - 1) / pageSize;

            System.out.println("\n========= APPLICATIONS (Page " + (page + 1) + " of " + totalPages + ") =========");
            for (int index = firstApplication; index < lastApplication; index++) {
                ApplicationForm application = applications.get(index);
                System.out.println((index + 1) + ". " + application.getId()
                        + " | " + application.getFirstName() + " " + application.getLastName()
                        + " | " + application.getEmail()
                        + " | " + application.getProgram()
                        + " | Status: " + application.getStatus());
            }

            System.out.println("\n[N] Next page  [P] Previous page  [B] Back");
            String option = ConsoleInput.readLine("Select an option: ").toLowerCase();
            if (option.equals("n")) {
                if (lastApplication < applications.size()) {
                    page++;
                } else {
                    System.out.println("You are already on the last page.");
                }
            } else if (option.equals("p")) {
                if (page > 0) {
                    page--;
                } else {
                    System.out.println("You are already on the first page.");
                }
            } else if (option.equals("b")) {
                return;
            } else {
                System.out.println("Invalid option. Enter N, P, or B.");
            }
        }
    }

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

            double acceptanceRate = forms.isEmpty() ? 0.0 : accepted * 100.0 / forms.size();

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