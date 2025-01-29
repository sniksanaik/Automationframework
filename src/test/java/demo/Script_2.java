package demo;

import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Script_2 {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

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
		
		//Step-3-------Navigate to Organizations link
		
		driver.findElement(By.linkText("Organizations")).click();
		System.out.println("Navigate to Organizations successfully");
		
		//Step-4-----click on create Organizations lookup img
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
		                     //take xpath by title
		
		
		//Step-5-----createorganization with mandatory details
		//to get random number
		Random r=new Random();
		int random=r.nextInt(1000);
		
		
		
		driver.findElement(By.name("accountname")).sendKeys("Wipro"+random);
		Thread.sleep(5000);
		WebElement radio = driver.findElement(By.xpath("//input[@name='assigntype' and @value='T']"));
		radio.click();

		//step-6---- save and varification
		driver.findElement(By.xpath("//input[ @title='Save [Alt+S]']")).click();
			
		String orgname = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		
		if(orgname.contains("Wipro"+random))
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
