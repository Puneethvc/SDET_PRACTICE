package TestCases;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import PageObjects.HomePage;
import PageObjects.RegistractionPage;
import TestBase.baseClass;

public class Tc_01RegistrationPage extends baseClass {
	
	@Test(groups= {"Regression", "Master"})
	public void verify_registrationPage() throws InterruptedException {
		
		logger.info("*** Starting Tc_01RegistrationPage ***");
		
		try
		
		{
		HomePage hp = new HomePage(driver);
		
		hp.clickMyAccount();
		logger.info("clicked on myAccount link");
		
		hp.clickRegister();
		logger.info("clicked on registration link");
		
		RegistractionPage rp = new RegistractionPage(driver);
		
		logger.info("Providing Customer Information");
		rp.setFirstName(randomString().toUpperCase());
		rp.setLastName(randomNum().toUpperCase());
		rp.setEmail(randomString()+"@gmail.com");
		rp.setTelePhone(randomNum ());
		
		String password = randomNum();
		
		rp.setPassword(password);
		rp.setconfirmPassword(password);
		rp.setPolicy();
		rp.SubmitBtn();
		
		
		logger.info("Validating expected results...");
		String confMsg = rp.getConfirmationMsg();
		
		if(confMsg.equals("Your Account Has Been Created!")) {
			
			Assert.assertTrue(true);
		}else
		{
			logger.error("Test Failed...");
			logger.debug("Debug TestCase..");
			Assert.assertTrue(false);
		}
		
		// Assert.assertEquals(confMsg, "Your Account Has Been Created!");
		
		}
		catch(Exception e) {
			
			Assert.fail();
		}
		
		logger.info("Finished Execution...");
	
	}
	


}
