package PageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class OwlyMailPage {
    @FindBy(how = How.ID,using = "current-tmail-id")
    public WebElement randomEmailAdress;

    @FindBy(how = How.XPATH,using = "//i[@class='fa fa-paper-plane']")
    public WebElement randomButton;

    @FindBy(how = How.XPATH,using = "//input[@placeholder='Set EMail ID']")
    public WebElement nameField;

    @FindBy(how = How.XPATH,using = "//option[@value='@owlymail.com']")
    public WebElement hostSelection;

    @FindBy(how = How.ID,using = "tmail-data")
    public WebElement faceMessage;

    @FindBy(how = How.CSS,using = "div.tmail-email-title")
    public WebElement messageTitle;

}
