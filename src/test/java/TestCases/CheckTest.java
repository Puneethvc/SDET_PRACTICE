package TestCases;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class CheckTest {
	public WebDriver driver;
	@Test
	public void readData() throws IOException {
		FileReader file = new FileReader("./src\\test\\resources\\config.properties");
		Properties p =new Properties();
		p.load(file);
		
		driver = new ChromeDriver();
		driver.get(p.getProperty("url"));
	}

}
