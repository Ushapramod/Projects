Feature: Product Search and Filtering

  Scenario: Search products by title
    Given I send a request to search for products containing the keyword "laptop"
    Then I should receive a list of products that match the keyword "laptop"

  Scenario: Filter products by category
    Given I send a request to fetch products in the "jewelery" category
    Then I should receive products only from the "jewelery" category

  Scenario: Filter products by price range
    Given I send a request to filter products with price between 10 and 50
    Then I should receive products within the specified price range

  Scenario: Sort products by rating in ascending order
    Given I send a request to fetch products sorted by rating in ascending order
    Then I should receive products sorted by rating from lowest to highest
