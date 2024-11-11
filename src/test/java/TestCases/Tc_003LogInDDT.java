 package TestCases;

import org.testng.annotations.Test;

import PageObjects.HomePage;
import PageObjects.LogInPage;
import PageObjects.MyAccountPage;
import TestBase.baseClass;
import junit.framework.Assert;
import utilities.DataProviders;

public class Tc_003LogInDDT extends baseClass{
	
	
	@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class, groups="DataDriven")// getting dataprovider from different package
	public void verify_LogInDDT(String email, String password, String exp) {
		
		logger.info("**** Starting Tc_003LogIn data verify****");
	try {
		
	// HomePage
 	
	HomePage hp = new HomePage(driver);
	hp.clickMyAccount();
	hp.clicklogIn();
	
	//LogInpage
	LogInPage lp = new LogInPage(driver);
	lp.setEmail(email);
	lp.setPasword(password);
	lp.clickLogIn();
	
	//MyAccountPage
	
	MyAccountPage map= new MyAccountPage(driver);
	boolean targetPage = map.isMyAccountPageExist();
	
	/*
	 data is valid -- login successful -- test pass --logout
	   				  login unsuccessful -- test fail
	   				  
	   				  
	  data is invalid -- login successful -- test fail -- logout  
	  				  	 login unsuccessful -- test pass
	 */
	
	if(exp.equalsIgnoreCase("pass")) {
		if(targetPage == true) {
			map.clickLogout();
			Assert.assertTrue(true);
		}
		else {
			Assert.assertTrue(false); 
		}
	}
		if(exp.equalsIgnoreCase("fail")) {
			if(targetPage==false) {
				map.clickLogout();
				Assert.assertTrue(false);				
			}
			else {
				Assert.assertTrue(true);
			}			
		}	
	}
	catch(Exception e) {
		Assert.fail();
	}
	logger.info("**** Starting Tc_003LogIn data verification successfully****");
	}
}
