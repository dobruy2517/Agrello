package TestTask;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import precondition.logic.PreconditionClass;

import java.io.IOException;

public class
CreateDataForTests extends PreconditionClass {

    @Test(priority = 1, dataProvider = "userType")
    public void createTestEmailTest(String userType) throws IOException {
        Assert.assertTrue(mainClass.getRegisterPageClass().createNewEmailAdress(userType));
    }

    @DataProvider
    public Object[][] userType(){
        return new Object[][]{
                {"Individual"},
                {"Company"},
        };
    }

    @Test (priority = 2)
    public void createTestPasswordTest() throws IOException {
        Assert.assertTrue(mainClass.getRegisterPageClass().createPassword());
    }
}
