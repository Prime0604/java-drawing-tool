package gui.Main;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

public class MyCanvas extends JPanel{
	
	Mediator mediator;
	
	public MyCanvas() {
		this.mediator = new Mediator(this);
		setBackground(Color.white);
	}
	
	@Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        for (MyDrawing drawing : mediator.drawings) {
            drawing.draw(g2d);
        }
        repaint();
    }
	public void handleMouseMoved(MouseEvent e) {
        boolean found = false;
        for (MyDrawing drawing :mediator.drawings) {
        	int resize=drawing.contains_resize(e.getX(), e.getY());
            if(drawing.isSelected==true&&resize!=22&&resize/10!=0&&resize%10!=0) {
            	setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
            	found=true;
            	break;
            }
            if (drawing.contains(e.getX(), e.getY())) {
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                found = true;
            }
        }
        if (!found) {
            setCursor(Cursor.getDefaultCursor());
        }
    }
}
