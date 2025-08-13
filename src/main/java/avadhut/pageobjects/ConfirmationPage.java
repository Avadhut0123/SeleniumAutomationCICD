package avadhut.pageobjects;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import avadhut.AbstractComponents.AbstractComponent;

public class ConfirmationPage extends AbstractComponent {
	WebDriver driver;
	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".hero-primary")
	WebElement confirmMessage;
	
	@FindBy(css="label[class='ng-star-inserted']")
	WebElement OrderNumber;
	
	
	public String getConfirmMessage() {
		return confirmMessage.getText();
	}
	
	public String OrderNumber() {
		String OrderID = OrderNumber.getText();
		String OrderId = OrderID.replaceAll("[\\s|]", "");
		return OrderId;
	}
	
}
