package com.applicant.intake.util;

public class FormValidator {   
     
    public static boolean isValidEmail(String email) {
        return email != null && email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    };
    
    
    public static boolean isValidGpa(double gpa) {
        return gpa >= 0.0 && gpa <= 5.0;
    };
}