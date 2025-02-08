@tag
Feature: Swag Labs Login

  @sc1
  Scenario: I want to sucessfully login to Swag Labs
    Given Using username and password
    When I login using the username "standard_user" and  password "secret_sauce"
    Then I will be nagivated to home page
