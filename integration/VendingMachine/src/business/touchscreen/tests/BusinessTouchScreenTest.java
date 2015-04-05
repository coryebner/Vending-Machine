package business.touchscreen.tests;

import static org.junit.Assert.*;

import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Scanner;

import javax.swing.JTextField;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import business.touchscreen.BusinessTouchScreen;
import business.touchscreen.BusinessTouchScreenListener;

public class BusinessTouchScreenTest {

	class ScreenListener implements BusinessTouchScreenListener {

		char pressed = ' ';
		boolean notified = false;
		
		@Override
		public void userKeyboardInput(char userInput) {
			pressed = userInput;
			notified = true;
		}
		
		public String getKeyPress(){
			if(notified)
				return (String.valueOf(pressed));
			else 
				return ("Nothing");
		}
		
	}
	
	ScreenListener screenListener = null;
	BusinessTouchScreen touchScreen = null;
	
	//The tests will imitate keyboard presses, so if your cursor is not on the console
	//the test will start printing random things all over your code or wherever your 
	//cursor is focused on.
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Scanner scan = new Scanner(System.in);
		System.out.println("Please Click the Console and press Enter to begin test!");
		scan.nextLine();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		screenListener = new ScreenListener();
		touchScreen = new BusinessTouchScreen();
		
