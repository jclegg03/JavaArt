package art.view;

import java.awt.BasicStroke;
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
		
		generateTriangles(10);
		generateOtherShapes(10);
		generateEllipses(10);
		
		for(Polygon triangle : triangles)
		{
			drawingGraphics.setStroke(new BasicStroke((int) (Math.random() * 3 + 1)));
			drawingGraphics.setColor(randomColor());
			drawingGraphics.fill(triangle);
		}
		
		for(Polygon shape : otherShapes)
		{
			drawingGraphics.setStroke(new BasicStroke((int) (Math.random() * 3 + 1)));
			drawingGraphics.setColor(randomColor());
			drawingGraphics.draw(shape);
		}
		
		for(Ellipse2D.Double ellipse : ellipses)
		{
			drawingGraphics.setStroke(new BasicStroke((int) (Math.random() * 3 + 1)));
			drawingGraphics.setColor(randomColor());
			drawingGraphics.fill(ellipse);
		}
	}
	
	private void generateTriangles(int numTriangles)
	{
		triangles.clear();
		
		while(numTriangles > 0)
		{
			int[] point1 = randomLocation();
			int[] point2 = randomLocation();
			int[] point3 = randomLocation();
			
			int[] xValues = {point1[0], point2[0], point3[0]};
			int[] yValues = {point1[1], point2[1], point3[1]};
			
			Polygon triangle = new Polygon(xValues, yValues, xValues.length);
			triangles.add(triangle);
			
			numTriangles--;
		}
	}
	
	private void generateOtherShapes(int numShapes)
	{
		otherShapes.clear();
		
		while(numShapes > 0)
		{
			int numPoints = (int) (Math.random() * 10);
			
			int[] xValues = new int[numPoints];
			int[] yValues = new int[numPoints];
			
			for(int index = 0; index < numPoints; index++)
			{
				xValues[index] = (int) (Math.random() * 600);
				yValues[index] = (int) (Math.random() * 600);
			}
			
			Polygon shape = new Polygon(xValues, yValues, xValues.length);
			otherShapes.add(shape);
			
			numShapes--;
		}
	}
	
	private void generateEllipses(int numEllipses)
	{
		ellipses.clear();
		
		while(numEllipses > 0)
		{
			double xVal = (Math.random() * 560 + 20);
			double yVal = (Math.random() * 560 + 20);
			double length = (Math.random() * 19 + 1);
			double width = (Math.random() * 19 + 1);
			
			Ellipse2D.Double ellipse = new Ellipse2D.Double(xVal, yVal, length, width);
			ellipses.add(ellipse);
			
			numEllipses--;
		}
	}
	
	private int[] randomLocation()
	{
		int[] location = {(int) (Math.random() * 600), (int) (Math.random() * 600)};
		return location;
	}
	
	private Color randomColor()
	{
		int randRed = (int) (Math.random() * 236 + 10);
		int randGreen = (int) (Math.random() * 236 + 10);
		int randBlue = (int) (Math.random() * 236 + 10);
		int randAlpha = (int) (Math.random() * 236 + 10);
		
		return new Color(randRed, randGreen, randBlue);
	}
	
	@Override
	protected void paintComponent(Graphics graphics)
	{
		super.paintComponent(graphics);
		graphics.drawImage(canvasImage, 0, 0, null);
	}
}
