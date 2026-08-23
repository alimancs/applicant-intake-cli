package com.applicant.intake.util;

import com.applicant.intake.model.ApplicationForm;
import com.applicant.intake.service.ApplicationRepository;
import java.io.IOException;
import java.util.List;

public class FormValidator {   
     
    public static boolean isValidEmail(String email) {
        return email != null && email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    };
    
    public static boolean isValidGpa(double gpa) {
        return gpa >= 0.0 && gpa <= 5.0;
    };

    public static boolean doesEmailExist(String email) {
        ApplicationRepository repo = new ApplicationRepository();
        boolean emailExists = false;
        try {
            List<ApplicationForm> forms = repo.load();
            for (int i = 0;i<=forms.size()-1;i++) {
                // System.out.println("Comparing: "+forms.get(i).getEmail()+" --> "+email);
                if (forms.get(i).getEmail().equals(email)) {
                    emailExists=true;
                    break;
                } 
            }
        } catch (IOException exception) {
            System.out.println("Unable to load existing applications: " + exception.getMessage());
        }
        return emailExists;
    }
}