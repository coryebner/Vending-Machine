package gui;

import hardware.exceptions.DisabledException;
import hardware.exceptions.NoSuchHardwareException;
import hardware.funds.Banknote;
import hardware.funds.Card;
import hardware.funds.CardSlotNotEmptyException;
import hardware.funds.Coin;
import hardware.funds.Card.CardType;
import hardware.products.Product;
import hardware.racks.ProductRack;
import hardware.racks.ProductRackListener;
import hardware.ui.IndicatorLight;
import hardware.ui.IndicatorLightListener;

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

import java.awt.ComponentOrientation;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;

import java.awt.Color;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Locale;

import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.BevelBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTextPane;
import javax.swing.JList;

import hardware.*;
import hardware.exceptions.*;
import hardware.funds.*;
import hardware.funds.Card.*;
import hardware.products.Product;
import hardware.racks.*;
import hardware.simulators.*;
import hardware.ui.*;
import gui.test.*;


/**
 * Initial setup will involve being passed an abstract vending machine as well
 * as a list of booleans (may not be final) representing what parts the machine
 * has.
 * 
 * The touchscreen is a method of selection for the products.
 * 
 * Setup parts based on boolean parts
 * 	- Touchscreen machines do not have the sequence buttons (A-F, 0-9), therefore part 5 is set false.
 * 
 * @author Shayne, with touchscreen edits by Arnold
 * 
 * 
 */		

public class StandardTouchMachineGUI extends VendingMachineGUI implements ProductRackListener,IndicatorLightListener{
	
	// AP : This image is just a placeholder, config will give us resources (?)
	ImageIcon coke = createImageIcon("img/coca_cola.png", "Coke Logo");

	// unicode for the euro symbol
	private final String EURO = "\u20ac";
	private static final String[] ALPHABET = { "A", "B", "C", "D", "E", "F", "G",
		"H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
		"U", "V", "W", "X", "Y", "Z" };
	
	private AbstractVendingMachine machine;
	private boolean codeInProgress = false;
	private boolean buttonPressed = false;
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
	private JButton btnReturn;

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
					StandardTouchMachineGUI window = new StandardTouchMachineGUI();
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
	public StandardTouchMachineGUI() {
		int []coinvalue = { 5, 10, 25, 100, 200 };
		int []banknote = {500,1000,2000};
		int [] popcost	= {200,200,200,200,200,200 };
		String [] popname = {"Coke","DietCode","RootBeer", "asdf", "qwer","zxcv"};
		Locale locale = Locale.CANADA;
		AbstractVendingMachine vm = new VMRUS_SFF_P_C(locale,coinvalue);
		initialize(vm, true, true, true, true, true, true);
	}
	
	public StandardTouchMachineGUI(AbstractVendingMachine machine, ArrayList<Boolean> parts) {
		initialize(machine, parts.get(0), parts.get(1), parts.get(2), parts.get(3), parts.get(4), parts.get(5));
	}
	
