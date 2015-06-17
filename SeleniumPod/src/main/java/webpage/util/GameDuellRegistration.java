package webpage.util;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import webpage.model.TrashMail.TrashMailPage;
import webpage.model.gameduell.GameDuellLoginNewHerePage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by justin on 17.06.15.
 */
public class GameDuellRegistration {

    private static Matcher matcher;
    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    private static Pattern patternEmail = Pattern.compile(EMAIL_PATTERN);;


    private static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{10,}$";

    private static Pattern patternPassword = Pattern.compile(PASSWORD_PATTERN);;

    private static boolean validateEmail(String hex) {
        matcher = patternEmail.matcher(hex);
        return matcher.matches();
    }

    private static boolean validatePassword(String hex) {
        matcher = patternPassword.matcher(hex);
        return matcher.matches();
    }

    private static boolean validateTrashMailDomain(String domain) {
        return TrashMailPage.getTrashMailSet().contains(domain);
    }



    public static void main(String[] args) {

        if (args.length <= 2 || (
                (args[0] == null && args[0].trim().isEmpty()) &&
                (args[1] == null && args[1].trim().isEmpty()) &&
                (args[2] == null && args[2].trim().isEmpty()))) {
            System.out.println("The needed arguements are");
            System.out.println(" - 1st arguement - A valid and real email address");
            System.out.println(" - 2nd arguement - An existing trashMailDomain (please see https://trashmail.com/");
            System.out.println(" - 3rd arguement (optional) - a password of your choice. otherwise the default password: yaddayadda");
            return;
        }



        if (args[0] == null || args[0].trim().isEmpty() || !validateEmail(args[0].trim()) ) {
            System.out.println("Please enter a valid and real email address");
            return;
        }

        if (args[1] == null || args[1].trim().isEmpty() || !validateTrashMailDomain(args[1].trim())) {
            System.out.println("Please enter an existing trashMailDomain (please see https://trashmail.com/");
            System.out.println("Or choose from: " + TrashMailPage.getTrashMailSet());
            return;
        }

        String password = args[2];

        if (password == null || password.trim().isEmpty()) {
            password = "yaddayadda";
        } else if(args[2].trim().length() < 0  || !validatePassword(args[2].trim())){

            System.out.println("Please enter a valid password with the following: ");
            System.out.println("a) at least one digit, b)at least one lower case letter ");
            System.out.println("c) at least one upper case letter");
            System.out.println("d) consisting of at least 10 characters");
            System.out.println("VALID EXAMPLE: aB1XXXXXXX");
            System.out.println("Alternatively. you can leave the 3rd aguement empty for the default password: yaddayadda");

            return;
        }



        GameDuellRegistration t = new GameDuellRegistration();
        GameDuellLoginNewHerePage page = PageFactory.initElements(new FirefoxDriver(), GameDuellLoginNewHerePage.class);
        page.open("http://www.gameduell.com/registration.html");

        TrashMailGenerator generator = new TrashMailGenerator();
        String temp_email = "";
        try {
            temp_email = generator.generateTrashEmail(args[0], args[1]);
        } catch (InterruptedException e) {
            System.out.println("Something went wonky with the main thread -- sleep choked");
            System.out.println(e.getCause());
        }

        String userId = "";
        int index = temp_email.indexOf('@');
        if (index != -1) {
            userId = temp_email.substring(0, index);
        }
        page.typeUserNameTerm(userId);
        page.typePassWordTerm(password);
        page.typeEmail(temp_email);
        page.clickOnRegisterButton();
        page.close();
        System.out.println("You now are regustered at Gamduell with the temporary email: " + temp_email);
        System.out.println("Please right down your password, which is: " + password);
    }

}