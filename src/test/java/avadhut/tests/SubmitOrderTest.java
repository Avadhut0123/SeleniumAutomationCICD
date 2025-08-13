package avadhut.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import avadhut.TestComponents.BaseTest;
import avadhut.pageobjects.CartPage;
import avadhut.pageobjects.CheckoutPage;
import avadhut.pageobjects.ConfirmationPage;
import avadhut.pageobjects.OrderPage;
import avadhut.pageobjects.ProductCatalogue;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class SubmitOrderTest extends BaseTest {

	// String productName = "ZARA COAT 3";

	@Test(dataProvider = "getData", groups = { "Purchase" })
	public void SubmitOrder(HashMap<String, String> input) throws IOException, InterruptedException {

		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("passWord"));

		productCatalogue.getProductsList();
		productCatalogue.addProductToCart(input.get("productName"));
		CartPage cartPage = productCatalogue.goToCartPage();

		Boolean match = cartPage.VerifyProductDisplay(input.get("productName"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.GoToCheckoutPage();
		checkoutPage.selectCountry("India");
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();

		String confirmMessage = confirmationPage.getConfirmMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		String OrderNumber = confirmationPage.OrderNumber();
		System.out.println("New Order Number is " + OrderNumber);
	}

	@Test(dependsOnMethods = { "SubmitOrder" }, dataProvider = "getData")
	public void OrderHistoryTest(HashMap<String, String> input) throws InterruptedException {
		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("passWord"));
		OrderPage ordersPage = productCatalogue.goToOrdersPage();
		Assert.assertTrue(ordersPage.VerifyOrderDisplay(input.get("productName")));
	}
	
	
	
	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String, String>> data = getJsonDataToHashMap(System.getProperty("user.dir") + "\\src\\test\\java\\avadhut\\data\\PurchaseOrder.json");
		return new Object[][] { { data.get(0) }, { data.get(1) } };
	}
	
//	HashMap<String, String> cred = new HashMap<String, String>();
//	cred.put("email", "testframe@sharklasers.com");
//	cred.put("passWord", "Pass@123");
//	cred.put("productName", "ZARA COAT 3");
//
//	HashMap<String, String> cred1 = new HashMap<String, String>();
//	cred1.put("email", "king1@gmail.com");
//	cred1.put("passWord", "Pass@123");
//	cred1.put("productName", "ADIDAS ORIGINAL");

}
