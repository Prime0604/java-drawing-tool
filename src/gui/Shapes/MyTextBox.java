package gui.Shapes;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import gui.Main.MyDrawing;

public class MyTextBox extends MyDrawing{
	private String text;
	private int fontSize=30;
	private Font font;
	private String fontName="Default";
	FontMetrics fm;
	private Rectangle region;
	
	public MyTextBox(String _text, int xpt, int ypt) {
		text = _text;
		setLocation(xpt,ypt);
		setFontSize(fontSize);
		setisString();
		setisFill(false);
	}

	public void draw(Graphics2D g) {
		super.draw(g);
		
		setFontSize(getLineWidth()*10);
		g.setFont(font);
		
		FontMetrics fm = g.getFontMetrics();
		this.fm=fm;
		int width = fm.stringWidth(text);
		int height = fm.getHeight();
		setSize(width,height);
		
		if(getisFill()){
			g.setColor(getFillColor());
			g.fillRect(getX(),getY(),getW(),getH());
		}
		
		g.setColor(getLineColor());
		if(text!=null)g.drawString(text, getX(), getY() + fm.getAscent());
		setRegion();
	}

	@Override
	public void setFontName(String fontName) {
		this.fontName=fontName;
		this.font=new Font(fontName,Font.PLAIN,fontSize);
	}
	@Override
	public int getFontSize() {
		return fontSize;
	}
	public void setFontSize(int size) {
		fontSize=size;
		this.font=new Font(fontName,Font.PLAIN,fontSize);
	}
	@Override
	public void setRegion() {
			if(fm!=null) {

        	region = new Rectangle(getX(), getY(), getW(), getH());
		}

	}
	public void setFont(Font font) {
		this.font=new Font(this.fontName,Font.PLAIN,fontSize);
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
		return region!=null&&region.intersects(x,y,w,h);
	}
	@Override
	public boolean contains(int x,int y) {
		return region!=null&&region.contains(x,y);
	}
}