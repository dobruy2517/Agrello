package InitDriverClass;

import helpers.HelpersClass;
import helpers.ListData;
import helpers.MainClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.log4testng.Logger;

import java.io.File;

//public class InitDriverClass extends MainClass {
//    private WebDriver driver;
//
//    public InitDriverClass(WebDriver driver, HelpersClass helper, ListData ld, Logger log) {
//        super(driver, helper, ld, log);
//    }
//
//    @BeforeClass
//    public void precondition(){
//        driver = new ChromeDriver();
//        ClassLoader classLoader = getClass().getClassLoader();
//        File file = new File(classLoader.getResource("chromedriver").getFile());
//        System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
//        driver.manage().window().maximize();
//    }
//}
