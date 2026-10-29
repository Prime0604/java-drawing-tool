package gui.Button;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import gui.Main.Mediator;

public class OrderComboBoxListener implements ActionListener{
	private JComboBox<String> orderComboBox;
	private String[] order;
	private Mediator mediator;
	
	public OrderComboBoxListener(JComboBox<String> orderComboBox,String[] order,Mediator mediator) {
		this.orderComboBox=orderComboBox;
		this.order=order;
		this.mediator=mediator;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		int selectedIndex=orderComboBox.getSelectedIndex();
		mediator.order(selectedIndex);
		mediator.repaint();
	}
}