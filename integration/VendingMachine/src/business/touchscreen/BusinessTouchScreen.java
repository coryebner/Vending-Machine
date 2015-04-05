package business.touchscreen;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import gui.*;

public class BusinessTouchScreen {
//implements VirtualKeyboardListener{

	private ArrayList<BusinessTouchScreenListener> listeners = new ArrayList<BusinessTouchScreenListener>();
	Robot robot;
	
	
	public BusinessTouchScreen() {
		try {
			robot = new Robot();
		} catch (Exception e) {
			System.out.println("Could not create Robot");
		}
	}
	
	
	//This should be the listener on the hardware GUI, name might change depending on their interface
	public void OnKeyDown(char character){
		simulateKeyPress(character);
		notifyUserKeyboard(character);
	}
	
	
	private void simulateKeyPress(char input){
		switch (input		
				) {

		// UPPERCASE ASCII LETTERS
		case 'A':
			robot.keyPress(KeyEvent.VK_SHIFT);
			robot.keyPress(KeyEvent.VK_A);
			robot.keyRelease(KeyEvent.VK_A);
			robot.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case 'B':
			robot.keyPress(KeyEvent.VK_SHIFT);
			robot.keyPress(KeyEvent.VK_B);
			robot.keyRelease(KeyEvent.VK_B);
			robot.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case 'C':
			robot.keyPress(KeyEvent.VK_SHIFT);
			robot.keyPress(KeyEvent.VK_C);
			robot.keyRelease(KeyEvent.VK_C);
			robot.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case 'D':
			robot.keyPress(KeyEvent.VK_SHIFT);
			robot.keyPress(KeyEvent.VK_D);
			robot.keyRelease(KeyEvent.VK_D);
			robot.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case 'E':
			robot.keyPress(KeyEvent.VK_SHIFT);
			robot.keyPress(KeyEvent.VK_E);
			robot.keyRelease(KeyEvent.VK_E);
			robot.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case 'F':
			robot.keyPress(KeyEvent.VK_SHIFT);
			robot.keyPress(KeyEvent.VK_F);
			robot.keyRelease(KeyEvent.VK_F);
			robot.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case 'G':
			robot.keyPress(KeyEvent.VK_SHIFT);
			robot.keyPress(KeyEvent.VK_G);
			robot.keyRelease(KeyEvent.VK_G);
			robot.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case 'H':
			robot.keyPress(KeyEvent.VK_SHIFT);
			robot.keyPress(KeyEvent.VK_H);
			robot.keyRelease(KeyEvent.VK_H);
			robot.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case 'I':
			robot.keyPress(KeyEvent.VK_SHIFT);
			robot.keyPress(KeyEvent.VK_I);
			robot.keyRelease(KeyEvent.VK_I);
			robot.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case 'J':
			robot.keyPress(KeyEvent.VK_SHIFT);
			robot.keyPress(KeyEvent.VK_J);
			robot.keyRelease(KeyEvent.VK_J);
			robot.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case 'K':
			robot.keyPress(KeyEvent.VK_SHIFT);
			robot.keyPress(KeyEvent.VK_K);
			robot.keyRelease(KeyEvent.VK_K);
			robot.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case 'L':
			robot.keyPress(KeyEvent.VK_SHIFT);
			robot.keyPress(KeyEvent.VK_L);
			robot.keyRelease(KeyEvent.VK_L);
			robot.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case 'M':
			robot.keyPress(KeyEvent.VK_SHIFT);
			robot.keyPress(KeyEvent.VK_M);
			robot.keyRelease(KeyEvent.VK_M);
			robot.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case 'N':
			robot.keyPress(KeyEvent.VK_SHIFT);
			robot.keyPress(KeyEvent.VK_N);
			robot.keyRelease(KeyEvent.VK_N);
			robot.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case 'O':
			robot.keyPress(KeyEvent.VK_SHIFT);
			robot.keyPress(KeyEvent.VK_O);
			robot.keyRelease(KeyEvent.VK_O);
			robot.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case 'P':
			robot.keyPress(KeyEvent.VK_SHIFT);
			robot.keyPress(KeyEvent.VK_P);
			robot.keyRelease(KeyEvent.VK_P);
			robot.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case 'Q':
			robot.keyPress(KeyEvent.VK_SHIFT);
			robot.keyPress(KeyEvent.VK_Q);
			robot.keyRelease(KeyEvent.VK_Q);
			robot.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case 'R':
			robot.keyPress(KeyEvent.VK_SHIFT);
			robot.keyPress(KeyEvent.VK_R);
			robot.keyRelease(KeyEvent.VK_R);
			robot.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case 'S':
			robot.keyPress(KeyEvent.VK_SHIFT);
			robot.keyPress(KeyEvent.VK_S);
			robot.keyRelease(KeyEvent.VK_S);
			robot.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case 'T':
			robot.keyPress(KeyEvent.VK_SHIFT);
			robot.keyPress(KeyEvent.VK_T);
			robot.keyRelease(KeyEvent.VK_T);
			robot.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case 'U':
			robot.keyPress(KeyEvent.VK_SHIFT);
			robot.keyPress(KeyEvent.VK_U);
			robot.keyRelease(KeyEvent.VK_U);
			robot.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case 'V':
			robot.keyPress(KeyEvent.VK_SHIFT);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case 'W':
			robot.keyPress(KeyEvent.VK_SHIFT);
			robot.keyPress(KeyEvent.VK_W);
			robot.keyRelease(KeyEvent.VK_W);
			robot.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case 'X':
			robot.keyPress(KeyEvent.VK_SHIFT);
			robot.keyPress(KeyEvent.VK_X);
			robot.keyRelease(KeyEvent.VK_X);
			robot.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case 'Y':
			robot.keyPress(KeyEvent.VK_SHIFT);
			robot.keyPress(KeyEvent.VK_Y);
			robot.keyRelease(KeyEvent.VK_Y);
			robot.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case 'Z':
			robot.keyPress(KeyEvent.VK_SHIFT);
			robot.keyPress(KeyEvent.VK_Z);
			robot.keyRelease(KeyEvent.VK_Z);
			robot.keyRelease(KeyEvent.VK_SHIFT);
			break;

		// LOWERCASE ASCII LETTERS

		// UPPERCASE ASCII LETTERS
		case 'a':
			robot.keyPress(KeyEvent.VK_A);
			robot.keyRelease(KeyEvent.VK_A);
			break;
		case 'b':
			robot.keyPress(KeyEvent.VK_B);
			robot.keyRelease(KeyEvent.VK_B);
			break;
		case 'c':
			robot.keyPress(KeyEvent.VK_C);
			robot.keyRelease(KeyEvent.VK_C);
			break;
		case 'd':
			robot.keyPress(KeyEvent.VK_D);
			robot.keyRelease(KeyEvent.VK_D);
			break;
		case 'e':
			robot.keyPress(KeyEvent.VK_E);
			robot.keyRelease(KeyEvent.VK_E);
			break;
		case 'f':
			robot.keyPress(KeyEvent.VK_F);
			robot.keyRelease(KeyEvent.VK_F);
			break;
		case 'g':
			robot.keyPress(KeyEvent.VK_G);
			robot.keyRelease(KeyEvent.VK_G);
			break;
		case 'h':
			robot.keyPress(KeyEvent.VK_H);
			robot.keyRelease(KeyEvent.VK_H);
			break;
		case 'i':
			robot.keyPress(KeyEvent.VK_I);
			robot.keyRelease(KeyEvent.VK_I);
			break;
		case 'j':
			robot.keyPress(KeyEvent.VK_J);
			robot.keyRelease(KeyEvent.VK_J);
			break;
		case 'k':
			robot.keyPress(KeyEvent.VK_K);
			robot.keyRelease(KeyEvent.VK_K);
			break;
		case 'l':
			robot.keyPress(KeyEvent.VK_L);
			robot.keyRelease(KeyEvent.VK_L);
			break;
		case 'm':
			robot.keyPress(KeyEvent.VK_M);
			robot.keyRelease(KeyEvent.VK_M);
			break;
		case 'n':
			robot.keyPress(KeyEvent.VK_N);
			robot.keyRelease(KeyEvent.VK_N);
			break;
		case 'o':
			robot.keyPress(KeyEvent.VK_O);
			robot.keyRelease(KeyEvent.VK_O);
			break;
		case 'p':
			robot.keyPress(KeyEvent.VK_P);
			robot.keyRelease(KeyEvent.VK_P);
			break;
		case 'q':
			robot.keyPress(KeyEvent.VK_Q);
			robot.keyRelease(KeyEvent.VK_Q);
			break;
		case 'r':
			robot.keyPress(KeyEvent.VK_R);
			robot.keyRelease(KeyEvent.VK_R);
			break;
		case 's':
			robot.keyPress(KeyEvent.VK_S);
			robot.keyRelease(KeyEvent.VK_S);
			break;
		case 't':
			robot.keyPress(KeyEvent.VK_T);
			robot.keyRelease(KeyEvent.VK_T);
			break;
		case 'u':
			robot.keyPress(KeyEvent.VK_U);
			robot.keyRelease(KeyEvent.VK_U);
			break;
		case 'v':
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_V);
			break;
		case 'w':
			robot.keyPress(KeyEvent.VK_W);
			robot.keyRelease(KeyEvent.VK_W);
			break;
		case 'x':
			robot.keyPress(KeyEvent.VK_X);
			robot.keyRelease(KeyEvent.VK_X);
			break;
		case 'y':
			robot.keyPress(KeyEvent.VK_Y);
			robot.keyRelease(KeyEvent.VK_Y);
			break;
		case 'z':
			robot.keyPress(KeyEvent.VK_Z);
			robot.keyRelease(KeyEvent.VK_Z);
			break;
			
		// NUMBERS
		case '0':
			robot.keyPress(KeyEvent.VK_0);
			robot.keyRelease(KeyEvent.VK_0);
			break;
		case '1':
			robot.keyPress(KeyEvent.VK_1);
			robot.keyRelease(KeyEvent.VK_1);
			break;
		case '2':
			robot.keyPress(KeyEvent.VK_2);
			robot.keyRelease(KeyEvent.VK_2);
			break;
		case '3':
			robot.keyPress(KeyEvent.VK_3);
			robot.keyRelease(KeyEvent.VK_3);
			break;
		case '4':
			robot.keyPress(KeyEvent.VK_4);
			robot.keyRelease(KeyEvent.VK_4);
			break;
		case '5':
			robot.keyPress(KeyEvent.VK_5);
			robot.keyRelease(KeyEvent.VK_5);
			break;
		case '6':
			robot.keyPress(KeyEvent.VK_6);
			robot.keyRelease(KeyEvent.VK_6);
			break;
		case '7':
			robot.keyPress(KeyEvent.VK_7);
			robot.keyRelease(KeyEvent.VK_7);
			break;
		case '8':
			robot.keyPress(KeyEvent.VK_8);
			robot.keyRelease(KeyEvent.VK_8);
			break;
		case '9':
			robot.keyPress(KeyEvent.VK_9);
			robot.keyRelease(KeyEvent.VK_9);
			break;
			
		// NUMBERS SHIFT VARIANTS
			
		case ')':
			robot.keyPress(KeyEvent.VK_SHIFT);
			robot.keyPress(KeyEvent.VK_0);
			robot.keyRelease(KeyEvent.VK_0);
			robot.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case '!':
			robot.keyPress(KeyEvent.VK_SHIFT);
			robot.keyPress(KeyEvent.VK_1);
			robot.keyRelease(KeyEvent.VK_1);
			robot.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case '@':
			robot.keyPress(KeyEvent.VK_SHIFT);
			robot.keyPress(KeyEvent.VK_2);
			robot.keyRelease(KeyEvent.VK_2);
			robot.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case '#':
			robot.keyPress(KeyEvent.VK_SHIFT);
			robot.keyPress(KeyEvent.VK_3);
			robot.keyRelease(KeyEvent.VK_3);
			robot.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case '$':
			robot.keyPress(KeyEvent.VK_SHIFT);
			robot.keyPress(KeyEvent.VK_4);
			robot.keyRelease(KeyEvent.VK_4);
			robot.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case '%':
			robot.keyPress(KeyEvent.VK_SHIFT);
			robot.keyPress(KeyEvent.VK_5);
			robot.keyRelease(KeyEvent.VK_5);
			robot.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case '^':
			robot.keyPress(KeyEvent.VK_SHIFT);
			robot.keyPress(KeyEvent.VK_6);
			robot.keyRelease(KeyEvent.VK_6);
			robot.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case '&':
			robot.keyPress(KeyEvent.VK_SHIFT);
			robot.keyPress(KeyEvent.VK_7);
			robot.keyRelease(KeyEvent.VK_7);
			robot.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case '*':
			robot.keyPress(KeyEvent.VK_SHIFT);
			robot.keyPress(KeyEvent.VK_8);
			robot.keyRelease(KeyEvent.VK_8);
			robot.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case '(':
			robot.keyPress(KeyEvent.VK_SHIFT);
			robot.keyPress(KeyEvent.VK_9);
			robot.keyRelease(KeyEvent.VK_9);
			robot.keyRelease(KeyEvent.VK_SHIFT);
			break;
		
		// RANDOM OTHER CHARACTERS
		
		case '`':
			robot.keyPress(KeyEvent.VK_BACK_QUOTE);
			robot.keyRelease(KeyEvent.VK_BACK_QUOTE);
			break;
		case '~':
			robot.keyPress(KeyEvent.VK_SHIFT);
			robot.keyPress(KeyEvent.VK_BACK_QUOTE);
			robot.keyRelease(KeyEvent.VK_BACK_QUOTE);
			robot.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case '-':
			robot.keyPress(KeyEvent.VK_MINUS);
			robot.keyRelease(KeyEvent.VK_MINUS);
			break;
		case '_':
			robot.keyPress(KeyEvent.VK_SHIFT);
			robot.keyPress(KeyEvent.VK_MINUS);
			robot.keyRelease(KeyEvent.VK_MINUS);
			robot.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case '=':
			robot.keyPress(KeyEvent.VK_EQUALS);
			robot.keyRelease(KeyEvent.VK_EQUALS);
			break;
		case '+':
			robot.keyPress(KeyEvent.VK_SHIFT);
			robot.keyPress(KeyEvent.VK_EQUALS);
			robot.keyRelease(KeyEvent.VK_EQUALS);
			robot.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case '[':
			robot.keyPress(KeyEvent.VK_OPEN_BRACKET);
			robot.keyRelease(KeyEvent.VK_OPEN_BRACKET);
			break;
		case '{':
			robot.keyPress(KeyEvent.VK_SHIFT);
			robot.keyPress(KeyEvent.VK_OPEN_BRACKET);
			robot.keyRelease(KeyEvent.VK_OPEN_BRACKET);
			robot.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case ']':
			robot.keyPress(KeyEvent.VK_CLOSE_BRACKET);
			robot.keyRelease(KeyEvent.VK_CLOSE_BRACKET);
			break;
		case '}':
			robot.keyPress(KeyEvent.VK_SHIFT);
			robot.keyPress(KeyEvent.VK_CLOSE_BRACKET);
			robot.keyRelease(KeyEvent.VK_CLOSE_BRACKET);
			robot.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case '\\':
			robot.keyPress(KeyEvent.VK_BACK_SLASH);
			robot.keyRelease(KeyEvent.VK_BACK_SLASH);
			break;
		case '|':
			robot.keyPress(KeyEvent.VK_SHIFT);
			robot.keyPress(KeyEvent.VK_BACK_SLASH);
			robot.keyRelease(KeyEvent.VK_BACK_SLASH);
			robot.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case ';':
			robot.keyPress(KeyEvent.VK_SEMICOLON);
			robot.keyRelease(KeyEvent.VK_SEMICOLON);
			break;
		case ':':
			robot.keyPress(KeyEvent.VK_SHIFT);
			robot.keyPress(KeyEvent.VK_SEMICOLON);
			robot.keyRelease(KeyEvent.VK_SEMICOLON);
			robot.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case '\'':
			robot.keyPress(KeyEvent.VK_QUOTE);
			robot.keyRelease(KeyEvent.VK_QUOTE);
			break;
		case '"':
			robot.keyPress(KeyEvent.VK_SHIFT);
			robot.keyPress(KeyEvent.VK_QUOTE);
			robot.keyRelease(KeyEvent.VK_QUOTE);
			robot.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case ',':
			robot.keyPress(KeyEvent.VK_COMMA);
			robot.keyRelease(KeyEvent.VK_COMMA);
			break;
		case '<':
			robot.keyPress(KeyEvent.VK_SHIFT);
			robot.keyPress(KeyEvent.VK_COMMA);
			robot.keyRelease(KeyEvent.VK_COMMA);
			robot.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case '.':
			robot.keyPress(KeyEvent.VK_PERIOD);
			robot.keyRelease(KeyEvent.VK_PERIOD);
			break;
		case '>':
			robot.keyPress(KeyEvent.VK_SHIFT);
			robot.keyPress(KeyEvent.VK_PERIOD);
			robot.keyRelease(KeyEvent.VK_PERIOD);
			robot.keyRelease(KeyEvent.VK_SHIFT);
			break;
		case '/':
			robot.keyPress(KeyEvent.VK_SLASH);
			robot.keyRelease(KeyEvent.VK_SLASH);
			break;
		case '?':
			robot.keyPress(KeyEvent.VK_SHIFT);
			robot.keyPress(KeyEvent.VK_SLASH);
			robot.keyRelease(KeyEvent.VK_SLASH);
			robot.keyRelease(KeyEvent.VK_SHIFT);
			break;
			
		// NEED TO FIX
		case '\b':
			robot.keyPress(KeyEvent.VK_BACK_SPACE);
			robot.keyRelease(KeyEvent.VK_BACK_SPACE);
			break;
		case '\n':
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			break;
		case '\r':
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			break;
		case '\t':
			robot.keyPress(KeyEvent.VK_TAB);
			robot.keyRelease(KeyEvent.VK_TAB);
			break;
		case ' ':
			robot.keyPress(KeyEvent.VK_SPACE);
			robot.keyRelease(KeyEvent.VK_SPACE);
			break;
		default:
			break;
		}
	}
	
	
	
	public void register(BusinessTouchScreenListener listener) {
		listeners.add(listener);
	}
	
	private void notifyUserKeyboard(char userInput){
		for (BusinessTouchScreenListener listener : listeners){
			listener.userKeyboardInput(userInput);
		}
	}
	
	
	
//	public static void main(String[] args) throws InterruptedException {
//		BusinessTouchScreen sick = new BusinessTouchScreen();
//		String byrone = "THE QUICK BROWN FOX JUMPS OVER THE LAZY DOG \n0123456789 the quick brown fox jumps over the lazy dog !@#$%^&*()-_=+[{]}\\|;:'\",<.>/?";
//		Thread.sleep(2000);
//		for (int i = 0; i < byrone.length(); i++)
//		{
//			sick.keyPress(String.valueOf(byrone.charAt(i)));
//		}
//	}
	
}
