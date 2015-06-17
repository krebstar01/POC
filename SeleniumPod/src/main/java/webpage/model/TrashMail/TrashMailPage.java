package webpage.model.TrashMail;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static java.lang.Thread.sleep;

/**
 * Created by justin on 15.06.15.
 */
public class TrashMailPage {


    public TrashMailPage(WebDriver driver) {
        this.driver = driver;
        this.jSdriver = (JavascriptExecutor) driver;
        driver.manage().window().maximize();
    }

    protected WebDriver driver;
    protected JavascriptExecutor jSdriver;



    @FindBy(id = "fe-notify")
    private WebElement notifyUserUponDiposalCheckbox;
    @FindBy(id = "fe-name")
    private WebElement disposableEmailAddressUser;
    @FindBy(id = "fe-domain")
    private WebElement disposableEmailAddressDomainHiddenValue;
    @FindBy(id = "fe-fwd-nb")
    private WebElement dropdownSelectedForwardHiddenValue;
    @FindBy(id = "fe-life-span")
    private WebElement dropdownSelectedLifespanHiddenValue;
    @FindBy(id = "fe-forward")
    private WebElement realUserEmail;
    @FindBy(id = "fe-submit")
    private WebElement trashSubmitButton;
    @FindBy(id = "fe-dea")
    private WebElement postSubmitReturnedEmail;


    private static HashSet<String> trashMailSet = new HashSet<String>();

    public static final String TRASH_MAIL_DOMAIN_01 = "trashmail.com";
    public static final String TRASH_MAIL_DOMAIN_02 = "trashmail.com";
    public static final String TRASH_MAIL_DOMAIN_03 = "kurzepost.de";
    public static final String TRASH_MAIL_DOMAIN_04 = "objectmail.com";
    public static final String TRASH_MAIL_DOMAIN_05 = "proxymail.eu";
    public static final String TRASH_MAIL_DOMAIN_06 = "rcpt.at";
    public static final String TRASH_MAIL_DOMAIN_07 = "trash-mail.at";
    public static final String TRASH_MAIL_DOMAIN_08 = "trashmail.at";
    public static final String TRASH_MAIL_DOMAIN_09 = "trashmail.com";
    public static final String TRASH_MAIL_DOMAIN_10 = "trashmail.me";
    public static final String TRASH_MAIL_DOMAIN_11 = "trashmail.net";
    public static final String TRASH_MAIL_DOMAIN_12 = "wegwerfmail.de";
    public static final String TRASH_MAIL_DOMAIN_13 = "wegwerfmail.net";
    public static final String TRASH_MAIL_DOMAIN_14 = "wegwerfmail.org";


    public static HashSet<String> getTrashMailSet() {
        trashMailSet.add(TRASH_MAIL_DOMAIN_01);
        trashMailSet.add(TRASH_MAIL_DOMAIN_02);
        trashMailSet.add(TRASH_MAIL_DOMAIN_03);
        trashMailSet.add(TRASH_MAIL_DOMAIN_04);
        trashMailSet.add(TRASH_MAIL_DOMAIN_05);
        trashMailSet.add(TRASH_MAIL_DOMAIN_06);
        trashMailSet.add(TRASH_MAIL_DOMAIN_07);
        trashMailSet.add(TRASH_MAIL_DOMAIN_08);
        trashMailSet.add(TRASH_MAIL_DOMAIN_09);
        trashMailSet.add(TRASH_MAIL_DOMAIN_10);
        trashMailSet.add(TRASH_MAIL_DOMAIN_11);
        trashMailSet.add(TRASH_MAIL_DOMAIN_12);
        trashMailSet.add(TRASH_MAIL_DOMAIN_13);
        trashMailSet.add(TRASH_MAIL_DOMAIN_14);
        return trashMailSet;
    }



    public WebElement getUserEmail() {
        return driver.findElement(By.cssSelector("input[ng-model='user.email']"));
    }


    /**
     *@return  WebElement -- fe-name
     */
    public WebElement getDisposableEmailAddressUser() {
        return disposableEmailAddressUser;
    }

    /**
     *@return  WebElement -- fe-domain
     */
    public WebElement getDisposableEmailAddressDomainHiddenValue() {
        return disposableEmailAddressDomainHiddenValue;
    }

    /**
     *@return  WebElement -- fe-fwd-nb
     */
    public WebElement getDropdownSelectedForwardHiddenValue() {
        return dropdownSelectedForwardHiddenValue;
    }

    /**
     *@return  WebElement -- fe-life-span
     */
    public WebElement getDropdownSelectedLifespanHiddenValue() {
        return dropdownSelectedLifespanHiddenValue;
    }

    /**
     *@return  WebElement -- fe-forward
     */
    public WebElement getrealUserEmail() {
        return realUserEmail;
    }

    /**
     *@return  WebElement -- fe-notify
     */
    public WebElement getNotifyUserUponDiposalCheckbox() {
        return notifyUserUponDiposalCheckbox;
    }

    /**
     *@return  WebElement -- fe-submit
     */
    public WebElement getTrashSubmitButton() {
        return trashSubmitButton;
    }

    /**
     *@return  WebElement -- fe-dea  value will only exists after sucessful submission
     */
    public WebElement getPostSubmitReturnedEmail() {
        return postSubmitReturnedEmail;
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

    /**
     *@return  WebDriver
     */
    public WebDriver getDriver() {
        return driver;
    }

    /**
     *@return  JavascriptExecutor
     */
    public JavascriptExecutor getjSdriver() {
        return jSdriver;
    }

}