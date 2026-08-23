package com.applicant.intake.ui;

import com.applicant.intake.model.Menu;
import com.applicant.intake.service.ApplicationService;

public class MainMenu extends Menu {

    @Override
    public void displayMenu() {
        System.out.println("=========================================\n        APPLICANT INTAKE SYSTEM\n=========================================");
        System.out.println("\n1. Apply\n2. Search Application\n3. Reviewer Mode\n4. List Applications\n5. Exit");
    }

    @Override
    public void handleOption(int option) {
        if (option == 1) {
            ApplicationService.createApplication();
            displayMenu();
            int newOption = getUserOption();
            handleOption(newOption);
        } else if (option == 2) {
            System.out.println("You selected option 2: Search Application");
        } else if (option == 3) {
            System.out.println("You selected option 3: Reviewer Mode");
            // Call the reviewer mode method or class here
        } else if (option == 4) {
            System.out.println("You selected option 4: List Applications");
            // Call the list applications method or class here
        } else if (option == 5) {
            System.out.println("Exiting the system. Goodbye!");
        } else {
            System.out.println("Invalid option selected. Please try again.");
            int newOption = getUserOption();
            handleOption(newOption);
        };
    }
}