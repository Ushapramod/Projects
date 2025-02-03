Feature: Product Management
@GetAllProducts
  Scenario: Get all products
    Given I send a request to fetch all products with "GetAllProducts" and with "GET" method
    Then I should receive a list of products in the response

@GetaSingleProduct
  Scenario: Get a single product
    Given I send a request to fetch product with ID 1 with "GetaSingleProduct" and with "GET" method
    Then I should receive the details of the product with ID 1

@Limitresults
  Scenario: Limit results when fetching products
    Given I send a request to fetch 5 products with "Limitresults" and with "GET" method
    Then I should receive 5 products in the response

@SortResults1
  Scenario: Sort products in descending order
    Given I send a request to fetch all products sorted by price in descending order with the "SortResults1" and with "GET" method
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
