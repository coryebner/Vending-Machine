package gui.test;

import static org.junit.Assert.*;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Locale;

import gui.*;
import hardware.*;
import hardware.exceptions.NoSuchHardwareException;
import hardware.simulators.AbstractVendingMachine;
import hardware.simulators.VMRUS_SFF_P_C;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;


public class POPGUITest {

	private AbstractVendingMachine machine;
	private StandardMachineGUI window;
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		int [] coinValue = {5,10,25,100,200};
		int [] billValue = {500,1000,2000};
		ArrayList<Boolean> parts = new ArrayList();//{true,true,true,true,true,false,false};
		for(int i = 0; i< 7; i++){			
			if(i==5 || i == 6){
				parts.add(false);
			}else{
				parts.add(true);
			}
		}
		Locale locale = Locale.CANADA;
		machine = new VMRUS_SFF_P_C(locale,coinValue);
		window = new StandardMachineGUI (machine,parts);
		window.getMainFrame().setVisible(true);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void Insert5CentsCointest() {
		window.resetButtonPressedStatus();
		window.getCoinBtn(0).doClick();
		assertTrue(window.getButtonPressStatus());
	}
	
	@Test
	public void Insert10CentsCointest() {
		window.resetButtonPressedStatus();
		window.getCoinBtn(1).doClick();
		assertTrue(window.getButtonPressStatus());
	}
	
	@Test
	public void Insert25CentsCointest() {
		window.resetButtonPressedStatus();
		window.getCoinBtn(2).doClick();
		assertTrue(window.getButtonPressStatus());
	}
	
	@Test
	public void Insert1DollarCointest() {
		window.resetButtonPressedStatus();
		window.getCoinBtn(3).doClick();
		assertTrue(window.getButtonPressStatus());
	}
	
	@Test
	public void Insert2DollarCointest() {
		window.resetButtonPressedStatus();
		window.getCoinBtn(4).doClick();
		assertTrue(window.getButtonPressStatus());
	}
	
////	No cards and bills in this machine
////	@Test
////	public void InsertCardWith0Valuetest() {
////		window.resetButtonPressedStatus();
////		window.getcardButtons(0).doClick();
////		assertTrue(window.getButtonPressStatus());
////	}
////	
////	@Test
////	public void InsertCardWith5Dollartest() {
////		window.resetButtonPressedStatus();
////		window.getcardButtons(1).doClick();
////		assertTrue(window.getButtonPressStatus());
////	}
////	
////	@Test
////	public void InsertCardWith10Dollartest() {
////		window.resetButtonPressedStatus();
////		window.getcardButtons(2).doClick();
////		assertTrue(window.getButtonPressStatus());
////	}
////	
//	@Test
//	public void Insert5DollarBilltest() {
//		window.resetButtonPressedStatus();
//		window.getbillButtons(0).doClick();
//		assertTrue(window.getButtonPressStatus());
//	}
//	
//	@Test
//	public void Insert10DollarBilltest() {
//		window.resetButtonPressedStatus();
//		window.getbillButtons(1).doClick();
//		assertTrue(window.getButtonPressStatus());
//	}
//	
//	@Test
//	public void Insert20DollarBilltest() {
//		window.resetButtonPressedStatus();
//		window.getbillButtons(2).doClick();
//		assertTrue(window.getButtonPressStatus());
//	}
	
	@Test
	public void SelectionButton1Test(){
		window.resetButtonPressedStatus();
		window.getPopBtn(0).doClick();
		assertTrue(window.getButtonPressStatus());				
	}
	
	@Test
	public void SelectionButton2Test(){
		window.resetButtonPressedStatus();
		window.getPopBtn(1).doClick();
		assertTrue(window.getButtonPressStatus());				
	}
	
	@Test
	public void SelectionButton3Test(){
		window.resetButtonPressedStatus();
		window.getPopBtn(2).doClick();
		assertTrue(window.getButtonPressStatus());				
	}
	
	@Test
	public void SelectionButton4Test(){
		window.resetButtonPressedStatus();
		window.getPopBtn(3).doClick();
		assertTrue(window.getButtonPressStatus());				
	}
	
	@Test
	public void SelectionButton5Test(){
		window.resetButtonPressedStatus();
		window.getPopBtn(4).doClick();
		assertTrue(window.getButtonPressStatus());				
	}
	
	@Test
	public void SelectionButton6Test(){
		window.resetButtonPressedStatus();
		window.getPopBtn(5).doClick();
		assertTrue(window.getButtonPressStatus());				
	}
	
	
	@Test
	public void ReturnButtonTest(){
		window.resetButtonPressedStatus();
		window.getReturnButton().doClick();
		assertTrue(window.getButtonPressStatus());
	}
	
	@Test
	public void OutOfOrderActivatedTest(){
		try {
			machine.getOutOfOrderLight().activate();
		} catch (NoSuchHardwareException e) {
			e.printStackTrace();
		}
		assertEquals("The Foreground colar of OutOfOrder the GUI",Color.BLACK, window.getOutOfOrderLight().getForeground());
	}
	
	@Test
	public void OutOfOrderDeactivatedTest(){
		try {
			machine.getOutOfOrderLight().activate();
			machine.getOutOfOrderLight().deactivate();
		} catch (NoSuchHardwareException e) {
			e.printStackTrace();
		}
		assertEquals("The Foreground colar of OutOfOrder the GUI",Color.LIGHT_GRAY, window.getOutOfOrderLight().getForeground());
	}
	
	@Test
	public void ExactChangeActivatedTest(){
		try {
			machine.getExactChangeLight().activate();
		} catch (NoSuchHardwareException e) {
			e.printStackTrace();
		}
		assertEquals("The Foreground colar of ExactChange the GUI",Color.BLACK, window.getExactChangeLight().getForeground());
	}
	
	@Test
	public void ExactChangeDeactivatedTest(){
		try {
			machine.getExactChangeLight().activate();
			machine.getExactChangeLight().deactivate();
		} catch (NoSuchHardwareException e) {
			e.printStackTrace();
		}
		assertEquals("The Foreground colar of ExactChange the GUI",Color.RED, window.getExactChangeLight().getForeground());
	}
}
