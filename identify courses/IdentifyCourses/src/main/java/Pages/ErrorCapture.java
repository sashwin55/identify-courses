package Pages;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.ui.Select;

import com.aventstack.extentreports.Status;


import Base.Base;

public class ErrorCapture extends Base {
	
	//13 inputs - 6 are sendkeys and 7 are dropdown

	By campus = By.xpath("//a[text()='For Campus']");
	
	By ContactUs = By.xpath("//span[text()='Contact Us']");
	
	By fname = By.id("FirstName");
	By lname = By.id("LastName");
	By title = By.id("Title");
	By discipline = By.id("Primary_Discipline__c");
	By mail  = By.id("Email");
	By phone  = By.id("Phone");
	By instituteName  = By.id("Company");
	By instituteType  = By.id("Institution_Type__c");
	By country = By.id("Country");
	By state = By.id("State");
	By describe = By.id("What_the_lead_asked_for_on_the_website__c");
	
	By submit = By.xpath("//button[text()='Submit']");

	public void error() throws InterruptedException, IOException {
		exttest = report.createTest("Display error during invalid Email Address. ");
		wait(30, campus);
		driver.findElement(campus).click();
		wait(30, ContactUs);
		driver.findElement(ContactUs).click();
		exttest.log(Status.PASS, "Moved to Contact Sales Page Successfully");
		
		driver.findElement(fname).sendKeys("Sashwin");
		driver.findElement(lname).sendKeys("Shailesh");
		driver.findElement(title).sendKeys("Test Engineer");
		Select s1 = new Select(driver.findElement(discipline));
		s1.selectByVisibleText("I am a student");
		driver.findElement(mail).sendKeys("lmn@xyz");
		driver.findElement(phone).sendKeys("9042379605");
		driver.findElement(instituteName).sendKeys("RMK Engineering College");
		Select s2 = new Select(driver.findElement(instituteType));
		s2.selectByVisibleText("Government");
		Select s3 = new Select(driver.findElement(country));
		s3.selectByVisibleText("India");
		Select s5 = new Select(driver.findElement(state));
		s5.selectByVisibleText("Tamil Nadu");
		Select s4 = new Select(driver.findElement(describe));
		s4.selectByVisibleText("Learner Support");
		
		driver.findElement(submit).click();
		
		JavascriptExecutor ss1=(JavascriptExecutor) driver;
		ss1.executeScript("arguments[0].scrollIntoView()",driver.findElement(title));
		wait(20,title);
	    
		exttest.log(Status.PASS, "Data entered and submitted Successfully");
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//Taking screenshot of the error message.
		TakesScreenshot capture = (TakesScreenshot) driver;
		File sourceFile=capture.getScreenshotAs(OutputType.FILE);
		File destFile=new File("C:\\Users\\Sashwin\\eclipse-workspace\\IdentifyCourses6.zip_expanded\\IdentifyCourses6\\Screenshot\\errormsg.png");
		
		try {
			FileUtils.copyFile(sourceFile, destFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		exttest.log(Status.PASS, "Screenshot taken Successfully");
		driver.close();
		
		System.out.println("");
		System.out.println("Completed Successfully");
		Thread.sleep(2000);
	}
}
