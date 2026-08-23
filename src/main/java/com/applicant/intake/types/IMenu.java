package com.applicant.intake.types;

public interface IMenu {
    void handleOption(int option);
    void displayMenu();
    int getUserOption();
    void loadMenu();
}