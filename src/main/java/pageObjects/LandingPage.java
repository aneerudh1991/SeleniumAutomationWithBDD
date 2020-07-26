package pageObjects;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import resources.base;

public class LandingPage extends base{
	public static Logger log =LogManager.getLogger(base.class.getName());
	
	public WebDriver driver;
	
	//public static By fromMenu=By.xpath("(//div[@class='_31PN']//input[@id='text-box'])[1]");
	@FindBy(xpath="(//div[@class='_31PN']//input[@id='text-box'])[1]")
	public static WebElement fromMenu ;
	
	//public static By selectionOfCity=By.xpath("(//div[@class='_2xgL']//div[@class='_2SRm'])[1]");
	@FindBy(xpath="(//div[@class='_2xgL']//div[@class='_2SRm'])[1]")
	public static WebElement selectionOfCity ;
	
	//public static By toMenu=By.xpath("(//div[@class='_31PN']//input[@id='text-box'])[2]");
	@FindBy(xpath="(//div[@class='_31PN']//input[@id='text-box'])[2]")
	public static WebElement toMenu ;
	
	//public static By search=By.xpath("//button[text()='Search']");
	@FindBy(xpath="//button[text()='Search']")
	public static WebElement search ;
	
	//public static By cheapest=By.xpath("(//div[@class='_21a1']//div[@class='bvnu']/span)[1]");	
	@FindBy(xpath="(//div[@class='_21a1']//div[@class='bvnu']/span)[1]")
	public static WebElement cheapest ;
	
	//public static By filter=By.xpath("div[@class='_1w5k']/div[@class='bvnu']");	
	@FindBy(xpath="div[@class='_1w5k']/div[@class='bvnu']")
	public static WebElement filter ;
	
	//public static By fastestFilter=By.xpath("(//div[@class='_1w5k']/div[@class='bvnu']//following-sibling::div[@class='_-MQd _1aCi']//div[@class='_2AVk']//div[@class=' undefined']//span[@class='_3OE8'])[1]");
	@FindBy(xpath="(//div[@class='_1w5k']/div[@class='bvnu']//following-sibling::div[@class='_-MQd _1aCi']//div[@class='_2AVk']//div[@class=' undefined']//span[@class='_3OE8'])[1]")
	public static WebElement fastestFilter;
	
	public LandingPage(WebDriver driver) {
		// TODO Auto-generated constructor stub			
		PageFactory.initElements(driver, this);
		this.driver=driver;	
	}


	public WebElement getFromMenu()
	{
		return fromMenu;
	}
	public WebElement getSelectionOfCity()
	{
		return selectionOfCity;
	}
	
	public WebElement getToMenu()
	{
		return toMenu;
	}
	
	
	public WebElement getSearch()
	{
		return search;
	}
	
	public WebElement getCheapest()
	{
		return cheapest;
	}
	
	
	public WebElement getFilter()
	{
		return filter;
	}
	
	public WebElement getFastestFilter()
	{
		return fastestFilter;
	}
	
	
	public void waitForElement(WebElement fromMenu2) {
		WebDriverWait wait = new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.elementToBeClickable(fromMenu2));
	}
	
	
	public void flightSearch(String From, String To) throws InterruptedException {	
		
		
		getFromMenu().click();
		getFromMenu().sendKeys(From);
		getSelectionOfCity().click();
		 log.debug("Selected FROM city");
		 Reporter.log("Selected FROM city");
		 System.out.println();
		getToMenu().click();
		getToMenu().clear();		
		getToMenu().sendKeys(To);
		Thread.sleep(2000);		
		getSelectionOfCity().click();
		log.debug("Selected To city");
		Reporter.log("Selected To city");
		getSearch().click();
		log.debug("Clicked on Search");
		Reporter.log("Clicked on Search");
		getFares();
		getCheapestFare();
		getFastestFlight();
		
	}
	
