 package TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import PageObjects.HomePage;
import PageObjects.LogInPage;
import PageObjects.MyAccountPage;
import TestBase.baseClass;

public class Tc_002LogInPage extends baseClass{
	
	@Test(groups= {"Sanity","Master"})
	public void verifyLogInPage() {
		logger.info("**** Starting Tc_002LogInPage"+ "****");
		// HomePage
		try {
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.clicklogIn();
		
		//LogInpage
		LogInPage lp = new LogInPage(driver);
		lp.setEmail(p.getProperty("email"));
		lp.setPasword(p.getProperty("password"));
		lp.clickLogIn();
		
		//MyAccountPage
		
		MyAccountPage map= new MyAccountPage(driver);
		boolean targetPage = map.isMyAccountPageExist();
		Assert.assertTrue(targetPage);	
		}
		catch(Exception e) {
			Assert.fail();
		}
		logger.info("**** Finished Tc_002_LogInTest");
	}

}
