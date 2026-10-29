package gui.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import gui.Main.MyDrawing;
import gui.Main.State;
import gui.Main.StateManager;
import gui.Shapes.MyHendecagonal;

public class HendecagonalButton extends JButton{
	StateManager stateManager;
	
	public HendecagonalButton(StateManager stateManager) {
		super("Hendecagonal");
		
		addActionListener(new HendecagonalListener());
		
		this.stateManager=stateManager;
	}
	class HendecagonalListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			stateManager.setState(new HendecagonalState(stateManager));
		}
	}
	
	class HendecagonalState extends MyDrawing implements State{
		StateManager stateManager;
		public HendecagonalState(StateManager stateManager) {
			this.stateManager=stateManager;
		}
		public void mouseDown(int x,int y) {
			setLocation(x,y);
			stateManager.addDrawing(new MyHendecagonal(x,y,getSides(),0,getLineColor(),getFillColor()));
		}
		public void mouseUp(int x,int y) {}
		public void mouseDrag(int x, int y) {
			stateManager.removeDrawing();
			int xx=getX();
			int yy=getY();
			int radius=(int)(Math.sqrt(Math.pow(x-xx,2)+Math.pow(y-yy,2)));
			MyHendecagonal hend = new MyHendecagonal(xx, yy,getSides(),radius,getLineColor(),getFillColor());
			if (stateManager.getDashed()) {
		        hend.setDashed(true);
		    }
		    if(stateManager.getShadow()) {
		    	hend.setShadow(true);
		    }
		    if(stateManager.getThickness()) {
		    	hend.setLineWidth(getLineWidth()*2);
		    }
		    if(stateManager.getDashedPattern()) {
		    	hend.setDashedPattern(true);
		    }
		    stateManager.addDrawing(hend);
		}
	}
}