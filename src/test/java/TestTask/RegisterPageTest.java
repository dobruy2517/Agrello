package TestTask;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import precondition.logic.PreconditionClass;

import java.io.IOException;

public class RegisterPageTest extends PreconditionClass{


    @Test(priority = 1, dataProvider = "elementsOnRegisterPage")
    public void checkElementsOnRegisterFormTest(String userType){
        log.info("test is started");
        mainClass.getHelpersClass().goToUrl("register");
        Assert.assertTrue(mainClass.getRegisterPageClass().checkElementsOnRegisterForm(userType));
        log.info("test is finished");
    }

    @DataProvider
    public Object[][] elementsOnRegisterPage(){
        return new Object[][]{
                {"Individual"},
                {"Company"},
        };
    }

    @Test(priority = 2, dataProvider = "userType")
    public void checkRegisterUser(String userType) throws IOException, InterruptedException {
        log.info("test is started");
        mainClass.getHelpersClass().goToUrl("register");
        mainClass.getRegisterPageClass().checkRegiserOfUser(userType);
        log.info("test is finished");
    }

    @DataProvider
    public Object[][] userType(){
        return new Object[][]{
                {"Individual"},
                {"Company"},
        };
    }

    @Test(priority = 3, dataProvider = "dataForLogin")
    public void checkLoginTest(String userType) throws IOException {
        log.info("test is started");
        mainClass.getHelpersClass().goToUrl("login");
        Assert.assertTrue(mainClass.getRegisterPageClass().checkUserLogin(userType));
        log.info("test is finished");
    }

    @DataProvider
    public Object[][] dataForLogin(){
        return new Object[][]{
                {"Individual"},
                {"Company"},
        };
    }
}
