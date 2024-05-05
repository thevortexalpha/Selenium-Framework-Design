package thevortexalpha.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import thevortexalpha.AbstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent{
	
	WebDriver driver;
	
	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
		
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	@FindBy(css="[class*='la-ball-scale-multiple']")
	WebElement spinner;
	
	By productsBy = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toaster = By.cssSelector("#toast-container");
	
	public List<WebElement> getProductList() {
		waitForElementToAppear(productsBy);
		return products;
	}
	
	public WebElement getProductByName(String productname) {
		WebElement prod = getProductList().stream()
		.filter(product->product.findElement(By.cssSelector("b")).getText().equals(productname))
		.findFirst()
		.orElse(null);
		return prod;
	}
	
	public void addProductToCart(String productname) throws InterruptedException {
		WebElement prod = getProductByName(productname);
		prod.findElement(addToCart).click();
//		waitForElementToAppear(toaster);
//		waitForElementToDisappear(spinner);
		Thread.sleep(2000);
	}

}
