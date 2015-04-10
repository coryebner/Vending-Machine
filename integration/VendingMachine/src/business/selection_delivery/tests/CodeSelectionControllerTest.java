package business.selection_delivery.tests;

import static org.junit.Assert.*;

import org.jmock.Mockery;
import org.jmock.Expectations;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Before;
import org.junit.Test;

import business.funds.FundsController;
import business.funds.TransactionReturnCode;
import business.selection_delivery.CodeSelectionController;
import business.selection_delivery.InventoryController;
import hardware.racks.ProductRack;
//import hardware.racks.CoinRack;
import hardware.ui.DeliveryChute;
import hardware.ui.PushButtonCodeInterpreter;
//import business.funds.PaymentMethods;
import SDK.logger.Logger;
//import SDK.rifffish.Rifffish;
import hardware.acceptors.AbstractProductAcceptor;
import hardware.channels.ProductChannel;
//import hardware.funds.BanknoteReceptacle;
//import hardware.funds.BanknoteSlot;
//import hardware.funds.CoinReceptacle;
//import hardware.funds.CoinSlot;
//import hardware.ui.IndicatorLight;

//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;

public class CodeSelectionControllerTest {
	
	private Mockery context;

	public CodeSelectionController test;
	public InventoryController inv;
	public FundsController fun;
	public int off = 0;
	
	public ProductRack[] rack = new ProductRack[3];
	public int rackcount = 3;
	public String [] names = new String[3];
	public int [] costs = new int [3];
	public int [] quantity = new int [3];
	public int [] productID = new int[3];
	
	public PushButtonCodeInterpreter dud;
	
	@Before
	public void setUp() throws Exception {
		context = new Mockery() {{
			setImposteriser(ClassImposteriser.INSTANCE);
		}};
		rack[0] = new ProductRack(20);
		rack[1] = new ProductRack(20);
		rack[2] = new ProductRack(20);
		
		//rackcount = 3;
		names[0] = "Product1";
		names[1] = "Product2";
		names[2] = "Product3";
		costs[0] = 1;
		costs[1] = 2;
		costs[2] = 0; //yay free swag
		quantity[0] = 4;
		quantity [1] = 3;
		quantity [2] = 7;
		
		inv = new InventoryController(rack, rackcount, names, costs, quantity, null, new Logger(), 0);
		fun = 	context.mock(FundsController.class);
		test = new CodeSelectionController(inv, fun, off);
		
		//Add sinks to racks
		AbstractProductAcceptor acceptor = new DeliveryChute(100);
		ProductChannel sink = new ProductChannel(acceptor);
		
		for(int i=0; i<rackcount; i++){
			inv.getRack(i).connect(sink);
		}
		
	}

	@Test
	public void testCodeSelectionControllerConstructor() {
		assertNotNull(test);
	}
	
	@Test
	public void testCodeEntered() {
		context.checking(new Expectations(){
			{
			atLeast(1).of(fun).ConductTransaction(with(any(Integer.class)),with(any(Integer.class)));
			will(returnValue(TransactionReturnCode.SUCCESSFUL));
			}
		});
		test.codeEntered("A1", dud);
		//all is well with the world
		assertTrue(true);
	}
	
	@Test
	public void testCodeEnteredInsufficientFunds() {
		context.checking(new Expectations(){
			{
			atLeast(1).of(fun).ConductTransaction(with(any(Integer.class)),with(any(Integer.class)));
			will(returnValue(TransactionReturnCode.INSUFFICIENTFUNDS));
			}
		});
		test.codeEntered("A1", dud);
		//all is well with the world
		assertTrue(true);
	}
	
	@Test
	public void testProductIndexValidLowerBound() {
		context.checking(new Expectations(){
			{
			atLeast(1).of(fun).ConductTransaction(with(any(Integer.class)),with(any(Integer.class)));
			will(returnValue(TransactionReturnCode.SUCCESSFUL));
			}
		});
		assertEquals(0, test.productIndex("A0", off));
	}
	
	@Test 
	public void testProductIndexValidUpperBound() {
		context.checking(new Expectations(){
			{
			atLeast(1).of(fun).ConductTransaction(with(any(Integer.class)),with(any(Integer.class)));
			will(returnValue(TransactionReturnCode.SUCCESSFUL));
			}
		});
		assertEquals(2, test.productIndex("A2", off));
	}
	
	@Test
	public void testProductIndexFirstInvalidLowerBound() {
		context.checking(new Expectations(){
			{
			atLeast(1).of(fun).ConductTransaction(with(any(Integer.class)),with(any(Integer.class)));
			will(returnValue(TransactionReturnCode.SUCCESSFUL));
			}
		});
		assertEquals(-1, test.productIndex("@0", off));
	}
	
	@Test
	public void testProductIndexFirstInvalidUpperBound() {
		context.checking(new Expectations(){
			{
			atLeast(1).of(fun).ConductTransaction(with(any(Integer.class)),with(any(Integer.class)));
			will(returnValue(TransactionReturnCode.SUCCESSFUL));
			}
		});
		assertEquals(-1, test.productIndex("[0", off));
	}

	@Test
	public void testProductIndexSecondInvalidLowerBound() {
		context.checking(new Expectations(){
			{
			atLeast(1).of(fun).ConductTransaction(with(any(Integer.class)),with(any(Integer.class)));
			will(returnValue(TransactionReturnCode.SUCCESSFUL));
			}
		});
		assertEquals(-1, test.productIndex("A/", off));
	}
	
	@Test
	public void testProductIndexSecondInvalidUpperBound() {
		context.checking(new Expectations(){
			{
			atLeast(1).of(fun).ConductTransaction(with(any(Integer.class)),with(any(Integer.class)));
			will(returnValue(TransactionReturnCode.SUCCESSFUL));
			}
		});
		assertEquals(-1, test.productIndex("A:", off));
	}
	
	@Test
	public void testProductIndexBothInvalid() {
		context.checking(new Expectations(){
			{
			atLeast(1).of(fun).ConductTransaction(with(any(Integer.class)),with(any(Integer.class)));
			will(returnValue(TransactionReturnCode.SUCCESSFUL));
			}
		});
		assertEquals(-1, test.productIndex("$}", off));
	}
	
	@Test
	public void testProductIndexInvalidArg() {
		context.checking(new Expectations(){
			{
			atLeast(1).of(fun).ConductTransaction(with(any(Integer.class)),with(any(Integer.class)));
			will(returnValue(TransactionReturnCode.SUCCESSFUL));
			}
		});
		assertEquals(-1, test.productIndex("A11", off));
	}
}
