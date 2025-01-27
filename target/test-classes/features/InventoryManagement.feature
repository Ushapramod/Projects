Feature: Inventory Management

  Scenario: Update inventory after placing an order
    Given I send a request to place an order for product ID 2 with quantity 3
    Then the inventory for product ID 2 should decrease by 3

  Scenario: Check product availability
    Given I send a request to check the availability of product with ID 5
    Then I should receive the product availability status as "In Stock" or "Out of Stock"

  Scenario: Add stock for a product
    Given I send a request to add 10 units to the inventory of product ID 3
    Then the inventory for product ID 3 should increase by 10
