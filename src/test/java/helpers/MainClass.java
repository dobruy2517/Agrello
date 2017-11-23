package helpers;

import logic.RegisterPageClass;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.IOException;

public class MainClass {
    private WebDriver driver;


    private RegisterPageClass registerPageClass;
    private HelpersClass helpersClass;
    private ListData ld;
    private Logger log;

    public MainClass() {
    }

    public HelpersClass getHelpersClass() {
        return helpersClass;
    }

    public RegisterPageClass getRegisterPageClass() {
        return registerPageClass;
    }

    public void init() {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("chromedriver").getFile());
        System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        registerPageClass = new RegisterPageClass((ChromeDriver) driver, helpersClass, ld, log);
        helpersClass = new HelpersClass(driver);
        log = Logger.getLogger(getClass());
        ld = new ListData();
    }

    public boolean hasQuit() {
        return ((RemoteWebDriver) driver).getSessionId() == null;
    }

    public boolean hasLive() {
        return driver == null;
    }

    public void close() {
        if (!(hasLive() || hasQuit())) {
            driver.quit();
        }
        driver = null;
    }

    public void stop() {
        try {
            if (!(hasLive() || hasQuit())) {
                driver.quit();
            }
            driver = null;
        } catch (Exception e) {
            log.error("Catch stop - " + e);
        }
    }

    public void checkDriverLiveAndStart() throws IOException {
        if (hasLive() || hasQuit()) {
            init();
        }
    }
}
