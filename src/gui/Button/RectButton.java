package gui.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import gui.Main.MyDrawing;
import gui.Main.State;
import gui.Main.StateManager;
import gui.Shapes.MyRectangle;

public class RectButton extends JButton{
	StateManager stateManager;
	public RectButton(StateManager stateManager) {
		super("Rectangle");
		
		addActionListener(new RectListener());
		
		this.stateManager=stateManager;
	}
	class RectListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			stateManager.setState(new RectState(stateManager));
		}
	}
	
	class RectState extends MyDrawing implements State{
		StateManager stateManager;
		public MyRectangle rect;
		public RectState(StateManager stateManager) {
			this.stateManager=stateManager;
		}
		public void mouseDown(int x,int y) {
			setLocation(x,y);
			this.rect=new MyRectangle(x,y,0,0);
			if (stateManager.getDashed()) {
		        rect.setDashed(true);
		    }
		    if(stateManager.getShadow()) {
		    	rect.setShadow(true);
		    }
		    if(stateManager.getThickness()) {
		    	rect.setLineWidth(getLineWidth()*2);
		    }
		    if(stateManager.getDashedPattern()) {
		    	rect.setDashedPattern(true);
		    }
			stateManager.addDrawing(rect);
		}
		public void mouseUp(int x,int y) {}
		public void mouseDrag(int x, int y) {
			stateManager.removeDrawing();
			int xx=getX();
			int yy=getY();
			rect.setLocation(xx, yy);
			rect.setSize(x-xx,y-yy);
		    stateManager.addDrawing(rect);
		}
	}
}