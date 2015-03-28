/*
	- Source modified from :
	http://stackoverflow.com/questions/19261209/how-to-show-on-screen-keyboard-using-swing-in-java-for-typing-tutor-which-layou

*/


package vendingMachine;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class VirtualKeyboard extends JFrame {

    private JFrame frame = new JFrame("Virtual Keyboard");
    private JPanel parent = new JPanel(new GridLayout(0, 1));
    private JPanel[] panel;
    private JButton[][] button;
    private static final String[][] key = {
    	
    	{"~", "!", "@", "#", "$", "%", "^", "*", "(", ")", "-", "+", "="},
    	
        // Row 0
        {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0"},
            
        // Row 1    
        {"Tab", "Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P", "Backspace"},
        
        // Row 2
        {"A", "S", "D","F", "G", "H", "J", "K", "L", ":", "Enter"},
        
        // Row 3
        {"Shift", "Z", "X", "C", "V", "B", "N", "M", ".", "<", ">", "?", "!", "/"},
        
        // Row 4
        {"           Space            "},
     
    };

    public VirtualKeyboard() {
        super("Virtual Keyboard");
        panel = new JPanel[6];	// Number of Array elements is the number of rows on keyboard
        for (int row = 0; row < key.length; row++) {
            panel[row] = new JPanel();
            button = new JButton[20][20];
            for (int column = 0; column < key[row].length; column++) {
                button[row][column] = new JButton(key[row][column]);
                button[row][column].putClientProperty("column", column);
                button[row][column].putClientProperty("row", row);
                button[row][column].putClientProperty("key", key[row][column]);
                button[row][column].addActionListener(new MyActionListener());
                panel[row].add(button[row][column]);
            }
            parent.add(panel[row]);
        }
        //	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(parent);
        pack();
        setVisible(true);
    }

    public class MyActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton btn = (JButton) e.getSource();
            if (btn.getClientProperty("key").equals("Space")) {
//            		btn.getClientProperty("key").
            }
            System.out.println("clicked column --> " + btn.getClientProperty("column")
                    + ", row --> " + btn.getClientProperty("row")
                    + ", Key Typed --> " + btn.getClientProperty("key"));
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                VirtualKeyboard gui = new VirtualKeyboard();
            }
        });
    }
}