package demo;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import vtiger_genericUtility.ExcelFileUtility;
import vtiger_genericUtility.PropertyfileUtility;
import vtiger_genericUtility.WebDriverUtility;

public class Script_With_Webdriver_Utility {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
				

		PropertyfileUtility putil = new PropertyfileUtility();
		ExcelFileUtility eutil=new  ExcelFileUtility();
		WebDriverUtility wutil=new WebDriverUtility();
				
		String URL = putil.toReadDataFromPropertyFile("url");
		String BROWSER = putil.toReadDataFromPropertyFile("browser");
		String USERNAME = putil.toReadDataFromPropertyFile("username");
		String PASSWORD = putil.toReadDataFromPropertyFile("password");
	

		//step-1
         WebDriver driver=null;
         if (BROWSER.equalsIgnoreCase("Chrome")) {
        	 driver=new ChromeDriver();
         }
         else if(BROWSER.equalsIgnoreCase("Edge")) {
        	 driver=new EdgeDriver();
         }
         else if(BROWSER.equalsIgnoreCase("Firefox")) {
        	 driver=new FirefoxDriver();
         }
         System.out.println("browser Lunched");
    
		wutil.tomaximize(driver);//To read from  WebDriver Utility
		System.out.println("browser got maximized");
		wutil.toWaitForElement(driver);//Use from  WebDriver Utility
		
		driver.get(URL);
		
		//step-2
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		System.out.println("credential filled and login");
		
		//Step-3-------Navigate to contact list
		
		driver.findElement(By.linkText("Contacts")).click();
		System.out.println("Navigate to contact list successfully");
		
		//Step-4-----click on create contact lookup img
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
		
		//Step-5-----create contact with mandatory details
	   String LASTNAME = eutil.toReadDataFromExcelFile("Contacts", 1, 2);//use from
		driver.findElement(By.name("lastname")).sendKeys(LASTNAME);
		
		//step-6---- save and varification
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		String lastname = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		
		if(lastname.contains(LASTNAME))
				{
			System.out.println("Passed.............");
				}
		else {
			System.out.println("Failed...............");
		}
		//Step-7	
		//logout
		WebElement logoutlink = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		wutil.toMouseHover(driver, logoutlink);
		driver.findElement(By.linkText("Sign Out")).click();
		System.out.println("--------Sign out-----------");
		
		driver.quit();


	}

}
