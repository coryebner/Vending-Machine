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
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.JList;

/**
 * Initial setup will involve being passed an abstract vending machine as well
 * as a list of booleans (may not be final) representing what parts the machine
 * has
 * 
 * Setup parts based on boolean parts
 * 
 * @author Shayne
 * 
 */

public class GUI {

	// unicode for the euro symbol
	private final String EURO = "\u20ac";

	private JFrame frmVendingMachines;
	private JPanel pnlMachineButtons;
	private JPanel pnlPopButtons;
	private JPanel pnlCoinBtns;
	private JPanel pnlBillBtns;
	private JPanel pnlCardBtns;

	private ArrayList<JButton> coinButtons;
	private ArrayList<JButton> billButtons;
	private ArrayList<JButton> cardButtons;
	private ArrayList<JButton> selectionButtons;
	private ArrayList<JLabel> outOfProductLabels;
	
	private boolean hasCoinSlot, hasBillSlot, hasCardSlot;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
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
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		coinButtons = new ArrayList();
		billButtons = new ArrayList();
		cardButtons = new ArrayList();
		outOfProductLabels = new ArrayList();
		selectionButtons = new ArrayList();
		
		//defaults to has
		hasCoinSlot = hasBillSlot = hasCardSlot = true;

		frmVendingMachines = new JFrame();
		frmVendingMachines.setTitle("Vending Machines");
		frmVendingMachines.setBounds(100, 100, 800, 600);
		frmVendingMachines.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmVendingMachines.getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel pnlMachine = new JPanel();
		pnlMachine.setBorder(null);
		frmVendingMachines.getContentPane()
				.add(pnlMachine, BorderLayout.CENTER);
		pnlMachine.setLayout(new BorderLayout(0, 0));

		JPanel pnlMachineMain = new JPanel();
		pnlMachine.add(pnlMachineMain, BorderLayout.CENTER);
		pnlMachineMain.setLayout(new BorderLayout(0, 0));

		JPanel pnlDisplay = new JPanel();
		pnlMachineMain.add(pnlDisplay, BorderLayout.NORTH);

		JTextPane txtpnDiplay = new JTextPane();
		txtpnDiplay.setForeground(Color.RED);
		txtpnDiplay.setEditable(false);
		txtpnDiplay.setText("Diplay");
		pnlDisplay.add(txtpnDiplay);

		JTextPane Display_text = new JTextPane();
		Display_text.setSize(500, 1);
		Display_text.setVisible(true);
		Display_text.setEditable(false);
		Display_text
				.setText("$0.00                                                                                                                      ");
		pnlDisplay.add(Display_text, BorderLayout.EAST);

		JPanel pnlDeliveryChute = new JPanel();
		pnlMachineMain.add(pnlDeliveryChute, BorderLayout.SOUTH);

		JLabel lblDeliveryChute = new JLabel("Delivery Chute");
		pnlDeliveryChute.add(lblDeliveryChute);

		pnlMachineButtons = new JPanel();
		pnlMachineMain.add(pnlMachineButtons, BorderLayout.CENTER);

		JPanel panel = new JPanel();
		pnlMachineButtons.add(panel);

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
		pnlCoins.setLayout(new FlowLayout(FlowLayout.CENTER, 6, 6));

		JLabel lblCoins = new JLabel("Coins:");
		pnlCoins.add(lblCoins);

		pnlCoinBtns = new JPanel();
		pnlCoins.add(pnlCoinBtns);
		pnlCoinBtns.setLayout(new GridLayout(0, 2, 2, 2));

		JPanel pnlBills = new JPanel();
		pnlMoneySelection.add(pnlBills);
		pnlBills.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblBills = new JLabel("Bills:");
		pnlBills.add(lblBills);

		pnlBillBtns = new JPanel();
		pnlBills.add(pnlBillBtns);
		pnlBillBtns.setLayout(new GridLayout(0, 2, 0, 0));

		JPanel pnlCards = new JPanel();
		pnlMoneySelection.add(pnlCards);
		pnlCards.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblCards = new JLabel("Cards:");
		pnlCards.add(lblCards);

		pnlCardBtns = new JPanel();
		pnlCards.add(pnlCardBtns);
		pnlCardBtns.setLayout(new GridLayout(0, 2, 0, 0));

		JPanel pnlCurrencyType = new JPanel();
		pnlMoney.add(pnlCurrencyType, BorderLayout.NORTH);

		JLabel lblCurrencyType = new JLabel("Currency type:");
		lblCurrencyType.setHorizontalAlignment(SwingConstants.TRAILING);
		pnlCurrencyType.add(lblCurrencyType);

