package vendingMachine;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import java.awt.GridLayout;

import javax.swing.JButton;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.BevelBorder;

public class GuiTest {

	private JFrame frmVendingMachines;
	private JPanel pnlMachineButtons;
	private JPanel pnlPopButtons;
	
	private ArrayList<JButton> coinButtons;
	private ArrayList<JButton> billButtons;
	private ArrayList<JButton> selectionButtons;
	private ArrayList<JLabel> outOfProductLabels;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiTest window = new GuiTest();
					window.frmVendingMachines.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GuiTest() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		outOfProductLabels = new ArrayList();
		selectionButtons = new ArrayList();
		
		frmVendingMachines = new JFrame();
		frmVendingMachines.setTitle("Vending Machines");
		frmVendingMachines.setBounds(100, 100, 1000, 800);
		frmVendingMachines.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmVendingMachines.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel pnlMachine = new JPanel();
		pnlMachine.setBorder(null);
		frmVendingMachines.getContentPane().add(pnlMachine, BorderLayout.CENTER);
		pnlMachine.setLayout(new BorderLayout(0, 0));
		
		JPanel pnlMachineMain = new JPanel();
		pnlMachine.add(pnlMachineMain, BorderLayout.CENTER);
		pnlMachineMain.setLayout(new BorderLayout(0, 0));
		
		JPanel pnlDisplay = new JPanel();
		pnlMachineMain.add(pnlDisplay, BorderLayout.NORTH);
		
		JLabel lblDisplay = new JLabel("Display");
		pnlDisplay.add(lblDisplay);
		
		JPanel pnlDeliveryChute = new JPanel();
		pnlMachineMain.add(pnlDeliveryChute, BorderLayout.SOUTH);
		
		JLabel lblDeliveryChute = new JLabel("Delivery Chute");
		pnlDeliveryChute.add(lblDeliveryChute);
		
		pnlMachineButtons = new JPanel();
		pnlMachineMain.add(pnlMachineButtons, BorderLayout.CENTER);
		
		JPanel pnlMisc = new JPanel();
		pnlMachineButtons.add(pnlMisc);
		pnlMisc.setLayout(new GridLayout(0, 1, 0, 3));
		
		JButton btnReturn = new JButton("Return");
		pnlMisc.add(btnReturn);
		
		JLabel lblExactChange = new JLabel("     ");
		lblExactChange.setBackground(Color.RED);
		lblExactChange.setForeground(Color.RED);
		lblExactChange.setOpaque(true);
		pnlMisc.add(lblExactChange);
		
		JLabel lblOutOfOrder = new JLabel("     ");
		lblOutOfOrder.setOpaque(true);
		lblOutOfOrder.setForeground(Color.LIGHT_GRAY);
		lblOutOfOrder.setBackground(Color.LIGHT_GRAY);
		pnlMisc.add(lblOutOfOrder);
		
		pnlPopButtons = new JPanel();
		pnlMachineButtons.add(pnlPopButtons);
		pnlPopButtons.setLayout(new GridLayout(0, 2, 2, 2));
		
		machine1Setup();
		
		JPanel pnlMoney = new JPanel();
		pnlMoney.setBorder(new LineBorder(new Color(0, 0, 0)));
		frmVendingMachines.getContentPane().add(pnlMoney, BorderLayout.WEST);
		pnlMoney.setLayout(new BorderLayout(0, 0));
		
		JPanel pnlMoneySelection = new JPanel();
		pnlMoneySelection.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnlMoney.add(pnlMoneySelection, BorderLayout.CENTER);
		pnlMoneySelection.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel pnlCoins = new JPanel();
		pnlMoneySelection.add(pnlCoins);
		pnlCoins.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblCoins = new JLabel("Coins:");
		pnlCoins.add(lblCoins);
		
		JPanel pnlCoinBtns = new JPanel();
		pnlCoins.add(pnlCoinBtns);
		pnlCoinBtns.setLayout(new GridLayout(0, 2, 2, 2));
		
		pnlCoinBtns.add(createCoinButton(5, '$'));
		
		pnlCoinBtns.add(createCoinButton(10, '$'));
		
		pnlCoinBtns.add(createCoinButton(25, '$'));
		
		pnlCoinBtns.add(createCoinButton(100, '$'));
		
		pnlCoinBtns.add(createCoinButton(200, '$'));
		
