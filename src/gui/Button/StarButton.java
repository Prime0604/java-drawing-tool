package gui.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import gui.Main.MyDrawing;
import gui.Main.State;
import gui.Main.StateManager;
import gui.Shapes.MyStar;

public class StarButton extends JButton{
	StateManager stateManager;
	
	public StarButton(StateManager stateManager) {
		super("Star");
		
		addActionListener(new StarListener());
		
		this.stateManager=stateManager;
	}
	class StarListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			stateManager.setState(new StarState(stateManager));
		}
	}
	
	class StarState extends MyDrawing implements State{
		StateManager stateManager;
		public StarState(StateManager stateManager) {
			this.stateManager=stateManager;
		}
		public void mouseDown(int x,int y) {
			setLocation(x,y);
			stateManager.addDrawing(new MyStar(x,y,getSides(),20,getLineColor(),getFillColor()));
		}
		public void mouseUp(int x,int y) {}
		public void mouseDrag(int x, int y) {
			stateManager.removeDrawing();
			int xx=getX();
			int yy=getY();
			int radius=(int)(Math.sqrt(Math.pow(x-xx,2)+Math.pow(y-yy,2)));
			MyStar star = new MyStar(xx, yy,getSides(),radius,getLineColor(),getFillColor());
			if (stateManager.getDashed()) {
		        star.setDashed(true);
		    }
		    if(stateManager.getShadow()) {
		    	star.setShadow(true);
		    }
		    if(stateManager.getThickness()) {
		    	star.setLineWidth(getLineWidth()*2);
		    }
		    if(stateManager.getDashedPattern()) {
		    	star.setDashedPattern(true);
		    }
		    stateManager.addDrawing(star);
		}
	}
}