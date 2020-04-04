Feature: Amazon Login 
@AmazonLogin 
Scenario: Amazon Login 
	Given User Initializes TC for "firefox"
	And user Open "https://Amazon.com" website 
	When Logins to Amazon website 
	Then Search for "Bumblebee"
	And End TC