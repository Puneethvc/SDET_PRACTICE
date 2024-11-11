package TestBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class baseClass {
	
	
	public static WebDriver driver;
	public Logger logger;
	public Properties p;
	
	
		@BeforeClass (groups={"Sanity", "Regression", "Master"})
		@Parameters({"browser"})
		public void setUp(String br) throws IOException {
			
			// Loading Config.properties
			FileReader file = new FileReader(".//src//test//resources//config.properties");
			p = new Properties();
		    p.load(file);
		   
			
			
			logger = LogManager.getLogger(this.getClass());
			switch(br.toLowerCase()) {
			
			case"chrome" : 
//				WebDriverManager.chromedriver().setup();
				driver= new ChromeDriver();
				break;
			case"firefox" : driver = new FirefoxDriver(); break;
			case"edge" : driver = new EdgeDriver(); break;
			default : System.out.println("Invalid Browser Name..."); return;
			}
			
//			driver= new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
//			driver.get("https://tutorialsninja.com/demo/");
			
			driver.get(p.getProperty("url"));
			
		}
		
		@AfterClass(groups={"Sanity", "Regression", "Master"})
		public void tearDown() {
			driver.quit();
		}
		
		public String randomString() {
			String generatedString = RandomStringUtils.randomAlphabetic(5);
			return generatedString;
		}
		
		public String randomNum() {
			String generatedNumber = RandomStringUtils.randomNumeric(10);
			return generatedNumber;
		}
		
		public String randomAlphaNumeric() {
			String generatedstring = RandomStringUtils.randomAlphabetic(3);
			String generatednumber = RandomStringUtils.randomNumeric(5);
			return(generatedstring+"@"+generatednumber);
		}
		
		public String captureScreen(String tname) {
			
			String timeStamp = new SimpleDateFormat("yyyymmddhhmmss").format(new Date());
			
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File sourcefile = screenshot.getScreenshotAs(OutputType.FILE);
			String targetfilepath = System.getProperty("user.dir")+"\\screenshots\\"+tname+"-" + timeStamp + ".png";
			File targetfile = new File(targetfilepath);
			sourcefile.renameTo(targetfile);
			return targetfilepath;
		}
		
}

