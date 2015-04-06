package SDK.rifffish.endpoints;


import retrofit.http.*;
import SDK.rifffish.Product;
import SDK.rifffish.Error;


public interface ProductsService {	
	
	
	// REST (is history)
	
	// Get 
	@GET("/products/{product_id}")
	Product getProduct(@Path("product_id") Integer product_id);
	
	// Create
	@POST("/machines/{machine_id}/products")
	Product createProduct(@Path("machine_id") Integer machine_id, @Body Product product);
	
	// Update
	@PUT("/products/{product_id}")
	Product updateProduct(@Path("product_id") Integer product_id, @Body Product product);
	
	// Delete
	@DELETE("/products/{product_id}")
	Error deleteProduct(@Path("product_id") Integer product_id);
}
