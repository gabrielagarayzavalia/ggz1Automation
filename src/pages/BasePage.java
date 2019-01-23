package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BasePage {
	
	
	public WebDriver driver;
	public WebDriverWait wait;
	
////*constructor with url
	public BasePage(WebDriver driver, String URL) throws Exception {
		this.driver = driver;
		this.driver.get(URL);
		
		wait = new WebDriverWait(driver,15);
		
		wait.until(ExpectedConditions.urlMatches(URL));
		
		//Thread.sleep(5000);
		
		PageFactory.initElements(this.driver, this);
	}
	
	public BasePage(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
		//this.driver.get(URL);
	}
	

 
    //Click Method
    public void click (By elementLocation) {
        driver.findElement(elementLocation).click();
    }
 
    //Write Text
    public void writeText (By elementLocation, String text) {
        driver.findElement(elementLocation).sendKeys(text);
    }
 
    //Read Text
    public String readText (By elementLocation) {
        return driver.findElement(elementLocation).getText();
    }
    
    public WebElement listElement (By elementLocation) {
    	return driver.findElement(elementLocation) ;
    }
	
	
	
	

}

