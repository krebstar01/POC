package webpage.model.gameduell;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import webpage.model.TrashMail.TrashMailPage;
import webpage.model.gameduell.GameDuellLoginNewHerePage;
import webpage.util.TrashMailGenerator;

import static java.lang.Thread.sleep;

/**
 * Created by justin on 15.06.15.
 */
public class TestGameDuellLoginNewHerePage {

    private GameDuellLoginNewHerePage page;
    //TODO
    private static final String REAL_USER_EMAIL = "krebstar01@yahoo.com";

    @Before
    public void openTheBrowser() {
           page = PageFactory.initElements(new FirefoxDriver(), GameDuellLoginNewHerePage.class);
           page.open("http://www.gameduell.com/registration.html");
    }

    @After
    public void closeTheBrowser() {
        page.close();
    }

    @Test
    public void whenTheUserSearchesForSeleniumTheResultPageTitleShouldContainCats() throws Exception {
        TrashMailGenerator generator = new TrashMailGenerator();
        String email = generator.generateTrashEmail(REAL_USER_EMAIL, TrashMailPage.TRASH_MAIL_DOMAIN_03);
        String userId = "";

        int index = email.indexOf('@');
        if (index!= -1)
        {
            userId = email.substring(0,index);
        }
        page.typeUserNameTerm(userId);
        page.typePassWordTerm("yaddayadda");
        page.typeEmail(email);
        page.clickOnRegisterButton();
        sleep(5000);
    }


}
