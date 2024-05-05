package thevortexalpha.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import thevortexalpha.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent{
	
	WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement userPassowrd;
	
	@FindBy(id="login")
	WebElement loginButton;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public ProductCatalogue loginApplication(String email, String password) {
		userEmail.sendKeys(email);
		userPassowrd.sendKeys(password);
		loginButton.click();
		return new ProductCatalogue(driver);
	}
	
	public String getErrorMessage() {
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}

}
