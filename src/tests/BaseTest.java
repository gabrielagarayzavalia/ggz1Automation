package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest {

	
	public WebDriver driver;

    public WebDriverWait wait;
    
    public static String strangeURL = "http://immense-hollows-74271.herokuapp.com/";
	
	@BeforeClass
	public void setup() throws Exception {
    	
		System.setProperty("webdriver.chrome.driver","C:\\ggAutomation\\ggzAutomation01\\resourses\\chromedriver_win32\\chromedriver.exe");
		
		//Initialize driver
        driver = new ChromeDriver();
    	//driver = new FirefoxDriver();

        wait = new WebDriverWait(driver,15);
 
        //Maximize Window
        driver.manage().window().maximize();
        Thread.sleep(5000);
	}
	
	
    @AfterClass
    public void teardown () {
       driver.quit();
    }
	
	
}