public void getFares() throws InterruptedException {	
		
		List<WebElement>elements=driver.findElements(By.xpath("//div[@class='_2TFk']"));
		int size=elements.size();
		log.debug("Total number of flights displayed in UI for given search criteria is : "+size);
		Reporter.log("Total number of flights displayed in UI for given search criteria is : "+size);
		for (int i=1;i<=size;i++) {
			WebElement resultRow=driver.findElement(By.xpath("(//div[@class='_2TFk'])["+i+"]"));
			WebElement resultFare=driver.findElement(By.xpath("(//div[@class='_2TFk']//div[@class='_2gMo']/div)["+i+"]"));
			String value = resultRow.getAttribute("Class");
			if(value.contains("disabled")) {
				//nothing
			}else {
				
				String Fare=resultFare.getText();
				log.debug("FARE displayed in UI for Flight number "+i+" is : "+ Fare);
				Reporter.log("FARE displayed in UI for Flight number "+i+" is : "+ Fare);
			}
		}
	}


public void getCheapestFare() throws InterruptedException {	
	
	List<WebElement>elements=driver.findElements(By.xpath("//div[@class='_2TFk']"));
	int size=elements.size();
	log.debug("Total number of flights displayed in UI for given search criteria is : "+size);
	Reporter.log("Total number of flights displayed in UI for given search criteria is : "+size);
	for (int i=1;i<=size;i++) {
		WebElement resultRow=driver.findElement(By.xpath("(//div[@class='_2TFk'])["+i+"]"));
		WebElement resultFare=driver.findElement(By.xpath("(//div[@class='_2TFk']//div[@class='_2gMo']/div)["+i+"]"));
		WebElement bookBtn=driver.findElement(By.xpath("(//div[@class='_2ifS ']//a)["+i+"]"));
		String value = resultRow.getAttribute("Class");
		if(value.contains("disabled")) {
			//nothing
		}else {
			
			String CheapestFare=resultFare.getText();
			log.debug("CHEAPEST FLIGHT FARE IS :"+CheapestFare);
			Reporter.log("CHEAPEST FLIGHT FARE IS :"+CheapestFare);
			bookBtn.click();
			Thread.sleep(5000);
			log.debug("SELECTED CHEAPEST FLIGHT IN UI");
			Reporter.log("SELECTED CHEAPEST FLIGHT IN UI");
			switchToWindow();
			break;
		}
	}
}
		
		public void switchToWindow() {
			String parent=driver.getWindowHandle();
			Set<String>s=driver.getWindowHandles();
			// Now iterate using Iterator
			Iterator<String> I1= s.iterator();
			while(I1.hasNext())
			{
			String child_window=I1.next();
			if(!parent.equals(child_window))
				{
			driver.switchTo().window(child_window);
			log.debug("SWITCHED TO CHILD WINDOW");
			Reporter.log("SWITCHED TO CHILD WINDOW");
			System.out.println(driver.switchTo().window(child_window).getTitle());
			Reporter.log(driver.switchTo().window(child_window).getTitle());
			driver.switchTo().window(parent);
			log.debug("SWITCHED BACK TO PARENT WINDOW");
			Reporter.log("SWITCHED BACK TO PARENT WINDOW");
			break;
				}
			}
		}


public void getFastestFlight() throws InterruptedException {	
	
	getCheapest().click();
	getFastestFilter().click();
	Thread.sleep(5000);
	List<WebElement>elements=driver.findElements(By.xpath("//div[@class='_2TFk']"));
	int size=elements.size();
	log.debug("Total number of flights displayed in UI for given search criteria is : "+size);
	Reporter.log("Total number of flights displayed in UI for given search criteria is : "+size);
	for (int i=1;i<=size;i++) {
		WebElement resultRow=driver.findElement(By.xpath("(//div[@class='_2TFk'])["+i+"]"));
		WebElement resultFare=driver.findElement(By.xpath("(//div[@class='_2TFk']//div[@class='_2gMo']/div)["+i+"]"));
		WebElement bookBtn=driver.findElement(By.xpath("(//div[@class='_2ifS ']//a)["+i+"]"));
		String value = resultRow.getAttribute("Class");
		if(value.contains("disabled")) {
			//nothing
		}else {
			
			String FastestFlightFare=resultFare.getText();
			log.debug("FASTEST FLIGHT FARE IS :"+FastestFlightFare);
			Reporter.log("FASTEST FLIGHT FARE IS :"+FastestFlightFare);
			
			bookBtn.click();
			Thread.sleep(5000);
			log.debug("SELECTED FASTEST FLIGHT");
			Reporter.log("SELECTED FASTEST FLIGHT");
			switchToWindow();
			break;
		}
	}
}
}
