package gui.Button;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JTextField;

import gui.Main.Mediator;
import gui.Main.StateManager;

public class TextInputBox extends JFrame{
	StateManager stateManager;
	Mediator mediator;
    JTextField textField = new JTextField(10);
    TextBoxButton textButton;

    TextInputBox(TextBoxButton textBoxButton,StateManager stateManager,Mediator mediator) {
    	this.textButton=textBoxButton;
        getContentPane().add(textField);
        textField.addActionListener(new ActionListener() {
        	
            public void actionPerformed(ActionEvent e) {
                String text=textField.getText();
                textButton.Mynotify(text);
                dispose();
            }
        });
        setSize(200,100);
        setTitle("Text");
    }
    
}
