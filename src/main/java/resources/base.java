package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class base {

	  public WebDriver driver;
	public Properties prop;
public WebDriver initializeDriver() throws IOException
{
	
 prop= new Properties();
 String path = System.getProperty("user.dir");
 System.out.println(path); 
 
FileInputStream fis=new FileInputStream(path+"\\src\\main\\java\\resources\\data.properties");

prop.load(fis);
String browserName=prop.getProperty("browser");
System.out.println(browserName);
String url=prop.getProperty("url");
System.out.println(url);

if(browserName.equals("chrome"))
{
	 //System.setProperty("webdriver.chrome.driver", "C://chromedriver.exe");
	 System.setProperty("webdriver.chrome.driver",path+"\\src\\main\\java\\resources\\chromedriver.exe");
	driver= new ChromeDriver();
}
else if (browserName.equals("firefox"))
{
	 driver= new FirefoxDriver();
}
else if (browserName.equals("SAFARI"))
{
	driver= new SafariDriver();
}

driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
return driver;


}

public void getScreenshot(String result) throws IOException
{
	File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	FileUtils.copyFile(src, new File("C://Screenshots//"+result+"screenshot.png"));
	
}




}
