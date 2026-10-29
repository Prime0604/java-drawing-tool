package gui.Main;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import gui.Button.DashedCheckBox;
import gui.Button.DashedPatternBox;
import gui.Button.FillColorComboBoxListener;
import gui.Button.FontComboBoxListener;
import gui.Button.HendecagonalButton;
import gui.Button.LineColorComboBoxListener;
import gui.Button.LineThicknessBox;
import gui.Button.OrderComboBoxListener;
import gui.Button.OvalButton;
import gui.Button.RectButton;
import gui.Button.ShadowCheckBox;
import gui.Button.StarButton;
import gui.Button.TextBoxButton;
import gui.Button.ThicknessComboBoxListener;

public class MyApplication extends JFrame implements ActionListener{
	StateManager stateManager;
	MyCanvas canvas;
	Mediator mediator;
	JMenu stateMenu;
	JMenu fontMenu;
	private JMenuItem shadowItem;
	private JMenuBar menuBar;
	private JComboBox<String> lineColorComboBox;
	private JComboBox<String> fillColorComboBox;
	private JComboBox<String> thicknessComboBox;
	private JComboBox<String> fontComboBox;
	private JComboBox<String> orderComboBox;
    private final String[] colorNames = {"Red", "Blue", "Green", "Yellow", "Orange","Black ","Other Colors"};
    private final Color[] colors = {Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.ORANGE,Color.black};
    private final String[] thicknessNames = {"1", "2", "3", "4", "5","10"};
    private final int[] thickness = {1,2,3,4,5,10};
    private final String[] fontNames = {"Default","Times New Roman","Courier New"};
    private final String[]  order= {"先頭","1つ前","1つ後ろ","末尾"};
    
    
	public MyApplication() {
		super("My Paint Application");
		
		canvas = new MyCanvas();
		canvas.setBackground(Color.white);
		
		
		JPanel jp = new JPanel();
		jp.setLayout(new FlowLayout());
		
		mediator=canvas.mediator;
		
		stateManager = new StateManager(mediator);

		menuBar=new JMenuBar();
		
		JLabel OrderLabel = new JLabel("Order");
        menuBar.add(OrderLabel);
		orderComboBox=new JComboBox<>(order);
		orderComboBox.addActionListener(new OrderComboBoxListener(orderComboBox,order, mediator));
		menuBar.add(orderComboBox);
		
		JLabel lineColorLabel = new JLabel("Color:Line");
        menuBar.add(lineColorLabel);
		lineColorComboBox=new JComboBox<>(colorNames);
		lineColorComboBox.addActionListener(new LineColorComboBoxListener(lineColorComboBox,colorNames,colors,mediator));
		menuBar.add(lineColorComboBox);
		
		JLabel fillColorLabel = new JLabel("Color:Fill");
        menuBar.add(fillColorLabel);
		fillColorComboBox=new JComboBox<>(colorNames);
		fillColorComboBox.addActionListener(new FillColorComboBoxListener(fillColorComboBox,colorNames,colors,mediator));
		menuBar.add(fillColorComboBox);
		
		JLabel ThicknessLabel = new JLabel("Line:Thickness");
        menuBar.add(ThicknessLabel);
		thicknessComboBox=new JComboBox<>(thicknessNames);
		thicknessComboBox.addActionListener(new ThicknessComboBoxListener(thicknessComboBox,thickness,mediator));
		menuBar.add(thicknessComboBox);
		
		JLabel FontLabel = new JLabel("Text:Font");
        menuBar.add(FontLabel);
		fontComboBox=new JComboBox<>(fontNames);
		fontComboBox.addActionListener(new FontComboBoxListener(fontComboBox,fontNames, mediator));
		menuBar.add(fontComboBox);
		
		FileInput_OutputStream fileIO = new FileInput_OutputStream(mediator);
		
		JLabel alphaLabel = new JLabel("Transparency");
        menuBar.add(alphaLabel);
        JSlider alphaSlider = new JSlider(0, 100, 100);
        alphaSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int value = alphaSlider.getValue();
                float alpha = value / 100f;
                if(mediator.selectedDrawings!=null) {
                	for (MyDrawing d : mediator.selectedDrawings) {
                		d.setAlpha(alpha);
                	}
                canvas.repaint();
                }
            }
        });
        menuBar.add(alphaSlider);
		
        JMenu fileMenu = new JMenu("File");
        JMenuItem loadMenuItem = new JMenuItem("Load");
        JMenuItem saveMenuItem = new JMenuItem("Save");
        JMenuItem imageMenuItem = new JMenuItem("Image");

        loadMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fileIO.loadDrawingsFromFile();
                
            }
        });

        saveMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fileIO.saveDrawingsToFile();
            }
        });
        
        imageMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fileIO.imageChooser();
            }
        });

        fileMenu.add(loadMenuItem);
        fileMenu.add(saveMenuItem);
        fileMenu.add(imageMenuItem);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);
		
		stateMenu = new JMenu("State");
		shadowItem=new JMenuItem("Shadow");
		stateMenu.add(shadowItem);
		shadowItem.addActionListener(this);
		menuBar.add(stateMenu);
		setJMenuBar(menuBar);
		
		
		RectButton rectButton = new RectButton(stateManager);
		jp.add(rectButton);
		OvalButton ovalButton = new OvalButton(stateManager);
		jp.add(ovalButton);
		HendecagonalButton hendButton = new HendecagonalButton(stateManager);
		jp.add(hendButton);
		StarButton starButton = new StarButton(stateManager);
		jp.add(starButton);
		SelectButton selectButton = new SelectButton(stateManager,mediator);
		jp.add(selectButton);
		TextBoxButton textBoxButton = new TextBoxButton(stateManager,mediator);
		jp.add(textBoxButton);
		
		JCheckBox  shadowCheck = new JCheckBox("shadow");
		shadowCheck.addItemListener(new ShadowCheckBox(stateManager));
		jp.add(shadowCheck);
		JCheckBox  dashedCheck = new JCheckBox("dashed");
		dashedCheck.addItemListener(new DashedCheckBox(stateManager));
		jp.add(dashedCheck);
		JCheckBox  lineThickness = new JCheckBox("line thickness");
		lineThickness.addItemListener(new LineThicknessBox(stateManager));
		jp.add(lineThickness);
		JCheckBox  dashedPattern = new JCheckBox("dashed pattern");
		dashedPattern.addItemListener(new DashedPatternBox(stateManager));
		jp.add(dashedPattern);
		
		
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(jp,BorderLayout.NORTH);
		getContentPane().add(canvas,BorderLayout.CENTER);
		
		canvas.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e) {
				stateManager.mouseDown(e.getX(),e.getY());
				mediator.repaint();
				canvas.requestFocusInWindow();
			}
			public void mouseReleased(MouseEvent e) {
				stateManager.mouseUp(e.getX(),e.getY());
		        if (e.isPopupTrigger()) {
		        	mediator.deselectAll();
		            mediator.paste(e.getX(), e.getY());
		        }
		    }
		});
		canvas.addMouseMotionListener(new MouseMotionAdapter() {
		    public void mouseDragged(MouseEvent e) {
		        stateManager.mouseDrag(e.getX(), e.getY());
		        mediator.repaint();
		    }
		    public void mouseMoved(MouseEvent e) {
		    	canvas.handleMouseMoved(e);
		    	
		    }
		});
		canvas.setFocusable(true);
		canvas.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_DELETE) {
					if(mediator.getSelectedDrawing()!=null) {
						mediator.removeDrawing(mediator.getSelectedDrawing());
						canvas.repaint();
					}
                }
				if (e.getKeyCode() == KeyEvent.VK_C) {
					if(mediator.getSelectedDrawing()!=null) {
						mediator.clearBuffer();
						mediator.copy();
					}
                }
				if (e.getKeyCode() == KeyEvent.VK_X) {
					if(mediator.getSelectedDrawing()!=null) {
						mediator.clearBuffer();
						mediator.cut();
						canvas.repaint();
					}
                }
				
			}
		});
		
		
		
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==shadowItem) {
			mediator.setShadow(true);
			canvas.repaint();
		}
	}
	public Dimension getPreferredSize() {
		return new Dimension(300,400);
	}
	public static void main(String[] args) {
		MyApplication app = new MyApplication();
		app.pack();
		app.setVisible(true);
	}
}
