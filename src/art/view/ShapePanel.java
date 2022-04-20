package art.view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JPanel;

import art.controller.Controller;

public class ShapePanel extends JPanel
{
	public static final int MAX_VERTICIES = 50;
	public static final int MIN_VERTICIES = 3;
	public static final int MAX_SCALE = 300;
	public static final int MIN_SCALE = 10;
	private int currentScale;
	private int currentVerticies;
	private Controller app;
	private ArrayList<Polygon> triangles;
	private ArrayList<Polygon> otherShapes;
	private ArrayList<Ellipse2D.Double> ellipses;
	private BufferedImage canvasImage;
	
	public ShapePanel(Controller app)
	{
		super();
		this.setBackground(Color.BLACK);
		this.currentScale = 50;
		this.currentVerticies = 10;
		this.app = app;
		this.triangles = new ArrayList<Polygon>();
		this.otherShapes = new ArrayList<Polygon>();
		this.ellipses = new ArrayList<Ellipse2D.Double>();
		this.canvasImage = new BufferedImage(600, 600, BufferedImage.TYPE_INT_ARGB);
		
		makeArt(10, 10, 10);
	}
	
	private void generateArt()
	{
		Graphics2D drawingGraphics = (Graphics2D) (canvasImage.getGraphics());
		
		drawingGraphics.setColor(Color.BLACK);
		drawingGraphics.setStroke(new BasicStroke(1));
		drawingGraphics.fill(imageRectangle());
		
		for(Polygon triangle : triangles)
		{
			drawingGraphics.setStroke(new BasicStroke((int) (Math.random() * 3 + 1)));
			drawingGraphics.setColor(randomColor());
			drawingGraphics.fill(triangle);
		}
		
		for(Polygon shape : otherShapes)
		{
			drawingGraphics.setStroke(new BasicStroke((int) (Math.random() * 4 + 4)));
			drawingGraphics.setColor(randomColor());
			drawingGraphics.draw(shape);
		}
		
		for(Ellipse2D.Double ellipse : ellipses)
		{
			drawingGraphics.setStroke(new BasicStroke((int) (Math.random() * 3 + 1)));
			drawingGraphics.setColor(randomColor());
			drawingGraphics.fill(ellipse);
		}
		
		drawingGraphics.dispose();
		this.repaint();
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
			int numPoints = (int) (Math.random() * (currentVerticies + 1));
			
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
			double xVal = (Math.random() * (600 - 2 * currentScale));
			double yVal = (Math.random() * (600 - 2 * currentScale));
			double length = (Math.random() * (currentScale + 1) + currentScale);
			double width = (Math.random() * (currentScale + 1) + currentScale);
			
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
	
	private Polygon imageRectangle()
	{
		Polygon rectangle = new Polygon();
		
		rectangle.addPoint(0, 0);
		rectangle.addPoint(599, 0);
		rectangle.addPoint(599, 599);
		rectangle.addPoint(0, 599);
		
		return rectangle;
	}
	
	@Override
	protected void paintComponent(Graphics graphics)
	{
		super.paintComponent(graphics);
		graphics.drawImage(canvasImage, 0, 0, null);
	}
	
	public void save()
	{
		JFileChooser saver = new JFileChooser();
		
		try
		{
			if(saver.showSaveDialog(this) == JFileChooser.APPROVE_OPTION)
			{
				String savePath = saver.getSelectedFile().getPath();
				
				if(! savePath.endsWith(".png"))
				{
					savePath += ".png";
				}
				
				ImageIO.write(canvasImage, "PNG", new File(savePath));
			}
		}
		catch(IOException saveError)
		{
			app.handleError(saveError);
		}
		catch(NullPointerException majorError)
		{
			app.handleError(majorError);
		}
	}
	
	public void makeArt(int triangles, int shapes, int ellipses)
	{
		generateTriangles(triangles);
		generateOtherShapes(shapes);
		generateEllipses(ellipses);
		
		generateArt();
	}
	
	public void clear()
	{
		makeArt(0, 0, 0);
	}
}
