package selenium1;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class CSTBPartnerProgram {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("hello Selenium");
		
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium_jars\\ChromeDriver\\chromedriver.exe");
		
	    WebDriver driver = new ChromeDriver();
	    
	    driver.get("https://www.cstb.ca");
	    
	    driver.manage().window().maximize();
	    
	    driver.manage().deleteAllCookies();
	    
	    //Clicking to Partner Program using Actions
	    
	    Actions action = new Actions(driver);
	    
	    action.moveToElement(driver.findElement(By.xpath("//li[contains(@id, 'menu-item')]//a[contains(text(), 'ISTQB� Partner Program')]"))).perform();
	    
	    driver.findElement(By.xpath("//ul[@class = 'sub-menu']//a[contains(text(), 'ISTQB� Partner Program Guidelines')]")).click();
	    
	    //checking all the steps to become partner:
	    
	    System.out.println("*********************************************************");

	    List<WebElement> stepsToPartner = driver.findElements(By.xpath("//div[@class = 'courses-listing clearfix']/following-sibling::ol//li"));
	    
	    for(int i = 0; i < stepsToPartner.size(); i++) {
	    	
	    	int j = i + 1;
	    	
	    	System.out.println( j + ". " + stepsToPartner.get(i).getText());
	    	
	    }
	    
	    //Checking program Rules:
	    
	    System.out.println("*********************************************************");
	    
	    List<WebElement> progRules = driver.findElements(By.xpath("//p[contains(a, 'how the program works')]/following-sibling::ul//li"));
	    
	    for(int i = 0; i < progRules.size(); i++) {
	    		    	
	    	System.out.println(progRules.get(i).getText());
	    	
	    }
	    
	    //Checking program Rules links using child window: 
	    
	    System.out.println("*********************************************************");
	    
	    List<WebElement> progRulesLinks = driver.findElements(By.xpath("//p[contains(a, 'how the program works')]/following-sibling::ul//li//a"));
	    
	    for(int i = 0; i < progRulesLinks.size(); i++) {
	    		    	
	    	System.out.println(progRulesLinks.get(i).getText());
	    	
	    	String[] givenUrl = progRulesLinks.get(i).getAttribute("href").split("/");
	    		    		    	
	    	progRulesLinks.get(i).click();
	    	
		    driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);  

	    	//switching to new tab:
		    
	    	Set<String> winHandle = driver.getWindowHandles();
	    	
	    	Iterator<String> it = winHandle.iterator();
	    	
	    	String parentW = it.next();
	    	String childW = it.next();
	    	
	    	driver.switchTo().window(childW);
	    	
	    	String[] currentUrl = driver.getCurrentUrl().split("/"); 		    	
			
			if (givenUrl[givenUrl.length - 1].equalsIgnoreCase(currentUrl[currentUrl.length - 1])) {
						  
				System.out.println("Link is opened and matches with the given URL : Pass " + driver.getCurrentUrl() );
						  
			} 
			else {
						  
				System.out.println("Link is opened but doesn't match with the given URL : Fail");
						  
			}
			driver.close();
			
			driver.switchTo().window(parentW);
			
		    driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);  

			    	
	    }
	    
	    //Checking Partners in Canada
	    
	    action.moveToElement(driver.findElement(By.xpath("//li[contains(@id, 'menu-item')]//a[contains(text(), 'ISTQB� Partner Program')]"))).perform();
	    
	    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);  

	    driver.findElement(By.xpath("//ul[@class = 'sub-menu']//a[contains(text(), 'ISTQB� Partners in Canada')]")).click();
	    
	    String global = driver.findElement(By.xpath("//h2[contains(text(), 'Global')]//following-sibling::p[1]//a")).getAttribute("href");
	    
	    String silver = driver.findElement(By.xpath("//h2[contains(text(), 'Silver')]//following-sibling::p[1]//a")).getAttribute("href");
	    
	    System.out.println(global + silver);
	    
	    
	    System.out.println("End of session");
	    
	    driver.close();
	    

	}

}
