package resources;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import pojo.AddCart;
import pojo.productsForAddCart;
import pojo.UpdateCart;

public class TestDataBuild {
	public static AddCart addCartPayload(Integer userId, String date, String productsJson)
	{
		AddCart addcart = new AddCart();
		addcart.setUserId(5);
		addcart.setDate(new Date(2020-02-03));
		
		List<productsForAddCart> productsList = new ArrayList<>();
		
		productsForAddCart product1 = new productsForAddCart();
		product1.setProductId(5);
		product1.setQuantity(1);
		productsList.add(product1);
		
		productsForAddCart product2 = new productsForAddCart();
		product1.setProductId(1);
		product1.setQuantity(5);
		productsList.add(product2);
		
	
		addcart.setProducts(productsList);
		return addcart;
		
		
	}
public static UpdateCart UpdateCartPayLoad(Integer userId, Integer productId,Integer quantity)
{
	UpdateCart updatecart = new UpdateCart();
	updatecart.setDate1(new Date(2019-12-10));
	updatecart.setUserId1(3);
	
	
	List<productsForAddCart> productsList = new ArrayList<productsForAddCart>();
	
	productsForAddCart product1 = new productsForAddCart();
	product1.setProductId(1);
	product1.setQuantity(3);
	productsList.add(product1);
	return updatecart;
}
}
