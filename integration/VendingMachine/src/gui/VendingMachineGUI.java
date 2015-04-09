package gui;

import javax.swing.JFrame;

public abstract class VendingMachineGUI {

	private JFrame frmVendingMachines;
	
	public JFrame getMainFrame() {
		return frmVendingMachines;
	}
	
	public void setMainFrame(JFrame frm) {
		frmVendingMachines = frm;
	}
}
