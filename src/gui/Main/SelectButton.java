package gui.Main;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;

import gui.Shapes.MyRectangle;

public class SelectButton extends JButton{
	StateManager stateManager;
	Mediator mediator;
	int x1,y1;
	public SelectButton(StateManager stateManager,Mediator mediator) {
		super("Select");
		
		addActionListener(new SelectListener());
		
		this.stateManager=stateManager;
		this.mediator = mediator;
	}
	class SelectListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			mediator.clearSelection();
			stateManager.setState(new SelectState(stateManager,mediator));
		}
	}
	
	class SelectState extends MyDrawing implements State{
		StateManager stateManager;
		Mediator mediator;
		public MyRectangle rect;
		public MyDrawing head;
		public MyDrawing prev;
		public String headprev="no";
		public int l=0,r=0,resize=0,prel,prer,resize_prev=0;
		public int px,py,pw,ph,pr,prex=0,prey=0;
		public SelectState(StateManager stateManager,Mediator mediator) {
			this.stateManager=stateManager;
			this.mediator=mediator;
		}
		public void mouseDown(int x,int y) {
			if(head!=null) {
				head.isSelected=false;
				head=null;
			}
			Vector<MyDrawing> Drawings=mediator.getSelectedDrawing();
			if(prev!=null) {
				resize_prev=prev.contains_resize(x,y);
			}
			
			mediator.setSelected(x,y);
			Drawings=mediator.getSelectedDrawing();
			
			
			prel=resize_prev/10;
			prer=resize_prev%10;
			
			head=mediator.head;
			
			if(resize_prev!=22&&prel!=0&&prer!=0)head=prev;
			
			headprev="no";
			l=0;
			r=0;
			resize=0;

			if(head!=null) {
				resize=head.contains_resize(x,y);
				headprev="head";
				head.setprexy(x,y);
			}
			else if(prev!=null) {
				resize=prev.contains_resize(x,y);
				headprev="prev";
				prev.setprexy(x, y);
			}
			
			if(resize!=0) {
				l=resize/10;
				r=resize%10;
				
			}
			if(resize!=22&&l!=0&&r!=0) {
				mediator.deselectAll();
				if(headprev=="head")mediator.setSelectedDrawing(head);
				if(headprev=="prev")mediator.setSelectedDrawing(prev);
				
			}
			
			
			
			else if(head!=null) {
				if(Drawings!=null) {
					if(!Drawings.contains(head)) {
						mediator.deselectAll();
						Drawings=null;
					}
				}
				
				if(!mediator.containsSelectedDrawing(head))mediator.setSelectedDrawing(head);
				if (Drawings == null) {
					Drawings = new Vector<MyDrawing>();
				}
				if(!Drawings.contains(head)) {
					Drawings.add(head);
				}
				else head.isSelected=true;
		
				for(MyDrawing d:Drawings) {
					d.isSelected=true;
					d.setprexy(x,y);
				}
					//}
					
				rect=null;
				mediator.repaint();
				prev=head;
				headprev="no";
			}
			
			else{
				mediator.clearSelection();
				this.rect=new MyRectangle(x,y,0,0);
				rect.setDashed(true);
				rect.setisFill(false);
				mediator.addDrawing(rect);
				headprev="no";
				prev=null;
			}
		}
		public void mouseUp(int x,int y) {
			mediator.removeDrawing(rect);
			
			rect=null;
			mediator.repaint();
		}
		public void mouseDrag(int x, int y) {
			if(headprev!="no") {
				if(mediator.selectedDrawings!=null) {
				MyDrawing select=mediator.selectedDrawings.get(0);
				if(select.isString);
				else if(!select.isPolygon) {
					px=select.getX();
					py=select.getY();
					pw=select.getW();
					ph=select.getH();

					if(prex==0&&prey==0) {
						prex=x;
						prey=y;
					}
					int gw=l==1?px-x+pw:l==3?(x-px):pw;
					int gh=r==1?py-y+ph:r==3?(y-py):ph;
					int gx=l==1?x:px;
					int gy=r==1?y:py;
				
					if(select.isString)select.setLineWidth(gh);
					mediator.resize(gx,gy,gw,gh);
					
					mediator.repaint();
					prex=x;
					prey=y;
				}
				else if(select.isPolygon) {
					
					pr=select.getRadius();
					px=select.getX();
					py=select.getY();
					double gr=Math.sqrt(((px-x)*(px-x)+(py-y)*(py-y))/((l==2||r==2)?1:2));
					select.setRadius((int) gr);
					mediator.repaint();
				}
				}
			}
			else if(rect==null) {
				Vector<MyDrawing> Drawings=mediator.getSelectedDrawing();
				if(Drawings!=null) {
					for(MyDrawing d:Drawings) {
						d.move(x,y);
					}
				}
			}
			else{
				
				int xx=rect.getX();
				int yy=rect.getY();
				rect.setLocation(xx, yy);
				rect.setSize(x-xx,y-yy);
				mediator.repaint();
				if(mediator.drawings!=null) {
					Vector<MyDrawing> Drawings=mediator.drawings;
					for(MyDrawing d:Drawings) {
						if(d!=rect) {
							d.setRegion();
							if(d.intersect(rect)) {
								if(!mediator.containsSelectedDrawing(d))mediator.setSelectedDrawing(d);
							}
							else {
								if(mediator.containsSelectedDrawing(d))mediator.removeSelectedDrawing(d);
							}
						}
					}
				}

							
			}
			//if(mediator.selectedDrawings!=null)System.out.println(mediator.selectedDrawings.size());
			mediator.repaint();
		}
	}
}