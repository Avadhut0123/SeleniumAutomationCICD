package avadhut.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import avadhut.AbstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent {

	WebDriver driver;
	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Page Factory
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
//	List <WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
	@FindBy(css=".cartSection h3")
	List <WebElement> cartProducts;

	
	By productBy = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastMessage = By.cssSelector("#toast-container");
	
	
	public List<WebElement> getProductsList() {
		waitForElementToAppear(productBy);
		return products;
	}
	
	public WebElement getProductName(String productName) {
		WebElement item = getProductsList().stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		return item;
	}
	
	public void addProductToCart(String productName) throws InterruptedException {
		WebElement item = getProductName(productName);
		item.findElement(addToCart).click();
		// Page Factory cannot be added in WebElement.FindElement
		waitForElementToAppear(toastMessage);
		waitForElementToDisappear(spinner);
		
	}
	
	
}
