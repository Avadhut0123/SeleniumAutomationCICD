package avadhut.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import avadhut.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent{

	WebDriver driver;
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	//WebElement userEmail = driver.findElement(By.id("userEmail"));
	// Page Factory
	@FindBy(id="userEmail")
	WebElement userEmail;

	@FindBy(id="userPassword")
	WebElement userPassword;
	
	@FindBy(id="login")
	WebElement submit; 
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
	
	public ProductCatalogue loginApplication(String email,String Password) {
		userEmail.sendKeys(email);
		userPassword.sendKeys(Password);
		submit.click();
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		return productCatalogue;
	}
	
	public String getErrorMessage() {
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}
	
}
