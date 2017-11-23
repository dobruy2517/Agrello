package helpers;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

public class BaseClass {
    /**
     *
     */
    protected HelpersClass helper;

    /**
     *
     */
    protected WebDriver driver;

    /**
     *
     */
    protected ListData lp;

    /**
     *
     */
    protected Logger log;

    /**
     * @param driver
     * @param helper
     */
    public BaseClass(ChromeDriver driver, HelpersClass helper, ListData lp, Logger log) {
        this.driver = driver;
        this.helper = helper;
        this.lp = lp;
        this.log = log;
        PageFactory.initElements(driver, this);
    }
}
