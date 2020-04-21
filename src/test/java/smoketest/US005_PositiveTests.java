package smoketest;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;
import utilities.TestBase;

public class US005_PositiveTests extends TestBase {

    @Test
    public void TC01_hotelCreationTest() throws InterruptedException {
        // login
        LoginPage loginPage = new LoginPage(driver);
        driver.get("http://www.fhctrip.com/admin/useradmin");
        wait.until(ExpectedConditions.visibilityOf(loginPage.username));
        loginPage.username.sendKeys("manager2");
        loginPage.password.sendKeys("Man1ager2!");
        loginPage.loginButton.click();

        // click "Hotel Management" and then "Hotel List"
        UsersPage usersPage = new UsersPage(driver);
        wait.until(ExpectedConditions.elementToBeClickable(usersPage.hotelManagementLink));
        usersPage.hotelManagementLink.click();
        Thread.sleep(1000);
        usersPage.hotelListLink.click();

        // click "ADD HOTEL" button
        HotelListPage hotelListPage = new HotelListPage(driver);
        wait.until(ExpectedConditions.elementToBeClickable(hotelListPage.addHotelButton));
//        hotelListPage.addHotelButton.click();
        actions.doubleClick(hotelListPage.addHotelButton).perform();
        // create an hotel
        CreateHotelPage createHotelPage = new CreateHotelPage(driver);

        Thread.sleep(1000);
        wait.until(ExpectedConditions.elementToBeClickable(createHotelPage.code));
        createHotelPage.code.sendKeys("1515");

        String name = faker.name().firstName();
        createHotelPage.name.sendKeys(name);

        String address = faker.address().fullAddress();
        createHotelPage.address.sendKeys(address);

        createHotelPage.phone.sendKeys("123456789");

        createHotelPage.email.sendKeys("test@gmail.com");

        createHotelPage.selectIDGroup(1);

        createHotelPage.saveButton.click();

        wait.until(ExpectedConditions.visibilityOf(createHotelPage.success_message));
        Assert.assertEquals(createHotelPage.success_message.getText(), "Hotel was inserted successfully");
        createHotelPage.okButton.click();

        createHotelPage.hotelListLink.click();
    }

    @Test
    public void TC02_editHotelDetailsTest() throws InterruptedException {
        // login
        LoginPage loginPage = new LoginPage(driver);
        driver.get("http://www.fhctrip.com/admin/useradmin");
        wait.until(ExpectedConditions.visibilityOf(loginPage.username));
        loginPage.username.sendKeys("manager2");
        loginPage.password.sendKeys("Man1ager2!");
        loginPage.loginButton.click();

        // click "Hotel Management" and then "Hotel List"
        UsersPage usersPage = new UsersPage(driver);
        usersPage.hotelManagementLink.click();
        wait.until(ExpectedConditions.elementToBeClickable(usersPage.hotelListLink));
        Thread.sleep(1000);
        usersPage.hotelListLink.click();

        // note the current phone number
        HotelListPage hotelListPage = new HotelListPage(driver);
        String currentPhoneNumber = hotelListPage.currentPhoneNumber.getText();
        hotelListPage.detailsButton.click();

        driver.switchTo().window(driver.getWindowHandles().toArray()[1].toString());
        EditHotelPage editHotelPage = new EditHotelPage(driver);
        editHotelPage.phone.clear();
        String newPhoneNumber = currentPhoneNumber.substring(0,8) + "45";
        editHotelPage.phone.sendKeys(newPhoneNumber);
        editHotelPage.saveButton.click();
        wait.until(ExpectedConditions.visibilityOf(editHotelPage.success_message));
        Assert.assertEquals(editHotelPage.success_message.getText(), "Hotel was updated successfully");
        editHotelPage.okButton.click();

        driver.switchTo().window(driver.getWindowHandles().toArray()[0].toString());

        driver.navigate().refresh();
        wait.until(ExpectedConditions.visibilityOf(hotelListPage.phoneNumberAfterEdit));
        Assert.assertEquals(hotelListPage.phoneNumberAfterEdit.getText(), newPhoneNumber);
    }

    @Test(dependsOnMethods = {"TC01_hotelCreationTest"})
    public void TC03_deleteHotelTest() throws InterruptedException {
        // login
        LoginPage loginPage = new LoginPage(driver);
        driver.get("http://www.fhctrip.com/admin/useradmin");
        wait.until(ExpectedConditions.visibilityOf(loginPage.username));
        loginPage.username.sendKeys("manager2");
        loginPage.password.sendKeys("Man1ager2!");
        loginPage.loginButton.click();

        // click "Hotel Management" and then "Hotel List"
        UsersPage usersPage = new UsersPage(driver);
        wait.until(ExpectedConditions.elementToBeClickable(usersPage.hotelManagementLink));
        usersPage.hotelManagementLink.click();
        Thread.sleep(1000);
        usersPage.hotelListLink.click();


        HotelListPage hotelListPage = new HotelListPage(driver);
//        wait.until(ExpectedConditions.elementToBeClickable(hotelListPage.codeBox));
        Thread.sleep(3000);
        hotelListPage.codeBox.sendKeys("1515");
        hotelListPage.searchButton.click();
//        wait.until(ExpectedConditions.visibilityOf(hotelListPage.currentCode));
        Thread.sleep(2000);
        Assert.assertEquals(hotelListPage.currentCode.getText(), "1515");

        hotelListPage.detailsButton.click();
        driver.switchTo().window(driver.getWindowHandles().toArray()[1].toString());

        EditHotelPage editHotelPage = new EditHotelPage(driver);
        wait.until(ExpectedConditions.visibilityOf(editHotelPage.deleteButton));
        editHotelPage.deleteButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(editHotelPage.deleteConfirmButton));
        editHotelPage.deleteConfirmButton.click();

//        wait.until(ExpectedConditions.elementToBeClickable(hotelListPage.codeBox));
        Thread.sleep(3000);
        hotelListPage.codeBox.sendKeys("1515");
        hotelListPage.searchButton.click();

        wait.until(ExpectedConditions.visibilityOf(hotelListPage.no_data_message));
        Assert.assertEquals(hotelListPage.no_data_message.getText(), "No data available in table");

    }
}
