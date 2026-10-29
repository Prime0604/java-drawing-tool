package gui.Shapes;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import gui.Button.MyDashStroke;
import gui.Main.MyDrawing;

public class MyOval extends MyDrawing {
	private  Ellipse2D.Double region;
	public MyOval(int xpt,int ypt) {
		super();
		setLocation(xpt,ypt);
	}
	
	public MyOval(int xpt, int ypt, int width, int height) {
        super();
        setLocation(xpt, ypt);
        setSize(width, height);
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
			g3.fillOval(x+10, y+10, w, h);
		}
		g2.setColor(getFillColor());
		g2.fillOval(x, y, w, h);
		g2.setColor(getLineColor());
		g2.drawOval(x, y, w, h);
		
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
		region = new Ellipse2D.Double(x,y,w,h);
	}
	@Override
	public boolean contains(int x, int y) {
        
        double centerX = region.getCenterX();
        double centerY = region.getCenterY();
        double radiusX = region.getWidth() / 2;
        double radiusY = region.getHeight() / 2;

        double dx = x - centerX;
        double dy = y - centerY;
        return (dx * dx) / (radiusX * radiusX) + (dy * dy) / (radiusY * radiusY) <= 1;
    }
}