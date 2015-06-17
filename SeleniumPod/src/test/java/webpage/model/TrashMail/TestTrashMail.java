package webpage.model.TrashMail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import webpage.model.TrashMail.TrashMailPage;

import java.math.BigInteger;
import java.security.SecureRandom;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertEquals;


/**
 * Created by justin on 15.06.15.
 */
public class TestTrashMail {


    //register a real email address with trashmail and set it here
    //TODO
    private static final String REAL_USER_EMAIL = "YOUR EMAIL HERE!!!!";

    private TrashMailPage pageTrashMail;
    private SecureRandom random = new SecureRandom();

    public String nextRandomString() {
        return new BigInteger(130, random).toString(32);
    }

    @Before
    public void openTheBrowser() {
        pageTrashMail = PageFactory.initElements(new FirefoxDriver(), TrashMailPage.class);
        pageTrashMail.open("https://trashmail.com/");
    }

    @After
    public void closeTheBrowser() {
        pageTrashMail.close();
    }

    @Test
    public void testGenerateTrashEmailFromPageModel() throws InterruptedException {
        sleep(2000);
        //values se'll use for our trash email
        String randomTrashEmailName = nextRandomString();
        //show off our trash email
        String expectedTrashEmailAddress =  randomTrashEmailName + "@" + TrashMailPage.TRASH_MAIL_DOMAIN_03;
        pageTrashMail.getjSdriver().executeScript("alert('The rendered email will be: " + expectedTrashEmailAddress + "');");
        sleep(4000);
        //close alert and continue
        pageTrashMail.getDriver().switchTo().alert().accept();
        pageTrashMail.getDisposableEmailAddressUser().clear();
        pageTrashMail.getDisposableEmailAddressUser().sendKeys(randomTrashEmailName);

        pageTrashMail.getjSdriver().executeScript("document.getElementById('fe-domain').setAttribute('type', 'text');");
        pageTrashMail.getDisposableEmailAddressDomainHiddenValue().clear();
        pageTrashMail.getDisposableEmailAddressDomainHiddenValue().sendKeys(TrashMailPage.TRASH_MAIL_DOMAIN_03);

        pageTrashMail.getjSdriver().executeScript("document.getElementById('fe-fwd-nb').setAttribute('type', 'text');");
        pageTrashMail.getDropdownSelectedForwardHiddenValue().clear();
        pageTrashMail.getDropdownSelectedForwardHiddenValue().sendKeys("1");

        pageTrashMail.getjSdriver().executeScript("document.getElementById('fe-life-span').setAttribute('type', 'text');");
        pageTrashMail.getDropdownSelectedLifespanHiddenValue().clear();
        pageTrashMail.getDropdownSelectedLifespanHiddenValue().sendKeys("1");

        pageTrashMail.getrealUserEmail().sendKeys(REAL_USER_EMAIL);
        pageTrashMail.getNotifyUserUponDiposalCheckbox().click();
        pageTrashMail.getDisposableEmailAddressUser().click();
        //saftey sleep, just to verify all values have been rendered the way we want before submission
        sleep(4000);
        pageTrashMail.getTrashSubmitButton().submit();

        //quick sleep, allow browser to redirect
        sleep(4000);
        //show off our newly rendered email address
        //NOTE: as value is readonly, we have to use getAttribute("value"); rather then plain old getText, which in this case returns empty string
        String actualTrashEmailAddress = pageTrashMail.getPostSubmitReturnedEmail().getAttribute("value");
        pageTrashMail.getjSdriver().executeScript("alert('The rendered email is indeed: " + actualTrashEmailAddress + "');");
        sleep(4000);
        pageTrashMail.getDriver().switchTo().alert().accept();
        //assert the mail we created was the one we got from trashmail...
        assertEquals(expectedTrashEmailAddress,actualTrashEmailAddress);
        //leave page open for a few seconds for a final visual confirmation
        sleep(5000);
    }

}