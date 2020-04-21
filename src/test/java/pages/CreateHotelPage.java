package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CreateHotelPage {

    WebDriver driver;

    public CreateHotelPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "Code")
    public WebElement code;

    @FindBy(id = "Name")
    public WebElement name;

    @FindBy(id = "Address")
    public WebElement address;

    @FindBy(id = "Phone")
    public WebElement phone;

    @FindBy(id = "Email")
    public WebElement email;

    @FindBy(id = "IDGroup")
    public WebElement idGroupSelectElement;

    @FindBy(id = "btnSubmit")
    public WebElement saveButton;

    @FindBy(className = "bootbox-body")
    public WebElement success_message;

    @FindBy(xpath = "//*[@data-bb-handler='ok']")
    public WebElement okButton;

    @FindBy(xpath = "//*[@href='/admin/HotelAdmin']")
    public WebElement hotelListLink;

    public void selectIDGroup(int index){
        Select idGroup = new Select(idGroupSelectElement);
        idGroup.selectByIndex(index);
    }
}
