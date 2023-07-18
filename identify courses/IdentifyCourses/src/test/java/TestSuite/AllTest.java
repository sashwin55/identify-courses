package TestSuite;

import java.io.IOException;
import org.testng.annotations.Test;

import Pages.Courses;
import Pages.ErrorCapture;
import Pages.LangLearn;


public class AllTest {
	
	@Test
	public void testing() throws InterruptedException, IOException
	{
		Courses coursespage= new Courses();
		LangLearn learninglanguage= new LangLearn();
		ErrorCapture forcampuspage= new ErrorCapture();
		
		learninglanguage.driverSetup();
		learninglanguage.openUrl();
	    coursespage.find();
	    
	    learninglanguage.openUrl();
	    learninglanguage.learningLang();
	    learninglanguage.openUrl();
	    
		forcampuspage.error();
		learninglanguage.closeBrowser();
	}

}
