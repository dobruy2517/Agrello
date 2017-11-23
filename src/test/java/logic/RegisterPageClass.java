package logic;

import helpers.BaseClass;
import helpers.HelpersClass;
import helpers.ListData;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class RegisterPageClass extends BaseClass {

    public HelpersClass helper = new HelpersClass(driver);
    public Logger log = Logger.getLogger(getClass());


    public RegisterPageClass(ChromeDriver driver, HelpersClass helper, ListData ld, Logger log) {
        super(driver, helper, ld, log);
    }

    public By individualCheckbox = By.xpath("//form/div[1]/div[1]/div");
    public By individualFirstName = By.name("first_name");
    public By individualLastName = By.name("name");
    public By individualEmail = By.name("email");
    public By individualPassword = By.name("password");
    public By individualPasswordConfirmation = By.name("password_confirmation");
    public By individualTwoStepAuthCheckbox = By.name("two_step_auth");
    public By individualAcceptTockenCheckbox = By.xpath("//div[input[@name='terms']]");
    public By submit = By.id("submit-btn");

    //Company locators
    public By companyUserType = By.xpath("//div[input[@value='2']]");
    public By companyName = By.name("company_name");
    public By companyRegistrationCode = By.name("registration_code");
    public By companyAddress = By.name("address");
    public By companyVatNumber = By.name("vat_number");

    //Login form locators
    public By loginEmail = By.name("email");
    public By loginPassword = By.name("password");
    public By loginSubmit = By.cssSelector("[type='submit'");


    public boolean checkElementsOnRegisterForm(String userType) {
        boolean flag = false;
        if (userType.equals("Individual")) {
            if (helper.isElementPresent(individualFirstName) &&
                    helper.isElementPresent(individualLastName) &&
                    helper.isElementPresent(individualEmail) &&
                    helper.isElementPresent(individualPassword) &&
                    helper.isElementPresent(individualPasswordConfirmation) &&
                    helper.isElementPresent(individualAcceptTockenCheckbox)) {
                flag = true;
                log.info("All elements present");
            }
        } else if (userType.equals("Company")) {
            helper.presenceOfElementLocatedClick(companyUserType);
            if (helper.isElementPresent(companyName) &&
                    helper.isElementPresent(companyAddress) &&
                    helper.isElementPresent(companyVatNumber) &&
                    helper.isElementPresent(companyRegistrationCode) &&
                    helper.isElementPresent(individualFirstName) &&
                    helper.isElementPresent(individualLastName) &&
                    helper.isElementPresent(individualEmail) &&
                    helper.isElementPresent(individualPassword) &&
                    helper.isElementPresent(individualPasswordConfirmation) &&
                    helper.isElementPresent(individualAcceptTockenCheckbox)) {
                flag = true;
                log.info("All elements present");
            }
        }
        return flag;
    }

    public boolean createPassword() throws IOException {
        String pass = "test_" + helper.randomWord(10);
        helper.writeDataClient(helper.getLd().getClientPasswordName(), pass);
        return true;
    }

    public boolean checkRegiserOfUser(String userType) throws IOException, InterruptedException {
        boolean flag = false;
        if (userType.equals("Individual")) {
            log.info("Create individual user");
            helper.presenceOfElementLocated(individualFirstName);
            helper.presenceOfElementLocatedAndSendKey(individualFirstName, userFirstName);
            helper.presenceOfElementLocatedAndSendKey(individualLastName, userLastName);
            helper.presenceOfElementLocatedAndSendKey(individualEmail, helper.getDataClient(helper.getLd().getClientEmailName()));
            helper.presenceOfElementLocatedAndSendKey(individualPassword, helper.getDataClient(helper.getLd().getClientPasswordName()));
            helper.presenceOfElementLocatedAndSendKey(individualPasswordConfirmation, helper.getDataClient(helper.getLd().getClientPasswordName()));
            helper.scrollToelement(helper.df(individualAcceptTockenCheckbox));
            helper.click(individualAcceptTockenCheckbox);
            helper.click(submit);
            if (driver.getCurrentUrl().equals("https://uat-token.agrello.org/login")) {
                flag = true;
            }
            confirmAccount(userType);
        }
        if (userType.equals("Company")) {
            log.info("Create company user");
            helper.presenceOfElementLocatedClick(companyUserType);
            helper.presenceOfElementLocated(companyName);
            helper.presenceOfElementLocatedAndSendKey(companyName, "test_" + helper.randomWord(3));
            helper.presenceOfElementLocatedAndSendKey(companyRegistrationCode, helper.randomWord(5));
            helper.presenceOfElementLocatedAndSendKey(companyAddress, helper.randomWord(5) + " st.");
            helper.presenceOfElementLocatedAndSendKey(companyVatNumber, helper.randomWord(5));
            helper.presenceOfElementLocatedAndSendKey(individualFirstName, userFirstName);
            helper.presenceOfElementLocatedAndSendKey(individualLastName, userLastName);
            helper.presenceOfElementLocatedAndSendKey(individualEmail, helper.getDataClient(helper.getLd().getCompanyEmailName()));
            helper.presenceOfElementLocatedAndSendKey(individualPassword, helper.getDataClient(helper.getLd().getClientPasswordName()));
            helper.presenceOfElementLocatedAndSendKey(individualPasswordConfirmation, helper.getDataClient(helper.getLd().getClientPasswordName()));
            helper.scrollToelement(helper.df(individualAcceptTockenCheckbox));
            helper.click(individualAcceptTockenCheckbox);
            helper.click(submit);
            if (driver.getCurrentUrl().equals("https://uat-token.agrello.org/login")) {
                flag = true;
            }
            confirmAccount(userType);
        }
        log.info("User with type " + userType + " created");
        return flag;
    }

    /**
     * Create new email address on https://temp-mail.ru/
     */
    Random rand = new Random();
    ArrayList<String> firstNamesList = new ArrayList<String>(Arrays.asList("Noah", "Liam", "Mason", "Jacob", "William", "Ethan",
            "Michael", "Alexander", "James", "Daniel", "Elijah", "Benjamin", "Logan", "Aiden", "Jayden", "Matthew", "Jackson",
            "David", "Lucas", "Joseph"));
    ArrayList<String> lastNamesList = new ArrayList<String>(Arrays.asList("Adderiy", "Addington", "Adrian", "Albertson", "Aldridge",
            "Allford", "Alsopp", "Anderson", "Andrews", "Archibald", "Arnold", "Arthurs", "Atcheson", "Attwood", "Audley", "Austin",
            "Ayrton", "Babcock", "Backer", "Baldwin", "Bargeman", "Barnes", "Barrington", "Bawerman", "Becker"));

    By createNewAddressLink = By.id("click-to-change");
    By mailLoginInputfield = By.name("mail");
    By createNewMailAddress = By.id("postbut");
    By email = By.xpath("//*[@id='mail' and @readonly]");
    By refreshEmail = By.id("click-to-refresh");
    By mailSubject = By.cssSelector("a.link");
    By confirmYourAccountUrl = By.xpath("//a[contains(@href,'uat-token.agrello.org')]");
    String userFirstName = firstNamesList.get(rand.nextInt((firstNamesList.size() - 0) + 1));
    String userLastName = lastNamesList.get(rand.nextInt((lastNamesList.size() - 0) + 1));
    String mailValue = userFirstName.toLowerCase() + "." + userLastName.toLowerCase();


    public boolean createNewEmailAdress(String userType) throws IOException {
        boolean flag = false;
        String eMail;
        driver.get("https://temp-mail.ru/option/change");
        if (userType.equals("Individual")) {
            helper.presenceOfElementLocatedAndSendKey(mailLoginInputfield, mailValue);
            helper.presenceOfElementLocatedClick(createNewMailAddress);
            helper.presenceOfElementLocated(By.cssSelector(".alert-success"));
            helper.textToBePresentInElementValue(email, mailLoginInputfield, "value");
            eMail = helper.df(email).getAttribute("value");
            if (eMail.contains(mailValue)) {
                flag = true;
            }
            log.info("createNewEmailAdress - " + flag);
            helper.writeDataClient(helper.getLd().getClientEmailName(), eMail);
        } else if (userType.equals("Company")) {
            helper.presenceOfElementLocatedAndSendKey(mailLoginInputfield, mailValue + "_com");
            helper.presenceOfElementLocatedClick(createNewMailAddress);
            helper.presenceOfElementLocated(By.cssSelector(".alert-success"));
            helper.textToBePresentInElementValue(email, mailLoginInputfield, "value");
            eMail = helper.df(email).getAttribute("value");
            if (eMail.contains(mailValue)) {
                flag = true;
            }
            log.info("createNewEmailAdress - " + flag);
            helper.writeDataClient(helper.getLd().getCompanyEmailName(), eMail);
        }
        return flag;
    }

    public boolean confirmAccount(String userType) throws IOException, InterruptedException {
        boolean flag = false;
        String str = "";
        driver.get("https://temp-mail.ru/");
        helper.presenceOfElementLocatedClick(createNewAddressLink);
        if (userType.equals("Individual")) {
            str = helper.getDataClient(helper.getLd().getClientEmailName());
            str = str.split("@")[0];
        } else if (userType.equals("Company")) {
            str = helper.getDataClient(helper.getLd().getCompanyEmailName());
            str = str.split("@")[0];
        }
        helper.presenceOfElementLocatedAndSendKey(mailLoginInputfield, str);
        helper.presenceOfElementLocatedClick(createNewMailAddress);
        helper.presenceOfElementLocated(By.cssSelector(".alert-success"));
        driver.get("https://temp-mail.ru");
        do {
            helper.presenceOfElementLocatedClick(refreshEmail);
            helper.presenceOfElementLocated(mailSubject);
        } while (!(helper.df(mailSubject)).isDisplayed());
        helper.presenceOfElementLocatedClick(mailSubject);
        helper.presenceOfElementLocated(confirmYourAccountUrl);
        String acceptUrl = helper.getAttribute(confirmYourAccountUrl, "href");
        driver.get(acceptUrl);
        if (driver.getCurrentUrl().equals("https://uat-token.agrello.org/login")) {
            flag = true;
        }
        log.info("confirmAccount - " + flag);
        return flag;
    }

    public boolean checkUserLogin(String userType) throws IOException {
        boolean flag = false;
        if (userType.equals("Individual")) {
            helper.presenceOfElementLocatedAndSendKey(loginEmail, helper.getDataClient(helper.getLd().getClientEmailName()));
            helper.presenceOfElementLocatedAndSendKey(loginPassword, helper.getDataClient(helper.getLd().getClientPasswordName()));
            helper.click(loginSubmit);
            if (helper.isElementPresent(By.id("invest")) &&
                    driver.getCurrentUrl().contains("/home")) {
                flag = true;
            }
        } else if (userType.equals("Company")) {
            helper.presenceOfElementLocatedAndSendKey(loginEmail, helper.getDataClient(helper.getLd().getCompanyEmailName()));
            helper.presenceOfElementLocatedAndSendKey(loginPassword, helper.getDataClient(helper.getLd().getClientPasswordName()));
            helper.click(loginSubmit);
            if (helper.isElementPresent(By.id("invest")) &&
                    driver.getCurrentUrl().contains("/home")) {
                flag = true;
            }
        }
        log.info("checkUserLogin - " + flag);
        return flag;
    }
}
