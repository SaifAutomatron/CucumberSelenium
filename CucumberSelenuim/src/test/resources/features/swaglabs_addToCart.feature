@tag
Feature: Swag Labs Add to cart

  @sc2 @login @finish
  Scenario: I want to test add to cart feature
    Given I am logged in to Swag Labs
    When I want to add item to the cart
      | item                  |
      | Sauce Labs Bike Light |
    Then I am sucessfully able to add Item to the cart

  @sc3 @login @finish
  Scenario Outline: Add different items to cart
    Given I am logged in to Swag Labs
    When I want to add '<item>' to the cart
    Then I am sucessfully able to add '<item>' to the cart

    Examples: 
      | item                     |
      | Sauce Labs Backpack      |
      | Sauce Labs Bolt T-Shirt  |
      | Sauce Labs Fleece Jacket |
