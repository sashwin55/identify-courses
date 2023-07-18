package Base;

import java.io.FileInputStream;

import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Base {

	public static WebDriver driver;
	public static Properties prop;
	public static WebDriverWait wait;
	public static ExtentSparkReporter exthtml;
	public static ExtentTest exttest;
	public static ExtentReports report;

	public void driverSetup() {
		prop = new Properties();
		try {
			prop.load(new FileInputStream("src/main/java/Config/config.properties")); // Loading the properties
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (prop.getProperty("browserName").equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver(); 									// Initializing new Chrome driver
		}else if (prop.getProperty("browserName").equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver(); 									// Initializing new Firefox driver
		}else if (prop.getProperty("browserName").equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();										// Initializing new Edge driver
		}
		
		driver.manage().window().maximize(); 								// To maximize the window
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20)); // Waiting time to page the load completely
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));  // Adding driver waits to timeouts

		exthtml = new ExtentSparkReporter(System.getProperty("user.dir") + "/Report/ExtentReport.html");
		report = new ExtentReports();
		report.attachReporter(exthtml);
	}

	public void openUrl() // Method to open URL for smoke test
	{
		driver.get(prop.getProperty("url"));
	}

	// Function to Put Wait
	public void wait(int sec, By locator) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(sec));
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public void closeBrowser() throws IOException // method to close the browser
	{
	
		report.flush();
		driver.quit(); // To close the browser
		
		}
	}

