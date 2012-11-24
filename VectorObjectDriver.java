import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;


public class VectorObjectDriver extends JFrame {
	private final int width = 500;
	private final int height = 500;
	
	private VectorObject obj;
	private Vector mouse;
	private Vector gravity;
	private Vector wind;
	private Image dbImage;
	private Graphics dbg;
	
	public VectorObjectDriver()
	{
		// Set up window
		this.addMouseMotionListener(new ML());
		setResizable(false);
		setSize(height, width);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// Set up vectors
		obj = new VectorObject(100,100, .0001, .1);
		obj.limitVelocity(2.5);
		gravity = new Vector(0,9.8);
		mouse = new Vector(100,100);
		wind = new Vector(4, 0);
		
	}
	
	public void update()
	{
		//   Forces
		obj.applyForce(gravity);
		obj.applyForce(wind);

		
		
		Vector loc = obj.getLocation();
		double x = loc.getX();
		double y = loc.getY();
		if (x+26>=width && y+26>=height)
		{
			obj.setLocation(new Vector(width-26,height-26));
			obj.bounceX();
			obj.bounceY();
			
		}
		else if (x+26>=width)
		{
			obj.setLocation(new Vector(width-26,y));
			obj.bounceX();
		}
		else if (y+26>=height)
		{
			obj.setLocation(new Vector(x,height-26));
			obj.bounceY();
		}
		
		obj.updateLocation();
	}

	public void paint(Graphics g)
	{
		dbImage = createImage(getWidth(), getHeight());
		dbg = dbImage.getGraphics();
		paintComponent(dbg);
		g.drawImage(dbImage, 0, 0, this);
	}
	
	public void paintComponent(Graphics g)
	{
		update();
		Vector loc = obj.getLocation();
		int x = (int) loc.getX();
		int y = (int) loc.getY();
		g.fillOval(x, y, 25, 25);
		
		repaint();
	}
	
	private class ML implements MouseMotionListener
	{

		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
			obj.setAccel(new Vector());
			obj.zeroVelocity();
			obj.setLocation(new Vector(e.getX(), e.getY()));
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			double x = e.getX();
			double y = e.getY();
	
			mouse = new Vector(x,y);
			
			
			
		}
		
	}
	
	public static void main(String[] args) {
		new VectorObjectDriver();
	}
}
