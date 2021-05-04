package com.ea.drsa;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.Assert.fail;

public class RentalMission {
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();
    private List<User> users = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
        driver = new ChromeDriver();
        baseUrl = "https://www.google.com/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        User user1 = new User("AdminTest", "Admintest123!");
        users.add(user1);

        User user2 = new User("mail2", "pass2");
        users.add(user2);
    }

    @Test
    public void testRentalMission() throws Exception {
        AtomicBoolean exist = new AtomicBoolean(false);
        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
        driver.get("http://localhost:4000/claim/10000/new/10002/rental");
        driver.findElement(By.id("text-field-undefined")).sendKeys("AdminTest");
        String login = driver.findElement(By.id("text-field-undefined")).getAttribute("value");
        System.out.println("le mail est :" + login);
        driver.findElement(By.xpath("(//input[@id='text-field-undefined'])[2]")).sendKeys("Admintest123!");
        String password = driver.findElement(By.xpath("(//input[@id='text-field-undefined'])[2]")).getAttribute("value");
        System.out.println("le password est :" + password);
        User user = new User(login, password);
        users.stream().forEach(user1 -> {

            if (user1.getLogin().equals(user.getLogin()) && user1.getPassword().equals(user.getPassword())) {
                exist.set(true);
            }
        });
        if(exist.get()){
            driver.findElement(By.xpath("//div[@id='root']/div[3]/div/form/div[3]/button/span")).click();
            driver.findElement(By.id("habiltation field")).sendKeys("1");
            String hab1 = driver.findElement(By.id("habiltation field")).getAttribute("value");
            if(hab1.equals("1")){

            }
        }

    }


    public void tearDown() throws Exception {
        // driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }
}
