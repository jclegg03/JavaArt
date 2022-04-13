package art.view;

import javax.swing.JFrame;

import art.controller.Controller;

public class ArtFrame extends JFrame
{
	private Controller app;
	
	public ArtFrame(Controller app)
	{
		super("Javart");
		this.app = app;
		setupFrame();
	}
	
	private void setupFrame()
	{
		this.setContentPane(new ArtPanel(app));
		this.setSize(800, 600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}
