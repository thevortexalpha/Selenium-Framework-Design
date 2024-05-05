package thevortexalpha.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;


import thevortexalpha.TestComponents.BaseTest;
import thevortexalpha.TestComponents.Retry;
import thevortexalpha.pageobjects.CartPage;
import thevortexalpha.pageobjects.ProductCatalogue;

public class ErrorValidation extends BaseTest{

	@Test(groups= {"ErrorHandling"}, retryAnalyzer=Retry.class)
	public void loginErrorValidation() throws IOException {
		
		ProductCatalogue productCatalogue = landingPage.loginApplication("vsvatheking@gmail.com", "Rockvibin123$");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
	}
	
	@Test
	public void productErrorValidation() throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		String productName = "ADIDAS ORIGINAL";
		
		ProductCatalogue productCatalogue = landingPage.loginApplication("vsvatheking@gmail.com", "Rockvibin123");
		productCatalogue.addProductToCart(productName);
		
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.verifyProductDisplay("ADIDAS ORIGINAL 3");
		Assert.assertFalse(match);
	}

}
