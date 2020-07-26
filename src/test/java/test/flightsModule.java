package test;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.LandingPage;
import resources.base;

public class flightsModule extends base{
	
	 public static Logger log =LogManager.getLogger(base.class.getName());
	 
@BeforeTest
public void initialize() throws IOException
{
	driver =initializeDriver();		
	driver.get(prop.getProperty("url"));
	driver.manage().window().maximize();
}
	
	  @Test(dataProvider="getData")
	  public void flightSearch(String From, String To) throws IOException, InterruptedException { 
	  LandingPage l=new LandingPage(driver); 
	  l.waitForElement(LandingPage.fromMenu); 
	  l.getFromMenu().click();
	  l.getFromMenu().sendKeys(From); 
	  l.waitForElement(LandingPage.selectionOfCity);
	  l.getSelectionOfCity().click();
	  
	  l.waitForElement(LandingPage.toMenu); 
	  l.getToMenu().click(); 
	  l.getToMenu().clear();
	  l.getToMenu().sendKeys(To); Thread.sleep(2000);
	  l.waitForElement(LandingPage.selectionOfCity); 
	  l.getSelectionOfCity().click();
	  l.getSearch().click();
	  l.getFares();
	  l.getCheapestFare();
	  l.getFastestFlight();
	  }
	  
	  
	  @DataProvider
		public Object[][] getData()
		{
			// Row stands for how many different data types test should run
			// column stands for how many values per each test
			
			Object[][] data=new Object[1][2];		
			data[0][0]="Hyderabad";
			data[0][1]="Mumbai";	
			return data;			
		}
	
	@AfterTest
	public void teardown()
	{		
		driver.close();
		driver=null;
		
	}

	
}
