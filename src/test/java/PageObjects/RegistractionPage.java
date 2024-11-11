package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegistractionPage extends BasePage {
	
	
	public RegistractionPage(WebDriver driver) {
		 
			super(driver);
		}
		
		@FindBy(xpath="//input[@id='input-firstname']")
		WebElement firstname;
		
		@FindBy(xpath="//input[@id='input-lastname']")
		WebElement lastname;
		
		@FindBy(xpath="//input[@id='input-email']")
		WebElement email;
		
		@FindBy(xpath="//input[@id='input-telephone']")
		WebElement telephone;
		
		@FindBy(xpath="//input[@id='input-password']")
		WebElement password;
		
		@FindBy(xpath="//input[@id='input-confirm']")
		WebElement confirmpassword ;
		
		@FindBy(xpath="//input[@name='agree']")
		WebElement chkpolicyagree;
		
		@FindBy(xpath="//input[@class='btn btn-primary']")
		WebElement btnsubmit;
		
		@FindBy(xpath="//h1[text()='Your Account Has Been Created!']")
		WebElement msgconfirmation;
		
		
		public void setFirstName(String fname) {
			firstname.sendKeys(fname);
		}
		public void setLastName(String lname) {
			lastname.sendKeys(lname);
		}
		
		public void setEmail(String Email) {
			email.sendKeys(Email);
		}
		
		public void setTelePhone(String tele) {
			telephone.sendKeys(tele);
		}
		
		public void setPassword(String Password) {
			password.sendKeys(Password);
		}
		
		public void setconfirmPassword(String confirmPassword) {
			confirmpassword.sendKeys(confirmPassword);
		}
		
		public void setPolicy() {
			chkpolicyagree.click();
		}
		
		public void SubmitBtn() {
			btnsubmit.click();
		}
		
		public String getConfirmationMsg() {
			try {
				return(msgconfirmation.getText());
			}catch(Exception e)
			{
				return(e.getMessage());
			}
		
	}
}
	
	


