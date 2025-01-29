package demo;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;


public class Script_5 {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

// step-1
//---------------------
		WebDriver driver = new ChromeDriver();
		System.out.println("browser Lunched");
		driver.manage().window().maximize();
		System.out.println("browser got maximized");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.get("http:localhost:8888/");

// step-2
//---------------
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("password");
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		System.out.println("credential filled and login");

// Step-3-------Navigate to contacts link
// -------------------------------------------
		driver.findElement(By.linkText("Contacts")).click();
		System.out.println("Navigate to Contacts successfully");
		String parentWindow = driver.getWindowHandle();
		System.out.println("parentWindow ----" + parentWindow);

// Step-4-----click on create Contacts lookup img
//---------------------------------------------------------
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();

		
// Step-5-----create contact with mandatory details
// -------------------------------------------------------
		driver.findElement(By.name("lastname")).sendKeys("Advik_1 Patel");
		// select radiobutton as group
		driver.findElement(By.xpath("//img[contains(@onclick,'return window.open')]")).click();

		
//step-6----select the organaization from organization Group
//	----------------------------------------------------
		Set<String> childwindow = driver.getWindowHandles();
		System.out.println("childwindow---" + childwindow);

		for (String handle : childwindow) {
			System.out.println(handle);
			if (!handle.equals(parentWindow)) {
				driver.switchTo().window(handle);
				Thread.sleep(5000);
				driver.findElement(By.partialLinkText("Snik ORG")).click();
				Thread.sleep(5000);
				//driver.close();
			}
		}
		driver.switchTo().window(parentWindow);

		
//step-7---- save and varification
//----------------------------------
				driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
					
				String contactname = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
				
				if(contactname.contains("Advik_1 Patel"))
						{
					System.out.println("Passed.............");
						}
				else {
					System.out.println("Failed...............");
				}
	
				
//step-8------logout
//----------------------
				WebElement logoutlink = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
				Actions action=new Actions(driver);
				action.moveToElement(logoutlink).perform();
				driver.findElement(By.linkText("Sign Out")).click();
				System.out.println("--------Sign out-----------");
				
				driver.quit();
  
	}

}
