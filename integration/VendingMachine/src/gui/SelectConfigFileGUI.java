package gui;

import hardware.simulators.AbstractVendingMachine;

import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.BorderLayout;

//import org.eclipse.wb.swing.FocusTraversalOnArray;

import business.config.Configuration;
import business.config.ConfigurationException;

import java.awt.Color;
import java.awt.Component;
import java.awt.Panel;

import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SelectConfigFileGUI {

	private JFrame configFrame;
	private JFrame frame;
	private JPanel mainPanel;
	private ButtonGroup FilesToPick; 
	private FileFinder fileFinder = null;
	private String fileName = null;

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SelectConfigFileGUI window = new SelectConfigFileGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SelectConfigFileGUI() {
		initialize();
	}
	
	public SelectConfigFileGUI(JFrame configGUIFrame,FileFinder finder){
		configFrame = configGUIFrame;
		fileFinder = finder;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		fileFinder = new FileFinder();
		frame = new JFrame();
		frame.setTitle("Pick a Config File");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		mainPanel = new JPanel();
		mainPanel.setBorder(new LineBorder(Color.LIGHT_GRAY));		
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(mainPanel, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
					.addGap(54))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(mainPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(46, Short.MAX_VALUE))
		);
		mainPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout(0, 0));
		
		JButton btnLaunch = new JButton("Launch");
		panel.add(btnLaunch, BorderLayout.WEST);
		mainPanel.add(panel);
		btnLaunch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fileName != null){
					Configuration config = new Configuration();
					AbstractVendingMachine machine = null;
					try {
						machine = config.load(new File(fileName));
					} catch (ConfigurationException e1) {
						e1.printStackTrace();
					}
					ArrayList<Boolean> parts = null;
					parts = config.parts();

					VendingMachineGUI window = new StandardMachineGUI(machine,parts);
					window.getMainFrame().setVisible(true);
					frame.setVisible(false);
				}
			}
		});

		
		JButton CancelBtn = new JButton("Cancel");
		panel.add(CancelBtn, BorderLayout.EAST);
		CancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				configFrame.setVisible(true);
				frame.setVisible(false);
			}
		});
		
		
		FilesToPick = new ButtonGroup();
		
		RadioAction radioAction = new RadioAction();
		for(int i = 0; i< fileFinder.getFileArraySize(); i++){
			JRadioButton file = new JRadioButton(fileFinder.getElementInFileArray(i));
			file.setActionCommand(fileFinder.getElementInFileArray(i));
			file.setBounds(6, 6, 172, 23);
			mainPanel.add(file);
			file.addActionListener(radioAction);
			FilesToPick.add(file);
		}
		
	}
	
	
	public JFrame getMainFrame(){
		return frame;
	}
	
	
	class RadioAction implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			fileName = FilesToPick.getSelection().getActionCommand();
//			System.out.println("File name is: "+ fileName);		
		}
	}
}
