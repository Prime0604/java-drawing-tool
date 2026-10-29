package gui.Button;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JComboBox;

import gui.Main.Mediator;
import gui.Main.MyDrawing;

public class ThicknessComboBoxListener implements ActionListener{
	private JComboBox<String> thicknessComboBox;
	private int[] thickness;
	private Mediator mediator;
	
	public ThicknessComboBoxListener(JComboBox<String> ComboBox,int[] thickness,Mediator mediator) {
		this.thicknessComboBox=ComboBox;
		this.thickness=thickness;
		this.mediator=mediator;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		int selectedIndex=thicknessComboBox.getSelectedIndex();
		Vector<MyDrawing>Drawings=mediator.selectedDrawings;
		if(Drawings!=null) {
			for(MyDrawing d:Drawings) {
				d.setLineWidth(thickness[selectedIndex]);
			}
			mediator.repaint();
		}
	}

}