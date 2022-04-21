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
	private JLabel scaleLabel;
	private JSlider verticySlider;
	private JLabel verticyLabel;
	private JSlider triangleSlider;
	private JLabel triangleLabel;
	private JSlider shapeSlider;
	private JLabel shapeLabel;
	private JSlider ellipseSlider;
	private JLabel ellipseLabel;
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
		this.scaleLabel = new JLabel("Scale: " + canvas.getCurrentScale());
		this.verticySlider = new JSlider(ShapePanel.MIN_VERTICIES, ShapePanel.MAX_VERTICIES);
		this.verticyLabel = new JLabel("Verticies: " + canvas.getCurrentVerticies());
		this.triangleSlider = new JSlider(0, 100);
		this.triangleLabel = new JLabel("Triangles: 10");
		this.shapeSlider = new JSlider(0, 100);
		this.shapeLabel = new JLabel("Shapes: 10");
		this.ellipseSlider = new JSlider(0, 100);
		this.ellipseLabel = new JLabel("Ellipses: 10");
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
		menuPanel.add(scaleLabel);
		
		menuPanel.add(verticySlider);
		menuPanel.add(verticyLabel);
		
		menuPanel.add(triangleSlider);
		menuPanel.add(triangleLabel);
		
		menuPanel.add(shapeSlider);
		menuPanel.add(shapeLabel);
		
		menuPanel.add(ellipseSlider);
		menuPanel.add(ellipseLabel);
		
		menuPanel.add(clearButton);
		menuPanel.add(generateButton);
	}
	
	private void setupSliders()
	{
		scaleSlider.setValue(canvas.getCurrentScale());
		scaleSlider.setSnapToTicks(true);
		scaleSlider.setMajorTickSpacing(50);
		scaleSlider.setMinorTickSpacing(1);
		scaleSlider.setPaintTicks(true);
		
		verticySlider.setValue(canvas.getCurrentVerticies());
		verticySlider.setSnapToTicks(true);
		verticySlider.setMajorTickSpacing(5);
		verticySlider.setMinorTickSpacing(1);
		verticySlider.setPaintTicks(true);
		
		triangleSlider.setValue(10);
		triangleSlider.setSnapToTicks(true);
		triangleSlider.setMajorTickSpacing(10);
		triangleSlider.setMinorTickSpacing(1);
		triangleSlider.setPaintTicks(true);
		
		
		shapeSlider.setValue(10);
		shapeSlider.setSnapToTicks(true);
		shapeSlider.setMajorTickSpacing(10);
		shapeSlider.setMinorTickSpacing(1);
		shapeSlider.setPaintTicks(true);
		
		ellipseSlider.setValue(10);
		ellipseSlider.setSnapToTicks(true);
		ellipseSlider.setMajorTickSpacing(10);
		ellipseSlider.setMinorTickSpacing(1);
		ellipseSlider.setPaintTicks(true);
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
		
		scaleSlider.addChangeListener(slide -> updateScale());
		verticySlider.addChangeListener(slide -> updateVerticies());
		
		triangleSlider.addChangeListener(slide -> triangleLabel.setText("Triangles: " + triangleSlider.getValue()));
		shapeSlider.addChangeListener(slide -> shapeLabel.setText("Shapes: " + shapeSlider.getValue()));
		ellipseSlider.addChangeListener(slide -> ellipseLabel.setText("Ellipses: " + ellipseSlider.getValue()));
	}
	
	private void updateScale()
	{
		if(! scaleSlider.getValueIsAdjusting())
		{
			canvas.setCurrentScale(scaleSlider.getValue());
		}
		
		scaleLabel.setText("Scale: " + scaleSlider.getValue());
	}
	
	private void updateVerticies()
	{
		if(! verticySlider.getValueIsAdjusting())
		{
			canvas.setCurrentVerticies(verticySlider.getValue());
		}
		
		verticyLabel.setText("Verticies :" + verticySlider.getValue());
	}
}
