package SDK.rifffish.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import SDK.rifffish.Product;
import SDK.rifffish.Rifffish;


public class ProductEndpointTest {
	private Rifffish r = null;
	private Product p = null;
	
	@Before
	public void setUp() throws Exception {
		// Local dev testing, API Key will need to be regenerated
		r = new Rifffish("rsh_Wv9Q58YtKQBh7v7C0fzTvQtt", "http://localhost:3000/api"); //generate a ton of junk data!
		p = new Product(1, "Mr. Pibb", 100, 5, 30);
		
		// Assume that there is a machine 1.
	}
	
	@Test
	public void testCreateProductAddedSuccessfully() {
		Product responseProduct = r.createProduct(p);
		
		assertNotNull(responseProduct.getId());
		assertEquals(p.getMachineId(), responseProduct.getMachineId());
		assertEquals(p.getName(), responseProduct.getName());
		assertEquals(p.getPrice(), responseProduct.getPrice());
		assertEquals(p.getCurrentStockLevel(), responseProduct.getCurrentStockLevel()); 
		assertEquals(p.getMaxStockLevel(), responseProduct.getMaxStockLevel()); 
	}

	@Test
	public void testGetProductSuccessfully() {
		Product product = r.createProduct(p);
		Product responseProduct = r.getProduct(product.getId());
		
		assertNotEquals(-1, responseProduct.getId());
		assertEquals(product.getMachineId(), responseProduct.getMachineId());
		assertEquals(product.getName(), responseProduct.getName());
		assertEquals(product.getPrice(), responseProduct.getPrice());
		assertEquals(product.getCurrentStockLevel(), responseProduct.getCurrentStockLevel()); 
		assertEquals(product.getMaxStockLevel(), responseProduct.getMaxStockLevel()); 
	}
	
	@Test
	public void testUpdateProductSuccessfully() {
		Product product = r.createProduct(p);
		
		product.setName("Dr. Bipp (a real doctor)");
		
		Product responseProduct = r.updateProduct(product);
		
		assertEquals(product.getId(), responseProduct.getId());
		assertEquals(product.getMachineId(), responseProduct.getMachineId());
		assertEquals(product.getName(), responseProduct.getName());
		assertEquals(product.getPrice(), responseProduct.getPrice());
		assertEquals(product.getCurrentStockLevel(), responseProduct.getCurrentStockLevel()); 
		assertEquals(product.getMaxStockLevel(), responseProduct.getMaxStockLevel()); 
	}
	
	@Test
	public void testDeleteProductSuccessfully() {
		Product product = r.createProduct(p);
				
		assertEquals(null, r.deleteProduct(product.getId()));
	}

 
}
