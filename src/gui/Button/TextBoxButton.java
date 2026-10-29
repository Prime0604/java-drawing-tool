package gui.Button;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

import gui.Main.Mediator;
import gui.Main.MyDrawing;
import gui.Main.State;
import gui.Main.StateManager;
import gui.Shapes.MyTextBox;


public class TextBoxButton extends JButton{
	StateManager stateManager;
	Mediator mediator;
	TextInputBox textInputBox;
	int x1,y1;
	
	
	public TextBoxButton(StateManager stateManager,Mediator mediator) {
		super("Text");
		
		addActionListener(new TextBoxListener());
		
		this.stateManager=stateManager;
		this.mediator = mediator;
	}
	
	public void mousePressed(MouseEvent e) {
        TextInputBox textInputBox = new TextInputBox(this,stateManager,mediator);
        textInputBox.setSize(10,100);
        textInputBox.setVisible(true);
    }
	
	class TextBoxListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			stateManager.setState(new TextBoxState(stateManager,mediator));
		}
	}
	class TextBoxState extends MyDrawing implements State{
		StateManager stateManager;
		Mediator mediator;
		MyTextBox textBox;
		TextInputBox textInputBox;
		

		public TextBoxState(StateManager stateManager,Mediator mediator) {
			this.stateManager=stateManager;
			this.mediator=mediator;
		}
		public void mouseDown(int x,int y) {
			x1=x;
			y1=y;
			this.textInputBox=new TextInputBox(TextBoxButton.this,stateManager,mediator);
			textInputBox.setVisible(true);
		}
		public void mouseUp(int x,int y) {}
		public void mouseDrag(int x, int y) {

		}
		
		public void MysetText(String text) {
			
			this.textBox=new MyTextBox(text,x1,y1);
			//System.out.println("TextBoxButton");
			textBox.setSize(textBox.getW(),textBox.getH());
			textBox.setLineWidth(3);
			textBox.setFontSize(textBox.getLineWidth());
			stateManager.addDrawing(textBox);
			mediator.repaint();
			
		}
	}
	public  void Mynotify(String text) {
		if(text!=null)((TextBoxState) stateManager.getState()).MysetText(text);
	}
}