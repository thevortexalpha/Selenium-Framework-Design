package thevortexalpha.pageobjects;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import thevortexalpha.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent{

	WebDriver driver;
	
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="[placeholder='Select Country']")
	WebElement country;
	
	@FindBy(css=".list-group-item span")
	List<WebElement> countryResults;
	
	@FindBy(css=".action__submit ")
	WebElement submit;
	
	public void selectingCountry(String countryName) {
		country.sendKeys(countryName);
		List<WebElement> results = countryResults;
		WebElement countryOption = results.stream()
				.filter(country->country.getText().trim().equals(countryName))
				.findFirst()
				.orElse(null);
		countryOption.click();
	}
	
	public ConfirmationPage submitOrder() {
		submit.click();
		return new ConfirmationPage(driver);
	}

}
