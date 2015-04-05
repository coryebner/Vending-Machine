package business.touchscreen.tests;

import static org.junit.Assert.*;

import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import business.touchscreen.BusinessTouchScreen;

public class BusinessTouchScreenTest {

	class KeyboardListener implements KeyListener{

		
		@Override
		public void keyPressed(KeyEvent arg0) {
			System.out.println("Something was pressed!");
			
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	KeyboardListener theListener = null;
	BusinessTouchScreen touchScreen = null;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		theListener = new KeyboardListener();
		touchScreen = new BusinessTouchScreen();
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public final void testStuff() {
		touchScreen.OnKeyDown('A');
		System.out.println("Hello");
	}

	
	
	
}
