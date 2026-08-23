package com.applicant.intake.ui;

import com.applicant.intake.model.Menu;

public class SearchApplicationMenu extends Menu {
    @Override
    public void displayMenu() {
        System.out.println("=========================================\n        SEARCH APPLICATIONS\n=========================================");
        System.out.println("\n1. Search by ID\n2. Search by email address\n3. Go back \n4. Exit");
    }

    @Override
    public void handleOption(int option) {
        if (option == 1) {
            System.out.println("You selected option 1: Search Application");
        } else if (option == 2) {
            System.out.println("You selected option 2: Search Application");
        } else if (option == 3) {
            System.out.println("You selected option 3: Search Application");
        } else if (option == 4) {
            System.out.println("You selected option 4: Go back");
        } else {
            System.out.println("Exiting the system. Goodbye!");
        };
    }
}