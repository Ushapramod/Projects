package resources;

public enum APIResources {
    Getallcarts("https://fakestoreapi.com/carts"),
	GetASinglecart("https://fakestoreapi.com/carts/5"),
	LimitResults("https://fakestoreapi.com/carts?limit=5"),
	SortResults("https://fakestoreapi.com/carts?startdate=2019-12-10&enddate=2020-10-10"),
	Getusercarts("https://fakestoreapi.com/carts/user/2"),
	Addanewproduct("https://fakestoreapi.com/carts"),
	Updateaproduct("https://fakestoreapi.com/carts/7"),
	DeleteACart("https://fakestoreapi.com/carts/6"),
	GetCartsInDateRange("https://fakestoreapi.com/carts?startdate=2019-12-10&enddate=2020-10-10");

    private String resource;

    APIResources(String resource) {
        this.resource = resource;
    }

    public String getResource() {
        return resource;
    }
}
