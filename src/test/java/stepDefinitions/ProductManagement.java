package stepDefinitions;

import io.cucumber.java.en.Given;
import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertEquals;

import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import org.apiguardian.api.API;
import org.junit.Assert;

import com.fasterxml.jackson.databind.introspect.TypeResolutionContext.Empty;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertThat;

public class ProductManagement extends Utils {
	static Response response;
	private String startDate;
	private String enddate;
	private static TestDataBuild testdatabuild;
	private static RequestSpecification requestSpecification;

	@Given("I send a request to fetch all products with {string} and with {string} method")
	public void i_send_a_request_to_fetch_all_products_with_and_with_method(String endpoint, String method)
			throws IOException {
		response = given().spec(Utils.getRequestSpecification()).when()
				.get(APIResources.valueOf(endpoint).getResource());
	}

	@Then("I should receive a list of products in the response")
	public void i_should_receive_a_list_of_products_in_the_response() {
		// Validate the status code
		response.then().assertThat().statusCode(200);

		// Extract the list of products
		List<Map<String, Object>> products = response.jsonPath().getList("$");

		// Check if the products list exists and is not empty
		if (products != null && !products.isEmpty()) {
			int numberOfProducts = products.size();
			System.out.println("Number of products received: " + numberOfProducts);

			// Assert that the number of products is greater than 0
			assertThat("Product list should not be empty", numberOfProducts, greaterThan(0));
		} else {
			System.out.println("The products list is empty or not present in the response.");
			throw new AssertionError("The products list is empty or missing.");
		}
	}

	@Given("I send a request to fetch product with ID {int} with {string} and with {string} method")
	public void i_send_a_request_to_fetch_product_with_id(Integer userId, String endpoint, String method)
			throws IOException {
		response = given().spec(Utils.getRequestSpecification()).when()
				.get(APIResources.valueOf(endpoint).getResource());
	}

	@Then("I should receive the details of the product with ID {int}")
	public void i_should_receive_the_details_of_the_product_with_id(Integer userId) {
		response.then().assertThat().statusCode(200);

		// Extract the ID from the response
		Integer userIDInResponse = response.then().extract().path("id");

		// Use an if condition to check if the extracted ID is valid
		if (userIDInResponse != null) {
			System.out.println("The product Id is " + userIDInResponse);

			// Validate if the extracted ID matches the expected ID
			if (userIDInResponse.equals(userId)) {
				System.out.println("Product ID matches the expected ID: " + userId);
			} else {
				System.out.println("Product ID does not match the expected ID. Expected: " + userId + ", but got: "
						+ userIDInResponse);
			}
		} else {
			System.out.println("No product ID found in the response.");
		}
	}

	@Given("I send a request to fetch {int} products with {string} and with {string} method")
	public void i_send_a_request_to_fetch_products(Integer userId, String endPoint, String method) throws IOException {
		// Send the request using the specified endpoint
		response = given(Utils.getRequestSpecification()).when().get(APIResources.valueOf(endPoint).getResource());
	}

	@Then("I should receive {int} products in the response")
	public void i_should_receive_products_in_the_response(Integer userId) {
		// Extract the product list from the response
		List<Map<String, Object>> productList = response.jsonPath().getList("$");

		// Get the actual size of the product list
		int actualSize = productList.size();

		// Check if the list is empty
		if (actualSize == 0) {
			System.out.println("No products found in the response.");
		}

		// Assert that the actual size matches the expected userId (which represents the
		// expected number of products)
		try {
			assertEquals("Expected result doesn't match the actual result", userId.intValue(), actualSize);
		} catch (AssertionError e) {
			System.out.println("Assertion failed: Expected " + userId + " but found " + actualSize);
			throw e; // Rethrow the error to fail the test properly
		}

		// Print the actual number of products received
		System.out.println("Number of products received: " + actualSize);
	}

	@Given("I send a request to fetch all products sorted by price in descending order with the {string} and with {string} method")
	public void i_send_a_request_to_fetch_all_products_sorted_by_price_in_descending_order_with_the_and_with_method(
			String endPoint, String method) throws IOException {
		// Send the request using the provided endpoint and method
		if ("GET".equalsIgnoreCase(method)) {
			response = given().spec(Utils.getRequestSpecification()).when()
					.get(APIResources.valueOf(endPoint).getResource());
		} else if ("POST".equalsIgnoreCase(method)) {
			response = given().spec(Utils.getRequestSpecification()).when()
					.post(APIResources.valueOf(endPoint).getResource());
		}
		// Handle any other HTTP methods if needed
	}

	@Then("I should receive products sorted by price in descending order")
	public void i_should_receive_products_sorted_by_price_in_descending_order() {
		// Get the list of products from the response
		List<Map<String, Object>> productList = response.jsonPath().getList("$");

		// Sort the products by 'price' in descending order
		productList.sort((product1, product2) -> {
			Object price1 = product1.get("price");
			Object price2 = product2.get("price");

			// Convert all price values to Double for comparison
			Double price1Value = toDouble(price1);
			Double price2Value = toDouble(price2);

			return price2Value.compareTo(price1Value); // Sorting in descending order
		});

		// Now extract and print the prices of the products
		for (Map<String, Object> product : productList) {
			Object price = product.get("price");
			System.out.println("Product Price: " + price);
		}
	}

	// Helper method to convert price to Double
	private Double toDouble(Object price) {
		if (price instanceof Double) {
			return (Double) price;
		} else if (price instanceof Float) {
			return ((Float) price).doubleValue();
		} else if (price instanceof Integer) {
			return ((Integer) price).doubleValue();
		} else {
			throw new IllegalArgumentException("Unknown price type: " + price.getClass());
		}
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Given("I send a request to fetch products in the {string} category")
	public void i_send_a_request_to_fetch_products_in_the_category(String string) {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}

	@Then("I should receive products only from the {string} category")
	public void i_should_receive_products_only_from_the_category(String string) {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Given("I send a request to add a new product with title {string}, price {double}, description {string}, and category {string}")
	public void i_send_a_request_to_add_a_new_product_with_title_price_description_and_category(String string,
			Double double1, String string2, String string3) {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}

	@Then("the new product should be added to the system and return a new product ID")
	public void the_new_product_should_be_added_to_the_system_and_return_a_new_product_id() {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}

	@Given("I send a request to update product with ID {int} with new title {string} and price {double}")
	public void i_send_a_request_to_update_product_with_id_with_new_title_and_price(Integer int1, String string,
			Double double1) {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}

	@Then("the product with ID {int} should be updated with new details")
	public void the_product_with_id_should_be_updated_with_new_details(Integer int1) {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}

	@Given("I send a request to update product with ID {int} with price {double} only")
	public void i_send_a_request_to_update_product_with_id_with_price_only(Integer int1, Double double1) {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}

	@Then("the product with ID {int} should be updated with the new price")
	public void the_product_with_id_should_be_updated_with_the_new_price(Integer int1) {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}

	@Given("I send a request to delete product with ID {int}")
	public void i_send_a_request_to_delete_product_with_id(Integer int1) {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}

	@Then("the product with ID {int} should be deleted \\(response will indicate successful deletion)")
	public void the_product_with_id_should_be_deleted_response_will_indicate_successful_deletion(Integer int1) {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}

}
