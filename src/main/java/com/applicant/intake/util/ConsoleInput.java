package com.applicant.intake.util;

import java.util.Scanner;

public final class ConsoleInput {
    private static final Scanner SCANNER = new Scanner(System.in);

    private ConsoleInput() {
    }

    public static String readLine(String prompt) {
        System.out.print(prompt);
        if (!SCANNER.hasNextLine()) {
            throw new IllegalStateException("No more input is available.");
        }
        return SCANNER.nextLine().trim();
    }

    public static int readInt(String prompt) {
        while (true) {
            String input = readLine(prompt);
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException exception) {
                System.out.println("Invalid number. Please enter a whole number.");
            }
        }
    }

    public static double readDouble(String prompt) {
        while (true) {
            String input = readLine(prompt);
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException exception) {
                System.out.println("Invalid number. Please enter a numeric value.");
            }
        }
    }
}
