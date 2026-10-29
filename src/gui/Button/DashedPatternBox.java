package gui.Button;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import gui.Main.StateManager;

public class DashedPatternBox implements ItemListener{
	StateManager stateManager;
	
	public DashedPatternBox(StateManager stateManager) {
		this.stateManager=stateManager;
	}
	
	public void itemStateChanged(ItemEvent e) {
		int state = e.getStateChange();
		if(state==ItemEvent.SELECTED) {	
			stateManager.setDashedPattern(true);
		}
		else {
			stateManager.setDashedPattern(false);
		}
	}
}