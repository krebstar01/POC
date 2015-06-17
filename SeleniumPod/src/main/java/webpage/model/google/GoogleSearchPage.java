package webpage.model.google;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by justin on 15.06.15.
 */
public class GoogleSearchPage {



    protected WebDriver driver;
    private WebElement q;
    private WebElement btnK;

    public GoogleSearchPage(WebDriver driver) {
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
    public void searchFor(String searchTerm) {
        q.sendKeys(searchTerm);
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", btnK);
    }
    public void typeSearchTerm(String searchTerm) {
        q.sendKeys(searchTerm);
    }
    public void clickOnSearch() {
        btnK.click();
    }

}