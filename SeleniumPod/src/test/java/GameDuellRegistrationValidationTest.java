import webpage.model.TrashMail.TrashMailPage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by justin on 17.06.15.
 */
public class GameDuellRegistrationValidationTest {

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

        GameDuellRegistrationValidationTest regexTest = new GameDuellRegistrationValidationTest();
        System.out.println(regexTest.validateTrashMailDomain("yyy"));
        System.out.println(regexTest.validateTrashMailDomain(TrashMailPage.TRASH_MAIL_DOMAIN_02));
        System.out.println(regexTest.validateEmail("XXXX"));
        System.out.println(regexTest.validateEmail("yadda@yadda.com"));
        System.out.println(regexTest.validatePassword("XXX"));
        System.out.println(regexTest.validatePassword("aB1XXXXXXX"));
        System.out.println(("aB1XXXXXXX".trim().length() < 0));



    }

}
