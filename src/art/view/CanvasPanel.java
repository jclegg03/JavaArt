package art.view;

import java.awt.Point;
import java.awt.Polygon;
import java.awt.geom.Ellipse2D;

import javax.swing.JPanel;

public class CanvasPanel extends JPanel
{
	
	
	private Polygon drawBill()
	{
		Polygon bill = new Polygon();
		
		
		
		return bill;
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
		Polygon mirror = new Polygon();
		
		int[] xPoints = new int[shape.npoints];
		int[] yPoints = new int[shape.npoints];
		
		for(int x : shape.xpoints)
		{
			
		}
		
		return mirror;
	}
}
