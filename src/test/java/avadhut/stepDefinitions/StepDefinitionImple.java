package avadhut.stepDefinitions;

import java.io.IOException;

import org.testng.Assert;

import avadhut.TestComponents.BaseTest;
import avadhut.pageobjects.CartPage;
import avadhut.pageobjects.CheckoutPage;
import avadhut.pageobjects.ConfirmationPage;
import avadhut.pageobjects.LandingPage;
import avadhut.pageobjects.ProductCatalogue;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImple extends BaseTest {

	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public ConfirmationPage confirmationPage;

	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException {
		landingPage = launchApplication();
	}

	@Given("^Logged in with username (.+) and password (.+)$")
	public void logged_in_with_username_and_password(String username, String password) {
		productCatalogue = landingPage.loginApplication(username, password);
	}

	@When("^I add the product (.+) to Cart$")
	public void I_add_the_product_to_Cart(String productName) throws InterruptedException {
		productCatalogue.getProductsList();
		productCatalogue.addProductToCart(productName);
	}

	@And("^Checkout (.+) and submit the order$")
	public void Checkout_submit_the_order(String productName) {
		CartPage cartPage = productCatalogue.goToCartPage();

		Boolean match = cartPage.VerifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.GoToCheckoutPage();
		checkoutPage.selectCountry("India");
		confirmationPage = checkoutPage.submitOrder();
	}

	@Then("{string} message is  displayed on ConfirmationPage")
	public void message_displayed_ConfirmationPage(String string) {
		String confirmMessage = confirmationPage.getConfirmMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
		driver.close();
	}
	
	@Then("{string} message is displayed")
	public void message_displayed(String string) {
		Assert.assertEquals(string, landingPage.getErrorMessage()); 
		driver.close();
	}

}
