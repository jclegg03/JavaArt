package art.view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
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
		this.setBackground(Color.GREEN);
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
		
		Polygon rightShoulder = mirrorX(leftShoulder);
		drawingGraphics.fill(rightShoulder);
		
		Polygon leftArm = drawArm();
		drawingGraphics.setStroke(new BasicStroke(2));
		drawingGraphics.fill(leftArm);
		
		Polygon rightArm = mirrorX(leftArm);
		drawingGraphics.fill(rightArm);
		
		Polygon leftBody = drawBody();
		drawingGraphics.setColor(Color.YELLOW);
		drawingGraphics.setStroke(new BasicStroke(4));
		drawingGraphics.fill(leftBody);
		
		Polygon rightBody = mirrorX(leftBody);
		drawingGraphics.fill(rightBody);
		
		Polygon leftLeg = drawLeg();
		drawingGraphics.setColor(Color.BLACK);
		drawingGraphics.setStroke(new BasicStroke(2));
		drawingGraphics.fill(leftLeg);
		
		Polygon rightLeg = mirrorX(leftLeg);
		drawingGraphics.fill(rightLeg);
		
		Ellipse2D.Double eye = drawEye();
		drawingGraphics.setColor(Color.RED);
		drawingGraphics.setStroke(new BasicStroke(1));
		drawingGraphics.fill(eye);
		
		Ellipse2D.Double pupil = drawPupil();
		drawingGraphics.setColor(Color.BLACK);
		drawingGraphics.fill(pupil);
		
		Polygon leftHat = drawHat();
		drawingGraphics.fill(leftHat);
		
		Polygon rightHat = mirrorX(leftHat);
		drawingGraphics.fill(rightHat);
		
		Polygon leftTie = drawBowTie();
		drawingGraphics.fill(leftTie);
		
		Polygon rightTie = mirrorX(leftTie);
		drawingGraphics.fill(rightTie);
	}
	
	private Polygon drawShoulder()
	{
		int[] xValues = {371, 371, 257, 257};
		int[] yValues = {300, 330, 330, 300};
		
		return new Polygon(xValues, yValues, xValues.length);
	}
	
	private Polygon drawArm()
	{
		Point[] points = {new Point(257, 300), new Point(257, 480), new Point(229, 480), new Point(229, 300)};
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
	
	private Polygon drawHat()
	{
		int[] xValues = {400, 400, 371, 371, 352, 352};
		int[] yValues = {150, 70, 70, 142, 142, 150};
		
		return new Polygon(xValues, yValues, xValues.length);
	}
	
	private Polygon drawBowTie()
	{
		int[] xValues = {402, 374, 374};
		int[] yValues = {330, 345, 315};
		
		return new Polygon(xValues, yValues, xValues.length);
	}
	
	private Ellipse2D.Double drawEye()
	{
		return new Ellipse2D.Double(380.0, 240.0, 38.0, 19.0);
	}
	
	private Ellipse2D.Double drawPupil()
	{
		return new Ellipse2D.Double(396.0, 240.0, 7.0, 19.0);
	}
	
	private Polygon mirrorX(Polygon shape)
	{
		int[] xPoints = new int[shape.npoints];
		int[] yPoints = new int[shape.npoints];
		
		for(int index = 0; index < shape.ypoints.length; index++)
		{
			xPoints[index] = 800 - shape.xpoints[index];
			yPoints[index] = shape.ypoints[index];
		}
		
		return new Polygon(xPoints, yPoints, xPoints.length);
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
	
	@Override
	protected void paintComponent(Graphics graphics)
	{
		super.paintComponent(graphics);
		graphics.drawImage(canvasImage, 0, 0, null);
	}
}
