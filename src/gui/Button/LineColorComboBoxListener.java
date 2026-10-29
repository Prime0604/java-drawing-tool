package gui.Button;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JColorChooser;
import javax.swing.JComboBox;

import gui.Main.Mediator;

public class LineColorComboBoxListener implements ActionListener{
	private JComboBox<String> lineColorComboBox;
	private String[] colorNames;
	private Color[] colors;
	private Mediator mediator;
	
	public LineColorComboBoxListener(JComboBox<String> lineColorComboBox,String[] colorNames,Color[] colors,Mediator mediator) {
		this.lineColorComboBox=lineColorComboBox;
		this.colorNames=colorNames;
		this.colors=colors;
		this.mediator=mediator;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		int selectedIndex=lineColorComboBox.getSelectedIndex();
		
		if(selectedIndex==colorNames.length-1) {
			Color selectedColor=JColorChooser.showDialog(null, "Choose a color",Color.black);
			if(selectedColor!=null) {
				mediator.setLineColor(selectedColor);
			}
		}
		else {
			mediator.setLineColor(colors[selectedIndex]);
		}
		mediator.repaint();
	}

}
