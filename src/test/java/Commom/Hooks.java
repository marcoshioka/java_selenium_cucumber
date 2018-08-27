package Commom;

import cucumber.api.Scenario;
import cucumber.api.java.Before;
import org.openqa.selenium.WebDriver;


public class Hooks {

    private WebDriver driver;
    private Scenario scenario;


    public static String geradorDePessoasUrl = "https://www.4devs.com.br/gerador_de_pessoas";
    public static String getOwlyMailUrl = "https://owlymail.com/en/";
    public static String facebookHomeUrl = "https://www.facebook.com";
    public static String facebookSettingsUrl = "https://www.facebook.com/settings";
    public static String exitSettingsUrl = "https://www.facebook.com/settings#";
    public static String facebookWelcomeUrl = "https://www.facebook.com/?sk=welcome";


//Método para dar iniciar o driver
    @Before("@criarconta")
    public void beforeScenario () {

        System.setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\Selenium\\drivers\\chromedriver_win32\\chromedriver.exe");

    }


    //Método responsável por fechar o browser, no fim da execução do teste
    public void closeBrowser(WebDriver driver) {
        if (driver != null) {
            driver.quit();
        }
    }
}
