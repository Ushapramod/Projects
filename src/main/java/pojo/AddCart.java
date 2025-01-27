package pojo;

import java.util.Date;
import java.util.List;

public class AddCart {
	private int userId;
	private Date date;
	
	private List<productsForAddCart> products;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Object getDate() {
		return date;
	}

	public void setDate(Date date2) {
		this.date = date2;
	}

	public List<productsForAddCart> getProducts() {
		return products;
	}

	public void setProducts(List<productsForAddCart> products) {
		this.products = products;
	}

}