	/**
	 * Needed for Image generation. Used for testing purposes,
	 * This should come from the Configurations Team.
	 * 
	 * @param path the absolute path leading to the image resource (currently 137 x 46)
	 * @param description of the image
	 * 
	 */
	protected ImageIcon createImageIcon(String path, String description) {
		java.net.URL imgURL = getClass().getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL, description);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(AbstractVendingMachine machine, boolean coinSlot, boolean billSlot, boolean cardSlot, boolean internetLight, boolean popBtns, boolean candyBtns) {
		this.machine = machine;
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
		mainFrame.setBounds(400, 10, 1300, 1000);	// AP : Larger Boundary for Touch Screen impl
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

		JLabel Display = new JLabel ("Display");
		Display.setForeground(Color.red);
		pnlDisplay.add(Display);

		Display_text = new JTextPane();
		Display_text.setSize(500, 1);
		Display_text.setVisible(true);
		Display_text
				.setText("$0.00                                                                                                                      ");
		pnlDisplay.add(Display_text, BorderLayout.EAST);
		
		JPanel pnlDeliveryChute = new JPanel();
		pnlMachineMain.add(pnlDeliveryChute, BorderLayout.SOUTH);
		JLabel DeliveryChute = new JLabel("Delivery Chute");
		pnlDeliveryChute.add(DeliveryChute);
		DeliveryChute.setSize(35,1);

		JTextPane DeliveryChuteText = new JTextPane();
		DeliveryChuteText.setText("                                                                                ");
		DeliveryChuteText.setSize(500, 1);
		pnlDeliveryChute.add(DeliveryChuteText);
		DeliveryChute.setVisible(true); 
		//DeliveryChute.setEditable(false);


		pnlMachineButtons = new JPanel();
		pnlMachineMain.add(pnlMachineButtons, BorderLayout.CENTER);

		JPanel pnlMisc = new JPanel();
		pnlMachineButtons.add(pnlMisc);
		pnlMisc.setLayout(new GridLayout(0, 1, 0, 3));

		btnReturn = new JButton("Return");
		pnlMisc.add(btnReturn);

		lblExactChange = new JLabel("ExactChange");
		lblExactChange.setBackground(Color.LIGHT_GRAY);
		lblExactChange.setForeground(Color.LIGHT_GRAY);
		lblExactChange.setOpaque(true);
		pnlMisc.add(lblExactChange);

		lblOutOfOrder = new JLabel("Out of Order");
		lblOutOfOrder.setOpaque(true);
		lblOutOfOrder.setForeground(Color.LIGHT_GRAY);
		lblOutOfOrder.setBackground(Color.LIGHT_GRAY);
		pnlMisc.add(lblOutOfOrder);
		
		lblInternetLight = new JLabel("Internet Light");
		lblInternetLight.setOpaque(true);
		lblInternetLight.setForeground(Color.LIGHT_GRAY);
		lblInternetLight.setBackground(Color.LIGHT_GRAY);
		if (hasInternetLight) {
			pnlMisc.add(lblInternetLight);
		}

		pnlPopButtons = new JPanel();
		pnlPopButtons.setLayout(new GridLayout(3, 4, 5, 10));
		pnlPopButtons.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		if (hasPopButtons) {
			pnlMachineButtons.add(pnlPopButtons);
		}
		pnlPopButtons.setBorder(new LineBorder(new Color(0, 0, 0)));
//		pnlPopButtons.setLayout(new GridLayout(0, 2, 2, 2)); // AP : Changed layout to Grid for Touch Screen experience

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
		
		// First 6 keys are the A-F
		int key = 6;
		for (JButton btn : candyNumberButtons) {
				addNumButtonAction(btn, key);
	            key++;
		}

		machine6Setup();

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
		btnAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ControlPanelGUI configPanel = new ControlPanelGUI(getMainFrame());				
			}
		});
		pnlAdminBtn.add(btnAdmin);

		billEject = new JButton("Remove bill");
		billEject.setEnabled(false);
        billEject.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
					machine.getBanknoteSlot().removeBanknote();
				} catch (NoSuchHardwareException e1) {
					e1.printStackTrace();
				}
            }
        });
       
		// Registering GUI to listen to parts of the vending machine
        try {
        	// Added type cast to the machine because the class type of the machine is Object
            ((AbstractVendingMachine) machine).getOutOfOrderLight().register(this);
            ((AbstractVendingMachine) machine).getExactChangeLight().register(this);
            if(popBtns == true){
                for(int i = 0; i< ((AbstractVendingMachine) machine).getNumberOfProductRacks(); i++){
                    ((AbstractVendingMachine) machine).getProductRack(i).register(this);
                }
            }
        } catch (NoSuchHardwareException e2) {
            e2.printStackTrace();
        }
		canadaSetup();
	}
	