		final JComboBox cmbCurr = new JComboBox();
		cmbCurr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				switch (cmbCurr.getSelectedIndex()) {
				case 0:
					resetCurrencyButtons();
					canadaSetup();
					break;
				case 1:
					resetCurrencyButtons();
					americaSetup();
					break;
				case 2:
					resetCurrencyButtons();
					europeanSetup();
					break;
				}
			}
		});
		cmbCurr.setModel(new DefaultComboBoxModel(new String[] { "Canadian",
				"American", "European" }));
		pnlCurrencyType.add(cmbCurr);

		canadaSetup();
	}

	/**
	 * Clears all currency related buttons
	 */
	public void resetCurrencyButtons() {
		pnlCoinBtns.removeAll();
		pnlBillBtns.removeAll();
		pnlCardBtns.removeAll();

		coinButtons = new ArrayList();
		billButtons = new ArrayList();
		cardButtons = new ArrayList();
	}

	/**
	 * Adds coins, bills and cards to the display valid for Canada
	 */
	public void canadaSetup() {
		if (hasCoinSlot) {
			coinButtons.add(createCoinButton(5, "$"));
			coinButtons.add(createCoinButton(10, "$"));
			coinButtons.add(createCoinButton(25, "$"));
			coinButtons.add(createCoinButton(100, "$"));
			coinButtons.add(createCoinButton(200, "$"));
		}

		if (hasBillSlot) {
			billButtons.add(createBillButton(500, "$"));
			billButtons.add(createBillButton(1000, "$"));
			billButtons.add(createBillButton(2000, "$"));
		}

		if (hasCardSlot) {
			cardButtons.add(createCardButton(0, "$"));
			cardButtons.add(createCardButton(500, "$"));
			cardButtons.add(createCardButton(2500, "$"));
		}

		redisplayCurrencyButtons();
	}

	/**
	 * Adds coins, bills and cards to the display valid for America
	 */
	public void americaSetup() {
		coinButtons.add(createCoinButton(5, "$"));
		coinButtons.add(createCoinButton(10, "$"));
		coinButtons.add(createCoinButton(25, "$"));

		billButtons.add(createBillButton(100, "$"));
		billButtons.add(createBillButton(500, "$"));
		billButtons.add(createBillButton(1000, "$"));
		billButtons.add(createBillButton(2000, "$"));

		cardButtons.add(createCardButton(0, "$"));
		cardButtons.add(createCardButton(500, "$"));
		cardButtons.add(createCardButton(2500, "$"));

		redisplayCurrencyButtons();
	}

	/**
	 * Adds coins, bills and cards to the display valid for Europe
	 */
	public void europeanSetup() {
		coinButtons.add(createCoinButton(5, EURO));
		coinButtons.add(createCoinButton(10, EURO));
		coinButtons.add(createCoinButton(20, EURO));
		coinButtons.add(createCoinButton(50, EURO));
		coinButtons.add(createCoinButton(100, EURO));
		coinButtons.add(createCoinButton(200, EURO));

		billButtons.add(createBillButton(500, EURO));
		billButtons.add(createBillButton(1000, EURO));
		billButtons.add(createBillButton(2000, EURO));

		cardButtons.add(createCardButton(0, EURO));
		cardButtons.add(createCardButton(500, EURO));
		cardButtons.add(createCardButton(250, EURO));

		redisplayCurrencyButtons();
	}

	/**
	 * Puts all currency buttons in the arraylists into their respective panels
	 * and redraws those panels
	 */
	public void redisplayCurrencyButtons() {
		for (int i = 0; i < coinButtons.size(); i++) {
			pnlCoinBtns.add(coinButtons.get(i));
		}

		for (int i = 0; i < billButtons.size(); i++) {
			pnlBillBtns.add(billButtons.get(i));
		}

		for (int i = 0; i < cardButtons.size(); i++) {
			pnlCardBtns.add(cardButtons.get(i));
		}

		pnlCoinBtns.revalidate();
		pnlCoinBtns.repaint();
		pnlBillBtns.revalidate();
		pnlBillBtns.repaint();
		pnlCardBtns.revalidate();
		pnlCardBtns.repaint();
	}

	/**
	 * Should load the required components for the first machine's setup Not
	 * currently correct
	 */
	public void machine1Setup() {

		for (int i = 0; i < 6; i++) {
			outOfProductLabels.add(createOutOfProductLight());
			selectionButtons.add(createPopButton("Pop " + i));
		}

		for (int i = 0; i < selectionButtons.size(); i++) {
			pnlPopButtons.add(outOfProductLabels.get(i));
			pnlPopButtons.add(selectionButtons.get(i));
		}
	}

	/**
	 * Returns a button used for coins
	 * 
	 * @param amount
	 *            is the amount in cents/regional equivalent
	 * @param currType
	 *            is the currency identifier (ex. $)
	 * @return the button being asked for
	 */
	public JButton createCoinButton(int amount, String currType) {
		DecimalFormat df = new DecimalFormat("0.00");
		Double amountInDollars = (double) amount / 100;
		String buttonText = currType + df.format(amountInDollars);
		return new JButton(buttonText);
	}

	/**
	 * Returns a button used for bills Separate from coins as they will need to
	 * raise different events once those are implemented
	 * 
	 * @param amount
	 *            is the amount in cents/regional equivalent
	 * @param currType
	 *            is the currency identifier (ex. $)
	 * @return the button being asked for
	 */
	public JButton createBillButton(int amount, String currType) {
		DecimalFormat df = new DecimalFormat("0.00");
		Double amountInDollars = (double) amount / 100;
		String buttonText = currType + df.format(amountInDollars);
		return new JButton(buttonText);
	}

	/**
	 * Returns a button used for cards Separate from coins as they will need to
	 * raise different events once those are implemented
	 * 
	 * @param amount
	 *            is the amount in cents/regional equivalent
	 * @param currType
	 *            is the currency identifier (ex. $)
	 * @return the button being asked for
	 */
	public JButton createCardButton(int amount, String currType) {
		DecimalFormat df = new DecimalFormat("0.00");
		Double amountInDollars = (double) amount / 100;
		String buttonText = currType + df.format(amountInDollars);
		return new JButton(buttonText);
	}

	/**
	 * Creates a label used as an out of product light
	 * 
	 * @return the label being asked for
	 */
	public JLabel createOutOfProductLight() {
		JLabel label = new JLabel("     ");
		label.setOpaque(true);
		label.setForeground(Color.LIGHT_GRAY);
		label.setBackground(Color.LIGHT_GRAY);
		return label;
	}

	/**
	 * Creates a new button to be used to select and purchase pop
	 * 
	 * @param name
	 *            is what will be displayed on the button
	 * @return the button being asked for
	 */
	public JButton createPopButton(String name) {
		return new JButton(name);
	}
}
