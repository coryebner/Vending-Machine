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
		// This passes now (it was using a local database which I didn't have on my machine,
		//  nor did the build server) - but I'm pretty sure our tests are actually adding
		//  things to the real DB which isn't super cool
		// Could use a testing DB, maybe discuss with Thomas?
		r = new Rifffish("rsh_3wL4MyhWW4z3kfjoYfyN0gtt", "http://rifffish.com/api"); //generate a ton of junk data!
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
