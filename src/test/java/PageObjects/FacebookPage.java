package PageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class FacebookPage {

    @FindBy(how = How.ID,using = "u_0_j")
    public WebElement nameField;

    @FindBy(how = How.ID,using = "u_0_l")
    public WebElement surnameField;

    @FindBy(how = How.ID,using = "u_0_o")
    public WebElement emailField;

    @FindBy(how = How.ID,using = "u_0_r")
    public WebElement confirmEmailField;

    @FindBy(how = How.ID,using = "u_0_v")
    public WebElement passwordField;

    @FindBy(how = How.ID,using = "year")
    public WebElement birthdayField;

    @FindBy(how = How.ID,using = "u_0_w")
    public WebElement birthdayContext;

    @FindBy(how = How.XPATH,using = "//label[contains(text(),'Masculino')]")
    public WebElement maleRadioButton;

    @FindBy(how = How.ID,using = "u_0_11")
    public WebElement subscribeButton;

    @FindBy(how = How.ID,using = "code_in_cliff")
    public WebElement codeField;

    @FindBy(how = How.ID,using = "email")
    public WebElement loginEmail;

    @FindBy(how = How.ID,using = "pass")
    public WebElement loginPassword;

    @FindBy(how = How.ID,using = "loginbutton")
    public WebElement loginButton;

    @FindBy(how = How.XPATH,using = "//a[@title='Segurança e login']")
    public WebElement securityMenu;

    @FindBy(how = How.XPATH,using = "//span[contains(text(),'Alterar senha')]")
    public WebElement changePassword;

    @FindBy(how = How.ID,using = "password_old")
    public WebElement oldPasswordField;

    @FindBy(how = How.ID,using = "password_new")
    public WebElement newPasswordField;

    @FindBy(how = How.ID,using = "password_confirm")
    public WebElement confirmPasswordField;

    @FindBy(how = How.XPATH,using = "//label[@class='submit uiButton uiButtonConfirm']")
    public WebElement saveChangesButton;

}
