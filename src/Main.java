import com.applicant_intake.applicant.ui.MainMenu;

public class Main {
    public static void main(String[] args) {
        MainMenu mainMenu = new MainMenu();
        mainMenu.displayMenu();
        int option = mainMenu.getUserOption();
        mainMenu.handleOption(option);
    }
     
}