package gui.Button;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import gui.Main.StateManager;

public class DashedCheckBox implements ItemListener{
	StateManager stateManager;
	
	public DashedCheckBox(StateManager stateManager) {
		this.stateManager=stateManager;
	}
	
	public void itemStateChanged(ItemEvent e) {
		int state = e.getStateChange();
		if(state==ItemEvent.SELECTED) {	
			stateManager.setDashed(true);
		}
		else {
			stateManager.setDashed(false);
		}
	}
}