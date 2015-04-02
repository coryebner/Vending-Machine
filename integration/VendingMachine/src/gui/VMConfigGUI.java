package gui;

//import hardware.VendingMachine1;

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

import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;

public class VMConfigGUI implements ActionListener{
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

	public VMConfigGUI() {
		initialize();
	}

	private void initialize() {
		frmVendingMachinesRUS = new JFrame();
		frmVendingMachinesRUS.setTitle("Vending Machines R Us");
		frmVendingMachinesRUS.setBounds(100, 100, 386, 500);
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
					.addGap(22)
					.addComponent(lblVendingMachineType)
					.addPreferredGap(ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
					.addComponent(lblRemoteReportingType)
					.addGap(30))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel_3, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE)
						.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE)
							.addGap(7)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
										.addComponent(panel_4, GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(60)
									.addComponent(lblConfigFile)))))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(16)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblVendingMachineType)
						.addComponent(lblRemoteReportingType))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
							.addComponent(lblConfigFile)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panel_4, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE))
						.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 303, Short.MAX_VALUE))
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
		
		JRadioButton machineConfig4 = new JRadioButton("VMRUS-COM-P/MI");
		machineConfig4.setActionCommand("VMRUS-COM-P/MI");
		machineConfig4.setBounds(6, 78, 172, 23);
		panel_1.add(machineConfig4);
		machineConfig4.addActionListener(VMConfigLisenter);
		
		JRadioButton machineConfig5 = new JRadioButton("VMRUS-COM-P/M");
		machineConfig5.setActionCommand("VMRUS-COM-P/M");
		machineConfig5.setBounds(6, 102, 172, 23);
		panel_1.add(machineConfig5);
		machineConfig5.addActionListener(VMConfigLisenter);
		
		JRadioButton machineConfig6 = new JRadioButton("VMRUS-TOC-P/MI");
		machineConfig6.setActionCommand("VMRUS-TOC-P/MI");
		machineConfig6.setBounds(6, 126, 172, 23);
		panel_1.add(machineConfig6);
		machineConfig6.addActionListener(VMConfigLisenter);
		
		JRadioButton machineConfig7 = new JRadioButton("VMRUS-TOC-P/I");
		machineConfig7.setActionCommand("VMRUS-TOC-P/I");
		machineConfig7.setBounds(6, 150, 172, 23);
		panel_1.add(machineConfig7);
		machineConfig7.addActionListener(VMConfigLisenter);
		
		JRadioButton machineConfig8 = new JRadioButton("VMRUS-COM-C/MI");
		machineConfig8.setActionCommand("VMRUS-COM-C/MI");
		machineConfig8.setBounds(6, 174, 172, 23);
		panel_1.add(machineConfig8);
		machineConfig8.addActionListener(VMConfigLisenter);
		
		JRadioButton machineConfig9 = new JRadioButton("VMRUS-COM-C/M");
		machineConfig9.setActionCommand("VMRUS-COM-C/M");
		machineConfig9.setBounds(6, 198, 172, 23);
		panel_1.add(machineConfig9);
		machineConfig9.addActionListener(VMConfigLisenter);
		
		JRadioButton machineConfig10 = new JRadioButton("VMRUS-TOC-C/MI");
		machineConfig10.setActionCommand("VMRUS-TOC-C/MI");
		machineConfig10.setBounds(6, 222, 172, 23);
		panel_1.add(machineConfig10);
		machineConfig10.addActionListener(VMConfigLisenter);
		
		JRadioButton machineConfig11 = new JRadioButton("VMRUS-TOC-C+");
		machineConfig11.setActionCommand("VMRUS-TOC-C+");
		machineConfig11.setBounds(6, 246, 172, 23);
		panel_1.add(machineConfig11);
		machineConfig11.addActionListener(VMConfigLisenter);
		
		JRadioButton machineConfig12 = new JRadioButton("VMRUS-TOC-C+/I");
		machineConfig12.setActionCommand("VMRUS-TOC-C+/I");
		machineConfig12.setBounds(6, 270, 172, 23);
		panel_1.add(machineConfig12);
		panel.setLayout(null);
		machineConfig12.addActionListener(VMConfigLisenter);
		
		machineConfigRadioGroup = new ButtonGroup();
		machineConfigRadioGroup.add(machineConfig1);
		machineConfigRadioGroup.add(machineConfig2);
		machineConfigRadioGroup.add(machineConfig3);
		machineConfigRadioGroup.add(machineConfig4);
		machineConfigRadioGroup.add(machineConfig5);
		machineConfigRadioGroup.add(machineConfig6);
		machineConfigRadioGroup.add(machineConfig7);
		machineConfigRadioGroup.add(machineConfig8);
		machineConfigRadioGroup.add(machineConfig9);
		machineConfigRadioGroup.add(machineConfig10);
		machineConfigRadioGroup.add(machineConfig11);
		machineConfigRadioGroup.add(machineConfig12);
		
		ActionListener reportConfigListener = new ReportConfigListener();
		//Remote reporting configurations
		JRadioButton reportConfig1 = new JRadioButton("Every transaction");
		reportConfig1.setActionCommand("Every transaction");
		reportConfig1.setBounds(6, 6, 172, 23);
		reportConfig1.addActionListener(reportConfigListener);
		panel_2.add(reportConfig1);
		
		JRadioButton reportConfig2 = new JRadioButton("Every 100 transactions");
		reportConfig2.setActionCommand("Every 100 transactions");
		reportConfig2.setBounds(6, 30, 178, 23);
		reportConfig2.addActionListener(reportConfigListener);
		panel_2.add(reportConfig2);
		
		JRadioButton reportConfig3 = new JRadioButton("Daily at 4:00am");
		reportConfig3.setActionCommand("Daily at 4:00am");
		reportConfig3.setBounds(6, 54, 172, 23);
		reportConfig3.addActionListener(reportConfigListener);
		panel_2.add(reportConfig3);
		panel_1.setLayout(null);
		
		reportConfigRadioGroup = new ButtonGroup();
		reportConfigRadioGroup.add(reportConfig1);
		reportConfigRadioGroup.add(reportConfig2);
		reportConfigRadioGroup.add(reportConfig3);
		
		//Config file
		JLabel lblwhenYouClick = new JLabel("<html>When you click this button,<br>the program launches a<br>vending machine based on<br>the settings stored in its<br>.config file.</html>");
		lblwhenYouClick.setForeground(Color.DARK_GRAY);
		lblwhenYouClick.setBounds(6, 6, 178, 83);
		panel_4.add(lblwhenYouClick);
		
		JButton btnConfigFile = new JButton("Use Config File");
		btnConfigFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnConfigFile.setBounds(24, 114, 139, 29);
		panel_4.add(btnConfigFile);
		panel_2.setLayout(null);
		
		//Launch button
		JButton btnLaunch = new JButton("Launch");
		btnLaunch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(reportConfig!=null && machineConfig!=null){
					//Here use the two selected configurations to create the Vending Machine	
					int []coinvalue = { 5, 10, 25, 100, 200 };
					int [] popcost	= {200,200,200,200,200,200 };
					String [] popname = {"Coke","DietCode","RootBeer", "asdf", "qwer","zxcv"};
//					VendingMachine1 popmachine = new VendingMachine1(coinvalue,popcost,popname);
//					GUI window = new GUI(popmachine);
					
					VendingMachineGUI window = new StandardMachineGUI();
					window.getMainFrame().setVisible(true);
					frmVendingMachinesRUS.setVisible(false);
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
	
	
	public void actionPerformed(ActionEvent e) {
	
	}
}
