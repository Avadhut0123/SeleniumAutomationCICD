@tag
Feature: Error Validation


	@ErrorValidation
	Scenario Outline: Positive test of Submit Order
	Given I landed on Ecommerce Page
	When Logged in with username <name> and password <password>
	Then "Incorrect email or password." message is displayed
	
	Examples:
		| name                      | password |
		| testframe@sharklasers.com | Pass123  |
		