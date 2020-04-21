package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HotelListPage {

    WebDriver driver;

    public HotelListPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[@id='datatable_ajax']/tbody/tr/td[5]")
    public WebElement currentPhoneNumber;

    @FindBy(xpath = "//*[@id='datatable_ajax']/tbody/tr/td[8]/a")
    public WebElement detailsButton;

    @FindBy(xpath = "//*[@href='/admin/HotelAdmin/Create']")
    public WebElement addHotelButton;

    @FindBy(xpath = "//*[@id='datatable_ajax']/tbody/tr/td[5]")
    public WebElement phoneNumberAfterEdit;

    @FindBy(xpath = "//*[@id='datatable_ajax']/thead/tr[2]/td[2]/input")
    public WebElement codeBox;

    @FindBy(xpath = "//*[@class='margin-bottom-5']/button")
    public WebElement searchButton;

    @FindBy(xpath = "//*[@id='datatable_ajax']/tbody/tr/td[2]")
    public WebElement currentCode;

    @FindBy(className = "dataTables_empty")
    public WebElement no_data_message;
}
