package com.applicant.intake.model;

import com.applicant.intake.util.ConsoleInput;

public abstract class Menu {
    private boolean running;

    public int getUserOption() {
        return ConsoleInput.readInt("Please select an option: ");
    }

    public abstract void displayMenu();
    public abstract void handleOption(int option);

    public void loadMenu() {
        running = true;
        while (running) {
            displayMenu();
            handleOption(getUserOption());
        }
    }

    protected void stopMenu() {
        running = false;
    }
}