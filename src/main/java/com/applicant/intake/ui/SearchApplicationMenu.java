package com.applicant.intake.ui;

import com.applicant.intake.model.Menu;
import com.applicant.intake.service.ApplicationService;

public class SearchApplicationMenu extends Menu {
    @Override
    public void displayMenu() {
        System.out.println("=========================================\n        SEARCH APPLICATIONS\n=========================================");
        System.out.println("\n1. Search by ID\n2. Search by email address\n3. Go back \n4. Exit");
    }

    @Override
    public void handleOption(int option) {
        if (option == 1) {
            ApplicationService.searchApplicationById();
        } else if (option == 2) {
            ApplicationService.searchApplicationByEmail();
        } else if (option == 3) {
            stopMenu();
        } else if (option == 4) {
            System.out.println("Exiting the system. Goodbye!");
            stopMenu();
        } else {
            System.out.println("Invalid option selected. Please try again.");
        }
    }
}