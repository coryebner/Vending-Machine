//package business.touchscreen.tests;
//
//import static org.junit.Assert.*;
//
//import java.awt.Container;
//import javax.swing.*;
//
//import java.awt.KeyEventDispatcher;
//import java.awt.KeyboardFocusManager;
//import java.awt.event.KeyEvent;
//import java.awt.event.KeyListener;
//import java.util.Scanner;
//
//import javax.swing.JTextField;
//
//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//
//import business.touchscreen.BusinessTouchScreen;
//import business.touchscreen.BusinessTouchScreenListener;
//
//public class BusinessTouchScreenTest {
//	JTextField inputField;
//	JFrame inputFrame;
//	JPanel main;
//	char input;
//
//	class ScreenListener implements BusinessTouchScreenListener {
//
//		char pressed = ' ';
//		boolean notified = false;
//
//		@Override
//		public void userKeyboardInput(char userInput) {
//			pressed = userInput;
//			notified = true;
//		}
//
//		public String getKeyPress() {
//			if (notified)
//				return (String.valueOf(pressed));
//			else
//				return ("Nothing");
//		}
//
//	}
//
//	ScreenListener screenListener = null;
//	BusinessTouchScreen touchScreen = null;
//
//	// The tests will imitate keyboard presses, so if your cursor is not on the
//	// console
//	// the test will start printing random things all over your code or wherever
//	// your
//	// cursor is focused on.
//	@BeforeClass
//	public static void setUpBeforeClass() throws Exception {
//		// Scanner scan = new Scanner(System.in);
//		// System.out.println("Please Click the Console and press Enter to begin test!");
//		// scan.nextLine();
//	}
//
//	@AfterClass
//	public static void tearDownAfterClass() throws Exception {
//	}
//
//	@Before
//	public void setUp() throws Exception {
//		screenListener = new ScreenListener();
//		touchScreen = new BusinessTouchScreen();
//
//		touchScreen.register(screenListener);
//
//		inputFrame = new JFrame();
//		inputFrame.setSize(0, 0);
//		inputField = new JTextField();
//		inputField.setColumns(1);
//		inputField.addKeyListener(new KeyListener() {
//			public void keyPressed(KeyEvent arg0) {
//				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
//					input = '\n';
//				} else if (arg0.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
//					input = '\b';
//				} else if (arg0.getKeyCode() == KeyEvent.VK_TAB) {
//					input = '\t';
//				}
//			}
//
//			public void keyReleased(KeyEvent arg0) {
//
//			}
//
//			public void keyTyped(KeyEvent arg0) {
//
//			}
//		});
//		inputField.setFocusTraversalKeysEnabled(false);
//		main = new JPanel();
//		main.add(inputField);
//		inputFrame.getContentPane().add(main);
//	}
//
//	@After
//	public void tearDown() throws Exception {
//		inputFrame.removeAll();
//		inputFrame.dispose();
//	}
//
//	@Test
//	public final void testListener_a() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('a');
//		Thread.sleep(40);
//
//		assertTrue(screenListener.getKeyPress().equals("a"));
//	}
//
//	@Test
//	public final void testListener_b() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('b');
//		Thread.sleep(40);
//
//		assertTrue(screenListener.getKeyPress().equals("b"));
//	}
//
//	@Test
//	public final void testListener_c() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('c');
//		Thread.sleep(40);
//
//		assertTrue(screenListener.getKeyPress().equals("c"));
//	}
//
//	@Test
//	public final void testListener_d() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('d');
//		Thread.sleep(40);
//
//		assertTrue(screenListener.getKeyPress().equals("d"));
//	}
//
//	@Test
//	public final void testListener_e() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('e');
//		Thread.sleep(40);
//
//		assertTrue(screenListener.getKeyPress().equals("e"));
//	}
//
//	@Test
//	public final void testListener_f() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('f');
//		Thread.sleep(40);
//
//		assertTrue(screenListener.getKeyPress().equals("f"));
//	}
//
//	@Test
//	public final void testListener_g() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('g');
//		Thread.sleep(40);
//
//		assertTrue(screenListener.getKeyPress().equals("g"));
//	}
//
//	@Test
//	public final void testListener_h() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('h');
//		Thread.sleep(40);
//
//		assertTrue(screenListener.getKeyPress().equals("h"));
//	}
//
//	@Test
//	public final void testListener_i() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('i');
//		Thread.sleep(40);
//
//		assertTrue(screenListener.getKeyPress().equals("i"));
//	}
//
//	@Test
//	public final void testListener_j() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('j');
//		Thread.sleep(40);
//
//		assertTrue(screenListener.getKeyPress().equals("j"));
//	}
//
//	@Test
//	public final void testListener_k() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('k');
//		Thread.sleep(40);
//
//		assertTrue(screenListener.getKeyPress().equals("k"));
//	}
//
//	@Test
//	public final void testListener_l() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('l');
//		Thread.sleep(40);
//
//		assertTrue(screenListener.getKeyPress().equals("l"));
//	}
//
//	@Test
//	public final void testListener_m() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('m');
//		Thread.sleep(40);
//
//		assertTrue(screenListener.getKeyPress().equals("m"));
//	}
//
//	@Test
//	public final void testListener_n() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('n');
//		Thread.sleep(40);
//
//		assertTrue(screenListener.getKeyPress().equals("n"));
//	}
//
//	@Test
//	public final void testListener_o() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('o');
//		Thread.sleep(40);
//
//		assertTrue(screenListener.getKeyPress().equals("o"));
//	}
//
//	@Test
//	public final void testListener_p() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('p');
//		Thread.sleep(40);
//
//		assertTrue(screenListener.getKeyPress().equals("p"));
//	}
//
//	@Test
//	public final void testListener_q() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('q');
//		Thread.sleep(40);
//
//		assertTrue(screenListener.getKeyPress().equals("q"));
//	}
//
//	@Test
//	public final void testListener_r() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('r');
//		Thread.sleep(40);
//
//		assertTrue(screenListener.getKeyPress().equals("r"));
//	}
//
//	@Test
//	public final void testListener_s() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('s');
//		Thread.sleep(40);
//
//		assertTrue(screenListener.getKeyPress().equals("s"));
//	}
//
//	@Test
//	public final void testListener_t() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('t');
//		Thread.sleep(40);
//
//		assertTrue(screenListener.getKeyPress().equals("t"));
//	}
//
//	@Test
//	public final void testListener_u() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('u');
//		Thread.sleep(40);
//
//		assertTrue(screenListener.getKeyPress().equals("u"));
//	}
//
//	@Test
//	public final void testListener_v() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('v');
//		Thread.sleep(40);
//
//		assertTrue(screenListener.getKeyPress().equals("v"));
//	}
//
//	@Test
//	public final void testListener_w() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('w');
//		Thread.sleep(40);
//
//		assertTrue(screenListener.getKeyPress().equals("w"));
//	}
//
//	@Test
//	public final void testListener_x() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('x');
//		Thread.sleep(40);
//
//		assertTrue(screenListener.getKeyPress().equals("x"));
//	}
//
//	@Test
//	public final void testListener_y() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('y');
//		Thread.sleep(40);
//
//		assertTrue(screenListener.getKeyPress().equals("y"));
//	}
//
//	@Test
//	public final void testListener_z() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('z');
//		Thread.sleep(40);
//
//		assertTrue(screenListener.getKeyPress().equals("z"));
//	}
//
//	@Test
//	public final void testListener_A() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('A');
//		Thread.sleep(40);
//
//		assertTrue(screenListener.getKeyPress().equals("A"));
//	}
//
//	@Test
//	public final void testListener_B() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('B');
//		Thread.sleep(40);
//
//		assertTrue(screenListener.getKeyPress().equals("B"));
//	}
//
//	@Test
//	public final void testListener_C() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('C');
//		Thread.sleep(40);
//
//		assertTrue(screenListener.getKeyPress().equals("C"));
//	}
//
//	@Test
//	public final void testListener_D() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('D');
//		Thread.sleep(40);
//
//		assertTrue(screenListener.getKeyPress().equals("D"));
//	}
//
//	@Test
//	public final void testListener_E() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('E');
//		Thread.sleep(40);
//
//		assertTrue(screenListener.getKeyPress().equals("E"));
//	}
//
//	@Test
//	public final void testListener_F() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('F');
//		Thread.sleep(40);
//
//		assertTrue(screenListener.getKeyPress().equals("F"));
//	}
//
//	@Test
//	public final void testListener_G() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('G');
//		Thread.sleep(40);
//
//		assertTrue(screenListener.getKeyPress().equals("G"));
//	}
//
//	@Test
//	public final void testListener_H() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('H');
//		Thread.sleep(40);
//
//		assertTrue(screenListener.getKeyPress().equals("H"));
//	}
//
//	@Test
//	public final void testListener_I() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('I');
//		Thread.sleep(40);
//
//		assertTrue(screenListener.getKeyPress().equals("I"));
//	}
//
//	@Test
//	public final void testListener_J() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('J');
//		Thread.sleep(40);
//
//		assertTrue(screenListener.getKeyPress().equals("J"));
//	}
//
//	@Test
//	public final void testListener_K() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('K');
//		Thread.sleep(40);
//
//		assertTrue(screenListener.getKeyPress().equals("K"));
//	}
//
//	@Test
//	public final void testListener_L() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('L');
//		Thread.sleep(40);
//
//		assertTrue(screenListener.getKeyPress().equals("L"));
//	}
//
//	@Test
//	public final void testListener_M() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('M');
//		Thread.sleep(40);
//
//		assertTrue(screenListener.getKeyPress().equals("M"));
//	}
//
//	@Test
//	public final void testListener_N() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('N');
//		Thread.sleep(40);
//
//		assertTrue(screenListener.getKeyPress().equals("N"));
//	}
//
//	@Test
//	public final void testListener_O() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('O');
//		Thread.sleep(40);
//
//		assertTrue(screenListener.getKeyPress().equals("O"));
//	}
//
//	@Test
//	public final void testListener_P() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('P');
//		Thread.sleep(40);
//
//		assertTrue(screenListener.getKeyPress().equals("P"));
//	}
//
//	@Test
//	public final void testListener_Q() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('Q');
//		Thread.sleep(40);
//
//		assertTrue(screenListener.getKeyPress().equals("Q"));
//	}
//
//	@Test
//	public final void testListener_R() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('R');
//		Thread.sleep(40);
//
//		assertTrue(screenListener.getKeyPress().equals("R"));
//	}
//
//	@Test
//	public final void testListener_S() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('S');
//		Thread.sleep(40);
//
//		assertTrue(screenListener.getKeyPress().equals("S"));
//	}
//
//	@Test
//	public final void testListener_T() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('T');
//		Thread.sleep(40);
//
//		assertTrue(screenListener.getKeyPress().equals("T"));
//	}
//
//	@Test
//	public final void testListener_U() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('U');
//		Thread.sleep(40);
//
//		assertTrue(screenListener.getKeyPress().equals("U"));
//	}
//
//	@Test
//	public final void testListener_V() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('V');
//		Thread.sleep(40);
//
//		assertTrue(screenListener.getKeyPress().equals("V"));
//	}
//
//	@Test
//	public final void testListener_W() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('W');
//		Thread.sleep(40);
//
//		assertTrue(screenListener.getKeyPress().equals("W"));
//	}
//
//	@Test
//	public final void testListener_X() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('X');
//		Thread.sleep(40);
//
//		assertTrue(screenListener.getKeyPress().equals("X"));
//	}
//
//	@Test
//	public final void testListener_Y() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('Y');
//		Thread.sleep(40);
//
//		assertTrue(screenListener.getKeyPress().equals("Y"));
//	}
//
//	@Test
//	public final void testListener_Z() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('Z');
//		Thread.sleep(40);
//
//		assertTrue(screenListener.getKeyPress().equals("Z"));
//	}
//
//	@Test
//	public final void testlistener_1() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('1');
//		Thread.sleep(40);
//
//		assertTrue(screenListener.getKeyPress().equals("1"));
//	}
//
//	@Test
//	public final void testlistener_2() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('2');
//		Thread.sleep(40);
//
//		assertTrue(screenListener.getKeyPress().equals("2"));
//	}
//
//	@Test
//	public final void testlistener_3() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('3');
//		Thread.sleep(40);
//
//		assertTrue(screenListener.getKeyPress().equals("3"));
//	}
//
//	@Test
//	public final void testlistener_4() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('4');
//		Thread.sleep(40);
//
//		assertTrue(screenListener.getKeyPress().equals("4"));
//	}
//
//	@Test
//	public final void testlistener_5() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('5');
//		Thread.sleep(40);
//
//		assertTrue(screenListener.getKeyPress().equals("5"));
//	}
//
//	@Test
//	public final void testlistener_6() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('6');
//		Thread.sleep(40);
//
//		assertTrue(screenListener.getKeyPress().equals("6"));
//	}
//
//	@Test
//	public final void testlistener_7() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('7');
//		Thread.sleep(40);
//
//		assertTrue(screenListener.getKeyPress().equals("7"));
//	}
//
//	@Test
//	public final void testlistener_8() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('8');
//		Thread.sleep(40);
//
//		assertTrue(screenListener.getKeyPress().equals("8"));
//	}
//
//	@Test
//	public final void testlistener_9() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('9');
//		Thread.sleep(40);
//
//		assertTrue(screenListener.getKeyPress().equals("9"));
//	}
//
//	@Test
//	public final void testlistener_rightBracket() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown(')');
//		Thread.sleep(40);
//
//		assertTrue(screenListener.getKeyPress().equals(")"));
//	}
//
//	@Test
//	public final void testlistener_exclamation() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('!');
//		Thread.sleep(40);
//
//		assertTrue(screenListener.getKeyPress().equals("!"));
//	}
//
//	@Test
//	public final void testlistener_atSymbol() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('@');
//		Thread.sleep(40);
//
//		assertTrue(screenListener.getKeyPress().equals("@"));
//	}
//
//	@Test
//	public final void testlistener_hash() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('#');
//		Thread.sleep(40);
//
//		assertTrue(screenListener.getKeyPress().equals("#"));
//	}
//
//	@Test
//	public final void testlistener_dollarSign() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('$');
//		Thread.sleep(40);
//
//		assertTrue(screenListener.getKeyPress().equals("$"));
//	}
//
//	@Test
//	public final void testlistener_modulo() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('%');
//		Thread.sleep(40);
//
//		assertTrue(screenListener.getKeyPress().equals("%"));
//	}
//
//	@Test
//	public final void testlistener_caret() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('^');
//		Thread.sleep(40);
//
//		assertTrue(screenListener.getKeyPress().equals("^"));
//	}
//
//	@Test
//	public final void testlistener_amperstamp() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('&');
//		Thread.sleep(40);
//
//		assertTrue(screenListener.getKeyPress().equals("&"));
//	}
//
//	@Test
//	public final void testlistener_star() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('*');
//		Thread.sleep(40);
//
//		assertTrue(screenListener.getKeyPress().equals("*"));
//	}
//
//	@Test
//	public final void testlistener_leftBracket() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('(');
//		Thread.sleep(40);
//
//		assertTrue(screenListener.getKeyPress().equals("("));
//	}
//
//	// RANDOM OTHER CHARACTERS
//
//	@Test
//	public final void testlistener_tick() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('`');
//		Thread.sleep(40);
//
//		assertTrue(screenListener.getKeyPress().equals("`"));
//	}
//
//	@Test
//	public final void testlistener_Squigly() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('~');
//		Thread.sleep(40);
//
//		assertTrue(screenListener.getKeyPress().equals("~"));
//	}
//
//	@Test
//	public final void testlistener_dash() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('-');
//		Thread.sleep(40);
//
//		assertTrue(screenListener.getKeyPress().equals("-"));
//	}
//
//	@Test
//	public final void testlistener_underscore() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('_');
//		Thread.sleep(40);
//
//		assertTrue(screenListener.getKeyPress().equals("_"));
//	}
//
//	@Test
//	public final void testlistener_equals() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('=');
//		Thread.sleep(40);
//
//		assertTrue(screenListener.getKeyPress().equals("="));
//	}
//
//	@Test
//	public final void testlistener_plus() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('+');
//		Thread.sleep(40);
//
//		assertTrue(screenListener.getKeyPress().equals("+"));
//	}
//
//	@Test
//	public final void testlistener_leftSquareBracket() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('[');
//		Thread.sleep(40);
//
//		assertTrue(screenListener.getKeyPress().equals("["));
//	}
//
//	@Test
//	public final void testlistener_leftParenthesis() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('{');
//		Thread.sleep(40);
//
//		assertTrue(screenListener.getKeyPress().equals("{"));
//	}
//
//	@Test
//	public final void testlistener_rightSquareBracket() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown(']');
//		Thread.sleep(40);
//
//		assertTrue(screenListener.getKeyPress().equals("]"));
//	}
//
//	@Test
//	public final void testlistener_rightParenthesis() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('}');
//		Thread.sleep(40);
//
//		assertTrue(screenListener.getKeyPress().equals("}"));
//	}
//
//	@Test
//	public final void testlistener_backslash() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('\\');
//		Thread.sleep(40);
//
//		assertTrue(screenListener.getKeyPress().equals("\\"));
//	}
//
//	@Test
//	public final void testlistener_pipe() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('|');
//		Thread.sleep(40);
//
//		assertTrue(screenListener.getKeyPress().equals("|"));
//	}
//
//	@Test
//	public final void testlistener_semicolon() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown(';');
//		Thread.sleep(40);
//
//		assertTrue(screenListener.getKeyPress().equals(";"));
//	}
//
//	@Test
//	public final void testlistener_colon() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown(':');
//		Thread.sleep(40);
//
//		assertTrue(screenListener.getKeyPress().equals(":"));
//	}
//
//	@Test
//	public final void testlistener_singlequote() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('\'');
//		Thread.sleep(40);
//
//		assertTrue(screenListener.getKeyPress().equals("\'"));
//	}
//
//	@Test
//	public final void testlistener_quotation() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('"');
//		Thread.sleep(40);
//
//		assertTrue(screenListener.getKeyPress().equals("\""));
//	}
//
//	@Test
//	public final void testlistener_comma() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown(',');
//		Thread.sleep(40);
//
//		assertTrue(screenListener.getKeyPress().equals(","));
//	}
//
//	@Test
//	public final void testlistener_leftAngleBracket() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('<');
//		Thread.sleep(40);
//
//		assertTrue(screenListener.getKeyPress().equals("<"));
//	}
//
//	@Test
//	public final void testlistener_period() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('.');
//		Thread.sleep(40);
//
//		assertTrue(screenListener.getKeyPress().equals("."));
//	}
//
//	@Test
//	public final void testlistener_rightAngleBracket() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('>');
//		Thread.sleep(40);
//
//		assertTrue(screenListener.getKeyPress().equals(">"));
//	}
//
//	@Test
//	public final void testlistener_slash() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('/');
//		Thread.sleep(40);
//
//		assertTrue(screenListener.getKeyPress().equals("/"));
//	}
//
//	@Test
//	public final void testlistener_questionMark() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('?');
//		Thread.sleep(40);
//
//		assertTrue(screenListener.getKeyPress().equals("?"));
//	}
//
//	// NEED TO FIX
//	@Test
//	public final void testlistener_backspace() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('\b');
//		Thread.sleep(40);
//
//		assertTrue(screenListener.getKeyPress().equals("\b"));
//	}
//
//	@Test
//	public final void testlistener_newline() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('\n');
//		Thread.sleep(40);
//
//		assertTrue(screenListener.getKeyPress().equals("\n"));
//	}
//
//	@Test
//	public final void testlistener_enter() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('\r');
//		Thread.sleep(40);
//
//		assertTrue(screenListener.getKeyPress().equals("\r"));
//	}
//
//	@Test
//	public final void testlistener_tab() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('\t');
//		Thread.sleep(40);
//
//		assertTrue(screenListener.getKeyPress().equals("\t"));
//	}
//
//	@Test
//	public final void testlistener_space() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown(' ');
//		Thread.sleep(40);
//
//		assertTrue(screenListener.getKeyPress().equals(" "));
//	}
//
//	@Test
//	public void testa() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('a');
//		Thread.sleep(40);
//		assertEquals(inputField.getText().charAt(0), 'a');
//	}
//
//	@Test
//	public void testb() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('b');
//		Thread.sleep(40);
//		assertEquals(inputField.getText().charAt(0), 'b');
//	}
//
//	@Test
//	public void testc() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('c');
//		Thread.sleep(40);
//		assertEquals(inputField.getText().charAt(0), 'c');
//	}
//
//	@Test
//	public void testd() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('d');
//		Thread.sleep(40);
//		assertEquals(inputField.getText().charAt(0), 'd');
//	}
//
//	@Test
//	public void teste() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('e');
//		Thread.sleep(40);
//		assertEquals(inputField.getText().charAt(0), 'e');
//	}
//
//	@Test
//	public void testf() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('f');
//		Thread.sleep(40);
//		assertEquals(inputField.getText().charAt(0), 'f');
//	}
//
//	@Test
//	public void testg() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('g');
//		Thread.sleep(40);
//		assertEquals(inputField.getText().charAt(0), 'g');
//	}
//
//	@Test
//	public void testh() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('h');
//		Thread.sleep(40);
//		assertEquals(inputField.getText().charAt(0), 'h');
//	}
//
//	@Test
//	public void testi() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('i');
//		Thread.sleep(40);
//		assertEquals(inputField.getText().charAt(0), 'i');
//	}
//
//	@Test
//	public void testj() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('j');
//		Thread.sleep(40);
//		assertEquals(inputField.getText().charAt(0), 'j');
//	}
//
//	@Test
//	public void testk() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('k');
//		Thread.sleep(40);
//		assertEquals(inputField.getText().charAt(0), 'k');
//	}
//
//	@Test
//	public void testl() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('l');
//		Thread.sleep(40);
//		assertEquals(inputField.getText().charAt(0), 'l');
//	}
//
//	@Test
//	public void testm() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('m');
//		Thread.sleep(40);
//		assertEquals(inputField.getText().charAt(0), 'm');
//	}
//
//	@Test
//	public void testn() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('n');
//		Thread.sleep(40);
//		assertEquals(inputField.getText().charAt(0), 'n');
//	}
//
//	@Test
//	public void testo() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('o');
//		Thread.sleep(40);
//		assertEquals(inputField.getText().charAt(0), 'o');
//	}
//
//	@Test
//	public void testp() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('p');
//		Thread.sleep(40);
//		assertEquals(inputField.getText().charAt(0), 'p');
//	}
//
//	@Test
//	public void testq() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('q');
//		Thread.sleep(40);
//		assertEquals(inputField.getText().charAt(0), 'q');
//	}
//
//	@Test
//	public void testr() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('r');
//		Thread.sleep(40);
//		assertEquals(inputField.getText().charAt(0), 'r');
//	}
//
//	@Test
//	public void tests() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('s');
//		Thread.sleep(40);
//		assertEquals(inputField.getText().charAt(0), 's');
//	}
//
//	@Test
//	public void testt() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('t');
//		Thread.sleep(40);
//		assertEquals(inputField.getText().charAt(0), 't');
//	}
//
//	@Test
//	public void testu() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('u');
//		Thread.sleep(40);
//		assertEquals(inputField.getText().charAt(0), 'u');
//	}
//
//	@Test
//	public void testv() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('v');
//		Thread.sleep(40);
//		assertEquals(inputField.getText().charAt(0), 'v');
//	}
//
//	@Test
//	public void testw() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('w');
//		Thread.sleep(40);
//		assertEquals(inputField.getText().charAt(0), 'w');
//	}
//
//	@Test
//	public void testx() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('x');
//		Thread.sleep(40);
//		assertEquals(inputField.getText().charAt(0), 'x');
//	}
//
//	@Test
//	public void testy() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('y');
//		Thread.sleep(40);
//		assertEquals(inputField.getText().charAt(0), 'y');
//	}
//
//	@Test
//	public void testz() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('z');
//		Thread.sleep(40);
//		assertEquals(inputField.getText().charAt(0), 'z');
//	}
//
//	@Test
//	public void testA() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('A');
//		Thread.sleep(40);
//		assertEquals(inputField.getText().charAt(0), 'A');
//	}
//
//	@Test
//	public void testB() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('B');
//		Thread.sleep(40);
//		assertEquals(inputField.getText().charAt(0), 'B');
//	}
//
//	@Test
//	public void testC() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('C');
//		Thread.sleep(40);
//		assertEquals(inputField.getText().charAt(0), 'C');
//	}
//
//	@Test
//	public void testD() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('D');
//		Thread.sleep(40);
//		assertEquals(inputField.getText().charAt(0), 'D');
//	}
//
//	@Test
//	public void testE() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('E');
//		Thread.sleep(40);
//		assertEquals(inputField.getText().charAt(0), 'E');
//	}
//
//	@Test
//	public void testF() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('F');
//		Thread.sleep(40);
//		assertEquals(inputField.getText().charAt(0), 'F');
//	}
//
//	@Test
//	public void testG() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('G');
//		Thread.sleep(40);
//		assertEquals(inputField.getText().charAt(0), 'G');
//	}
//
//	@Test
//	public void testH() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('H');
//		Thread.sleep(40);
//		assertEquals(inputField.getText().charAt(0), 'H');
//	}
//
//	@Test
//	public void testI() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('I');
//		Thread.sleep(40);
//		assertEquals(inputField.getText().charAt(0), 'I');
//	}
//
//	@Test
//	public void testJ() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('J');
//		Thread.sleep(40);
//		assertEquals(inputField.getText().charAt(0), 'J');
//	}
//
//	@Test
//	public void testK() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('K');
//		Thread.sleep(40);
//		assertEquals(inputField.getText().charAt(0), 'K');
//	}
//
//	@Test
//	public void testL() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('L');
//		Thread.sleep(40);
//		assertEquals(inputField.getText().charAt(0), 'L');
//	}
//
//	@Test
//	public void testM() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('M');
//		Thread.sleep(40);
//		assertEquals(inputField.getText().charAt(0), 'M');
//	}
//
//	@Test
//	public void testN() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('N');
//		Thread.sleep(40);
//		assertEquals(inputField.getText().charAt(0), 'N');
//	}
//
//	@Test
//	public void testO() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('O');
//		Thread.sleep(40);
//		assertEquals(inputField.getText().charAt(0), 'O');
//	}
//
//	@Test
//	public void testP() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('P');
//		Thread.sleep(40);
//		assertEquals(inputField.getText().charAt(0), 'P');
//	}
//
//	@Test
//	public void testQ() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('Q');
//		Thread.sleep(40);
//		assertEquals(inputField.getText().charAt(0), 'Q');
//	}
//
//	@Test
//	public void testR() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('R');
//		Thread.sleep(40);
//		assertEquals(inputField.getText().charAt(0), 'R');
//	}
//
//	@Test
//	public void testS() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('S');
//		Thread.sleep(40);
//		assertEquals(inputField.getText().charAt(0), 'S');
//	}
//
//	@Test
//	public void testT() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('T');
//		Thread.sleep(40);
//		assertEquals(inputField.getText().charAt(0), 'T');
//	}
//
//	@Test
//	public void testU() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('U');
//		Thread.sleep(40);
//		assertEquals(inputField.getText().charAt(0), 'U');
//	}
//
//	@Test
//	public void testV() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('V');
//		Thread.sleep(40);
//		assertEquals(inputField.getText().charAt(0), 'V');
//	}
//
//	@Test
//	public void testW() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('W');
//		Thread.sleep(40);
//		assertEquals(inputField.getText().charAt(0), 'W');
//	}
//
//	@Test
//	public void testX() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('X');
//		Thread.sleep(40);
//		assertEquals(inputField.getText().charAt(0), 'X');
//	}
//
//	@Test
//	public void testY() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('Y');
//		Thread.sleep(40);
//		assertEquals(inputField.getText().charAt(0), 'Y');
//	}
//
//	@Test
//	public void testZ() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('Z');
//		Thread.sleep(40);
//		assertEquals(inputField.getText().charAt(0), 'Z');
//	}
//
//	@Test
//	public void test1() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('1');
//		Thread.sleep(40);
//		assertEquals(inputField.getText().charAt(0), '1');
//	}
//
//	@Test
//	public void test2() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('2');
//		Thread.sleep(40);
//		assertEquals(inputField.getText().charAt(0), '2');
//	}
//
//	@Test
//	public void test3() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('3');
//		Thread.sleep(40);
//		assertEquals(inputField.getText().charAt(0), '3');
//	}
//
//	@Test
//	public void test4() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('4');
//		Thread.sleep(40);
//		assertEquals(inputField.getText().charAt(0), '4');
//	}
//
//	@Test
//	public void test5() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('5');
//		Thread.sleep(40);
//		assertEquals(inputField.getText().charAt(0), '5');
//	}
//
//	@Test
//	public void test6() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('6');
//		Thread.sleep(40);
//		assertEquals(inputField.getText().charAt(0), '6');
//	}
//
//	@Test
//	public void test7() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('7');
//		Thread.sleep(40);
//		assertEquals(inputField.getText().charAt(0), '7');
//	}
//
//	@Test
//	public void test8() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('8');
//		Thread.sleep(40);
//		assertEquals(inputField.getText().charAt(0), '8');
//	}
//
//	@Test
//	public void test9() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('9');
//		Thread.sleep(40);
//		assertEquals(inputField.getText().charAt(0), '9');
//	}
//
//	@Test
//	public void test0() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('0');
//		Thread.sleep(40);
//		assertEquals(inputField.getText().charAt(0), '0');
//	}
//
//	@Test
//	public void testexclamation() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('!');
//		Thread.sleep(40);
//		assertEquals(inputField.getText().charAt(0), '!');
//	}
//
//	@Test
//	public void testat() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('@');
//		Thread.sleep(40);
//		assertEquals(inputField.getText().charAt(0), '@');
//	}
//
//	@Test
//	public void testpound() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('#');
//		Thread.sleep(40);
//		assertEquals(inputField.getText().charAt(0), '#');
//	}
//
//	@Test
//	public void testdollar() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('$');
//		Thread.sleep(40);
//		assertEquals(inputField.getText().charAt(0), '$');
//	}
//
//	@Test
//	public void testpercent() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('%');
//		Thread.sleep(40);
//		assertEquals(inputField.getText().charAt(0), '%');
//	}
//
//	@Test
//	public void testexponent() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('^');
//		Thread.sleep(40);
//		assertEquals(inputField.getText().charAt(0), '^');
//	}
//
//	@Test
//	public void testampersand() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('&');
//		Thread.sleep(40);
//		assertEquals(inputField.getText().charAt(0), '&');
//	}
//
//	@Test
//	public void testasterisk() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('*');
//		Thread.sleep(40);
//		assertEquals(inputField.getText().charAt(0), '*');
//	}
//
//	@Test
//	public void testopenparentheses() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('(');
//		Thread.sleep(40);
//		assertEquals(inputField.getText().charAt(0), '(');
//	}
//
//	@Test
//	public void testcloseparentheses() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown(')');
//		Thread.sleep(40);
//		assertEquals(inputField.getText().charAt(0), ')');
//	}
//
//	@Test
//	public void testminus() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('-');
//		Thread.sleep(40);
//		assertEquals(inputField.getText().charAt(0), '-');
//	}
//
//	@Test
//	public void testequals() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('=');
//		Thread.sleep(40);
//		assertEquals(inputField.getText().charAt(0), '=');
//	}
//
//	@Test
//	public void testopenbracket() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('[');
//		Thread.sleep(40);
//		assertEquals(inputField.getText().charAt(0), '[');
//	}
//
//	@Test
//	public void testclosebracket() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown(']');
//		Thread.sleep(40);
//		assertEquals(inputField.getText().charAt(0), ']');
//	}
//
//	@Test
//	public void testbackslash() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('\\');
//		Thread.sleep(40);
//		assertEquals(inputField.getText().charAt(0), '\\');
//	}
//
//	@Test
//	public void testsemicolon() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown(';');
//		Thread.sleep(40);
//		assertEquals(inputField.getText().charAt(0), ';');
//	}
//
//	@Test
//	public void testapostrophe() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('\'');
//		Thread.sleep(40);
//		assertEquals(inputField.getText().charAt(0), '\'');
//	}
//
//	@Test
//	public void testcomma() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown(',');
//		Thread.sleep(40);
//		assertEquals(inputField.getText().charAt(0), ',');
//	}
//
//	@Test
//	public void testperiod() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('.');
//		Thread.sleep(40);
//		assertEquals(inputField.getText().charAt(0), '.');
//	}
//
//	@Test
//	public void testslash() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('/');
//		Thread.sleep(40);
//		assertEquals(inputField.getText().charAt(0), '/');
//	}
//
//	@Test
//	public void testbackquote() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('`');
//		Thread.sleep(40);
//		assertEquals(inputField.getText().charAt(0), '`');
//	}
//
//	@Test
//	public void testunderscore() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('_');
//		Thread.sleep(40);
//		assertEquals(inputField.getText().charAt(0), '_');
//	}
//
//	@Test
//	public void testplus() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('+');
//		Thread.sleep(40);
//		assertEquals(inputField.getText().charAt(0), '+');
//	}
//
//	@Test
//	public void testopenbrace() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('{');
//		Thread.sleep(40);
//		assertEquals(inputField.getText().charAt(0), '{');
//	}
//
//	@Test
//	public void testclosebrace() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('}');
//		Thread.sleep(40);
//		assertEquals(inputField.getText().charAt(0), '}');
//	}
//
//	@Test
//	public void testline() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('|');
//		Thread.sleep(40);
//		assertEquals(inputField.getText().charAt(0), '|');
//	}
//
//	@Test
//	public void testcolon() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown(':');
//		Thread.sleep(40);
//		assertEquals(inputField.getText().charAt(0), ':');
//	}
//
//	@Test
//	public void testquote() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('"');
//		Thread.sleep(40);
//		assertEquals(inputField.getText().charAt(0), '"');
//	}
//
//	@Test
//	public void testleftarrow() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('<');
//		Thread.sleep(40);
//		assertEquals(inputField.getText().charAt(0), '<');
//	}
//
//	@Test
//	public void testrightarrow() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('>');
//		Thread.sleep(40);
//		assertEquals(inputField.getText().charAt(0), '>');
//	}
//
//	@Test
//	public void testquestion() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('?');
//		Thread.sleep(40);
//		assertEquals(inputField.getText().charAt(0), '?');
//	}
//
//	@Test
//	public void testtilde() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('~');
//		Thread.sleep(40);
//		assertEquals(inputField.getText().charAt(0), '~');
//	}
//
//	@Test
//	public void testspace() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown(' ');
//		Thread.sleep(40);
//		assertEquals(inputField.getText().charAt(0), ' ');
//	}
//
//	@Test
//	public void testenter() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('\n');
//		Thread.sleep(40);
//		assertEquals(input, '\n');
//	}
//
//	@Test
//	public void testdelete() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('\b');
//		Thread.sleep(40);
//		assertEquals(input, '\b');
//	}
//
//	@Test
//	public void testtab() throws Exception {
//		inputFrame.setVisible(true);
//		inputField.requestFocusInWindow();
//		Thread.sleep(90);
//		touchScreen.OnKeyDown('\t');
//		Thread.sleep(40);
//		assertEquals(input, '\t');
//	}
//
//}
