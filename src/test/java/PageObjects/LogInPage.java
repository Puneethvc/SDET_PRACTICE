package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LogInPage extends BasePage{
	
	public LogInPage(WebDriver driver) {
	super(driver);
	}
	
	@FindBy(id="input-email")
	WebElement Email;
	
	@FindBy(name="password")
	WebElement Password;
	
	@FindBy(xpath="//input[@class='btn btn-primary']")
	WebElement btnLogin;
	
	
	public void setEmail(String email) {
		Email.clear();
		Email.sendKeys(email);
	}
	
	public void setPasword(String password) {
		Password.clear();
		Password.sendKeys(password);
	}
	
	public void clickLogIn() {
		btnLogin.click();
	}
	
}
