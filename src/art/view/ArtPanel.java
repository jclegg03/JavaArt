package art.view;

import java.awt.Color;

import javax.swing.JPanel;

import art.controller.Controller;

public class ArtPanel extends JPanel
{
	private Controller app;
	
	public ArtPanel(Controller app)
	{
		this.app = app;
		
		setupPanel();
		setupLayout();
		setupListeners();
	}
	
	private void setupPanel()
	{
		this.setBackground(Color.MAGENTA);
	}
	
	private void setupLayout()
	{
		
	}
	
	private void setupListeners()
	{
		
	}
}
