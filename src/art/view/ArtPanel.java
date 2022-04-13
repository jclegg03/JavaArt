package art.view;

import javax.swing.JPanel;

import art.controller.Controller;

public class ArtPanel extends JPanel
{
	private Controller app;
	
	public ArtPanel(Controller app)
	{
		this.app = app;
	}
}
