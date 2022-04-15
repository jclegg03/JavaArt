package art.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;

import art.controller.Controller;

public class ShapePanel extends JPanel
{
	private Controller app;
	private ArrayList<Polygon> triangles;
	private ArrayList<Polygon> otherShapes;
	private ArrayList<Ellipse2D.Double> ellipses;
	private BufferedImage canvasImage;
	
	public ShapePanel(Controller app)
	{
		super();
		this.app = app;
		this.triangles = new ArrayList<Polygon>();
		this.otherShapes = new ArrayList<Polygon>();
		this.ellipses = new ArrayList<Ellipse2D.Double>();
		this.canvasImage = new BufferedImage(600, 600, BufferedImage.TYPE_INT_ARGB);
		
		generateArt();
	}
	
	private void generateArt()
	{
		Graphics2D drawingGraphics = (Graphics2D) (this.getGraphics());
		
		generateTriangles();
		generateOtherShapes();
		generateEllipses();
	}
	
	private void generateTriangles()
	{
		
	}
	
	private void generateOtherShapes()
	{
		
	}
	
	private void generateEllipses()
	{
		
	}
	
	private Color randomColor()
	{
		int randRed = (int) (Math.random() * 246 + 10);
		int randGreen = (int) (Math.random() * 246 + 10);
		int randBlue = (int) (Math.random() * 246 + 10);
		
		return new Color(randRed, randGreen, randBlue);
	}
	
	@Override
	protected void paintComponent(Graphics graphics)
	{
		super.paintComponent(graphics);
		graphics.drawImage(canvasImage, 0, 0, null);
	}
}
