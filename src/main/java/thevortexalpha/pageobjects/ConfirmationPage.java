package thevortexalpha.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import thevortexalpha.AbstractComponents.AbstractComponent;

public class ConfirmationPage extends AbstractComponent{

	WebDriver driver;
	
	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="label.ng-star-inserted")
	WebElement orderIdElement;
	
	@FindBy(css=".hero-primary")
	WebElement confirmationMessage;
	
	public String getOrderId() {
		return orderIdElement.getText().split(" | ")[1];
	}
	
	public String getConfirmationMessage() {
		return confirmationMessage.getText();
	}

}
