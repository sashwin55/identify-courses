package Pages;

import java.io.IOException;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.Status;

import Base.Base;

public class LangLearn extends Base {
	
	By search = By.xpath("//*[@aria-label = 'What do you want to learn?']");
	By button = By.xpath("//*[@id=\"rendered-content\"]/div/header/div/div/div/div[1]/div[3]/div/form/div/div/div[1]/button[2]/div");
	
	By more = By.xpath("(//span[text()='Show more'])[4]");
	By langs = By.xpath("/html/body/div[8]/div[3]/div/div/div[2]/div[2]/div/div/label/div/span");
	By close = By.xpath("//span[text()='Close']");
	
	By level = By.xpath("/html/body/div[2]/div/div/div[1]/main/div[2]/div/div/div/div/div[1]/div/div[4]/div/div/div/label/div/span");

	public void learningLang() throws InterruptedException, IOException {
		
		exttest = report.createTest("Extract all languages and levels with counts for Learning Language. ");
		wait(30, search);
		driver.findElement(search).sendKeys(prop.getProperty("search_course2"));
		driver.findElement(button).click();
		wait(30, more);
		driver.findElement(more).click();
		
		List<WebElement> langlist = driver.findElements(langs);
		System.out.println("");
		System.out.println("Total number of languages: " + langlist.size());
		System.out.println("The languages are: ");
		System.out.println("");
		
		for (int j = 0; j < langlist.size(); ++j) {
			System.out.println(langlist.get(j).getText());
		}
		driver.findElement(close).click();
		
		exttest.log(Status.PASS, "All Languages are extracted Successfully");
		
		List<WebElement> labels = driver.findElements(level);
		System.out.println("");
		System.out.println("Total number of levels: " + labels.size());
		System.out.println("The levels are: ");
		System.out.println("");
		
		for (int j = 0; j < labels.size(); ++j) {
			System.out.println(labels.get(j).getText());
		}
		exttest.log(Status.PASS, "All levels are extracted Successfully");
		Thread.sleep(3000);
		
	}
}
