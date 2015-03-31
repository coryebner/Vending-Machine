/*
	- Source modified from :
	http://stackoverflow.com/questions/19261209/
	
	- Added : 
		- Shift and Caps Lock Implementations
		- Removed unrequired Keyboard Elements
		- Rearranged keys to QWERTY configuration
		
*/

package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class VirtualKeyboard extends JFrame 
{    
	private ArrayList<VirtualKeyboardListener> keyboardListeners = new ArrayList<VirtualKeyboardListener>();
	
    public void register(VirtualKeyboardListener listener) { keyboardListeners.add(listener); }
	
	public boolean deregister(VirtualKeyboardListener listener) { return keyboardListeners.remove(listener); }
    
	private void notifyKeyDown(char key)
    {
    	for (VirtualKeyboardListener listener : keyboardListeners)
    		listener.OnKeyDown(key);    	
    }
	
	
	private JFrame frame = new JFrame("Virtual Keyboard");
    private JPanel parent = new JPanel(new GridLayout(0, 1));
    private JPanel[] panel;
    private JButton[][] button = new JButton[6][14];
    private boolean shiftOn = false;
    private final static String[][] key = {
    	
    	// Row 0
    	{"~", "!", "@", "#", "$", "%", "^", "*", "(", ")", "-", "+", "=", "/"},
    	
        // Row 1
        {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0"},
            
        // Row 2    
        {"Tab", "q", "w", "e", "r", "t", "y", "u", "i", "o", "p", "Backspace"},
        
        // Row 3
        {"Caps", "a", "s", "d","f", "g", "h", "j", "k", "l", ":", "Enter"},
        
        // Row 4
        {"Shift", "z", "x", "c", "v", "b", "n", "m", ".", "<", ">", "?", "!"},
        
        // Row 5
        {"           Space            "},
     
    };

    public VirtualKeyboard() 
    {
        super("Virtual Keyboard");
        panel = new JPanel[6];	// Number of Array elements is the number of rows on keyboard
        
        for (int row = 0; row < key.length; row++) {
            panel[row] = new JPanel();
            
            for (int column = 0; column < key[row].length; column++) {
                button[row][column] = new JButton(key[row][column]);
                button[row][column].putClientProperty("column", column);
                button[row][column].putClientProperty("row", row);
                button[row][column].putClientProperty("key", key[row][column]);
                button[row][column].addActionListener(new VirtualKeyboardListener());
                panel[row].add(button[row][column]);
            }
            parent.add(panel[row]);
        }
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(parent);
        pack();
        setVisible(true);
    }

    public class VirtualKeyboardListener implements ActionListener {
    	public char stringToSend;
    	public void updateString(char newValue){
    		this.stringToSend=newValue;
    		System.out.println("We are sending this string: "+ stringToSend);
    		
    		
    		
    		//TODO 
    		//Spencer you will do the action raising depending on key typed here
    	}
    	
        public void OnKeyDown(char key) {
			// TODO Auto-generated method stub
			
		}

		@Override
        public void actionPerformed(ActionEvent e) {
            JButton btn = (JButton) e.getSource();
            
            
            System.out.println("Clicked Column: --> " + btn.getClientProperty("column")
                    + ", Clicked Row: --> " + btn.getClientProperty("row")
                    + ", Key Typed: --> " + btn.getClientProperty("key"));
            
            if (btn.getClientProperty("key").equals("           Space            ")) {
            	updateString(' ');
            }
            else if (btn.getClientProperty("key").equals("Enter")) {
            	updateString('\n');
            }
            else if (btn.getClientProperty("key").equals("Tab")) {
            	updateString('\t');
            }
            else if (btn.getClientProperty("key").equals("Backspace")) {
            	updateString('\b');
            }
            
            else if (btn.getClientProperty("key").equals("Caps") && key[3][1].equals("a")) {
            	setCapsOn();
            	button[3][0].setForeground(Color.RED);
            }
            
            else if (btn.getClientProperty("key").equals("Caps") && key[3][1].equals("A")){
            	setCapsOff();
            	button[3][0].setForeground(Color.black);
            }
            
            else if (btn.getClientProperty("key").equals("Shift") && key[3][1].equals("a")) {
            	setCapsOn();
            	button[4][0].setForeground(Color.RED);
            	shiftOn = true;
            }
            
            else if (shiftOn == true){
            	setCapsOff();
            	button[4][0].setForeground(Color.black);
            	shiftOn = false;
            }
      
            
            else
            	updateString(btn.getClientProperty("key").toString().charAt(0));
            
            notifyKeyDown(stringToSend);
        }
    }

    public void setCapsOn(){
		for (int row = 0; row < key.length; row++) {
	        for (int column = 0; column < key[row].length; column++){
	        	if (Character.isLetter(key[row][column].charAt(0)) && (key[row][column].length()) == 1){
	        		button[row][column].setText(key[row][column].toUpperCase());
	        	 	key[row][column] = key[row][column].toUpperCase();
	        	 	button[row][column].putClientProperty("column", column);
	                button[row][column].putClientProperty("row", row);
	                button[row][column].putClientProperty("key", key[row][column]);
	        	}
	        }
	    }
    }
    
    public void setCapsOff(){
    	for (int row = 0; row < key.length; row++) {
            for (int column = 0; column < key[row].length; column++) {
            	if (Character.isLetter(key[row][column].charAt(0)) && (key[row][column].length()) == 1){
            		button[row][column].setText(key[row][column].toLowerCase());
            	 	key[row][column] = key[row][column].toLowerCase();
            	 	button[row][column].putClientProperty("column", column);
                    button[row][column].putClientProperty("row", row);
                    button[row][column].putClientProperty("key", key[row][column]);
            	}
            }
        }
    }
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                VirtualKeyboard virtualKeyboard = new VirtualKeyboard();
            }
        });
    }
}