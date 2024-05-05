package thevortexalpha.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


public class StandAloneTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// New comments were added
		String productName = "ADIDAS ORIGINAL";
		String countryName = "India";
		System.setProperty(
				"webdriver.chrome.driver", 
				"C:/Users/Vsvat/Documents/Docs/VortexScripts/eclipse-workspace/chromedriver/chromedriver.exe"
		);
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		driver.manage().window().maximize();
		
		driver.get("https://rahulshettyacademy.com/client");
		driver.findElement(By.id("userEmail")).sendKeys("vsvatheking@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Rockvibin123");
		driver.findElement(By.id("login")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		
		WebElement prod = products.stream()
			.filter(product->product.findElement(By.cssSelector("b")).getText().equals(productName))
			.findFirst()
			.orElse(null);
		
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector("[class*='la-ball-scale-multiple']"))));
		
		driver.findElement(By.cssSelector("[routerlink='/dashboard/cart']")).click();
			
		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		
		Boolean match = cartProducts.stream().anyMatch(product->product.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);
		
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		driver.findElement(By.cssSelector("[placeholder='Select Country']")).sendKeys(countryName);
		
		List<WebElement> results = driver.findElements(By.cssSelector(".list-group-item span"));
		
		WebElement countryOption = results.stream()
				.filter(country->country.getText().trim().equals(countryName))
				.findFirst()
				.orElse(null);
	
		countryOption.click();
		
		driver.findElement(By.cssSelector(".action__submit ")).click();
		
		String orderId = driver.findElement(By.cssSelector("label.ng-star-inserted")).getText().split(" | ")[1];
		
		System.out.println("Your order id is "+orderId);
		
	}

}
