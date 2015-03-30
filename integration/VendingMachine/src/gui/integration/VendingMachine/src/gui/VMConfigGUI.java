package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class VMConfigGUI {

	private JFrame frmVendingMachinesR;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VMConfigGUI window = new VMConfigGUI();
					window.frmVendingMachinesR.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VMConfigGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmVendingMachinesR = new JFrame();
		frmVendingMachinesR.setTitle("Vending Machines R Us");
		frmVendingMachinesR.setBounds(100, 100, 386, 500);
		frmVendingMachinesR.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		
		JPanel panel_1 = new JPanel();
		
		JPanel panel_2 = new JPanel();
		
		JPanel panel_3 = new JPanel();
		
		JPanel panel_4 = new JPanel();
		
		JLabel lblVendingMachineType = new JLabel("Vending Machine Type:");
		
		JLabel lblRemoteReportingType = new JLabel("Remote Reporting Type:");
		
		JLabel lblConfigFile = new JLabel("Config File:");
		GroupLayout groupLayout = new GroupLayout(frmVendingMachinesR.getContentPane());
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
		
		JButton btnLaunch = new JButton("Launch");
		btnLaunch.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		btnLaunch.setBounds(115, 18, 139, 41);
		panel_3.add(btnLaunch);
		panel_4.setLayout(null);
		
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
		
		JRadioButton rdbtnEveryTransaction = new JRadioButton("Every transaction");
		rdbtnEveryTransaction.setBounds(6, 6, 172, 23);
		panel_2.add(rdbtnEveryTransaction);
		
		JRadioButton rdbtnEveryTransactions = new JRadioButton("Every 100 transactions");
		rdbtnEveryTransactions.setBounds(6, 30, 178, 23);
		panel_2.add(rdbtnEveryTransactions);
		
		JRadioButton rdbtnDailyAtam = new JRadioButton("Daily at 4:00am");
		rdbtnDailyAtam.setBounds(6, 54, 172, 23);
		panel_2.add(rdbtnDailyAtam);
		panel_1.setLayout(null);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("VMRUS-SFF-P/C");
		rdbtnNewRadioButton.setBounds(6, 6, 172, 23);
		panel_1.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("VMRUS-SFF-P/CI");
		rdbtnNewRadioButton_1.setBounds(6, 30, 172, 23);
		panel_1.add(rdbtnNewRadioButton_1);
		
		JRadioButton rdbtnVmrussffppi = new JRadioButton("VMRUS-SFF-P/PI");
		rdbtnVmrussffppi.setBounds(6, 54, 172, 23);
		panel_1.add(rdbtnVmrussffppi);
		
		JRadioButton rdbtnVmruscompmi = new JRadioButton("VMRUS-COM-P/MI");
		rdbtnVmruscompmi.setBounds(6, 78, 172, 23);
		panel_1.add(rdbtnVmruscompmi);
		
		JRadioButton rdbtnVmruscompm = new JRadioButton("VMRUS-COM-P/M");
		rdbtnVmruscompm.setBounds(6, 102, 172, 23);
		panel_1.add(rdbtnVmruscompm);
		
		JRadioButton rdbtnVmrustocpmi = new JRadioButton("VMRUS-TOC-P/MI");
		rdbtnVmrustocpmi.setBounds(6, 126, 172, 23);
		panel_1.add(rdbtnVmrustocpmi);
		
		JRadioButton rdbtnVmrustocpi = new JRadioButton("VMRUS-TOC-P/I");
		rdbtnVmrustocpi.setBounds(6, 150, 172, 23);
		panel_1.add(rdbtnVmrustocpi);
		
		JRadioButton rdbtnVmruscomcmi = new JRadioButton("VMRUS-COM-C/MI");
		rdbtnVmruscomcmi.setBounds(6, 174, 172, 23);
		panel_1.add(rdbtnVmruscomcmi);
		
		JRadioButton rdbtnVmruscomcm = new JRadioButton("VMRUS-COM-C/M");
		rdbtnVmruscomcm.setBounds(6, 198, 172, 23);
		panel_1.add(rdbtnVmruscomcm);
		
		JRadioButton rdbtnVmrustoccmi = new JRadioButton("VMRUS-TOC-C/MI");
		rdbtnVmrustoccmi.setBounds(6, 222, 172, 23);
		panel_1.add(rdbtnVmrustoccmi);
		
		JRadioButton rdbtnVmrustocc = new JRadioButton("VMRUS-TOC-C+");
		rdbtnVmrustocc.setBounds(6, 246, 172, 23);
		panel_1.add(rdbtnVmrustocc);
		
		JRadioButton rdbtnVmrustocci = new JRadioButton("VMRUS-TOC-C+/I");
		rdbtnVmrustocci.setBounds(6, 270, 172, 23);
		panel_1.add(rdbtnVmrustocci);
		panel.setLayout(null);
		
		JLabel lblSelectVendingMachine = new JLabel("Select Vending Machine Configuration:");
		lblSelectVendingMachine.setBounds(65, 7, 243, 16);
		panel.add(lblSelectVendingMachine);
		frmVendingMachinesR.getContentPane().setLayout(groupLayout);
	}
}
