package gui.Button;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JColorChooser;
import javax.swing.JComboBox;

import gui.Main.Mediator;

public class FillColorComboBoxListener implements ActionListener{
	private JComboBox<String> fillColorComboBox;
	private String[] colorNames;
	private Color[] colors;
	private Mediator mediator;
	
	public FillColorComboBoxListener(JComboBox<String> fillColorComboBox,String[] colorNames,Color[] colors,Mediator mediator) {
		this.fillColorComboBox=fillColorComboBox;
		this.colorNames=colorNames;
		this.colors=colors;
		this.mediator=mediator;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		int selectedIndex=fillColorComboBox.getSelectedIndex();
		
		if(selectedIndex==colorNames.length-1) {
			Color selectedColor=JColorChooser.showDialog(null, "Choose a color",Color.black);
			if(selectedColor!=null) {
				mediator.setFillColor(selectedColor);
			}
		}
		else {
			mediator.setFillColor(colors[selectedIndex]);
		}
		mediator.repaint();
	}

}