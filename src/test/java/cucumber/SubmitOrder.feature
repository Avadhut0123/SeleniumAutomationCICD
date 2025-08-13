@tag
Feature: Purchase the order from Ecommerce Website

	Background:
	Given I landed on Ecommerce Page

	@Regression
	Scenario Outline: Positive test of Submit Order
	Given Logged in with username <name> and password <password>
	When I add the product <productName> to Cart
	And Checkout <productName> and submit the order
	Then "THANKYOU FOR THE ORDER." message is  displayed on ConfirmationPage
	
	Examples:
		| name                      | password  | productName     | 
		| testframe@sharklasers.com | Pass@123  | ZARA COAT 3     |
		| king1@gmail.com			| Pass@123  | ADIDAS ORIGINAL |