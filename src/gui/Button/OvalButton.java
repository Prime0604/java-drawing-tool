package gui.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import gui.Main.MyDrawing;
import gui.Main.State;
import gui.Main.StateManager;
import gui.Shapes.MyOval;

public class OvalButton extends JButton{
	StateManager stateManager;
	
	public OvalButton(StateManager stateManager) {
		super("Oval");
		
		addActionListener(new OvalListener());
		
		this.stateManager=stateManager;
	}
	class OvalListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			stateManager.setState(new OvalState(stateManager));
		}
	}
	
	class OvalState extends MyDrawing implements State{
		StateManager stateManager;
		public MyOval oval;
		public OvalState(StateManager stateManager) {
			this.stateManager=stateManager;
		}
		public void mouseDown(int x,int y) {
			setLocation(x,y);
			oval=new MyOval(x,y,0,0);
			stateManager.addDrawing(oval);
		}
		public void mouseUp(int x,int y) {}
		public void mouseDrag(int x, int y) {
			stateManager.removeDrawing();
			int xx=getX();
			int yy=getY();
			oval.setLocation(xx, yy);
			oval.setSize(x-xx,y-yy);
			if (stateManager.getDashed()) {
		        oval.setDashed(true);
		    }
		    if(stateManager.getShadow()) {
		    	oval.setShadow(true);
		    }
		    if(stateManager.getThickness()) {
		    	oval.setLineWidth(getLineWidth()*2);
		    }
		    if(stateManager.getDashedPattern()) {
		    	oval.setDashedPattern(true);
		    }
		    stateManager.addDrawing(oval);
		}
	}
}