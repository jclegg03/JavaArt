package art.view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import art.controller.Controller;

public class CanvasPanel extends JPanel
{
	private Controller app;
	private BufferedImage canvasImage;
	
	public CanvasPanel(Controller app)
	{
		super();
		this.app = app;
		this.canvasImage = new BufferedImage(600, 600, BufferedImage.TYPE_INT_ARGB);
		updateCanvas();
	}
	
	private void updateCanvas()
	{
		drawBill();
	}
	
	private void drawBill()
	{
		Graphics2D drawingGraphics = (Graphics2D) canvasImage.getGraphics();
		
		Polygon leftShoulder = drawShoulder();
		drawingGraphics.setColor(Color.BLACK);
		drawingGraphics.setStroke(new BasicStroke(3));
		drawingGraphics.fill(leftShoulder);
		
		Polygon rightShoulder = mirror(leftShoulder);
		drawingGraphics.fill(rightShoulder);
		
		Polygon leftArm = drawArm();
		drawingGraphics.setStroke(new BasicStroke(2));
		drawingGraphics.fill(leftArm);
		
		Polygon rightArm = mirror(leftArm);
		drawingGraphics.fill(rightArm);
		
		Polygon leftBody = drawBody();
		drawingGraphics.setColor(Color.YELLOW);
		drawingGraphics.setStroke(new BasicStroke(4));
		drawingGraphics.fill(leftBody);
		
		Polygon rightBody = mirror(leftBody);
		drawingGraphics.fill(rightBody);
		
		Polygon leftLeg = drawLeg();
		drawingGraphics.setColor(Color.BLACK);
		drawingGraphics.setStroke(new BasicStroke(2));
		drawingGraphics.fill(leftLeg);
		
		Polygon rightLeg = mirror(leftLeg);
		drawingGraphics.fill(rightLeg);
		
		Ellipse2D.Double eye = drawEye();
		drawingGraphics.setColor(Color.RED);
		drawingGraphics.setStroke(new BasicStroke(1));
		drawingGraphics.fill(eye);
	}
	
	private Polygon drawShoulder()
	{
		int[] xValues = {371, 371, 257, 257};
		int[] yValues = {270, 240, 240, 270};
		
		return new Polygon(xValues, yValues, xValues.length);
	}
	
	private Polygon drawArm()
	{
		Point[] points = {new Point(257, 240), new Point(257, 480), new Point(229, 480), new Point(229, 240)};
		Polygon arm = new Polygon();
		
		for(Point point : points)
		{
			arm.addPoint(point.x, point.y);
		}
		
		return arm;
	}
	
	private Polygon drawLeg()
	{
		Polygon leg = new Polygon();
		
		leg.addPoint(371, 390);
		leg.addPoint(371, 450);
		leg.addPoint(343, 450);
		leg.addPoint(343, 390);
		
		return leg;
	}
	
	private Polygon drawBody()
	{
		int[] xValues = {400, 400, 286};
		int[] yValues = {390, 150, 390};
		
		return new Polygon(xValues, yValues, xValues.length);
	}
	
	private Ellipse2D.Double drawEye()
	{
		return new Ellipse2D.Double(371.0, 240.0, 2.0, 2.0);
	}
	
	private Polygon mirror(Polygon shape)
	{
		int[] xPoints = new int[shape.npoints];
		int[] yPoints = new int[shape.npoints];
		
		for(int index = 0; index < shape.ypoints.length; index++)
		{
			xPoints[index] = 800 - shape.xpoints[index];
			yPoints[index] = 600 - shape.ypoints[index];
		}
		
		return new Polygon(xPoints, yPoints, xPoints.length);
	}
	
	@Override
	protected void paintComponent(Graphics graphics)
	{
		super.paintComponent(graphics);
		graphics.drawImage(canvasImage, 0, 0, null);
	}
}
