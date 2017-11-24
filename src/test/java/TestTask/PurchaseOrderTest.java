package TestTask;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import precondition.logic.PreconditionClass;

import java.io.IOException;

public class PurchaseOrderTest extends PreconditionClass {
    @BeforeClass
    public void preCond() throws IOException {
        mainClass.getHelpersClass().goToUrl("login");
        mainClass.getRegisterPageClass().checkUserLogin("Individual");
    }

    @Test
    public void createPurchaseOrderTest() throws InterruptedException {
        log.info("test is started");
        mainClass.getHelpersClass().goToUrl("token-sale");
        Assert.assertTrue(mainClass.getPurchaseOrderClass().createPurchaseOrder());
        log.info("test is finished");
    }
}
