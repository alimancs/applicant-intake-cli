package com.applicant.intake.ui;

import com.applicant.intake.model.Menu;
import com.applicant.intake.service.ApplicationService;

public class ReviewerModeMenu extends Menu {

    @Override
    public void displayMenu() {
        System.out.println("=========================================\n        REVIEWER MODE\n=========================================");
        System.out.println("\n1. Review Application \n2. View summary\n3. Back\n4. Exit");
    }

    @Override
    public void handleOption(int option) {
        if (option == 1) {
            ApplicationService.reviewApplication();
        } else if (option == 2) {
            System.out.println("You selected option 3: Reviewer Mode");
            // Call the reviewer mode method or class here
        } else if (option == 3) {
            MainMenu mainMenu = new MainMenu();
            mainMenu.loadMenu();
        } else if (option == 4) {
            System.out.println("Exiting the system. Goodbye!");
        } else {
            System.out.println("Invalid option selected. Please try again.");
            int newOption = getUserOption();
            handleOption(newOption);
        };
    }
}