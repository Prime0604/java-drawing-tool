package gui.Button;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import gui.Main.Mediator;

public class FontComboBoxListener implements ActionListener{
	private JComboBox<String> fontComboBox;
	private String[] fontNames;
	private Mediator mediator;
	
	public FontComboBoxListener(JComboBox<String> fontComboBox,String[] fontNames,Mediator mediator) {
		this.fontComboBox=fontComboBox;
		this.fontNames=fontNames;
		this.mediator=mediator;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		int selectedIndex=fontComboBox.getSelectedIndex();
		
		mediator.setFont(fontNames[selectedIndex]);
		mediator.repaint();
	}
}
