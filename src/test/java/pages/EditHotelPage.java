package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EditHotelPage {

    WebDriver driver;

    public EditHotelPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "Phone")
    public WebElement phone;

    @FindBy(xpath = "//*[@class='btn green']")
    public WebElement saveButton;

    @FindBy(className = "bootbox-body")
    public WebElement success_message;

    @FindBy(xpath = "//*[@data-bb-handler='ok']")
    public WebElement okButton;

    @FindBy(id = "btnDelete")
    public WebElement deleteButton;

    @FindBy(xpath = "//*[@data-bb-handler='confirm']")
    public WebElement deleteConfirmButton;
}
