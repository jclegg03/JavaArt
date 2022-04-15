package art.controller;

import javax.swing.JOptionPane;

import art.view.ArtFrame;

public class Controller
{
	private ArtFrame frame;
	
	public Controller()
	{
		this.frame = new ArtFrame(this);
	}
	
	public void handleError(Exception error)
	{
		JOptionPane.showMessageDialog(frame, error.getMessage(), "Javart error!", JOptionPane.ERROR_MESSAGE);	
	}
}
