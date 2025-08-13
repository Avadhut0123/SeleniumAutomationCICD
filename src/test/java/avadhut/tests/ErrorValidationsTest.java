package avadhut.tests;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import avadhut.TestComponents.BaseTest;
import avadhut.TestComponents.Retry;
import avadhut.pageobjects.CartPage;
import avadhut.pageobjects.CheckoutPage;
import avadhut.pageobjects.ConfirmationPage;
import avadhut.pageobjects.ProductCatalogue;

import java.io.IOException;
import java.util.List;

public class ErrorValidationsTest extends BaseTest{

	@Test(groups= {"ErrorHandling"},retryAnalyzer=Retry.class)
	public void LoginErrorValidation() throws IOException, InterruptedException {
		
		landingPage.loginApplication("testframe@sharklasers.com", "Pass123");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage()); 
	}
	
	@Test
	public void ProductErrorValidation() throws IOException, InterruptedException {
		
		String productName = "ZARA COAT 3";
		ProductCatalogue productCatalogue = landingPage.loginApplication("qaking@sharklasers.com", "Pass@123");
		
		List<WebElement> products = productCatalogue.getProductsList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();
		
		Boolean match = cartPage.VerifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);
	}

}
