package selenium1;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class CSTBAboutUs {

	public static void main(String[] args) {
		// TODO Auto-generated method stub 
		//swarna, tor code pora ektu kosto, formatting kora nai.
		
		System.out.println("hello Selenium");
		
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium_jars\\ChromeDriver\\chromedriver.exe");
		
	    WebDriver driver = new ChromeDriver();
	    Actions action = new Actions(driver);
	    driver.get("https://www.cstb.ca");
	    	    
	    driver.manage().deleteAllCookies();
	    
	    //Clicking to 'About Us' tab - 'Ask a question' link using Actions
	    
	    TC_AboutUs_AskAQuestion(driver, action);
	     
	    //Clicking to 'About Us' tab - 'Contact Us' link using Actions:
	    
	    TC_AboutUs_ContactUs(driver, "xyz", "xyz@abc.com", "test subject", "test message", action);
	    
	    //closing Testing:
	    
	    closeDriver(driver);
	    

	}


	private static void TC_AboutUs_ContactUs(WebDriver driver, String name, String email, String subject, String message, Actions action) {
		
		
		action.moveToElement(driver.findElement(By.xpath("//li[contains(@id, 'menu-item')]//a[contains(text(), 'About Us')]"))).perform();
	    
	    driver.findElement(By.xpath("//ul[@class = 'sub-menu']//a[contains(text(), 'Contact Us')]")).click();
	    
	    String address = driver.findElement(By.xpath("//div[@class = 'row']//p[2]")).getText();
	    
	    String phoneInfo = driver.findElement(By.xpath("//div[@class = 'row']//p[5]")).getText();
	    
	    String personName = driver.findElement(By.xpath("//div[@class = 'row']//p[4]")).getText();
	    
	    System.out.println("Address: " + address + ", PhoneInfo: " + phoneInfo + ", Person Name: " + personName);
	    
	    driver.findElement(By.xpath("//div[@class = 'row']//p[4]//a[contains(text(), 'CSTB Manager')]")).click();
	    
	    driver.findElement(By.xpath("//input[@name = 'your-name']")).sendKeys(name);
	    
	    driver.findElement(By.xpath("//input[@name = 'your-email']")).sendKeys(email);
	    
	    driver.findElement(By.xpath("//input[@name = 'your-subject']")).sendKeys(subject);
	    
	    driver.findElement(By.xpath("//textarea[@name = 'your-message']")).sendKeys(message);
	    
	    Scanner ip = new Scanner(System.in); 
	    
	    System.out.print("Enter the Captcha: ");
		
	    String captcha = ip.next();
		
	    driver.findElement(By.xpath("//input[@name = 'captcha-180']")).sendKeys(captcha); 
	    
	    ip.close();
	}

	private static void TC_AboutUs_AskAQuestion(WebDriver driver, Actions action) {
		
	    action.moveToElement(driver.findElement(By.xpath("//li[contains(@id, 'menu-item')]//a[contains(text(), 'About Us')]"))).perform();
	    
	    //Sometimes drop down after hover over may cause exception - element not interactable. Few seconds wait works
	    
	    driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);  
	    
	    driver.findElement(By.xpath("//ul[@class = 'sub-menu']//a[contains(text(), 'Ask a Question')]")).click();
	    
	    String questionText = driver.findElement(By.xpath("//div[@class = 'row']//b")).getText();
	    
	    if (questionText.contains("error")) {
	    	
	    	System.out.println("This page has error: FAIL");
	    }
	    else {
	    	
	    	System.out.println("This page doesn't have error: PASS");
	    }
		
	}
	
	private static void closeDriver(WebDriver driver) {
		// TODO Auto-generated method stub
		driver.close();
	    System.out.println("End of session");
	    
	}

}
