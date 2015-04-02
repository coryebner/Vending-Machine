package gui;

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

public class StandardMachineGUI extends VendingMachineGUI {

	// unicode for the euro symbol
	private final String EURO = "\u20ac";
	private static final String[] ALPHABET = { "A", "B", "C", "D", "E", "F", "G",
		"H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
		"U", "V", "W", "X", "Y", "Z" };
	
	private boolean codeInProgress = false;

	private JPanel pnlMachineButtons;
	private JPanel pnlPopButtons;
	private JPanel pnlCandyButtons;
	private JPanel pnlCandyLetterButtons;
	private JPanel pnlMoneySelection;
	private JPanel pnlCoins;
	private JPanel pnlCoinBtns;
	private JPanel pnlBills;
	private JPanel pnlBillBtns;
	private JPanel pnlCards;
	private JPanel pnlCardBtns;

	private JComboBox cmbCurr;
	private JButton billEject;

	private JTextPane Display_text;
	
	private JLabel lblExactChange;
	private JLabel lblOutOfOrder;
	private JLabel lblInternetLight;

	private ArrayList<JButton> coinButtons;
	private ArrayList<JButton> billButtons;
	private ArrayList<JButton> cardButtons;
	private ArrayList<JButton> popButtons;
	private ArrayList<JButton> candyLetterButtons;
	private ArrayList<JButton> candyNumberButtons;
	private ArrayList<JLabel> outOfProductLabels;

	// defaults to true
	private boolean hasCoinSlot = true;
	private boolean hasBillSlot = true;
	private boolean hasCardSlot = true;
	private boolean hasInternetLight = true;
	private boolean hasPopButtons = true;
	private boolean hasCandyButtons = true;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StandardMachineGUI window = new StandardMachineGUI();
					window.getMainFrame().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public StandardMachineGUI() {
		initialize(null, true, true, true, true, true, true);
	}
	
