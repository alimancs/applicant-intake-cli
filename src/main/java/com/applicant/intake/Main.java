package com.applicant.intake;

import com.applicant.intake.ui.MainMenu;

public class Main {
    public static void main(String[] args) {
        try {
            MainMenu mainMenu = new MainMenu();
            mainMenu.loadMenu();
        } catch (IllegalStateException exception) {
            System.out.println("Input ended before the application could finish: " + exception.getMessage());
        }
    }
     
}