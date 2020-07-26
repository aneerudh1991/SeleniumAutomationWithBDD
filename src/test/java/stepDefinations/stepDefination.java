package stepDefinations;

import org.junit.runner.RunWith;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.junit.Cucumber;
import pageObjects.LandingPage;
import resources.base;
import test.flightsModule;

@RunWith(Cucumber.class)
public class stepDefination extends base {

	@Given("^Initialize the browser with chrome$")
	public void initialize_the_browser_with_chrome() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		 driver =initializeDriver();
	}

	@Given("^Navigate to \"([^\"]*)\" Site$")
	public void navigate_to_Site(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		driver.get(arg1);
		driver.manage().window().maximize();
	}

	
	 @When("^User enters From (.+) and To (.+) cities and perform search$")
    public void user_enters_FromAndTo_SearchFlights(String FromLocation, String ToLocation) throws Throwable {
		
		//flightsModule l=new flightsModule();
		//l.flightSearch(FromLocation, ToLocation);
		 LandingPage l = new LandingPage(driver);
		 l.flightSearch(FromLocation, ToLocation);
		 
	   
	}

	

	
	 @And("^close browsers$")
	    public void close_browsers() throws Throwable {
	        driver.quit();
	    }
	


}
