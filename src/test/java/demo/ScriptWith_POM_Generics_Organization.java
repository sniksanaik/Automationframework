package demo;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import vtiger_ObjectRepository.Create_new_Organization;
import vtiger_ObjectRepository.HomePage;
import vtiger_ObjectRepository.LoginPage;
import vtiger_ObjectRepository.OrganizationPage;
import vtiger_ObjectRepository.Organization_Info_Page;
import vtiger_genericUtility.ExcelFileUtility;
import vtiger_genericUtility.PropertyfileUtility;
import vtiger_genericUtility.WebDriverUtility;

public class ScriptWith_POM_Generics_Organization {

	public static void main(String[] args) throws IOException {
		

			PropertyfileUtility putil = new PropertyfileUtility();
			ExcelFileUtility eutil = new ExcelFileUtility();
			WebDriverUtility wutil = new WebDriverUtility();

			String URL = putil.toReadDataFromPropertyFile("url");
			String BROWSER = putil.toReadDataFromPropertyFile("browser");
			String USERNAME = putil.toReadDataFromPropertyFile("username");
			String PASSWORD = putil.toReadDataFromPropertyFile("password");

			WebDriver driver = null;
			if (BROWSER.equalsIgnoreCase("Chrome")) {
				driver = new ChromeDriver();
			} else if (BROWSER.equalsIgnoreCase("Edge")) {
				driver = new EdgeDriver();
			} else if (BROWSER.equalsIgnoreCase("Firefox")) {
				driver = new FirefoxDriver();
			}
			System.out.println("browser Lunched");

			wutil.tomaximize(driver);// To read from WebDriver Utility
			System.out.println("browser got maximized");
			wutil.toWaitForElement(driver);// Use from WebDriver Utility
			driver.get(URL);

			// to perform Login
			LoginPage lp = new LoginPage(driver);
			lp.getUsernameTextField().sendKeys(USERNAME);
			lp.getPasswordTextfield().sendKeys(PASSWORD);
			lp.getLoginbutton().click();

			// click on Login Button

			HomePage hp = new HomePage(driver);
			hp.getOrganizationsLink().click();

			// click on create organization lookup page
			OrganizationPage op = new OrganizationPage(driver);
			op.getOrganizationLookupImageLink().click();

			// create organization page
					
			driver.findElement(By.name("accountname")).sendKeys("Snik_2 ORG");
			
			Create_new_Organization cop=new Create_new_Organization(driver);
			String Organization_name = eutil.toReadDataFromExcelFile("Organization", 1, 2);
			cop.getCreateNewOrgLink().sendKeys(Organization_name);
	    	//select radiobutton as group
			WebElement radio = driver.findElement(By.xpath("//input[@name='assigntype' and @value='T']"));
			radio.click();
			
			cop.getSaveOrgButton().click();
			
			
			// validate
			Organization_Info_Page oip = new Organization_Info_Page(driver);
			String org_name = oip.getVerifylink().getText();
			
			//oip.getVerifylink().

			if (org_name.contains(Organization_name)) {
				System.out.println("Passed........");
			} else {
				System.out.println("Failed...............");
			}
			
			wutil.toMouseHover(driver, hp.getLoginLink());
			hp.getSignoutLink().click();
			System.out.println("logout done");
			//driver.quit();
	}

}
