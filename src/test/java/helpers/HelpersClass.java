package helpers;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.Random;

public class HelpersClass {

    protected WebDriver driver;
    private JavascriptExecutor js;
    String folderPath = "/home/ihor/IdeaProjects/TestTask/src/main/resource/";
    WebDriverWait wait;
    private Logger log = Logger.getLogger(getClass());
    public static String testUrl = "https://uat-token.agrello.org/";

    protected ListData ld;

    public ListData getLd() {
        return ld;
    }

    public HelpersClass(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 50);
        ld = new ListData();
        js = (JavascriptExecutor) driver;
    }

    public void goToUrl(String url) {
        driver.get(testUrl + url);
    }

    public boolean isElementPresent(By element) {
        boolean flag = false;
        scrollToelement(df(element));
        if (df(element).isDisplayed()) {
            flag = true;
            log.info("WebElement is present");
        } else log.error("WebElement is not present");
        return flag;
    }

    public WebElement df(By locator) {
        return driver.findElement(locator);
    }


    public void presenceOfElementLocatedClick(By locator) {
        wait.until(ExpectedConditions.presenceOfElementLocated(locator)).click();
    }

    public void presenceOfElementLocatedAndSendKey(By locator, String value) {
        wait.until(ExpectedConditions.presenceOfElementLocated(locator)).sendKeys(value);
    }


    public void presenceOfElementLocated(By locator) {
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public boolean textToBePresentInElementValue(By locator, By locator_1, String value) {
        return wait.until(ExpectedConditions.textToBePresentInElementValue(locator, df(locator_1).getAttribute(value)));
    }

    public void writeDataClient(String paramName, String paramValue) throws IOException {
        try {
            boolean flagWrite = true;
            if (searchParam(paramName)) {
                update(paramName, paramValue);
            } else {
                FileWriter writer;
                File f = new File(folderPath + "data_client.txt");
                if (!f.exists()) {
                    writer = new FileWriter(createFile(folderPath + "data_client.txt"));
                } else {
                    writer = new FileWriter(folderPath + "data_client.txt", flagWrite);
                }
                writer.append(paramName + ":" + paramValue + "\r\n");
                writer.flush();
                writer.close();
            }
        } catch (Exception e) {
            log.error("Catch " + e);
        }
    }

    public File createFile(String file) throws IOException {
        File tempFileWidgetData = new File("", file);
        tempFileWidgetData.createNewFile();
        return tempFileWidgetData;
    }

    public boolean searchParam(String paramName) throws IOException {
        FileInputStream inFile = new FileInputStream(folderPath + "data_client.txt");
        byte[] str = new byte[inFile.available()];
        inFile.read(str);
        String text = new String(str);
        return text.contains(paramName);
    }

    public void update(String paramName, String paramValue) throws IOException {
        Path path = Paths.get(folderPath + "data_client.txt");
        Files.write(path, new String(Files.readAllBytes(path), StandardCharsets.UTF_8).replace(searchParamString(paramName), paramName + ":" + paramValue).getBytes(StandardCharsets.UTF_8));

    }

    public String searchParamString(String paramName) throws IOException {
        String value = "";
        FileInputStream inFile = new FileInputStream(folderPath + "data_client.txt");
        byte[] str = new byte[inFile.available()];
        inFile.read(str);
        String text = new String(str);
        if (text.contains(paramName)) {
            String[] mass = text.split("\r\n");
            for (int i = 0; i < mass.length; i++) {
                if (mass[i].contains(paramName)) {
                    value = mass[i];
                    break;
                }
            }
        }
        return value;
    }

    public String getDataClient(String paramName) throws IOException {
        String value = "";
        FileInputStream inFile = new FileInputStream(folderPath + "data_client.txt");
        byte[] str = new byte[inFile.available()];
        inFile.read(str);
        String text = new String(str);
        if (text.contains(paramName)) {
            String[] mass = text.split("\r\n");
            for (int i = 0; i < mass.length; i++) {
                if (mass[i].contains(paramName)) {
                    value = mass[i].substring(mass[i].indexOf(":") + 1, mass[i].length());
                    break;
                }
            }
        }
        return value;
    }

    public String randomWord(int t) {
        String AB = "0123456789abcdefghijklmnopqrstuvwxyz";

        StringBuilder sb = new StringBuilder(t);

        for (int i = 0; i < t; i++) {
            sb.append(AB.charAt(new Random().nextInt(AB.length())));
        }
        return String.valueOf(sb);
    }

    public void click(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    public void scrollToelement(WebElement webelement) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", webelement);
    }

    public String getAttribute(By locator, String attributeName) {
        return df(locator).getAttribute(attributeName);
    }

    public Random random = new Random();

    public double randomInRange(double min, double max) {
        double range = max - min;
        double scaled = random.nextDouble() * range;
        double shifted = scaled + min;
        return shifted; // == (rand.nextDouble() * (max-min)) + min;
    }

    public double roundTwoDecimals(double d) {
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        return Double.valueOf(twoDForm.format(d));
    }

}
