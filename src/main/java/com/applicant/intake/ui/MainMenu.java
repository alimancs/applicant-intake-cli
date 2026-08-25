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
        } else if (option == 2) {
            SearchApplicationMenu searchMenu = new SearchApplicationMenu();
            searchMenu.loadMenu();
        } else if (option == 3) {
            ReviewerModeMenu reviewModeMenu = new ReviewerModeMenu();
            reviewModeMenu.loadMenu();
        } else if (option == 4) {
            ApplicationService.listApplications();
        } else if (option == 5) {
            System.out.println("Exiting the system. Goodbye!");
            stopMenu();
        } else {
            System.out.println("Invalid option selected. Please try again.");
        }
    }
}