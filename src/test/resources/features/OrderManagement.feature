Feature: Order Management

  Scenario: Place a new order
    Given I send a request to place an order for products with IDs [1, 2, 5] for user ID 3
    Then the order should be successfully placed and return an order ID

  Scenario: View an order
    Given I send a request to view the details of the order with ID 10
    Then I should receive the order details with order ID 10

  Scenario: Update an order
    Given I send a request to update order with ID 10, adding product ID 3
    Then the order with ID 10 should be updated to include the new product

  Scenario: Cancel an order
    Given I send a request to cancel order with ID 10
    Then the order with ID 10 should be successfully canceled
