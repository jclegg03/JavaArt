package art.controller;

import art.view.ArtFrame;

public class Controller
{
	private ArtFrame frame;
	
	public Controller()
	{
		this.frame = new ArtFrame(this);
	}
}
