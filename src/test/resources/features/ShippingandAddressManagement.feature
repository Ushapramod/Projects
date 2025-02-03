Feature: Shipping and Address Management

  Scenario: Add a new shipping address
    Given I send a request to add a new shipping address for user ID 3 with address details
    Then the shipping address should be successfully added for user ID 3

  Scenario: Update shipping address
    Given I send a request to update the shipping address for user ID 3 with new address details
    Then the shipping address for user ID 3 should be updated

  Scenario: Get shipping status for an order
    Given I send a request to get the shipping status for order ID 10
    Then I should receive the shipping status for order ID 10 as "Shipped" or "In Transit"
