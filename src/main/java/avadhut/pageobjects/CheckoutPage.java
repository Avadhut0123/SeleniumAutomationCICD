package avadhut.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import avadhut.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent {
	
	WebDriver driver;
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	By waitEle = By.cssSelector(".ta-results");
	
	@FindBy(xpath="(//button[contains(@class,'ta-item')])[2]")
	WebElement SelectCountry;
	
	@FindBy(css=".action__submit")
	WebElement SubmitOrder;
	
	@FindBy(css="[placeholder='Select Country']")
	WebElement Country;
	
	
	public void selectCountry(String countryName) {
		Actions a = new Actions(driver);
		a.sendKeys(Country, countryName).build().perform();
		waitForElementToAppear(waitEle);
		SelectCountry.click();
	}
	
	public ConfirmationPage submitOrder() {
		SubmitOrder.click();
		return new ConfirmationPage(driver);
	}

}
