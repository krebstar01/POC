package webpage.model.gameduell;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import webpage.model.TrashMail.TrashMailPage;
import webpage.model.gameduell.GameDuellLoginPage;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertTrue;

/**
 * Created by justin on 15.06.15.
 */
public class TestGameDuellLoginPage {

    private GameDuellLoginPage page;

    @Before
    public void openTheBrowser() {
        page = PageFactory.initElements(new FirefoxDriver(), GameDuellLoginPage.class);
        page.open("http://www.gameduell.com/");
    }

    @After
    public void closeTheBrowser() {
        page.close();
    }



}