		JPanel pnlBills = new JPanel();
		pnlMoneySelection.add(pnlBills);
		pnlBills.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblBills = new JLabel("Bills:");
		pnlBills.add(lblBills);
		
		JPanel pnlBillsBtns = new JPanel();
		pnlBills.add(pnlBillsBtns);
		pnlBillsBtns.setLayout(new GridLayout(0, 2, 0, 0));
		
		pnlBillsBtns.add(createBillButton(500, '$'));
		
		pnlBillsBtns.add(createBillButton(1000, '$'));
		
		pnlBillsBtns.add(createBillButton(2000, '$'));
	}
	
	public void machine1Setup() {
		
		JLabel lblOutOfProduct1 = new JLabel("     ");
		lblOutOfProduct1.setOpaque(true);
		lblOutOfProduct1.setForeground(Color.LIGHT_GRAY);
		lblOutOfProduct1.setBackground(Color.LIGHT_GRAY);
		outOfProductLabels.add(lblOutOfProduct1);
		
		JButton btnPop1 = new JButton("Pop 1");
		selectionButtons.add(btnPop1);
		
		JLabel lblOutOfProduct2 = new JLabel("     ");
		lblOutOfProduct2.setOpaque(true);
		lblOutOfProduct2.setForeground(Color.LIGHT_GRAY);
		lblOutOfProduct2.setBackground(Color.LIGHT_GRAY);
		outOfProductLabels.add(lblOutOfProduct2);
		
		JButton btnPop2 = new JButton("Pop 2");
		selectionButtons.add(btnPop2);
		
		JLabel lblOutOfProduct3 = new JLabel("     ");
		lblOutOfProduct3.setOpaque(true);
		lblOutOfProduct3.setForeground(Color.LIGHT_GRAY);
		lblOutOfProduct3.setBackground(Color.LIGHT_GRAY);
		outOfProductLabels.add(lblOutOfProduct3);
		
		JButton btnPop3 = new JButton("Pop 3");
		selectionButtons.add(btnPop3);
		
		JLabel lblOutOfProduct4 = new JLabel("     ");
		lblOutOfProduct4.setOpaque(true);
		lblOutOfProduct4.setForeground(Color.LIGHT_GRAY);
		lblOutOfProduct4.setBackground(Color.LIGHT_GRAY);
		outOfProductLabels.add(lblOutOfProduct4);
		
		JButton btnPop4 = new JButton("Pop 4");
		selectionButtons.add(btnPop4);
		
		JLabel lblOutOfProduct5 = new JLabel("     ");
		lblOutOfProduct5.setOpaque(true);
		lblOutOfProduct5.setForeground(Color.LIGHT_GRAY);
		lblOutOfProduct5.setBackground(Color.LIGHT_GRAY);
		outOfProductLabels.add(lblOutOfProduct5);
		
		JButton btnPop5 = new JButton("Pop 5");
		selectionButtons.add(btnPop5);
		
		JLabel lblOutOfProduct6 = new JLabel("     ");
		lblOutOfProduct6.setOpaque(true);
		lblOutOfProduct6.setForeground(Color.LIGHT_GRAY);
		lblOutOfProduct6.setBackground(Color.LIGHT_GRAY);
		outOfProductLabels.add(lblOutOfProduct6);
		
		JButton btnPop6 = new JButton("Pop 6");
		selectionButtons.add(btnPop6);
		
		for (int i = 0; i < selectionButtons.size(); i++) {
			pnlPopButtons.add(outOfProductLabels.get(i));
			pnlPopButtons.add(selectionButtons.get(i));
		}
	}
	
	/**
	 * Returns a button used for coins
	 * @param amount is the amount in cents/regional equivalent
	 * @param currType is the currency identifier (ex. $)
	 * @return the button being asked for
	 */
	public JButton createCoinButton(int amount, char currType) {
		String buttonText = currType + Double.toString((amount / 100));
		return new JButton(buttonText);
	}
	
	/**
	 * Returns a button used for bills
	 * Separate from coins as they will need to raise different events once those are
	 * implemented
	 * @param amount is the amount in cents/regional equivalent
	 * @param currType is the currency identifier (ex. $)
	 * @return the button being asked for
	 */
	public JButton createBillButton(int amount, char currType) {
		String buttonText = currType + Double.toString((amount / 100));
		return new JButton(buttonText);
	}

}