package gui.test;

import static org.junit.Assert.*;

import java.awt.Color;
import java.util.ArrayList;

import gui.GUI;
import hardware.AbstractVendingMachine;
import hardware.exceptions.NoSuchHardwareException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

public class CandyGUITest {

	private AbstractVendingMachine machine;
	private GUI window;
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		int [] coinValue = {5,10,25,100,200};
		int [] billValue = {500,1000,2000};
		ArrayList<Boolean> parts = new ArrayList();//{true,true,true,false,true};
		for(int i = 0; i< 5; i++){			
			if(i==3){
				parts.add(false);
			}else{
				parts.add(true);
			}
		}
		machine = new CandyVendingMachine(coinValue, billValue);
		window = new GUI (machine,parts);
		window.getfrmVendingMachines().setVisible(true);
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
		window.getcardButtons(0).doClick();
	}
	
	@Test
	public void Insert10DollarBilltest() {
		window.getcardButtons(1).doClick();
	}
	
	@Test
	public void Insert20DollarBilltest() {
		window.getcardButtons(2).doClick();
	}
	
	@Test
	public void SelectionButtonTest(){
		try {
			System.out.println("Num of product racks: "+ machine.getNumberOfProductRacks());
			for(int i = 0, j = 0; i< 16; i++){
				System.out.println("i is: "+ i);
				if(i<6){
					window.getLetterBtn(i);
				}else{
					window.getNumButtons(j);
					j++;
				}
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
