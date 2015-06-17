package webpage.model.google;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Created by justin on 15.06.15.
 */
public class TestGoogleSearch {

    private GoogleSearchPage page;

    @Before
    public void openTheBrowser() {
        page = PageFactory.initElements(new FirefoxDriver(), GoogleSearchPage.class);
        page.open("http://google.de/");
    }

    @After
    public void closeTheBrowser() {
        page.close();
    }

    @Test
    public void whenTheUserSearchesForSeleniumTheResultPageTitleShouldContainCats() throws Exception{
        page.searchFor("selenium");
        //Dleep is needed to allow browswer to "do it's thing"
        // meaning page change and getTitle of current windows takes a bit of time
        sleep(2000);
        boolean results = page.getTitle().contains("selenium");
        assertTrue(results);
    }

}
