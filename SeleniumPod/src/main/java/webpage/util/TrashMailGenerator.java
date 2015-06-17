package webpage.util;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import webpage.model.TrashMail.TrashMailPage;

import java.math.BigInteger;
import java.security.SecureRandom;

import static java.lang.Thread.sleep;

/**
 * Created by justin on 17.06.15.
 */
public class TrashMailGenerator {

    private TrashMailPage pageTrashMail;
    private SecureRandom random = new SecureRandom();

    public String nextRandomString() {
        return new BigInteger(130, random).toString(32);
    }

    public String generateTrashEmail(String realEmail, String trashMailDomain) throws InterruptedException {
        pageTrashMail = PageFactory.initElements(new FirefoxDriver(), TrashMailPage.class);
        pageTrashMail.open("https://trashmail.com/");
        String randomTrashEmailName = nextRandomString();
        pageTrashMail.getDisposableEmailAddressUser().clear();
        pageTrashMail.getDisposableEmailAddressUser().sendKeys(randomTrashEmailName);
        pageTrashMail.getjSdriver().executeScript("document.getElementById('fe-domain').setAttribute('type', 'text');");
        pageTrashMail.getDisposableEmailAddressDomainHiddenValue().clear();
        pageTrashMail.getDisposableEmailAddressDomainHiddenValue().sendKeys(trashMailDomain);
        pageTrashMail.getjSdriver().executeScript("document.getElementById('fe-fwd-nb').setAttribute('type', 'text');");
        pageTrashMail.getDropdownSelectedForwardHiddenValue().clear();
        pageTrashMail.getDropdownSelectedForwardHiddenValue().sendKeys("1");
        pageTrashMail.getjSdriver().executeScript("document.getElementById('fe-life-span').setAttribute('type', 'text');");
        pageTrashMail.getDropdownSelectedLifespanHiddenValue().clear();
        pageTrashMail.getDropdownSelectedLifespanHiddenValue().sendKeys("1");
        pageTrashMail.getrealUserEmail().sendKeys(realEmail);
        pageTrashMail.getNotifyUserUponDiposalCheckbox().click();
        pageTrashMail.getDisposableEmailAddressUser().click();
        pageTrashMail.getTrashSubmitButton().submit();
        String returnedEmail = pageTrashMail.getPostSubmitReturnedEmail().getAttribute("value");
        pageTrashMail.close();

        return returnedEmail;
    }


}