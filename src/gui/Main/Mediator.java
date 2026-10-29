package gui.Main;
import java.awt.Color;
import java.util.Collections;
import java.util. Enumeration;
import java.util. Vector;

public class Mediator{
	Vector<MyDrawing> drawings=null;
	MyCanvas canvas;
	public Vector<MyDrawing> selectedDrawings=null;
	Vector<MyDrawing> buffer=null;
	boolean found=false;
	MyDrawing head=null;
	int fontSize=30;
	
	public Mediator (MyCanvas canvas) {
		this.canvas = canvas;
		drawings = new Vector<MyDrawing>();
		selectedDrawings=new Vector<MyDrawing>();
	}
	
	public Enumeration<MyDrawing> drawingsElements(){
		if(drawings!=null)return drawings.elements();
		else return null;
	}
	
	public void addDrawing (MyDrawing d) {
		if (drawings == null) {
	        drawings = new Vector<MyDrawing>();
	    }
		drawings.add(d);
	}
	public boolean containsSelectedDrawing(MyDrawing d) {
		if(selectedDrawings!=null)return selectedDrawings.contains(d);
		else return false;
	}
	public void removeDrawing () {
		drawings.remove(drawings.size() - 1);
	}
	public void removeDrawing (MyDrawing d) {
		drawings.remove(d);
	}
	public void removeDrawing (Vector<MyDrawing> Drawings) {
		for(MyDrawing d:Drawings) {
			drawings.remove(d);
		}
	}
	
	public Vector<MyDrawing> getSelectedDrawing() {
		return selectedDrawings;
	}
	
	public void move(int dx, int dy) {
		for(MyDrawing selectedDrawing:selectedDrawings) {
			if (selectedDrawing != null)
				selectedDrawing.move(dx, dy);
		}
	}
	
	public void repaint() {
		canvas.repaint();
	}
	
	public void setSelected(int x, int y) {
	    head=null;
	    for(MyDrawing d:drawings) {
	        d.setRegion();
	        if (d.contains(x, y)) {
	        	head=d;
	        }	
	    }
	    if (head==null) {
	        if (selectedDrawings != null) {
	            deselectAll();
	        }
	        selectedDrawings=null;
	    }
	}

	public void setSelectedDrawing(MyDrawing d) {
		if (selectedDrawings == null) {
	        selectedDrawings = new Vector<MyDrawing>();
	    }
		if(d!=null) {
			d.isSelected=true;
			selectedDrawings.add(d);
		}
	}
	public void removeSelectedDrawing(MyDrawing d) {
		if (selectedDrawings == null) {
	        selectedDrawings = new Vector<MyDrawing>();
	    }
		if(d!=null) {
			d.isSelected=false;
			selectedDrawings.remove(d);
		}
	}
	public void deselectAll() {
		if(selectedDrawings!=null) {
			for (MyDrawing drawing : selectedDrawings) {
				drawing.isSelected=false;
			}
			selectedDrawings.clear();
		}
    }
	public void clearSelection() {
        Enumeration<MyDrawing> e = drawingsElements();
        while (e.hasMoreElements()) {
            MyDrawing d = e.nextElement();
            if (d.isSelected) {
                d.isSelected = false;
            }
        }
        selectedDrawings = null;
    }
	public void setFillColor(Color x) {
		if(selectedDrawings!=null) {
			for(MyDrawing d:selectedDrawings) {
				d.setFillColor(x);
				d.setisFill(true);
			}
		}
	}
	public void setLineColor(Color x) {
		if(selectedDrawings!=null) {
			for(MyDrawing d:selectedDrawings) {
				d.setLineColor(x);
			}
		}
	}
	public void setShadow(boolean shadow) {
		if(selectedDrawings!=null) {
		for(MyDrawing d:selectedDrawings) {
			d.setMyShadow(shadow);
		}
		}
	}
	public void setFont(String fontNames) {
		if(selectedDrawings!=null) {
			for(MyDrawing d:selectedDrawings) {
				if(d.isString) {
					d.setFontName(fontNames);
				}
			}
			repaint();
		}
	}
	public int getFontSize() {
		return fontSize;
	}
	public void setFontSize(int fontSize) {
		if(selectedDrawings!=null) {
			for(MyDrawing d:selectedDrawings) {
				if(d.isString) {
					d.setFontSize(d.getLineWidth()*10);
				}
			}
			repaint();
			}
	}
	public void resize(int gx,int gy,int gw,int gh) {
		MyDrawing d=selectedDrawings.get(0);
		d.setLocation(gx,gy);
		d.setSize(gw, gh);
		d.setRegion();
		
	}
	public void clearBuffer(){
		buffer=null;
	}
	public void copy() {
		clearBuffer();
		buffer=cloneVector(selectedDrawings);
	}
	public void cut() {
		clearBuffer();
		if(selectedDrawings!=null) {
			buffer=cloneVector(selectedDrawings);
			removeDrawing(selectedDrawings);
			repaint();
		}
	}
	public void paste(int x, int y) {
	    if (buffer != null) {
	        for (MyDrawing d : buffer) {
	        	
	            d.isSelected = false;
	            MyDrawing clone = (MyDrawing) d.clone();
	            int deltaX = clone.getX() - buffer.get(0).getX();
	            int deltaY = clone.getY() - buffer.get(0).getY();
	            clone.setLocation(x + deltaX, y + deltaY);
	            addDrawing(clone);
	        }
	        repaint();
	    }
	}

	public static Vector<MyDrawing> cloneVector(Vector<MyDrawing> original) {
        Vector<MyDrawing> clonedVector = new Vector<>();
        for (MyDrawing drawing : original) {
            clonedVector.add(drawing.clone());
        }
        return clonedVector;
    }
	
	public void swapDrawings(int index1, int index2) {
        if (index1 >= 0 && index2 >= 0 && index1 < drawings.size() && index2 < drawings.size()) {
            Collections.swap(drawings, index1, index2);
        }
    }
	
	public void order(int selectedIndex) {
		Vector<MyDrawing> _drawings = new Vector<>();
		int headIndex=drawings.indexOf(head);
		switch(selectedIndex) {
		case 0:
			drawings.remove(head);
			for(MyDrawing d:drawings) {
				_drawings.add(d);
			}
			_drawings.add(head);
			drawings=_drawings;
			break;
		
		case 1:
			swapDrawings(headIndex,headIndex+1);
			break;
		
		case 2:
			swapDrawings(headIndex,headIndex-1);
			break;
		
		case 3:
			_drawings.add(head);
			drawings.remove(head);
			for(MyDrawing d:drawings) {
				_drawings.add(d);
			}
			drawings=_drawings;
			break;
		}
	}

	public Vector<MyDrawing> getDrawings() {
        return drawings;
    }

    public void setDrawings(Vector<MyDrawing> drawings) {
        this.drawings = drawings;
        repaint();
    }
}
