package demo;

import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Script_1_ToCreateContact {

	public static void main(String[] args) throws IOException {
		//TO READ DATA FROM PROPERTIES FILE
		 
		//step-1
		WebDriver driver=new ChromeDriver();
		System.out.println("browser Lunched");
		driver.manage().window().maximize();
		System.out.println("browser got maximized");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		
		driver.get("http:localhost:8888/");
		//step-2
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("password");
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		System.out.println("credential filled and login");
		
		//Step-3-------Navigate to contact list
		
		driver.findElement(By.linkText("Contacts")).click();
		System.out.println("Navigate to contact list successfully");
		
		//Step-4-----click on create contact lookup img
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
		
		//Step-5-----create contact with mandatory details
		driver.findElement(By.name("lastname")).sendKeys("Sniksa N");
		
		//step-6---- save and varification
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		String lastname = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		
		if(lastname.contains("Sniksa N"))
				{
			System.out.println("Passed.............");
				}
		else {
			System.out.println("Failed...............");
		}
			
		//logout
		WebElement logoutlink = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions action=new Actions(driver);
		action.moveToElement(logoutlink).perform();
		driver.findElement(By.linkText("Sign Out")).click();
		System.out.println("--------Sign out-----------");
		
		driver.quit();
	}

}
