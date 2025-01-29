package demo;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import vtiger_ObjectRepository.ContactInformPage;
import vtiger_ObjectRepository.ContactPage;
import vtiger_ObjectRepository.Create_NewContact_Page;
import vtiger_ObjectRepository.HomePage;
import vtiger_ObjectRepository.LoginPage;
import vtiger_genericUtility.ExcelFileUtility;
import vtiger_genericUtility.PropertyfileUtility;
import vtiger_genericUtility.WebDriverUtility;

public class ScriptWith_POM_Generics_contact {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

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
		hp.getContactsLink().click();

		// click on create contact lookup page
		ContactPage cp = new ContactPage(driver);
		cp.getContactlookupimageLink().click();

		// create contact page
		Create_NewContact_Page ccp = new Create_NewContact_Page(driver);
		String LASTNAME = eutil.toReadDataFromExcelFile("Contacts", 1, 2);
		ccp.getLastnameTextfield().sendKeys(LASTNAME);
		ccp.getSaveButton().click();

		// validate
		ContactInformPage cip = new ContactInformPage(driver);
		String lastname = cip.getContactinformLink().getText();

		if (lastname.contains(LASTNAME)) {
			System.out.println("Passed.............");
		} else {
			System.out.println("Failed...............");
		}
		
		wutil.toMouseHover(driver, hp.getLoginLink());
		hp.getSignoutLink().click();
		System.out.println("logout done");
		//driver.quit();
	}

}
