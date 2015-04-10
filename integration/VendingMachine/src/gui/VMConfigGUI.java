package gui;


//import hardware.AbstractVendingMachine;

import hardware.simulators.AbstractVendingMachine;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

import java.awt.Color;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.border.LineBorder;

import business.config.Configuration;
import business.config.ConfigurationException;

public class VMConfigGUI {
	private JFrame frmVendingMachinesRUS;
	private ButtonGroup machineConfigRadioGroup;
	private ButtonGroup reportConfigRadioGroup;
	private String machineConfig = null;
	private String reportConfig = null;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VMConfigGUI window = new VMConfigGUI();
					window.frmVendingMachinesRUS.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public VMConfigGUI(){
		initialize();
	}

	private void initialize()  {
		frmVendingMachinesRUS = new JFrame();
		frmVendingMachinesRUS.setTitle("Vending Machines R Us");
		frmVendingMachinesRUS.setBounds(100, 100, 386, 463);
		frmVendingMachinesRUS.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		JPanel panel_1 = new JPanel();
		JPanel panel_2 = new JPanel();
		JPanel panel_3 = new JPanel();
		JPanel panel_4 = new JPanel();
		panel.setBorder(new LineBorder(Color.LIGHT_GRAY));
		panel_1.setBorder(new LineBorder(Color.LIGHT_GRAY));
		panel_2.setBorder(new LineBorder(Color.LIGHT_GRAY));
		panel_3.setBorder(new LineBorder(Color.LIGHT_GRAY));
		panel_4.setBorder(new LineBorder(Color.LIGHT_GRAY));

		JLabel lblSelectVendingMachine = new JLabel("Select Vending Machine Configuration:");
		JLabel lblVendingMachineType = new JLabel("Vending Machine Type:");
		JLabel lblRemoteReportingType = new JLabel("Remote Reporting Type:");
		JLabel lblConfigFile = new JLabel("Config File:");
		
		//Auto-generated layout spaghetti code
		GroupLayout groupLayout = new GroupLayout(frmVendingMachinesRUS.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblConfigFile)
							.addGap(64))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addGap(6)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(panel_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
								.addComponent(panel_4, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE))
							.addContainerGap())))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(22)
					.addComponent(lblVendingMachineType)
					.addPreferredGap(ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
					.addComponent(lblRemoteReportingType)
					.addGap(30))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblVendingMachineType)
						.addComponent(lblRemoteReportingType))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblConfigFile)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panel_4, GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE))
						.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		panel_3.setLayout(null);

		lblSelectVendingMachine.setBounds(65, 7, 243, 16);
		panel.add(lblSelectVendingMachine);
		frmVendingMachinesRUS.getContentPane().setLayout(groupLayout);
		
		ActionListener VMConfigLisenter = new VendingMachineConfig();
		//Machine configurations
		JRadioButton machineConfig1 = new JRadioButton("VMRUS-SFF-P/C");
		machineConfig1.setActionCommand("VMRUS-SFF-P/C");
		machineConfig1.setBounds(6, 6, 172, 23);
		panel_1.add(machineConfig1);
		machineConfig1.addActionListener(VMConfigLisenter);
		
		JRadioButton machineConfig2 = new JRadioButton("VMRUS-SFF-P/CI");
		machineConfig2.setActionCommand("VMRUS-SFF-P/CI");
		machineConfig2.setBounds(6, 30, 172, 23);
		panel_1.add(machineConfig2);
		machineConfig2.addActionListener(VMConfigLisenter);
		
		JRadioButton machineConfig3 = new JRadioButton("VMRUS-SFF-P/PI");
		machineConfig3.setActionCommand("VMRUS-SFF-P/PI");
		machineConfig3.setBounds(6, 54, 172, 23);
		panel_1.add(machineConfig3);
		machineConfig3.addActionListener(VMConfigLisenter);
		
