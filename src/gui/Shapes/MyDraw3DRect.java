package gui.Shapes;
import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;

import gui.Main.MyDrawing;

public class MyDraw3DRect extends MyDrawing {
	public MyDraw3DRect(int xpt,int ypt) {
		super();
		setLocation(xpt,ypt);
	}
	public MyDraw3DRect(int xpt,int ypt,int w,int h) {
		super();
		setLocation(xpt,ypt);
		setSize(w,h);
	}
	
	public void draw(Graphics g) {
		int x=getX();
		int y=getY();
		int w=getW();
		int h=getH();
		boolean raised=true;
		
		if(w<0) {
			x+=w;
			w*=-1;
		}
		if(h<0) {
			y+=h;
			h*=-1;
		}
		
		Graphics2D g2=(Graphics2D) g;
		g2.setStroke(new BasicStroke(getLineWidth()));
		g2.setColor(getFillColor());
		g2.fill3DRect(x, y, w, h,raised);
		g2.setColor(getLineColor());
		g2.draw3DRect(x, y, w, h,raised);
	}
}