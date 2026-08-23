package com.applicant.intake.model;

import java.util.Scanner;

public abstract class Menu {
   
    public int getUserOption() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please select an option: ");
        int option = scanner.nextInt();
        return option;
    }

    public abstract void displayMenu();
    public abstract void handleOption(int option);

    public void loadMenu() {
        displayMenu();
        int option = getUserOption();
        handleOption(option);
    }
}