	public StandardMachineGUI(Object machine, ArrayList<Boolean> parts) {
		initialize(machine, parts.get(0), parts.get(1), parts.get(2), parts.get(3), parts.get(4), parts.get(5));
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Object machine, boolean coinSlot, boolean billSlot, boolean cardSlot, boolean internetLight, boolean popBtns, boolean candyBtns) {
		coinButtons = new ArrayList();
		billButtons = new ArrayList();
		cardButtons = new ArrayList();
		outOfProductLabels = new ArrayList();
		popButtons = new ArrayList();
		candyLetterButtons = new ArrayList();
		candyNumberButtons = new ArrayList();
		
		hasCoinSlot = coinSlot;
		hasBillSlot = billSlot;
		hasCardSlot = cardSlot;
		hasInternetLight = internetLight;
		hasPopButtons = popBtns;
		hasCandyButtons = candyBtns;

		JFrame mainFrame = new JFrame();
		mainFrame.setTitle("Vending Machines");
		mainFrame.setBounds(100, 100, 800, 600);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.getContentPane().setLayout(new BorderLayout(0, 0));
		setMainFrame(mainFrame);

		JPanel pnlMachine = new JPanel();
		pnlMachine.setBorder(null);
		getMainFrame().getContentPane()
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

		Display_text = new JTextPane();
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

		JPanel pnlMisc = new JPanel();
		pnlMachineButtons.add(pnlMisc);
		pnlMisc.setLayout(new GridLayout(0, 1, 0, 3));

		JButton btnReturn = new JButton("Return");
		pnlMisc.add(btnReturn);

		lblExactChange = new JLabel("     ");
		lblExactChange.setBackground(Color.LIGHT_GRAY);
		lblExactChange.setForeground(Color.LIGHT_GRAY);
		lblExactChange.setOpaque(true);
		pnlMisc.add(lblExactChange);

		lblOutOfOrder = new JLabel("     ");
		lblOutOfOrder.setOpaque(true);
		lblOutOfOrder.setForeground(Color.LIGHT_GRAY);
		lblOutOfOrder.setBackground(Color.LIGHT_GRAY);
		pnlMisc.add(lblOutOfOrder);
		
		lblInternetLight = new JLabel("     ");
		lblInternetLight.setOpaque(true);
		lblInternetLight.setForeground(Color.LIGHT_GRAY);
		lblInternetLight.setBackground(Color.LIGHT_GRAY);
		if (hasInternetLight) {
			pnlMisc.add(lblInternetLight);
		}

		pnlPopButtons = new JPanel();
		if (hasPopButtons) {
			pnlMachineButtons.add(pnlPopButtons);
		}
		pnlPopButtons.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnlPopButtons.setLayout(new GridLayout(0, 2, 2, 2));

		pnlCandyButtons = new JPanel();
		if (hasCandyButtons) {
			pnlMachineButtons.add(pnlCandyButtons);
		}
		pnlCandyButtons.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnlCandyButtons.setLayout(new GridLayout(1, 0, 0, 0));

		pnlCandyLetterButtons = new JPanel();
		pnlCandyButtons.add(pnlCandyLetterButtons);
		pnlCandyLetterButtons.setLayout(new GridLayout(0, 1, 0, 0));

		JPanel pnlNumberCandyButtons = new JPanel();
		pnlCandyButtons.add(pnlNumberCandyButtons);
		pnlNumberCandyButtons.setLayout(new GridLayout(0, 3, 0, 0));

		JButton btn1 = new JButton("1");
		candyNumberButtons.add(btn1);
		pnlNumberCandyButtons.add(btn1);

		JButton btn2 = new JButton("2");
		candyNumberButtons.add(btn2);
		pnlNumberCandyButtons.add(btn2);

		JButton btn3 = new JButton("3");
		candyNumberButtons.add(btn3);
		pnlNumberCandyButtons.add(btn3);

		JButton btn4 = new JButton("4");
		candyNumberButtons.add(btn4);
		pnlNumberCandyButtons.add(btn4);

		JButton btn5 = new JButton("5");
		candyNumberButtons.add(btn5);
		pnlNumberCandyButtons.add(btn5);

		JButton btn6 = new JButton("6");
		candyNumberButtons.add(btn6);
		pnlNumberCandyButtons.add(btn6);

		JButton btn7 = new JButton("7");
		candyNumberButtons.add(btn7);
		pnlNumberCandyButtons.add(btn7);

		JButton btn8 = new JButton("8");
		candyNumberButtons.add(btn8);
		pnlNumberCandyButtons.add(btn8);

		JButton btn9 = new JButton("9");
		candyNumberButtons.add(btn9);
		pnlNumberCandyButtons.add(btn9);
		

		JPanel pnlCndyNumSpaceing = new JPanel();
		pnlNumberCandyButtons.add(pnlCndyNumSpaceing);

		JButton btn0 = new JButton("0");
		candyNumberButtons.add(btn0);
		pnlNumberCandyButtons.add(btn0);
		
		for (JButton btn : candyNumberButtons) {
			btn1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if (codeInProgress) {
						enableInteractivity(false);
						// TODO add button press method call
					}
				}
			});
		}

		machine1Setup();

		JPanel pnlMoney = new JPanel();
		pnlMoney.setBorder(new LineBorder(new Color(0, 0, 0)));
		getMainFrame().getContentPane().add(pnlMoney, BorderLayout.WEST);
		pnlMoney.setLayout(new BorderLayout(0, 0));

		pnlMoneySelection = new JPanel();
		pnlMoneySelection.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnlMoney.add(pnlMoneySelection, BorderLayout.CENTER);
		pnlMoneySelection.setLayout(new GridLayout(0, 1, 0, 0));

		pnlCoins = new JPanel();
		if (hasCoinSlot) {
			pnlMoneySelection.add(pnlCoins);
		}
		pnlCoins.setLayout(new FlowLayout(FlowLayout.CENTER, 6, 6));

		JLabel lblCoins = new JLabel("Coins:");
		pnlCoins.add(lblCoins);

		pnlCoinBtns = new JPanel();
		pnlCoins.add(pnlCoinBtns);
		pnlCoinBtns.setLayout(new GridLayout(0, 2, 2, 2));

		pnlBills = new JPanel();
		if (hasBillSlot) {
			pnlMoneySelection.add(pnlBills);
		}
		pnlBills.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblBills = new JLabel("Bills:");
		pnlBills.add(lblBills);

		pnlBillBtns = new JPanel();
		pnlBills.add(pnlBillBtns);
		pnlBillBtns.setLayout(new GridLayout(0, 2, 0, 0));

		pnlCards = new JPanel();
		if (hasCardSlot) {
			pnlMoneySelection.add(pnlCards);
		}
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

		cmbCurr = new JComboBox();
		cmbCurr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setCurrencyType();
			}
		});
		cmbCurr.setModel(new DefaultComboBoxModel(new String[] { "Canadian",
				"American", "European" }));
		pnlCurrencyType.add(cmbCurr);
		
		JPanel pnlAdminBtn = new JPanel();
		pnlMoney.add(pnlAdminBtn, BorderLayout.SOUTH);
		
		JButton btnAdmin = new JButton("Admin");
		pnlAdminBtn.add(btnAdmin);

		billEject = new JButton("Remove bill");
		billEject.setEnabled(false);

		canadaSetup();
	}

	/**
	 * Sets what currency buttons are shown based on the currency type selected
	 * in the combo box Mainly used by combo box action listener
	 */
	private void setCurrencyType() {
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

	/**
	 * Will create a pop button and out of product light for each product name
	 * passed
	 * 
	 * @param names
	 *            are the names of the products the buttons are being created
	 *            for
	 */
	private void createPopButtons(ArrayList<String> names) {
		for (String name : names) {
			outOfProductLabels.add(createOutOfProductLight());
			popButtons.add(createPopButton(name));
		}
	}

	/**
	 * Creates a number of candy letter buttons, starting with the letter A and
	 * adds them to the appropriate arraylist
	 * 
	 * @param num
	 *            is the number of the buttons being created
	 */
	private void createCandyLetterButtons(int num) {
		for (int i = 0; i < num; i++) {
			JButton btn = new JButton(ALPHABET[i]);
			btn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if (!codeInProgress) {
						codeInProgress = true;
					} else {
						codeInProgress = false;
					}
					// TODO add press button method call here
				}
			});
			candyLetterButtons.add(btn);
		}
	}

	/**
	 * Redraws the candy letter buttons panel based on the elements in the candy
	 * letter buttons arraylist
	 */
	private void reloadCandyLetterButtons() {
		for (JButton btn : candyLetterButtons) {
			pnlCandyLetterButtons.add(btn);
		}

		pnlCandyLetterButtons.revalidate();
		pnlCandyLetterButtons.repaint();
	}

	/**
	 * Redraws the pop button panel based on the elements in the selection and
	 * out of product light arraylists
	 */
	private void reloadPopButtons() {
		for (int i = 0; i < popButtons.size(); i++) {
			pnlPopButtons.add(outOfProductLabels.get(i));
			pnlPopButtons.add(popButtons.get(i));
		}
		pnlPopButtons.revalidate();
		pnlPopButtons.repaint();
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

		reloadCurrencyButtons();
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

		reloadCurrencyButtons();
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

		reloadCurrencyButtons();
	}

	/**
	 * Puts all currency buttons in the arraylists into their respective panels
	 * and redraws those panels
	 */
	public void reloadCurrencyButtons() {
		for (int i = 0; i < coinButtons.size(); i++) {
			pnlCoinBtns.add(coinButtons.get(i));
		}

		for (int i = 0; i < billButtons.size(); i++) {
			pnlBillBtns.add(billButtons.get(i));
		}

		JPanel pnl = new JPanel();
		pnlBillBtns.add(pnl);
		pnlBillBtns.add(billEject);

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
	 * currently correct Will not be used for actual program TODO remove when
	 * functionality complete
	 */
	public void machine1Setup() {
		ArrayList<String> names = new ArrayList();

		names.add("Coke");
		names.add("Diet Coke");
		names.add("Coke Zero");
		names.add("Sprite");
		names.add("Root Beer");
		names.add("Water");

		createPopButtons(names);
		reloadPopButtons();
		
		createCandyLetterButtons(6);
		reloadCandyLetterButtons();
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
		JButton btn = new JButton(buttonText);
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// TODO add insertCoin method call here
			}
		});
		return btn;
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
		JButton btn = new JButton(buttonText);
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// TODO add insertBill method call here
			}
		});
		return btn;
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
		JButton btn = new JButton(buttonText);
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// TODO add insertCard method call here
			}
		});
		return btn;
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
		JButton btn = new JButton(name);
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// pushing a pop button starts a "transaction"
				// components are disabled as the hardware will
				// not be handling thread type behavior
				enableInteractivity(false);
				// TODO add buttonPressed method call here
			}
		});
		return btn;
	}

	/**
	 * Enables or disables all interactable components in the GUI based on the
	 * passed parameter
	 * 
	 * @param status
	 *            is true if the components will be enabled, false if the
	 *            components will be disabled
	 */
	public void enableInteractivity(boolean status) {
		for (JButton btn : coinButtons) {
			btn.setEnabled(status);
		}

		enableBillButtons(status);

		for (JButton btn : cardButtons) {
			btn.setEnabled(status);
		}

		for (JButton btn : popButtons) {
			btn.setEnabled(status);
		}
		
		for (JButton btn : candyLetterButtons) {
			btn.setEnabled(status);
		}
		
		for (JButton btn : candyNumberButtons) {
			btn.setEnabled(status);
		}

		cmbCurr.setEnabled(status);
	}

	/**
	 * Enables or disables all bill buttons based on the parameter
	 * 
	 * @param status
	 */
	public void enableBillButtons(boolean status) {
		for (JButton btn : billButtons) {
			btn.setEnabled(status);
		}
	}

	// TODO replace method signature with proper one from listener
	// implementation
	/**
	 * Listens to the display of the machine and updates the display text shown
	 * on the GUI Also enables components on the machine This event is being
	 * used as a "transaction complete/failed" event
	 * 
	 * @param display
	 *            is the physical display on the machine that is showing the
	 *            message
	 * @param oldMsg
	 *            is the message that was displayed before the message was
	 *            changed
	 * @param newMsg
	 *            is the message that is currently displayed on the display
	 */
	public void messageChange(Object display, String oldMsg, String newMsg) {
		// prevents wrong enabling cases for timed messages
		if (!oldMsg.equals(newMsg)) {
			enableInteractivity(true);
			Display_text.setText(newMsg);
		}
	}

	/**
	 * Listens to the indicator lights and changes the color of 
	 * the appropriate label to red
	 * @param light
	 */
	public void activated(Object light) {
		/*
		 * if (light.equals(machine.getOutOfOrderLight()) {
		 * 	lblInternetLight.setForeground(Color.RED);
		 *	lblInternetLight.setBackground(Color.RED);
		 * }
		 */
	}
}
