package thevortexalpha.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import thevortexalpha.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent{
	
	WebDriver driver;
	
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
		
	@FindBy(css=".cartSection h3")
	List<WebElement> cartProducts;
	
	@FindBy(css=".totalRow button")
	WebElement checkoutButton;
	
	public Boolean verifyProductDisplay(String productName) {
		Boolean match = cartProducts.stream()
				.anyMatch(product->product.getText()
				.equalsIgnoreCase(productName));
		return match;
	}
	
	public CheckoutPage goToCheckout() {
		checkoutButton.click();
		return new CheckoutPage(driver);
	}
}
