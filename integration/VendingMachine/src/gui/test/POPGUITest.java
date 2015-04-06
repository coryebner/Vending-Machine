package gui.test;

import static org.junit.Assert.*;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import gui.*;
import hardware.*;
import hardware.exceptions.NoSuchHardwareException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import com.sun.java.swing.plaf.windows.resources.windows;

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
		ArrayList<Boolean> parts = new ArrayList();//{true,true,true,true,false};
		for(int i = 0; i< 6; i++){			
			if(i==5){
				parts.add(false);
			}else{
				parts.add(true);
			}
		}
		machine = new PopVendingMachine(coinValue, billValue);
		window = new StandardMachineGUI (machine,parts);
		window.getMainFrame().setVisible(true);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void Insert5CentsCointest() {
		window.getCoinBtn(0).doClick();
	}
	
	@Test
	public void Insert10CentsCointest() {
		window.getCoinBtn(1).doClick();
	}
	
	@Test
	public void Insert25CentsCointest() {
		window.getCoinBtn(2).doClick();
	}
	
	@Test
	public void Insert1DollarCointest() {
		window.getCoinBtn(3).doClick();
	}
	
	@Test
	public void Insert2DollarCointest() {
		window.getCoinBtn(4).doClick();
	}
	
	@Test
	public void InsertCardWith0Valuetest() {
		window.getcardButtons(0).doClick();
	}
	
	@Test
	public void InsertCardWith5Dollartest() {
		window.getcardButtons(1).doClick();
	}
	
	@Test
	public void InsertCardWith10Dollartest() {
		window.getcardButtons(2).doClick();
	}
	
	@Test
	public void Insert5DollarBilltest() {
		window.getbillButtons(0).doClick();

	}
	
	@Test
	public void Insert10DollarBilltest() {
		window.getbillButtons(1).doClick();

	}
	
	@Test
	public void Insert20DollarBilltest() {
		window.getbillButtons(2).doClick();
	}
	
	@Test
	public void SelectionButtonTest(){
		try {
			for(int i = 0; i< machine.getNumberOfProductRacks(); i++){
				System.out.println("i is: "+ i);
				window.getPopBtn(i).doClick();
			}
		} catch (NoSuchHardwareException e) {		
			e.printStackTrace();
		}
	}
	
	@Test
	public void ReturnButtonTest(){
		window.getReturnButton().doClick();
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
