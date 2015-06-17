package webpage.model.gameduell;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by justin on 15.06.15.
 */
public class GameDuellLoginPage {


    //


    /**
     *input name="j_username"
     * tabindex="7"
     * maxlength="60"
     * type="text"
     */

    /**
     *input name="j_password"
     * tabindex="8"
     * maxlength="60"
     * type="password"
     */

    /**
     *input name="j_password"
     * tabindex="8"
     * maxlength="60"
     * type="password"
     */

    /**
     *a href
     * tabindex="9"
     * id="loginLink"
     */




    protected WebDriver driver;
    private WebElement j_username;
    private WebElement j_password;
    private WebElement loginLink;

    public GameDuellLoginPage(WebDriver driver) {
        this.driver = driver;
    }
    public void open(String url) {
        driver.get(url);
    }
    public void close() {
        driver.quit();
    }
    public String getTitle() {
        return driver.getTitle();
    }
    public void typeUserNameTerm(String userName) {
        j_username.sendKeys(userName);
    }
    public void typePassWordTerm(String password) {
        j_password.sendKeys(password);
    }
    public void clickOnSearch() {
        loginLink.click();
    }
}