//	Get Methods, mainly used for testing
	JButton getCoinBtn(int index){
		return coinButtons.get(index);		
	}
	
	public JButton getbillButtons(int index){
		return billButtons.get(index);
	}
	
	public JButton getcardButtons(int index){
		return cardButtons.get(index);
	}
	
	
	public JButton getPopBtn(int index){
		return popButtons.get(index);
	}
	
	public JButton getLetterBtn(int index){
		return candyLetterButtons.get(index);
	}
	
	public JButton getNumButtons(int index){
		return candyNumberButtons.get(index);
	}
	
	public JLabel getOutOfProductLabels(int index){
		return outOfProductLabels.get(index);
	}	
	
	public JLabel getOutOfOrderLight(){
		return lblOutOfOrder;
	}
	
	public JLabel getExactChangeLight(){
		return lblExactChange;
	}
	
	public JButton getReturnButton(){
		return btnReturn;
	}
	
	public JButton getEjectBillButton(){
		return billEject;
	}
	
	public boolean getButtonPressStatus(){
		return buttonPressed;
	}
	
	public void resetButtonPressedStatus(){
		buttonPressed = false;
	}
	
	public JTextPane getDisplay(){
		return Display_text;
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
		int i = 0;
		for (String name : names) {
			outOfProductLabels.add(createOutOfProductLight());
            JButton popbtn = createPopButton(name);
            popButtons.add(popbtn);
            addButtonAction(popbtn,i);
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
			addLetterButtonAction(btn, i);
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
			//pnlPopButtons.add(outOfProductLabels.get(i));	// AP: Need different impl here for Touchscreen
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
	 * Should load the required components for the sixth machine's setup Not
	 * currently correct Will not be used for actual program TODO remove when
	 * functionality complete
	 */
	public void machine6Setup() {
		ArrayList<String> names = new ArrayList();
		
		try {
			for(int i=0; i< machine.getNumberOfProductRacks(); i++){
				names.add("Pop "+ i);
			}
		} catch (NoSuchHardwareException e) {
			e.printStackTrace();
		}

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
              Coin coin = new Coin(amount);
              try {
            	  buttonPressed = true;
                  machine.getCoinSlot().addCoin(coin);
              } catch (NoSuchHardwareException e) {
                  e.printStackTrace();
              } catch (DisabledException e) {
                  e.printStackTrace();
              }
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
              Banknote bill = new Banknote(amount);
              try {
            	  buttonPressed = true;
                  machine.getBanknoteSlot().addBanknote(bill);
              } catch (DisabledException | NoSuchHardwareException e) {
                  
                  e.printStackTrace();
              }
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
						CardType cardtype = Card.CardType.PREPAID; 
						Card card = new Card (cardtype,"123456","prepaidcard","","03/2020",null,amount);
			                try {
			                	buttonPressed = true;
			                    machine.getCardSlot().insertCard(card);
			                } catch (CardSlotNotEmptyException | DisabledException
			                         | NoSuchHardwareException e) {
			                    e.printStackTrace();
			                }
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
		JLabel label = new JLabel("OutOfProduct");
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
		btn.setIcon(coke);
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
   * Add Listener to the JButtons on the GUI
   * @param name of the JButton
   * @param key to the product rack
   */
  public void addButtonAction(JButton button, int key){
      button.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              try {
            	  buttonPressed = true;
            	  GUIHelper.enableComponents(getMainFrame(), false);
                  machine.getSelectionButton(key).press();
              } catch (NoSuchHardwareException e1) {			
                  e1.printStackTrace();
              }
          }
      });
  }
 
  public void addNumButtonAction(JButton button, int key){
  	button.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              try {
              	buttonPressed = true;
              	if (codeInProgress) {
              		GUIHelper.enableComponents(getMainFrame(), false);
              		machine.getSelectionButton(key).press();
              	}
              } catch (NoSuchHardwareException e1) {			
                  e1.printStackTrace();
              }
          }
      });
  }
  
  public void addLetterButtonAction(JButton button, int key){
      button.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              try {
            	  buttonPressed = true;
              	if (!codeInProgress) {
						codeInProgress = true;
					} else {
						codeInProgress = false;
					}
                  machine.getSelectionButton(key).press();
              } catch (NoSuchHardwareException e1) {			
                  e1.printStackTrace();
              }
          }
      });
  }
  
  @Override
  public void enabled(AbstractHardware<AbstractHardwareListener> hardware) {
      // TODO Auto-generated method stub
      
  }
  
  @Override
  public void disabled(AbstractHardware<AbstractHardwareListener> hardware) {
      // TODO Auto-generated method stub
      
  }
  
  @Override
  public void activated(IndicatorLight light) {
      try {
          if(light == machine.getExactChangeLight()){
              lblExactChange.setForeground(Color.BLACK);
          }else{
              lblOutOfOrder.setForeground(Color.BLACK);
          }
      } catch (NoSuchHardwareException e) {			
          e.printStackTrace();
      }
      
  }
  
  @Override
  public void deactivated(IndicatorLight light) {
      try {
          if(light == machine.getExactChangeLight()){
              lblExactChange.setForeground(Color.RED);
          }else{
              lblOutOfOrder.setForeground(Color.LIGHT_GRAY);
          }
      } catch (NoSuchHardwareException e) {
          e.printStackTrace();
      }	
  }
  
  @Override
  public void productAdded(ProductRack productRack, Product product) {
      int productIndex;
      for(productIndex = 0; ;productIndex++){
          try {
              if(machine.getProductRack(productIndex) == productRack){
                  break;
              }
          } catch (NoSuchHardwareException e) {
              e.printStackTrace();
          }
      }
      outOfProductLabels.get(productIndex).setForeground(Color.LIGHT_GRAY);	
  }
  
  @Override
  public void productRemoved(ProductRack productRack, Product product) {
      // No need to do anything
  }
  
  @Override
  public void productFull(ProductRack productRack) {
      // No need to do anything
  }
  
  @Override
  public void productEmpty(ProductRack productRack) {
      int productIndex;
      for(productIndex = 0; ;productIndex++){
          try {
              if(machine.getProductRack(productIndex) == productRack){
                  break;
              }
          } catch (NoSuchHardwareException e) {
              e.printStackTrace();
          }
      }
      outOfProductLabels.get(productIndex).setForeground(Color.BLACK);
      
  }
  

}
