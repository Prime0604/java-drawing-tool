package gui.Main;

public class StateManager extends MyCanvas{
	State state;
	Mediator mediator;
	boolean isDashed;
	boolean isShadow;
	boolean shadow;
	boolean isThickness;
	boolean isDashedPattern;
	boolean Selecting;
	
	public StateManager(Mediator mediator) {
		this.mediator=mediator;
		this.state=null;
		isDashed=false;
		isShadow=false;
		shadow=false;
		isThickness=false;
	}
	public void mouseDown(int x,int y) {
		if (state != null)state.mouseDown(x, y);
	}
	public void mouseUp(int x,int y) {
		if (state != null)state.mouseUp(x,y);
	}
	public void mouseDrag(int x, int y) {
		if (state != null)state.mouseDrag(x, y);
	}
	
	public void setState(State state) {
		this.state=state;
	}
	public State getState() {
		return this.state;
	}
	public void addDrawing(MyDrawing d) {
		mediator.addDrawing(d);
	}
	public void removeDrawing() {
		mediator.removeDrawing();
	}
	public void setDashed(boolean dashed) {
		isDashed=dashed;
	}
	public boolean getDashed() {
		return isDashed;
	}
	public void setShadow(boolean shadow) {
		isShadow=shadow;
	}
	public boolean getShadow() {
		return isShadow;
	}
	public void setLineThickness(boolean thick) {
		isThickness=thick;
	}
	public boolean getThickness() {
		return isThickness;
	}
	public void setDashedPattern(boolean dashedpattern) {
		isDashedPattern=dashedpattern;
	}
	public boolean getDashedPattern() {
		return isDashedPattern;
	}
	public boolean getSelect() {
		return Selecting;
	}
	public void setSelect(boolean select) {
		Selecting=select;
	}
}
