package avadhut.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import avadhut.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent{
	
	WebDriver driver;
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
	}
	
	@FindBy(css=".cartSection h3")
	List <WebElement> cartProducts;
	
	@FindBy(css=".totalRow button")
	WebElement CheckoutEle;
	
	public Boolean VerifyProductDisplay(String productName) {
		Boolean match = cartProducts.stream().anyMatch(cartProduct-> cartProduct.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public CheckoutPage GoToCheckoutPage() {
		CheckoutEle.click();
		return new CheckoutPage(driver);
	}
}
