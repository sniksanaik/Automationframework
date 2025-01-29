package demo;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class Script_1_ToCreateContact2_DDT_EXCEL {

	public static void main(String[] args) throws IOException {
		//TO READ DATA FROM PROPERTIES FILE
		
		FileInputStream pfis=new FileInputStream(".\\src\\test\\resources\\Common_Data.properties");//take source from prpertis loc
		Properties prop=new Properties();
        prop.load(pfis);//give connection to fis and  pfis
         
        String URL=prop.getProperty("url");
        String BROWSER=prop.getProperty("browser");
        String USERNAME=prop.getProperty("username");
        String PASSWORD=prop.getProperty("password");
         
        //To read data from Excel file
        
         FileInputStream efis=new FileInputStream(".\\src\\test\\resources\\test_Excel.xlsx");
         Workbook wb=WorkbookFactory.create(efis);
         String LASTNAME=wb.getSheet("Contacts").getRow(1).getCell(2).toString();
        
         
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
         
		driver.manage().window().maximize();
		System.out.println("browser got maximized");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		
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
			
		//logout
		WebElement logoutlink = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions action=new Actions(driver);
		action.moveToElement(logoutlink).perform();
		driver.findElement(By.linkText("Sign Out")).click();
		System.out.println("--------Sign out-----------");
		
		driver.quit();
	}

}
