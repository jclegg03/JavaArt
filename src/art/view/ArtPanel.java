package art.view;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import art.controller.Controller;

public class ArtPanel extends JPanel
{
	private Controller app;
	private CanvasPanel canvas;
	private JPanel menuPanel;
	private JButton saveButton;
	private SpringLayout layout;
	
	public ArtPanel(Controller app)
	{
		this.app = app;
		this.canvas = new CanvasPanel(app);
		this.menuPanel = new JPanel();
		this.saveButton = new JButton("Save");
		this.layout = new SpringLayout();
		
		setupPanel();
		setupLayout();
		setupListeners();
	}
	
	private void setupPanel()
	{
//		this.setBackground(Color.MAGENTA);
		this.add(canvas);
		this.add(menuPanel);
		
		menuPanel.add(saveButton);
	}
	
	private void setupLayout()
	{
		this.setLayout(layout);
		
		layout.putConstraint(SpringLayout.NORTH, menuPanel, 0, SpringLayout.NORTH, canvas);
		layout.putConstraint(SpringLayout.WEST, menuPanel, 20, SpringLayout.EAST, canvas);
		layout.putConstraint(SpringLayout.SOUTH, menuPanel, 0, SpringLayout.SOUTH, canvas);
		layout.putConstraint(SpringLayout.EAST, menuPanel, -20, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.NORTH, canvas, 20, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, canvas, 20, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.SOUTH, canvas, -20, SpringLayout.SOUTH, this);
		layout.putConstraint(SpringLayout.EAST, canvas, -150, SpringLayout.EAST, this);
	}
	
	private void setupListeners()
	{
		
	}
}
