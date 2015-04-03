package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;

import javax.swing.*;


public class ControlPanelGUI {

	private JFrame myFrame;
	private JButton Pin1, Pin2, Pin3, Pin4, Pin5, Pin6, Pin7, Pin8, Pin9, Pin0, PinC, PinX, PinCheck;
	private JTextArea[] textPaneArray;
	private JTextField textField;
	private Container callerContainer;
	
	private String display;
	private boolean SIM_Mode = false;
	
	/**
	 * Disables the passed containers components on launch, enables on close
	 * @param caller should be the container who created this GUI
	 */
	public ControlPanelGUI(Container caller) {
		this();
		callerContainer = caller;
		GUIHelper.enableComponents(caller, false);
		myFrame.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			/**
			 * Renables caller window's components on close
			 */
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				GUIHelper.enableComponents(callerContainer, true);
			}
		});
	}
	
    public ControlPanelGUI() {
		myFrame = new JFrame("CONTROL PANEL");
    	myFrame.setSize(500, 400);
        myFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		myFrame.setBackground(Color.LIGHT_GRAY);
		myFrame.setForeground(Color.BLACK);
		myFrame.setResizable(false);
		myFrame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.BOLD, 12));
		textField.setForeground(Color.RED);
		textField.setBackground(Color.DARK_GRAY);
		textField.setBounds(30, 11, 355, 23);
		myFrame.getContentPane().add(textField);
		textField.setColumns(10);
		
		display = textField.getText();
		
		JButton toggle = new JButton("Toggle SIM mode");
		toggle.setBounds(328, 38, 115, 34);
		myFrame.getContentPane().add(toggle);
		toggle.addActionListener(new ActionListener() {
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
		
		JLabel lblEntry = new JLabel("Entry: ");
		lblEntry.setBounds(7, 70, 53, 42);
		myFrame.getContentPane().add(lblEntry);
		
		Pin1 = new JButton("1");
		Pin1.setBounds(147, 75, 53, 34);
		myFrame.getContentPane().add(Pin1);
		Pin1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				display = textField.getText();
				textField.setText(display + "1");
			}
		});
		
		JButton pin2 = new JButton("2");
		pin2.setBounds(203, 75, 53, 34);
		myFrame.getContentPane().add(pin2);
		pin2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				display = textField.getText();
				textField.setText(display + "2");
			}
		});
		
		
		Pin3 = new JButton("3");
		Pin3.setBounds(258, 75, 53, 34);
		myFrame.getContentPane().add(Pin3);
		Pin3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				display = textField.getText();
				textField.setText(display + "3");
			}
		});
		
		JButton pin4 = new JButton("4");
		pin4.setBounds(147, 113, 53, 34);
		myFrame.getContentPane().add(pin4);
		pin4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				display = textField.getText();
				textField.setText(display + "4");
			}
		});
		
		JButton pin5 = new JButton("5");
		pin5.setBounds(203, 113, 53, 34);
		myFrame.getContentPane().add(pin5);
		pin5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				display = textField.getText();
				textField.setText(display + "5");
			}
		});
		
		JButton pin6 = new JButton("6");
		pin6.setBounds(258, 113, 53, 34);
		myFrame.getContentPane().add(pin6);
		pin6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				display = textField.getText();
				textField.setText(display + "6");
			}
		});

		JButton pin7 = new JButton("7");
		pin7.setBounds(147, 149, 54, 34);
		myFrame.getContentPane().add(pin7);
		pin7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				display = textField.getText();
				textField.setText(display + "7");
			}
		});
		
		JButton pin8 = new JButton("8");
		pin8.setBounds(203, 149, 53, 34);
		myFrame.getContentPane().add(pin8);
		pin8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				display = textField.getText();
				textField.setText(display + "8");
			}
		});
		
		JButton pin9 = new JButton("9");
		pin9.setBounds(258, 149, 53, 34);
		myFrame.getContentPane().add(pin9);
		pin9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				display = textField.getText();
				textField.setText(display + "9");
			}
		});
		
		JButton pin0 = new JButton("0");
		pin0.setBounds(203, 186, 53, 34);
		myFrame.getContentPane().add(pin0);
		pin0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				display = textField.getText();
				textField.setText(display + "0");
			}
		});
		
		
		JButton confirm = new JButton("Confirm");
		confirm.setBounds(147, 228, 82, 34);
		myFrame.getContentPane().add(confirm);
		confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				display = textField.getText();
				textField.setText(display+" yes");
			}
		});
		
		JButton Cancel = new JButton("Cancel");
		Cancel.setBounds(229, 228, 82, 34);
		myFrame.getContentPane().add(Cancel);
		Cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				display = textField.getText();
				textField.setText(null);
			}
		});
		
		
		final JButton correct = new JButton("Correct");
		correct.setBounds(196, 264, 80, 34);
		myFrame.getContentPane().add(correct);
		if (display == null)
			correct.setEnabled(false);
		correct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (display != null) {
					display = textField.getText();
					String newdisplay = display.substring(0, display.length() - 1);
					textField.setText(newdisplay);
				}
				else{
					correct.setEnabled(false);
				}
			}
		});
		
		JToggleButton tglbtnToggleSimMode = new JToggleButton("Toggle SIM Mode");
		tglbtnToggleSimMode.setBounds(338, 83, 121, 23);
		myFrame.getContentPane().add(tglbtnToggleSimMode);
		
		myFrame.setVisible(true);
    }
  
	public static void main(String[] args) {
		ControlPanelGUI myGUI = new ControlPanelGUI();

	}
}
