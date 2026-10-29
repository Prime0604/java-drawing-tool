package gui.Shapes;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;

import gui.Button.MyDashStroke;
import gui.Main.MyDrawing;

public class MyStar extends MyDrawing {
	Polygon region;
	public MyStar(int xpt,int ypt) {
		super();
		setLocation(xpt,ypt);
	}
	
	public MyStar(int xpt, int ypt,int sides,int radius, Color lineColor, Color fillColor) {
        super();
        setLocation(xpt, ypt);
        setLineColor(lineColor);
        setFillColor(fillColor);
        setSides(sides);
        setRadius(radius);
    }
	
	public void draw(Graphics2D g) {
		setIsPolygon(true);
		super.draw(g);
		int x=getX();
		int y=getY();
		int sides=5;
		int radius=getRadius();
		
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
		int[] xPoints = new int[sides];
        int[] yPoints = new int[sides];
        for (int i = 0; i < sides; i++) {
            double angle = 4 * Math.PI / sides * i;
            xPoints[i] = (int) (x + radius * Math.cos(angle));
            yPoints[i] = (int) (y + radius * Math.sin(angle));
        }
        if(getShadow()) {
			Graphics2D g3=(Graphics2D)g;
			int[] xPoints_shadow=new int[sides];
			int[] yPoints_shadow=new int[sides];
			for (int i = 0; i < sides; i++) {
	            xPoints_shadow[i] = xPoints[i]+10;
	            yPoints_shadow[i] = yPoints[i]+10;
	        }
			g3.setColor(Color.BLACK);
			g3.fillPolygon(xPoints_shadow, yPoints_shadow, sides);
		}
		g2.setColor(getFillColor());
        g2.fillPolygon(xPoints, yPoints, sides);
		g2.setColor(getLineColor());
		g2.drawPolygon(xPoints, yPoints, sides);
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
            int sides = 5;
            int radius = getRadius();

            int[] xPoints = new int[sides];
            int[] yPoints = new int[sides];
            for (int i = 0; i < sides; i++) {
                double angle = 4 * Math.PI / sides * i;
                xPoints[i] = (int) (x + radius * Math.cos(angle));
                yPoints[i] = (int) (y + radius * Math.sin(angle));
            }
            region = new Polygon(xPoints, yPoints, sides);
        
    }
	@Override
	public boolean contains(int x,int y) {
		return region.contains(x,y);
	}
}
