Feature: Testing Login Page
  Background:
    Given Set up driver

  Scenario Outline: Testing of positive filling login page
    When Opening Login Page
    And set email "<email>"
    And set password "<password>"
    And click on sign in button
    Then check username "<username>"
    Then quit driver
    Examples:
      |email|password|username|
      |aliceperkins@mail.com|96ALiceperkINS71|Alice Perkins|



  Scenario Outline: Testing of negative filling of password field in login page
    When Opening Login Page
    And set email "<email>"
    And set password "<password>"
    And click on sign in button
    Then check error message for email "<errorMessage>"
    Then check error message for password "<errorMessage>"
    Then quit driver
    Examples:
      |email|password|errorMessage|
      |aliceperkins@mail.com|12345678|Email or password is not valid|

  Scenario Outline: Testing of negative filling of email field in login page
    When Opening Login Page
    And set email "<email>"
    And set password "<password>"
    And click on sign in button
    Then check error message for email "<errorMessage>"
    Then check error message for password "<errorMessage>"
    Then quit driver
    Examples:
      |email|password|errorMessage|
      |mymail@mail.com|96ALiceperkINS71|Email or password is not valid|