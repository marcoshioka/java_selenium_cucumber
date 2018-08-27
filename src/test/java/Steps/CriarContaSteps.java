package Steps;


import Commom.Hooks;
import PageObjects.*;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

public class CriarContaSteps {


    private static WebDriver driver = new ChromeDriver();
    private WebDriverWait wait = new WebDriverWait(driver, 75);
    private static GeradorDePessoasPage GeradorDePessoasPage = PageFactory.initElements(driver, GeradorDePessoasPage.class);
    private static OwlyMailPage OwlyMailPage = PageFactory.initElements(driver, OwlyMailPage.class);
    private static FacebookPage FacebookPage = PageFactory.initElements(driver, FacebookPage.class);

    @After(value = "@criarconta", order = 0)
    public void afterScenario(Scenario scenario) throws IOException {

        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yy h-m-s");
        Date date = new Date();
        if (scenario.isFailed()) {
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File("C:\\Users\\cwg0660\\Downloads\\screenshot_" + dateFormat.format(date) + ".png"));

        }
    }


    @After(value = "@criarconta", order = 1)
    public void close() {

        Hooks closeBrowser = new Hooks();
        closeBrowser.closeBrowser(driver);

    }


    public static class getGeradorElements {

        private static String name = GeradorDePessoasPage.name.getText();
        private static String password = GeradorDePessoasPage.password.getText();

    }


    public static class getOwlyMailElements {

        private static String randomEmailAdress = OwlyMailPage.randomEmailAdress.getText();

    }


    @Dado("^que eu acesse um site para gegar dados ficticios de uma pessoa$")
    public void que_eu_acesse_um_site_para_gegar_dados_ficticios_de_uma_pessoa() throws Throwable {

        driver.manage().window().maximize();
        driver.get(Hooks.geradorDePessoasUrl);
        wait.until(ExpectedConditions.elementToBeClickable(GeradorDePessoasPage.gerarPessoaButton));
        GeradorDePessoasPage.stateNameSP.click();
        GeradorDePessoasPage.gerarPessoaButton.click();
        Thread.sleep(1000);
        wait.until(ExpectedConditions.elementToBeClickable(GeradorDePessoasPage.name));
        GeradorDePessoasPage.name.click();
        System.out.println("Nome completo: " + getGeradorElements.name);
        wait.until(ExpectedConditions.elementToBeClickable(GeradorDePessoasPage.password));
        GeradorDePessoasPage.password.click();
        System.out.println("Senha: " + getGeradorElements.password);
        Thread.sleep(2000);

        driver.get(Hooks.getOwlyMailUrl);
        wait.until(ExpectedConditions.urlToBe(Hooks.getOwlyMailUrl));
        String fullName= getGeradorElements.name;
        String[] mailName=fullName.split("// ");
        OwlyMailPage.nameField.sendKeys(mailName);
        OwlyMailPage.hostSelection.click();
        OwlyMailPage.randomButton.click();
        wait.until(ExpectedConditions.visibilityOf(OwlyMailPage.randomEmailAdress));
        OwlyMailPage.randomEmailAdress.click();
        System.out.println("E-mail: " + getOwlyMailElements.randomEmailAdress);

    }


    @Quando("^realizo cadastro no Facebook com esses dados$")
    public void realizo_cadastro_no_Facebook_com_esses_dados() throws Throwable {


        String parentWindow= driver.getWindowHandle();
        ((JavascriptExecutor) driver).executeScript("window.open('https://www.facebook.com','_blank');");
        Set<String> tab_handles = driver.getWindowHandles();
        int number_of_tabs = tab_handles.size();
        int new_tab_index = number_of_tabs-1;
        driver.switchTo().window(tab_handles.toArray()[new_tab_index].toString());
        Thread.sleep(2000);

        String fullName= getGeradorElements.name;
        String surName=fullName.split(" ")[fullName.split(" ").length-1];
        String firstName = fullName.substring(0, fullName.length() - surName.length());
        System.out.println("\n" + "Nome: " + firstName );
        System.out.println("\n" + "Sobrenome: " + surName );

        wait.until(ExpectedConditions.elementToBeClickable(FacebookPage.nameField));
        FacebookPage.nameField.sendKeys(firstName);
        FacebookPage.surnameField.sendKeys(surName);
        FacebookPage.emailField.sendKeys(getOwlyMailElements.randomEmailAdress);
        wait.until(ExpectedConditions.elementToBeClickable(FacebookPage.confirmEmailField));
        FacebookPage.confirmEmailField.sendKeys(getOwlyMailElements.randomEmailAdress);
        FacebookPage.passwordField.sendKeys(getGeradorElements.password);
        FacebookPage.birthdayField.click();
        FacebookPage.birthdayField.sendKeys("1970");
        FacebookPage.birthdayField.sendKeys(Keys.ENTER);
        FacebookPage.birthdayContext.click();
        wait.until(ExpectedConditions.elementToBeClickable(FacebookPage.maleRadioButton));
        FacebookPage.maleRadioButton.click();

        wait.until(ExpectedConditions.elementToBeClickable(FacebookPage.subscribeButton));
        FacebookPage.subscribeButton.click();
        Thread.sleep(5000);
        driver.switchTo().window(parentWindow);

    }


    @Então("^eu devo conseguir ver o e-mail para validar os dados da conta$")
    public void eu_devo_conseguir_ver_o_e_mail_para_validar_os_dados_da_conta() throws Throwable {
        driver.navigate().refresh();
        wait.until(ExpectedConditions.elementToBeClickable(OwlyMailPage.faceMessage));
        OwlyMailPage.faceMessage.click();
        String messageTitle = OwlyMailPage.messageTitle.getText();
        String faceCode = messageTitle.replaceAll("\\D+", "");
        System.out.println("\n" + "Código: " + faceCode);

        Set<String> tab_handles = driver.getWindowHandles();
        int number_of_tabs = tab_handles.size();
        int new_tab_index = number_of_tabs-1;
        driver.switchTo().window(tab_handles.toArray()[new_tab_index].toString());

        FacebookPage.codeField.sendKeys(faceCode);
        FacebookPage.codeField.sendKeys(Keys.ENTER);
        Thread.sleep(2000);

    }


    @Então("^acessar a conta$")
    public void acessar_a_conta() throws Throwable {

        driver.manage().deleteAllCookies();
        driver.navigate().refresh();
        driver.get(Hooks.facebookHomeUrl);
        wait.until(ExpectedConditions.elementToBeClickable(FacebookPage.loginEmail));
        FacebookPage.loginEmail.sendKeys(getOwlyMailElements.randomEmailAdress);
        FacebookPage.loginPassword.sendKeys(getGeradorElements.password);
        wait.until(ExpectedConditions.elementToBeClickable(FacebookPage.loginButton));
        FacebookPage.loginButton.click();
        Thread.sleep(5000);

    }


    @Então("^entrar no menu de configurações/segurança$")
    public void entrar_no_menu_de_configurações_segurança() throws Throwable {

        Actions action = new Actions(driver);
        action.sendKeys(Keys.ESCAPE).build().perform();
        driver.navigate().to(Hooks.facebookSettingsUrl);
        wait.until(ExpectedConditions.urlToBe(Hooks.facebookSettingsUrl));

    }


    @Então("^alterar a minha senha$")
    public void alterar_a_minha_senha() throws Throwable {

        Actions action = new Actions(driver);
        action.sendKeys(Keys.ESCAPE).build().perform();
        action.sendKeys(Keys.ESCAPE).build().perform();
        wait.until(ExpectedConditions.elementToBeClickable(FacebookPage.securityMenu));
        FacebookPage.securityMenu.click();
        wait.until(ExpectedConditions.elementToBeClickable(FacebookPage.changePassword));
        FacebookPage.changePassword.click();
        wait.until(ExpectedConditions.elementToBeClickable(FacebookPage.oldPasswordField));
        FacebookPage.oldPasswordField.sendKeys(getGeradorElements.password);
        wait.until(ExpectedConditions.elementToBeClickable(FacebookPage.newPasswordField));
        FacebookPage.newPasswordField.sendKeys("#Teste123");
        wait.until(ExpectedConditions.elementToBeClickable(FacebookPage.confirmPasswordField));
        FacebookPage.confirmPasswordField.sendKeys("#Teste123");
        wait.until(ExpectedConditions.elementToBeClickable(FacebookPage.saveChangesButton));
        FacebookPage.saveChangesButton.click();
        action.sendKeys(Keys.ESCAPE).build().perform();
        Thread.sleep(5000);

    }


    @Então("^encerrar sessão$")
    public void encerrar_sessão() throws Throwable {

        driver.navigate().to(Hooks.exitSettingsUrl);
        Thread.sleep(3000);
        driver.manage().deleteAllCookies();
        driver.navigate().refresh();
        driver.get(Hooks.facebookHomeUrl);

    }


    @Então("^logar com a nova senha$")
    public void logar_com_a_nova_senha() throws Throwable {

        wait.until(ExpectedConditions.elementToBeClickable(FacebookPage.loginEmail));
        FacebookPage.loginEmail.sendKeys(getOwlyMailElements.randomEmailAdress);
        FacebookPage.loginPassword.sendKeys("#Teste123");
        wait.until(ExpectedConditions.elementToBeClickable(FacebookPage.loginButton));
        FacebookPage.loginButton.click();
        //Thread.sleep(5000);
        wait.until(ExpectedConditions.urlToBe(Hooks.facebookWelcomeUrl));

    }
}
