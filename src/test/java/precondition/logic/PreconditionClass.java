package precondition.logic;

import helpers.MainClass;
import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

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