//		JRadioButton machineConfig4 = new JRadioButton("VMRUS-COM-P/MI");
//		machineConfig4.setActionCommand("VMRUS-COM-P/MI");
//		machineConfig4.setBounds(6, 78, 172, 23);
//		panel_1.add(machineConfig4);
//		machineConfig4.addActionListener(VMConfigLisenter);
		
		JRadioButton machineConfig4 = new JRadioButton("VMRUS-COM-P/M");
		machineConfig4.setActionCommand("VMRUS-COM-P/M");
		machineConfig4.setBounds(6, 78, 172, 23);
		panel_1.add(machineConfig4);
		machineConfig4.addActionListener(VMConfigLisenter);
		
		JRadioButton machineConfig5 = new JRadioButton("VMRUS-TOC-P/MI");
		machineConfig5.setActionCommand("VMRUS-TOC-P/MI");
		machineConfig5.setBounds(6, 102, 172, 23);
		panel_1.add(machineConfig5);
		machineConfig5.addActionListener(VMConfigLisenter);
		
		JRadioButton machineConfig6 = new JRadioButton("VMRUS-TOC-P/I");
		machineConfig6.setActionCommand("VMRUS-TOC-P/I");
		machineConfig6.setBounds(6, 126, 172, 23);
		panel_1.add(machineConfig6);
		machineConfig6.addActionListener(VMConfigLisenter);
		
//		JRadioButton machineConfig8 = new JRadioButton("VMRUS-COM-C/MI");
//		machineConfig8.setActionCommand("VMRUS-COM-C/MI");
//		machineConfig8.setBounds(6, 174, 172, 23);
//		panel_1.add(machineConfig8);
//		machineConfig8.addActionListener(VMConfigLisenter);
		
		JRadioButton machineConfig7 = new JRadioButton("VMRUS-COM-C/M");
		machineConfig7.setActionCommand("VMRUS-COM-C/M");
		machineConfig7.setBounds(6, 150, 172, 23);
		panel_1.add(machineConfig7);
		machineConfig7.addActionListener(VMConfigLisenter);
		
		JRadioButton machineConfig8 = new JRadioButton("VMRUS-TOC-C/MI");
		machineConfig8.setActionCommand("VMRUS-TOC-C/MI");
		machineConfig8.setBounds(6, 174, 172, 23);
		panel_1.add(machineConfig8);
		machineConfig8.addActionListener(VMConfigLisenter);
		
		JRadioButton machineConfig9 = new JRadioButton("VMRUS-TOC-C+");
		machineConfig9.setActionCommand("VMRUS-TOC-C+");
		machineConfig9.setBounds(6, 198, 172, 23);
		panel_1.add(machineConfig9);
		machineConfig9.addActionListener(VMConfigLisenter);
		
		JRadioButton machineConfig10 = new JRadioButton("VMRUS-TOC-C+/I");
		machineConfig10.setActionCommand("VMRUS-TOC-C+/I");
		machineConfig10.setBounds(6, 222, 172, 23);
		panel_1.add(machineConfig10);
		panel.setLayout(null);
		machineConfig10.addActionListener(VMConfigLisenter);
		
		machineConfigRadioGroup = new ButtonGroup();
		machineConfigRadioGroup.add(machineConfig1);
		machineConfigRadioGroup.add(machineConfig2);
		machineConfigRadioGroup.add(machineConfig3);
//		machineConfigRadioGroup.add(machineConfig4);
		machineConfigRadioGroup.add(machineConfig4);
		machineConfigRadioGroup.add(machineConfig5);
		machineConfigRadioGroup.add(machineConfig6);
