package webpage.model.gameduell;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by justin on 15.06.15.
 */
public class GameDuellLoginNewHerePage {

    protected WebDriver driver;
    private WebElement tf1;
    private WebElement tf2;
    private WebElement tf3;
    private WebElement registrationButton;

    public GameDuellLoginNewHerePage(WebDriver driver) {
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
        tf1.sendKeys(userName);
    }
    public void typePassWordTerm(String password) {
        tf2.sendKeys(password);
    }

    public void typeEmail(String email) {
        tf3.sendKeys(email);
    }

    public void clickOnRegisterButton() {
        registrationButton.click();
    }
}