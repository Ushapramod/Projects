Feature: Product Management

  Scenario: Get all products
    Given I send a request to fetch all products
    Then I should receive a list of products in the response

  Scenario: Get a single product
    Given I send a request to fetch product with ID 1
    Then I should receive the details of the product with ID 1

  Scenario: Limit results when fetching products
    Given I send a request to fetch 5 products
    Then I should receive 5 products in the response

  Scenario: Sort products in descending order
    Given I send a request to fetch all products sorted by price in descending order
    Then I should receive products sorted by price in descending order

  Scenario: Get products in a specific category
    Given I send a request to fetch products in the "electronics" category
    Then I should receive products only from the "electronics" category

  Scenario: Add a new product
    Given I send a request to add a new product with title "Test Product", price 13.5, description "Sample", and category "electronics"
    Then the new product should be added to the system and return a new product ID

  Scenario: Update an existing product
    Given I send a request to update product with ID 7 with new title "Updated Product" and price 20.0
    Then the product with ID 7 should be updated with new details

  Scenario: Partially update an existing product
    Given I send a request to update product with ID 7 with price 20.0 only
    Then the product with ID 7 should be updated with the new price

  Scenario: Delete a product
    Given I send a request to delete product with ID 6
    Then the product with ID 6 should be deleted (response will indicate successful deletion)
