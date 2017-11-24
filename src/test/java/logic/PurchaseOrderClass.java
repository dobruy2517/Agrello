package logic;

import helpers.BaseClass;
import helpers.HelpersClass;
import helpers.ListData;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import java.text.DecimalFormat;


public class PurchaseOrderClass extends BaseClass {
    HelpersClass helper = new HelpersClass(driver);
    Logger log = Logger.getLogger(getClass());

    public PurchaseOrderClass(ChromeDriver driver, HelpersClass helper, ListData ld, Logger log) {
        super(driver, helper, ld, log);
    }

    public By purchaseAmount = By.id("in");

    public boolean createPurchaseOrder() throws InterruptedException {
        boolean flag = false;
        String amount = String.valueOf(helper.randomInRange(1.5, 100));
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        amount = twoDForm.format(Double.valueOf(amount));
        amount = amount.replace(",", ".");
        helper.presenceOfElementLocatedAndSendKey(purchaseAmount, amount);
        helper.presenceOfElementLocatedClick(By.id("invest"));
        helper.presenceOfElementLocated(By.cssSelector("input#codeBtc"));
        helper.scrollToelement(helper.df(By.cssSelector("input#codeBtc")));
        do {
            driver.navigate().refresh();
            Thread.sleep(2000);
            helper.scrollToelement(helper.df(By.cssSelector("input#codeBtc")));
        } while (!helper.isElementPresent(By.xpath("//td[contains(text(),'" + amount + "')]")));
        String expectedValue = helper.df(By.xpath("//td[contains(text(),'" + amount + "')]")).getText();
        if (amount.equals(expectedValue)) {
            flag = true;
        }
        log.info("createPurchaseOrder - " + flag);
        return flag;
    }
}
