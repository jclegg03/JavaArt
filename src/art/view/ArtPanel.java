package art.view;

import java.awt.GridLayout;
import java.util.Hashtable;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SpringLayout;

import art.controller.Controller;

public class ArtPanel extends JPanel
{
	private Controller app;
	private ShapePanel canvas;
	private JPanel menuPanel;
	private JButton saveButton;
	private JButton clearButton;
	private JButton generateButton;
	private JSlider scaleSlider;
	private JSlider verticySlider;
	private JSlider triangleSlider;
	private JSlider shapeSlider;
	private JSlider ellipseSlider;
	private SpringLayout layout;
	
	public ArtPanel(Controller app)
	{
		this.app = app;
		this.canvas = new ShapePanel(app);
		this.menuPanel = new JPanel();
		this.saveButton = new JButton("Save");
		this.clearButton = new JButton("Clear");
		this.generateButton = new JButton("Generage");
		this.scaleSlider = new JSlider(ShapePanel.MIN_SCALE, ShapePanel.MAX_SCALE);
		this.verticySlider = new JSlider(ShapePanel.MIN_VERTICIES, ShapePanel.MAX_VERTICIES);
		this.triangleSlider = new JSlider(0, 100);
		this.shapeSlider = new JSlider(0, 100);
		this.ellipseSlider = new JSlider(0, 100);
		this.layout = new SpringLayout();
		
		setupPanel();
		setupLayout();
		setupListeners();
	}
	
	private void setupPanel()
	{
//		this.setBackground(Color.MAGENTA);
		setupSliders();
		
		this.add(canvas);
		this.add(menuPanel);
		
		menuPanel.add(saveButton);
		menuPanel.add(scaleSlider);
		menuPanel.add(clearButton);
		menuPanel.add(generateButton);
	}
	
	private void setupSliders()
	{
		scaleSlider.setValue(50);
		scaleSlider.setSnapToTicks(true);
		scaleSlider.setMajorTickSpacing(5);
		scaleSlider.setMinorTickSpacing(1);
		scaleSlider.setPaintTicks(true);
		
		verticySlider.setValue(10);
		
		
		triangleSlider.setValue(10);
		
		
		shapeSlider.setValue(10);
		
		
		ellipseSlider.setValue(10);
		
	}
	
	private void setupLayout()
	{
		this.setLayout(layout);
		
		menuPanel.setLayout(new GridLayout(0, 1, 0, 5));
		
		layout.putConstraint(SpringLayout.NORTH, menuPanel, 0, SpringLayout.NORTH, canvas);
		layout.putConstraint(SpringLayout.WEST, menuPanel, 20, SpringLayout.EAST, canvas);
		layout.putConstraint(SpringLayout.SOUTH, menuPanel, 0, SpringLayout.SOUTH, canvas);
		layout.putConstraint(SpringLayout.EAST, menuPanel, -20, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.NORTH, canvas, 20, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, canvas, 20, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.SOUTH, canvas, -20, SpringLayout.SOUTH, this);
		layout.putConstraint(SpringLayout.EAST, canvas, -240, SpringLayout.EAST, this);
	}
	
	private void setupListeners()
	{
		saveButton.addActionListener(click -> canvas.save());
		clearButton.addActionListener(click -> canvas.clear());
		generateButton.addActionListener(click -> canvas.makeArt(triangleSlider.getValue(), shapeSlider.getValue(), ellipseSlider.getValue()));
	}
	
	
}
