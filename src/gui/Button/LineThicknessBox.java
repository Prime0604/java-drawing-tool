package gui.Button;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import gui.Main.StateManager;

public class LineThicknessBox implements ItemListener{
	StateManager stateManager;
	
	public LineThicknessBox(StateManager stateManager) {
		this.stateManager=stateManager;
	}
	
	public void itemStateChanged(ItemEvent e) {
		int state = e.getStateChange();
		if(state==ItemEvent.SELECTED) {	
			stateManager.setLineThickness(true);
		}
		else {
			stateManager.setLineThickness(false);
		}
	}
}