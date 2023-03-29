package radio_buttons;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.GlobalConfigVariable;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

public class HandlingRadioButtonTests {

    WebDriver driver;
    @BeforeMethod
    public void initializeBrowser() throws MalformedURLException {

        /** initialize the test browser **/
        ChromeOptions browserOptions = new ChromeOptions();
        browserOptions.setPlatformName("Windows 10");
        browserOptions.setBrowserVersion("110.0");
        HashMap<String, Object> ltOptions = new HashMap<String, Object>();
        ltOptions.put("username", GlobalConfigVariable.USER_NAME);
        ltOptions.put("accessKey", GlobalConfigVariable.ACCESS_KEY);
        ltOptions.put("project", "Handling Radio Buttons in Selenium");
        ltOptions.put("w3c", true);
        ltOptions.put("plugin", "java-testNG");
        browserOptions.setCapability("LT:Options", ltOptions);

        /** LambdaTest hub url **/
        String hubUrl = "https://@hub.lambdatest.com/wd/hub";

        /** initializing the remote webdriver **/
        driver = new RemoteWebDriver(new URL(hubUrl), browserOptions);

        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        driver.get("https://www.lambdatest.com/selenium-playground/radiobutton-demo");
    }


    @Test
    public void testFindRadioButtons(){
        // Scenario 1 - find number of radio buttons on the web page

        // Case 1 - finding all the radio buttons
        List<WebElement> radioButtons = driver.findElements(By.xpath("//input[@type='radio']"));
        System.out.println("Total no of radio buttons on the page is "+radioButtons.size());

        // Case 2 - Verifying the status of the all the radio buttons
        for(WebElement radio : radioButtons){
            // Checking the status of all radio button
            System.out.println("The status of the radio button "+ radio.getAttribute("value")+" is "+radio.isSelected());
        }
    }

    @Test
    public void testSelectRadioButtons(){
        // Scenario 2 - selecting radio buttons on the web page

        WebElement maleRadioButton = driver.findElement(By.xpath("//input[@value='Male' and @name='optradio']"));
        // selecting the radio button by clicking it
        maleRadioButton.click();

        // verifying whether the radio button is selected or not using isSelected() method
        boolean selected = maleRadioButton.isSelected();
        if(selected){
            System.out.println("Male radio button is selected");
        }else{
            System.out.println("Male radio button is not selected");
        }

        // clicking on the Get Checked Value button
        WebElement getCheckedValueBtn = driver.findElement(By.id("buttoncheck"));
        getCheckedValueBtn.click();

        // fetching the displayed text upon clicking the button
        WebElement resultTextArea = driver.findElement(By.xpath("//button[@id='buttoncheck']/following-sibling::p"));
        String actualResultText = resultTextArea.getText();
        System.out.println("The displayed result text is - "+actualResultText);
    }

    @AfterMethod
    public void closeBrowser(){
        /*** close the test browser **/
        driver.quit();
    }
}
