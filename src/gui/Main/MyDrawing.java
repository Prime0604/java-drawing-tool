package gui.Main;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.io.Serializable;

import gui.Shapes.MyRectangle;

public class MyDrawing implements Cloneable,Serializable{
	private int x,y,w,h,sides,radius,prex,prey;
	private float alpha=1.0f;
	private Color lineColor,fillColor;
	private int lineWidth;
	private boolean isDashed;
	private boolean isShadow;
	private boolean shadow;
	private boolean isDashedPattern;
	boolean isSelected;
	boolean isPolygon;
	boolean isString;
	boolean isFill;
	Shape region;
	final int SIZE=12;
	
	public MyDrawing() {
		x=y=0;
		w=80;
		h=40;
		lineColor=Color.black;
		fillColor=Color.white;
		lineWidth=1;
		sides=0;
		radius=0;
		isSelected=false;
		isPolygon=false;
		isString=false;
		isFill=true;
		setRegion();
	}
	public void draw(Graphics2D g) {
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
		if(isSelected&&!isPolygon) {
			g.setColor(Color.black);
			g.fillRect(x+w/2-SIZE/2,y-SIZE/2,SIZE,SIZE);
			g.fillRect(x-SIZE/2,y+h/2-SIZE/2,SIZE,SIZE);
			g.fillRect(x+w/2-SIZE/2,y+h-SIZE/2,SIZE,SIZE);
			g.fillRect(x+w-SIZE/2, y+h/2-SIZE/2, SIZE, SIZE);
			g.fillRect(x-SIZE/2, y-SIZE/2, SIZE, SIZE);
			g.fillRect(x+w-SIZE/2, y-SIZE/2, SIZE, SIZE);
			g.fillRect(x-SIZE/2,y+h-SIZE/2,SIZE,SIZE);
			g.fillRect(x+w-SIZE/2, y+h-SIZE/2, SIZE, SIZE);
		}
		if (isSelected&&isPolygon) {
            g.setColor(Color.black);
            g.fillRect(x - SIZE / 2, y - radius - SIZE / 2, SIZE, SIZE); // 上中
            g.fillRect(x - radius - SIZE / 2, y - SIZE / 2, SIZE, SIZE); // 左中
            g.fillRect(x - SIZE / 2, y + radius - SIZE / 2, SIZE, SIZE); // 下中
            g.fillRect(x + radius - SIZE / 2, y - SIZE / 2, SIZE, SIZE); // 右中
            g.fillRect(x - radius - SIZE / 2, y - radius - SIZE / 2, SIZE, SIZE); // 左上
            g.fillRect(x + radius - SIZE / 2, y - radius - SIZE / 2, SIZE, SIZE); // 右上
            g.fillRect(x - radius - SIZE / 2, y + radius - SIZE / 2, SIZE, SIZE); // 左下
            g.fillRect(x + radius - SIZE / 2, y + radius - SIZE / 2, SIZE, SIZE); // 右下
        }
		
	}
	public void move(int dx,int dy) {
		x=x+dx-prex;
		y=y+dy-prey;
		setRegion();
		prex=dx;
		prey=dy;
	}
	public void setprexy(int x,int y) {
		prex=x;
		prey=y;
	}
	public void setLocation(int x,int y) {
		this.x=x;
		this.y=y;
	}
	public void setSize(int w,int h) {
		this.w=w;
		this.h=h;
	}
	public void setLineColor(Color x) {
		this.lineColor=x;
	}
	public void setFillColor(Color x) {
		this.fillColor=x;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getW() {
		return w;
	}
	public int getH() {
		return h;
	}
	public Color getLineColor() {
		return lineColor;
	}
	public int getLineWidth() {
		return lineWidth;
	}
	public void setLineWidth(int lineWidth) {
		this.lineWidth=lineWidth;
	}
	public Color getFillColor() {
		return fillColor;
	}
	public void setSides(int sides) {
		this.sides=sides;
	}
	public void setRadius(int radius) {
		this.radius=radius;
	}
	public int getSides() {
		return sides;
	}
	public int getRadius() {
		return radius;
	}
	public void setDashed(boolean dashed) {
		this.isDashed=dashed;
	}
	public boolean getDashed() {
		return isDashed;
	}
	public void setisFill(boolean isFill) {
		this.isFill=isFill;
	}
	public boolean getisFill() {
		return isFill;
	}
	public void setisString() {
		this.isString=true;
	}
	public void setShadow(boolean shadow) {
		this.isShadow=shadow;
	}
	public boolean getShadow() {
		return isShadow||shadow;
	}
	public void setMyShadow(boolean shadow) {
		if(this.shadow==false)this.shadow=shadow;
		else if(this.shadow==true)this.shadow=!shadow;
	}
	public boolean getMyShadow() {
		return shadow;
	}
	public void setDashedPattern(boolean dashedpattern) {
		isDashedPattern=dashedpattern;
	}
	public boolean getDashedPattern() {
		return isDashedPattern;
	}
	public void setFontSize(int size) {
		lineWidth=size;
	}
	public void setFont(String fontNames) {
	}
	public int contains_resize(int x,int y) {
		int resize=0;
		if(!isPolygon) {
			if(x>this.x-SIZE/2&&x<this.x+SIZE/2) resize+=10;
			if(x>this.x+w/2-SIZE/2&&x<this.x+w/2+SIZE/2)resize+=20;
			if(x>this.x+w-SIZE/2&&x<this.x+w+SIZE/2)resize+=30;
			if(y>this.y-SIZE/2&&y<this.y+SIZE/2)resize+=1;
			if(y>this.y+h/2-SIZE/2&&y<this.y+h/2+SIZE/2)resize+=2;
			if(y>this.y+h-SIZE/2&&y<this.y+h+SIZE/2)resize+=3;
		}
		else if(isPolygon){
			if(x>this.x-radius-SIZE/2&&x<this.x-radius+SIZE/2)resize+=10;
			if(x>this.x-SIZE/2&&x<this.x+SIZE/2)resize+=20;
			if(x>this.x+radius-SIZE/2&&x<this.x+radius+SIZE/2)resize+=30;
			if(y>this.y-radius-SIZE/2&&y<this.y-radius+SIZE/2)resize+=1;
			if(y>this.y-SIZE/2&&y<this.y+SIZE/2)resize+=2;
			if(y>this.y+radius-SIZE/2&&y<this.y+radius+SIZE/2)resize+=3;
		}
		return resize;

	}
	public boolean contains(int x,int y) {
		return false;
	}
	public void setRegion() {
	}
	public boolean intersect(MyRectangle rect) {
		return false;
	}
	public void setIsPolygon(boolean poly) {
		this.isPolygon=poly;
	}
	@Override
	public MyDrawing clone(){
		try {
			return (MyDrawing)super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}
	public int getFontSize() {
		return 0;
	}
	public void setFontName(String fontNames) {
		
	}
	public void setAlpha(float alpha) {
		this.alpha=alpha;
		
	}
	

}