//		machineConfigRadioGroup.add(machineConfig8);
		machineConfigRadioGroup.add(machineConfig7);
		machineConfigRadioGroup.add(machineConfig8);
		machineConfigRadioGroup.add(machineConfig9);
		machineConfigRadioGroup.add(machineConfig10);
		
		ActionListener reportConfigListener = new ReportConfigListener();
		//Remote reporting configurations
		JRadioButton reportConfig1 = new JRadioButton("Every transaction");
		reportConfig1.setActionCommand("instant");
		reportConfig1.setBounds(6, 6, 172, 23);
		reportConfig1.addActionListener(reportConfigListener);
		panel_2.add(reportConfig1);
		
		JRadioButton reportConfig2 = new JRadioButton("Every 100 transactions");
		reportConfig2.setActionCommand("batch");
		reportConfig2.setBounds(6, 30, 178, 23);
		reportConfig2.addActionListener(reportConfigListener);
		panel_2.add(reportConfig2);
		
		JRadioButton reportConfig3 = new JRadioButton("Daily at 4:00am");
		reportConfig3.setActionCommand("daily");
		reportConfig3.setBounds(6, 54, 172, 23);
		reportConfig3.addActionListener(reportConfigListener);
		panel_2.add(reportConfig3);
		
		JRadioButton reportConfig4 = new JRadioButton("Offline");
		reportConfig4.setActionCommand("offine");
		reportConfig4.setBounds(6, 80, 172, 23);
		reportConfig4.addActionListener(reportConfigListener);
		panel_2.add(reportConfig4);
		panel_1.setLayout(null);
		
		reportConfigRadioGroup = new ButtonGroup();
		reportConfigRadioGroup.add(reportConfig1);
		reportConfigRadioGroup.add(reportConfig2);
		reportConfigRadioGroup.add(reportConfig3);
		reportConfigRadioGroup.add(reportConfig4);
		
		
		//Config file
		JLabel lblwhenYouClick = new JLabel("<html>When you click this button,<br>the program launches a<br>vending machine based on<br>the settings stored in its<br>.config file.</html>");
		lblwhenYouClick.setForeground(Color.DARK_GRAY);
		lblwhenYouClick.setBounds(6, 14, 178, 83);
		panel_4.add(lblwhenYouClick);
		
		// Use config button
		JButton btnConfigFile = new JButton("Use Config File");
		btnConfigFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileFinder finder = new FileFinder ();
				SelectConfigFileGUI selectConfigFileGUI = new SelectConfigFileGUI(frmVendingMachinesRUS,finder);
				selectConfigFileGUI.getMainFrame().setVisible(true);
				frmVendingMachinesRUS.setVisible(false);	
			}
		});
		btnConfigFile.setBounds(27, 105, 139, 29);
		panel_4.add(btnConfigFile);
		panel_2.setLayout(null);
		
		//Launch button
		JButton btnLaunch = new JButton("Launch");
		btnLaunch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				if((reportConfig!=null || reportConfig == "offline")&& machineConfig!=null){	
					Configuration config = new Configuration();
					AbstractVendingMachine machine = null;
					try {
						machine = config.load(machineConfig,reportConfig);
					} catch (ConfigurationException e1) {
						e1.printStackTrace();
					}
					ArrayList<Boolean> parts = config.parts();
					if(machine == null){
						System.out.println("No such vending machine return from Configuration.");
					}else{
						VendingMachineGUI window = new StandardMachineGUI(machine,config,parts);
						window.getMainFrame().setVisible(true);
						frmVendingMachinesRUS.setVisible(false);
					}
				}else{
					System.out.println("Please choose the configuration for the vending machine and the report");
				}
			}
		});
		btnLaunch.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		btnLaunch.setBounds(115, 18, 139, 41);
		panel_3.add(btnLaunch);
		panel_4.setLayout(null);
	}


	class VendingMachineConfig implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			machineConfig = machineConfigRadioGroup.getSelection().getActionCommand();
			System.out.println("The selected Configuration is: "+ machineConfig);		
		}
	}
	class ReportConfigListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			reportConfig = reportConfigRadioGroup.getSelection().getActionCommand();	
			System.out.println("The selected report configuration is: "+ reportConfig);
		}
	}

}
