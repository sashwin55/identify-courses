package Pages;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.Status;

import Base.Base;

public class Courses extends Base {
	
	By search = By.xpath("//*[@aria-label = 'What do you want to learn?']");
	By button = By.xpath(
			"//*[@id=\"rendered-content\"]/div/header/div/div/div/div[1]/div[3]/div/form/div/div/div[1]/button[2]/div");
	By apply = By.xpath("//span[text()='Apply']");
	By Beginner = By.xpath("//span[text()='Beginner']");
	By more = By.xpath("(//span[text()='Show more'])[4]");
	By english = By.xpath("//span[text()='English']");
	By courses = By.xpath("//span[text() = 'Courses']");
	
	By name = By.xpath(
			"/html/body/div[2]/div/div/div[1]/main/div[2]/div/div/div/div/div[2]/ul/li/div/div/a/div/div[2]/div[1]/h2");
		
	By rating = By.xpath("/html/body/div[2]/div/div/div[1]/main/div[2]/div/div/div/div/div[2]/ul/li/div/div/a/div/div[2]/div[2]/div/p[1]");
	By duration = By.xpath("/html/body/div[2]/div/div/div[1]/main/div[2]/div/div/div/div/div[2]/ul/li/div/div/a/div/div[2]/div[2]/p");
	
	public void find() throws InterruptedException, IOException {
		exttest = report.createTest("Display two courses for beginner Web Development. ");
		wait(20, search);
		
		driver.findElement(search).sendKeys(prop.getProperty("search_course1"));
		driver.findElement(button).click();
		
		wait(10, Beginner);
		driver.findElement(Beginner).click();
		
		exttest.log(Status.PASS, "Beginners selected Successfully");
		
		wait(10, courses);
		driver.findElement(courses).click();
		
		
		try {
			wait(10, english);
			driver.findElement(english).click();
		}catch(Exception e){
			driver.findElement(more).click();
			
			wait(20, english);
			driver.findElement(english).click();
			driver.findElement(apply).click();
		}
		

		exttest.log(Status.PASS, "English selected Successfully");
		
		System.out.println("");
		System.out.println("The courses and their ratings are: ");
		System.out.println("");

		List<WebElement> names = driver.findElements(name);
		List<WebElement> ratings = driver.findElements(rating);
		List<WebElement> fullstring = driver.findElements(duration);
		
		//Printing the first two results.
		for (int j = 0; j <= 1; j++) {
			String fullstr = fullstring.get(j).getText();
			System.out.println(names.get(j).getText() + " - " + ratings.get(j).getText() + " - " + fullstr.substring(fullstr.indexOf("1-")));
		}
		exttest.log(Status.PASS, "Courses are displayed Successfully");
		
	}
}
