package thevortexalpha.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import thevortexalpha.TestComponents.BaseTest;
import thevortexalpha.pageobjects.CartPage;
import thevortexalpha.pageobjects.CheckoutPage;
import thevortexalpha.pageobjects.ConfirmationPage;
import thevortexalpha.pageobjects.OrderPage;
import thevortexalpha.pageobjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest{
	String productName = "ADIDAS ORIGINAL";
	@Test(dataProvider="getData", groups = {"Purchase"})
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		
		String countryName = "India";
		
		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
		productCatalogue.addProductToCart(input.get("product"));
		
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.verifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectingCountry(countryName);
		
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		String orderId = confirmationPage.getOrderId();
		System.out.println("Your order id is "+orderId);
		String confirmationMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmationMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}
	
	@Test(dependsOnMethods = {"submitOrder"})
	public void OrderHistoryTest() {
		ProductCatalogue productCatalogue = landingPage.loginApplication("vsvatheking@gmail.com", "Rockvibin123");
		OrderPage orderPage = productCatalogue.goToOrdersPage();
		Assert.assertTrue(orderPage.verifyOrderDisplay(productName));
	}
	
	@DataProvider
	public Object[][] getData() throws IOException {		
		List<HashMap<String, String>> data = getJsonDataToMap(
				System.getProperty("user.dir")+"/src/test//java/thevortexalpha/data/PurchaseOrder.json"
			);
		return new Object[][] {
			{data.get(0)}, 
			{data.get(1)}
		};
	}
	
}
