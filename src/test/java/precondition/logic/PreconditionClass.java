package precondition.logic;

import helpers.MainClass;
import org.testng.ITestContext;
import org.testng.annotations.*;
import org.apache.log4j.Logger;

import java.io.IOException;

public class PreconditionClass {

    protected MainClass mainClass = new MainClass();
    public Logger log = Logger.getLogger(getClass());


    @BeforeClass
    public void setUp(ITestContext context) throws IOException {
        runInit();
    }


    @AfterClass(alwaysRun = true)
    public void driverDie2() {
        try {
            mainClass.stop();
        } catch (Exception e) {
            log.error("Catch " + e);
        }
    }

    public void setContext(ITestContext context) {
        context.setAttribute("app", mainClass);
    }

    public void runInit() throws IOException {
        try {
            if (mainClass == null || mainClass.hasLive() || mainClass.hasQuit()) {
                mainClass.init();
            }
        } catch (Exception e) {
            log.error("Catch " + e);
        }
    }
}
