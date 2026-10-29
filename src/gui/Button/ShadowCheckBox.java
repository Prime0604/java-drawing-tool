package gui.Button;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import gui.Main.StateManager;

public class ShadowCheckBox implements ItemListener{
	StateManager stateManager;
	
	public ShadowCheckBox(StateManager stateManager) {
		this.stateManager=stateManager;
	}
	
	public void itemStateChanged(ItemEvent e) {
		int state = e.getStateChange();
		if(state==ItemEvent.SELECTED) {	
			stateManager.setShadow(true);
		}
		else {
			stateManager.setShadow(false);
		}
	}
}