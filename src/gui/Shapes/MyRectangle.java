package gui.Shapes;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import gui.Button.MyDashStroke;
import gui.Main.MyDrawing;

public class MyRectangle extends MyDrawing {
	private Rectangle region;
	public MyRectangle(int xpt,int ypt) {
		super();
		setLocation(xpt,ypt);
	}
	public MyRectangle(int xpt,int ypt,int w,int h) {
		super();
		setLocation(xpt,ypt);
		setSize(w,h);
	}
	
	public void draw(Graphics2D g) {
		setIsPolygon(false);
		super.draw(g);
		int x=getX();
		int y=getY();
		int w=getW();
		int h=getH();
		
		if(w<0) {
			x+=w;
			w*=-1;
		}
		if(h<0) {
			y+=h;
			h*=-1;
		}
		
		Graphics2D g2=(Graphics2D) g;
		if(getDashed()) {
			g2.setStroke(new MyDashStroke(getLineWidth(),1));
		}
		else if(getDashedPattern()){
			g2.setStroke(new MyDashStroke(getLineWidth(),2));
		}
		else {
			g2.setStroke(new BasicStroke(getLineWidth()));
		}
		if(getShadow()) {
			Graphics2D g3=(Graphics2D)g;
			g3.setColor(Color.BLACK);
			g3.fillRect(x+10, y+10, w, h);
		}
		if(getisFill()){
			g2.setColor(getFillColor());
			g2.fillRect(x, y, w, h);
		}
		g2.setColor(getLineColor());
		g2.drawRect(x, y, w, h);
	}
	@Override
	public void setRegion() {
	    int x = getX();
	    int y = getY();
	    int w = getW();
	    int h = getH();
	    
	    if (w < 0) {
	        x += w;
	        w = -w;
	    }
	    if (h < 0) {
	        y += h;
	        h = -h;
	    }
	    
	    region = new Rectangle(x, y, w, h);
	}
	@Override
	public boolean intersect(MyRectangle rect) {
		int x=rect.getX();
		int y=rect.getY();
		int w=rect.getW();
		int h=rect.getH();
		
		if(w<0) {
			x+=w;
			w*=-1;
		}
		if(h<0) {
			y+=h;
			h*=-1;
		}
		return region.intersects(x,y,w,h);
	}
	@Override
	public boolean contains(int x,int y) {
		return region.contains(x,y);
	}
	
}