		touchScreen.register(screenListener);
	}

	@After
	public void tearDown() throws Exception {
	}
	
	
	
	@Test
	public final void testListener_a() {
		touchScreen.OnKeyDown('a');
		assertTrue(screenListener.getKeyPress().equals("a"));
	}
	@Test
	public final void testListener_b() {
		touchScreen.OnKeyDown('b');
		assertTrue(screenListener.getKeyPress().equals("b"));
	}
	@Test
	public final void testListener_c() {
		touchScreen.OnKeyDown('c');
		assertTrue(screenListener.getKeyPress().equals("c"));
	}
	@Test
	public final void testListener_d() {
		touchScreen.OnKeyDown('d');
		assertTrue(screenListener.getKeyPress().equals("d"));
	}
	@Test
	public final void testListener_e() {
		touchScreen.OnKeyDown('e');
		assertTrue(screenListener.getKeyPress().equals("e"));
	}
	@Test
	public final void testListener_f() {
		touchScreen.OnKeyDown('f');
		assertTrue(screenListener.getKeyPress().equals("f"));
	}
	@Test
	public final void testListener_g() {
		touchScreen.OnKeyDown('g');
		assertTrue(screenListener.getKeyPress().equals("g"));
	}
	@Test
	public final void testListener_h() {
		touchScreen.OnKeyDown('h');
		assertTrue(screenListener.getKeyPress().equals("h"));
	}
	@Test
	public final void testListener_i() {
		touchScreen.OnKeyDown('i');
		assertTrue(screenListener.getKeyPress().equals("i"));
	}
	@Test
	public final void testListener_j() {
		touchScreen.OnKeyDown('j');
		assertTrue(screenListener.getKeyPress().equals("j"));
	}
	@Test
	public final void testListener_k() {
		touchScreen.OnKeyDown('k');
		assertTrue(screenListener.getKeyPress().equals("k"));
	}
	@Test
	public final void testListener_l() {
		touchScreen.OnKeyDown('l');
		assertTrue(screenListener.getKeyPress().equals("l"));
	}
	@Test
	public final void testListener_m() {
		touchScreen.OnKeyDown('m');
		assertTrue(screenListener.getKeyPress().equals("m"));
	}
	@Test
	public final void testListener_n() {
		touchScreen.OnKeyDown('n');
		assertTrue(screenListener.getKeyPress().equals("n"));
	}
	@Test
	public final void testListener_o() {
		touchScreen.OnKeyDown('o');
		assertTrue(screenListener.getKeyPress().equals("o"));
	}
	@Test
	public final void testListener_p() {
		touchScreen.OnKeyDown('p');
		assertTrue(screenListener.getKeyPress().equals("p"));
	}
	@Test
	public final void testListener_q() {
		touchScreen.OnKeyDown('q');
		assertTrue(screenListener.getKeyPress().equals("q"));
	}
	@Test
	public final void testListener_r() {
		touchScreen.OnKeyDown('r');
		assertTrue(screenListener.getKeyPress().equals("r"));
	}
	@Test
	public final void testListener_s() {
		touchScreen.OnKeyDown('s');
		assertTrue(screenListener.getKeyPress().equals("s"));
	}
	@Test
	public final void testListener_t() {
		touchScreen.OnKeyDown('t');
		assertTrue(screenListener.getKeyPress().equals("t"));
	}
	@Test
	public final void testListener_u() {
		touchScreen.OnKeyDown('u');
		assertTrue(screenListener.getKeyPress().equals("u"));
	}
	@Test
	public final void testListener_v() {
		touchScreen.OnKeyDown('v');
		assertTrue(screenListener.getKeyPress().equals("v"));
	}
	@Test
	public final void testListener_w() {
		touchScreen.OnKeyDown('w');
		assertTrue(screenListener.getKeyPress().equals("w"));
	}
	@Test
	public final void testListener_x() {
		touchScreen.OnKeyDown('x');
		assertTrue(screenListener.getKeyPress().equals("x"));
	}
	@Test
	public final void testListener_y() {
		touchScreen.OnKeyDown('y');
		assertTrue(screenListener.getKeyPress().equals("y"));
	}
	@Test
	public final void testListener_z() {
		touchScreen.OnKeyDown('z');
		assertTrue(screenListener.getKeyPress().equals("z"));
	}

	@Test
	public final void testListener_A() {
		touchScreen.OnKeyDown('A');
		assertTrue(screenListener.getKeyPress().equals("A"));
	}
	@Test
	public final void testListener_B() {
		touchScreen.OnKeyDown('B');
		assertTrue(screenListener.getKeyPress().equals("B"));
	}
	@Test
	public final void testListener_C() {
		touchScreen.OnKeyDown('C');
		assertTrue(screenListener.getKeyPress().equals("C"));
	}
	@Test
	public final void testListener_D() {
		touchScreen.OnKeyDown('D');
		assertTrue(screenListener.getKeyPress().equals("D"));
	}
	@Test
	public final void testListener_E() {
		touchScreen.OnKeyDown('E');
		assertTrue(screenListener.getKeyPress().equals("E"));
	}
	@Test
	public final void testListener_F() {
		touchScreen.OnKeyDown('F');
		assertTrue(screenListener.getKeyPress().equals("F"));
	}
	@Test
	public final void testListener_G() {
		touchScreen.OnKeyDown('G');
		assertTrue(screenListener.getKeyPress().equals("G"));
	}
	@Test
	public final void testListener_H() {
		touchScreen.OnKeyDown('H');
		assertTrue(screenListener.getKeyPress().equals("H"));
	}
	@Test
	public final void testListener_I() {
		touchScreen.OnKeyDown('I');
		assertTrue(screenListener.getKeyPress().equals("I"));
	}
	@Test
	public final void testListener_J() {
		touchScreen.OnKeyDown('J');
		assertTrue(screenListener.getKeyPress().equals("J"));
	}
	@Test
	public final void testListener_K() {
		touchScreen.OnKeyDown('K');
		assertTrue(screenListener.getKeyPress().equals("K"));
	}
	@Test
	public final void testListener_L() {
		touchScreen.OnKeyDown('L');
		assertTrue(screenListener.getKeyPress().equals("L"));
	}
	@Test
	public final void testListener_M() {
		touchScreen.OnKeyDown('M');
		assertTrue(screenListener.getKeyPress().equals("M"));
	}
	@Test
	public final void testListener_N() {
		touchScreen.OnKeyDown('N');
		assertTrue(screenListener.getKeyPress().equals("N"));
	}
	@Test
	public final void testListener_O() {
		touchScreen.OnKeyDown('O');
		assertTrue(screenListener.getKeyPress().equals("O"));
	}
	@Test
	public final void testListener_P() {
		touchScreen.OnKeyDown('P');
		assertTrue(screenListener.getKeyPress().equals("P"));
	}
	@Test
	public final void testListener_Q() {
		touchScreen.OnKeyDown('Q');
		assertTrue(screenListener.getKeyPress().equals("Q"));
	}
	@Test
	public final void testListener_R() {
		touchScreen.OnKeyDown('R');
		assertTrue(screenListener.getKeyPress().equals("R"));
	}
	@Test
	public final void testListener_S() {
		touchScreen.OnKeyDown('S');
		assertTrue(screenListener.getKeyPress().equals("S"));
	}
	@Test
	public final void testListener_T() {
		touchScreen.OnKeyDown('T');
		assertTrue(screenListener.getKeyPress().equals("T"));
	}
	@Test
	public final void testListener_U() {
		touchScreen.OnKeyDown('U');
		assertTrue(screenListener.getKeyPress().equals("U"));
	}
	@Test
	public final void testListener_V() {
		touchScreen.OnKeyDown('V');
		assertTrue(screenListener.getKeyPress().equals("V"));
	}
	@Test
	public final void testListener_W() {
		touchScreen.OnKeyDown('W');
		assertTrue(screenListener.getKeyPress().equals("W"));
	}
	@Test
	public final void testListener_X() {
		touchScreen.OnKeyDown('X');
		assertTrue(screenListener.getKeyPress().equals("X"));
	}
	@Test
	public final void testListener_Y() {
		touchScreen.OnKeyDown('Y');
		assertTrue(screenListener.getKeyPress().equals("Y"));
	}
	@Test
	public final void testListener_Z() {
		touchScreen.OnKeyDown('Z');
		assertTrue(screenListener.getKeyPress().equals("Z"));
	}	
	@Test
	public final void testlistener_1(){
		touchScreen.OnKeyDown('1');
		assertTrue(screenListener.getKeyPress().equals("1"));
	}	
	@Test
	public final void testlistener_2(){
		touchScreen.OnKeyDown('2');
		assertTrue(screenListener.getKeyPress().equals("2"));
	}
	@Test
	public final void testlistener_3(){
		touchScreen.OnKeyDown('3');
		assertTrue(screenListener.getKeyPress().equals("3"));
	}
	@Test
	public final void testlistener_4(){
		touchScreen.OnKeyDown('4');
		assertTrue(screenListener.getKeyPress().equals("4"));
	}
	@Test
	public final void testlistener_5(){
		touchScreen.OnKeyDown('5');
		assertTrue(screenListener.getKeyPress().equals("5"));
	}
	@Test
	public final void testlistener_6(){
		touchScreen.OnKeyDown('6');
		assertTrue(screenListener.getKeyPress().equals("6"));
	}
	@Test
	public final void testlistener_7(){
		touchScreen.OnKeyDown('7');
		assertTrue(screenListener.getKeyPress().equals("7"));
	}
	@Test
	public final void testlistener_8(){
		touchScreen.OnKeyDown('8');
		assertTrue(screenListener.getKeyPress().equals("8"));
	}
	@Test
	public final void testlistener_9(){
		touchScreen.OnKeyDown('9');
		assertTrue(screenListener.getKeyPress().equals("9"));
	}

	@Test
	public final void testlistener_rightBracket(){
		touchScreen.OnKeyDown(')');
		assertTrue(screenListener.getKeyPress().equals(")"));
	}
	
	@Test
	public final void testlistener_exclamation(){
		touchScreen.OnKeyDown('!');
		assertTrue(screenListener.getKeyPress().equals("!"));
	}
	
	@Test
	public final void testlistener_atSymbol(){
		touchScreen.OnKeyDown('@');
		assertTrue(screenListener.getKeyPress().equals("@"));
	}
	
	@Test
	public final void testlistener_hash(){
		touchScreen.OnKeyDown('#');
		assertTrue(screenListener.getKeyPress().equals("#"));
	}
	
	@Test
	public final void testlistener_dollarSign(){
		touchScreen.OnKeyDown('$');
		assertTrue(screenListener.getKeyPress().equals("$"));
	}
	
	@Test
	public final void testlistener_modulo(){
		touchScreen.OnKeyDown('%');
		assertTrue(screenListener.getKeyPress().equals("%"));
	}
	
	@Test
	public final void testlistener_caret(){
		touchScreen.OnKeyDown('^');
		assertTrue(screenListener.getKeyPress().equals("^"));
	}
	
	@Test
	public final void testlistener_amperstamp(){
		touchScreen.OnKeyDown('&');
		assertTrue(screenListener.getKeyPress().equals("&"));
	}
	
	@Test
	public final void testlistener_star(){
		touchScreen.OnKeyDown('*');
		assertTrue(screenListener.getKeyPress().equals("*"));
	}
	
	@Test
	public final void testlistener_leftBracket(){
		touchScreen.OnKeyDown('(');
		assertTrue(screenListener.getKeyPress().equals("("));
	}
	
	
	// RANDOM OTHER CHARACTERS
	
	@Test
	public final void testlistener_tick(){
		touchScreen.OnKeyDown('`');
		assertTrue(screenListener.getKeyPress().equals("`"));
	}
	
	@Test
	public final void testlistener_Squigly(){
		touchScreen.OnKeyDown('~');
		assertTrue(screenListener.getKeyPress().equals("~"));
	}
	
	@Test
	public final void testlistener_dash(){
		touchScreen.OnKeyDown('-');
		assertTrue(screenListener.getKeyPress().equals("-"));
	}
	
	@Test
	public final void testlistener_underscore(){
		touchScreen.OnKeyDown('_');
		assertTrue(screenListener.getKeyPress().equals("_"));
	}
	
	@Test
	public final void testlistener_equals(){
		touchScreen.OnKeyDown('=');
		assertTrue(screenListener.getKeyPress().equals("="));
	}
	
	@Test
	public final void testlistener_plus(){
		touchScreen.OnKeyDown('+');
		assertTrue(screenListener.getKeyPress().equals("+"));
	}
	
	@Test
	public final void testlistener_leftSquareBracket(){
		touchScreen.OnKeyDown('[');
		assertTrue(screenListener.getKeyPress().equals("["));
	}
	
	@Test
	public final void testlistener_leftParenthesis(){
		touchScreen.OnKeyDown('{');
		assertTrue(screenListener.getKeyPress().equals("{"));
	}
	
	@Test
	public final void testlistener_rightSquareBracket(){
		touchScreen.OnKeyDown(']');
		assertTrue(screenListener.getKeyPress().equals("]"));
	}
	
	@Test
	public final void testlistener_rightParenthesis(){
		touchScreen.OnKeyDown('}');
		assertTrue(screenListener.getKeyPress().equals("}"));
	}
	
	@Test
	public final void testlistener_backslash(){
		touchScreen.OnKeyDown('\\');
		assertTrue(screenListener.getKeyPress().equals("\\"));
	}
	
	@Test
	public final void testlistener_pipe(){
		touchScreen.OnKeyDown('|');
		assertTrue(screenListener.getKeyPress().equals("|"));
	}
	
	@Test
	public final void testlistener_semicolon(){
		touchScreen.OnKeyDown(';');
		assertTrue(screenListener.getKeyPress().equals(";"));
	}
	
	@Test
	public final void testlistener_colon(){
		touchScreen.OnKeyDown(':');
		assertTrue(screenListener.getKeyPress().equals(":"));
	}
	
	@Test
	public final void testlistener_singlequote(){
		touchScreen.OnKeyDown('\'');
		assertTrue(screenListener.getKeyPress().equals("\'"));
	}
	
	@Test
	public final void testlistener_quotation(){
		touchScreen.OnKeyDown('"');
		assertTrue(screenListener.getKeyPress().equals("\""));
	}
	
	@Test
	public final void testlistener_comma(){
		touchScreen.OnKeyDown(',');
		assertTrue(screenListener.getKeyPress().equals(","));
	}
	
	@Test
	public final void testlistener_leftAngleBracket(){
		touchScreen.OnKeyDown('<');
		assertTrue(screenListener.getKeyPress().equals("<"));
	}
	
	@Test
	public final void testlistener_period(){
		touchScreen.OnKeyDown('.');
		assertTrue(screenListener.getKeyPress().equals("."));
	}
	
	@Test
	public final void testlistener_rightAngleBracket(){
		touchScreen.OnKeyDown('>');
		assertTrue(screenListener.getKeyPress().equals(">"));
	}
	
	@Test
	public final void testlistener_slash(){
		touchScreen.OnKeyDown('/');
		assertTrue(screenListener.getKeyPress().equals("/"));
	}
	
	@Test
	public final void testlistener_questionMark(){
		touchScreen.OnKeyDown('?');
		assertTrue(screenListener.getKeyPress().equals("?"));
	}
	
		
	// NEED TO FIX
	@Test
	public final void testlistener_backspace(){
		touchScreen.OnKeyDown('\b');
		assertTrue(screenListener.getKeyPress().equals("\b"));
	}
	
	@Test
	public final void testlistener_newline(){
		touchScreen.OnKeyDown('\n');
		assertTrue(screenListener.getKeyPress().equals("\n"));
	}
	
	@Test
	public final void testlistener_enter(){
		touchScreen.OnKeyDown('\r');
		assertTrue(screenListener.getKeyPress().equals("\r"));
	}
	
	@Test
	public final void testlistener_tab(){
		touchScreen.OnKeyDown('\t');
		assertTrue(screenListener.getKeyPress().equals("\t"));
	}
	
	@Test
	public final void testlistener_space(){
		touchScreen.OnKeyDown(' ');
		assertTrue(screenListener.getKeyPress().equals(" "));
	}
	
}
	
	

