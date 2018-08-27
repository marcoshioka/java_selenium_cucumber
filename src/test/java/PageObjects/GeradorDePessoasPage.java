package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class GeradorDePessoasPage {


    @FindBy(how = How.ID,using = "bt_gerar_pessoa")
    public WebElement gerarPessoaButton;

    @FindBy(how = How.XPATH,using = "//option[@value='SP']")
    public WebElement stateNameSP;

    @FindBy(how = How.ID,using = "nome")
    public WebElement name;

    @FindBy(how = How.ID,using = "senha")
    public WebElement password;
}
