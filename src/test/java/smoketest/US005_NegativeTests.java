package smoketest;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import utilities.TestBase;

public class US005_NegativeTests extends TestBase {

    @Test
    public void TC04_customerServiceLoginTest(){
        driver.get("http://www.fhctrip.com/admin/useradmin");
        LoginPage loginPage = new LoginPage(driver);
        wait.until(ExpectedConditions.visibilityOf(loginPage.username));
        loginPage.username.sendKeys("customerservice2");
        loginPage.password.sendKeys("Cu2stomer2!!");
        loginPage.loginButton.click();

        Assert.assertEquals(loginPage.error_message.getText(), "Please check all the requirements!");
    }
}
