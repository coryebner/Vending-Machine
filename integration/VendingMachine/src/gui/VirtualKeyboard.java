/*
	- Source modified from :
	http://stackoverflow.com/questions/19261209/
	
	- Added : 
		- Shift and Caps Lock Implementations
		- Removed unrequired Keyboard Elements
		- Rearranged keys to QWERTY configuration
		
*/

package gui;

import hardware.*;
import hardware.exceptions.NoSuchHardwareException;
import hardware.simulators.*;
import hardware.ui.*;

import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Timer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

import net.miginfocom.swing.MigLayout;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.SwingConstants;

public class VirtualKeyboard extends JFrame implements DisplayListener{

    //---Methods to do with registering and deregistering listeners to the virtual keyboard.
    ArrayList<VirtualKeyboardListener> listeners = new ArrayList<VirtualKeyboardListener>();
    public void Register(VirtualKeyboardListener listener) {listeners.add(listener);}
    public boolean DeRegister(VirtualKeyboardListener listener) {return listeners.remove(listener);}
    void notifyKeyDown(char key)
    {
        for (VirtualKeyboardListener listener : listeners)
            listener.OnKeyDown(key);
    }


    private JFrame frame = new JFrame("Virtual Keyboard");
    private JPanel parent = new JPanel(new GridLayout(0, 1));
    private JPanel[] panel;
    private JButton[][] button = new JButton[6][14];
    private boolean shiftOn = false;
    private boolean SIM_Mode = false;
    private JTextField textField;
    private String display;
    private Container callerContainer;
    private AbstractVendingMachine machine;
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
    private JTextField displayField;
    private JPanel panel_1;

    /**
	 * Disables the passed containers components on launch, enables on close
	 * @param caller should be the container who created this GUI
	 */
	public VirtualKeyboard(Container caller, AbstractVendingMachine vm) {
		this();
		machine = vm;
        // Register this GUI listens to ConfigurationPanelDisplay  
        try {
			machine.getConfigPanelDisplay().register(this);
		} catch (NoSuchHardwareException e) {
			e.printStackTrace();
		}
		callerContainer = caller;
		GUIHelper.enableComponents(caller, false);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			/**
			 * Renables caller window's components on close
			 */
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				GUIHelper.enableComponents(callerContainer, true);
			}
		});
	}
    
    public VirtualKeyboard()
    {
    
        super("Virtual Keyboard");
        panel = new JPanel[6];	// Number of Array elements is the number of rows on keyboard

        frame.setResizable(false);

		
        for (int row = 0; row < key.length; row++) {
            panel[row] = new JPanel();
            
            for (int column = 0; column < key[row].length; column++) {
                button[row][column] = new JButton(key[row][column]);
                button[row][column].putClientProperty("column", column);
                button[row][column].putClientProperty("row", row);
                button[row][column].putClientProperty("key", key[row][column]);
                button[row][column].addActionListener(new KeyboardActionListener());
                panel[row].add(button[row][column]);
            }
            parent.add(panel[row]);
        }
        
        textField = new JTextField();
        textField.setFont(new Font("Tahoma", Font.BOLD, 16));
        textField.setForeground(Color.YELLOW);
        textField.setBackground(Color.DARK_GRAY);
        textField.setBounds(30, 11, 355, 50);
        parent.add(textField);
        textField.setColumns(10);
        textField.setVisible(true);
        textField.setEditable(true);
        textField.setFocusable(false);
        
        display = textField.getText();
        
        JToggleButton toggleSimMode = new JToggleButton("Toggle SIM Mode");
        toggleSimMode.setBounds(338, 83, 121, 23);
        parent.add(toggleSimMode);
        toggleSimMode.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		SIM_Mode = !SIM_Mode;
        		if (SIM_Mode){
        			SIM_Mode = true;
        			textField.setText("SIM_Mode on");
        		}
        		else 
        			textField.setText("SIM_Mode off");
        	}
        });
        getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
        
        panel_1 = new JPanel();
        getContentPane().add(panel_1);
        panel_1.setLayout(new GridLayout(0, 1, 0, 0));
        
        displayField = new JTextField();
        displayField.setEditable(false);
        displayField.setFont(new Font("Tahoma", Font.PLAIN, 15));
        displayField.setText("Enter 1 to change product price                     Enter 2 to change product name");
        displayField.setHorizontalAlignment(SwingConstants.CENTER);
        displayField.setToolTipText("");
        displayField.setForeground(Color.WHITE);
        displayField.setBackground(Color.DARK_GRAY);
        panel_1.add(displayField);
        displayField.setColumns(10);
        getContentPane().add(parent);
        pack();
        setVisible(true);
    }

    public class KeyboardActionListener implements ActionListener {
    	private String enteredString;
        public void actionPerformed(ActionEvent e) {
        	
            final JButton btn = (JButton) e.getSource();
            btn.addActionListener(new ActionListener() {
    			public void actionPerformed(ActionEvent arg0) {

    			}
    		});
            
            /*System.out.println("Clicked Column: --> " + btn.getClientProperty("column")
                    + ", Clicked Row: --> " + btn.getClientProperty("row")
                    + ", Key Typed: --> " + btn.getClientProperty("key"));
            */
            if (btn.getClientProperty("key") != null){

    				display = textField.getText();
    				display = display.replace("SIM_Mode on", "");
    				display = display.replace("SIM_Mode off", "");
    				
                	if (btn.getClientProperty("key").equals("Backspace")) {
                    	display = textField.getText();
                    	if (textField.getText().isEmpty()){
                    		//textField.setText("cant do that");
                    	}
                    	else
                    		textField.setText(display.substring(0, display.length() - 1));
                	}
                	else if (btn.getClientProperty("key").equals("           Space            ")) {
                    	textField.setText(display+ " ");
                    }
                    else if (btn.getClientProperty("key").equals("Enter")) {
                    	enteredString = textField.getText();
                    	textField.setText("");
//                    	System.out.println(enteredString);
                    	System.out.println("The String passing to the configuration panel transimmiter: " + enteredString);
                    	try {
        					machine.getConfigurationPanelTransmitter().enterCommand(enteredString);
        				} catch (NoSuchHardwareException e1) {
        					e1.printStackTrace();
        				}
                    }
                    else if (btn.getClientProperty("key").equals("Tab")) {
                    	display = textField.getText();
                    	textField.setText(display+ "	");
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
                    
                    else if (shiftOn == true && !btn.getClientProperty("key").equals("Shift")){
                    	textField.setText(display + btn.getClientProperty("key"));
                    	setCapsOff();
                    	button[4][0].setForeground(Color.black);
                    	shiftOn = false;
                    }
                	else{
                		textField.setText(display + btn.getClientProperty("key"));
                	}
            }
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
            public void run() {
                VirtualKeyboard virtualKeyboard = new VirtualKeyboard();
            }
        });
    }
	@Override
	public void enabled(AbstractHardware<AbstractHardwareListener> hardware) {
		// Nothing to do
		
	}
	@Override
	public void disabled(AbstractHardware<AbstractHardwareListener> hardware) {
		// Nothing to do
		
	}
	@Override
	public void messageChange(Display display, String oldMsg, String newMsg) {
		displayField.setText(newMsg);
		
	}
}