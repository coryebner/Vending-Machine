//package business.selection_delivery.tests;
//
//import static org.junit.Assert.*;
//
//import org.junit.Before;
//import org.junit.Test;
//
//import business.notifications.DisplayController;
//import business.funds.Funds;
//import business.selection_delivery.CodeSelectionController;
//import business.selection_delivery.InventoryController;
//import hardware.racks.ProductRack;
//import hardware.racks.CoinRack;
//import hardware.ui.PushButtonCodeInterpreter;
//import business.funds.PaymentMethods;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Locale;
//
//public class CodeSelectionControllerTest {
//
//	public CodeSelectionController test;
//	public InventoryController inv;
//	public DisplayController disp = new DisplayController();
//	public Funds fun;
//	int off = 0;
//	List<PaymentMethods> payments = new ArrayList<PaymentMethods>();
//	
//	public ProductRack[] rack = new ProductRack[3];
//	public int rackcount;
//	public CoinRack[] crack = new CoinRack[3];
//	public int[] cdenom = new int[3];
//	public int[] cquant = new int[3];
//	String [] names = new String[3];
//	int [] costs = new int [3];
//	int [] quantity = new int [3];
//	int [] productID = new int[3];
//	
//	public PushButtonCodeInterpreter dud;
//
//	@Before
//	public void setUp() throws Exception {
//		rack[0] = new ProductRack(10);
//		rack[1] = new ProductRack(10);
//		rack[2] = new ProductRack(10);
//		
//		crack[0] = new CoinRack(10);
//		crack[1] = new CoinRack(10);
//		crack[2] = new CoinRack(10);
//		cdenom[0] = 5;
//		cdenom[1] = 10;
//		cdenom[2] = 15;
//		cquant[0] = 10;
//		cquant[1] = 10;
//		cquant[2] = 10;
//		payments.add(PaymentMethods.COINS);
//		
//		rackcount = 3;
//		names[0] = "Product1";
//		names[1] = "Product2";
//		names[2] = "Product3";
//		costs[0] = 1;
//		costs[1] = 2;
//		costs[2] = 0; //yay free swag
//		quantity[0] = 4;
//		quantity [1] = 3;
//		quantity [2] = 7;
//		productID[0] = 7331;
//		productID[1] = 42;
//		productID[2] = 666;
//		
//		inv = new InventoryController(rack,rackcount,names,costs,quantity,productID);
//		fun = new Funds(Locale.US, true, crack, cdenom, cquant, payments, inv);
//		test = new CodeSelectionController(inv, disp, fun, off);
//	}
//
//	@Test
//	public void testCodeSelectionControllerConstructor() {
//		assertNotNull(test);
//	}
//	
//	@Test
//	public void testCodeEntered() {
//		test.codeEntered("A1", dud);
//		//not really sure how to test this
//	}
//	
//	@Test
//	public void testProductIndexValidLowerBound() {
//		assertEquals(0, test.productIndex("A0", off));
//	}
//	
//	@Test 
//	public void testProductIndexValidUpperBound() {
//		assertEquals(2, test.productIndex("A2", off));
//	}
//	
//	@Test
//	public void testProductIndexFirstInvalidLowerBound() {
//		assertEquals(-1, test.productIndex("@0", off));
//	}
//	
//	@Test
//	public void testProductIndexFirstInvalidUpperBound() {
//		assertEquals(-1, test.productIndex("[0", off));
//	}
//
//	@Test
//	public void testProductIndexSecondInvalidLowerBound() {
//		assertEquals(-1, test.productIndex("A/", off));
//	}
//	
//	@Test
//	public void testProductIndexSecondInvalidUpperBound() {
//		assertEquals(-1, test.productIndex("A:", off));
//	}
//	
//	@Test
//	public void testProductIndexBothInvalid() {
//		assertEquals(-1, test.productIndex("$}", off));
//	}
//	
//	@Test
//	public void testProductIndexInvalidArg() {
//		assertEquals(-1, test.productIndex("A11", off));
//	}
//}
