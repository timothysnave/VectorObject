
public class VectorObject {
	double mass;
	double damping;
	double limit;
	Vector location;
	Vector velocity;
	Vector acceleration;
	
	public VectorObject()
	{
		mass = 1;
		damping = 0.01;
		limit = 9999;
		location = new Vector();
		velocity = new Vector();
		acceleration = new Vector();
	}
	
	public VectorObject(double mass, double lx, double ly, double vx, double vy, double damping)
	{
		this.mass = mass;
		this.damping = damping;
		limit = 9999;
		location = new Vector(lx, ly);
		velocity = new Vector(vx, vy);
		acceleration = new Vector();
	}
	
	public VectorObject(double lx, double ly, double mass, double damping)
	{
		this.mass = mass;
		this.damping = damping;
		limit = 9999;
		location = new Vector(lx, ly);
		velocity = new Vector();
		acceleration = new Vector();
	}
	
	public VectorObject(double lx, double ly, double damping)
	{
		this.mass = 1;
		this.damping = damping;
		limit = 9999;
		location = new Vector(lx, ly);
		velocity = new Vector();
		acceleration = new Vector();
	}
	
	public void applyForce(Vector force)
	{
		Vector f = force.get();
		f.mult(mass);
		f.mult(damping);
		acceleration.add(f);
	}
	
	public Vector getLocation()
	{
		return location.get();
	}
	
	public void zeroVelocity()
	{
		velocity = new Vector(0,0);
	}
	
	public void setAccel(Vector accel)
	{
		acceleration = accel.get();
		acceleration.mult(damping);
	}
	
	public void limitVelocity(double limit)
	{
		this.limit = limit;
	}
	
	public void bounceX()
	{
		velocity.invertX();
	}
	
	public void bounceY()
	{
		velocity.invertY();
	}
	
	public void updateLocation()
	{
		velocity.add(acceleration);
		velocity.limit(limit);
		location.add(velocity);
	}
	
	public void setVelocity(Vector velocity)
	{
		this.velocity = velocity;
	}
	
	public Vector getVelocity()
	{
		return velocity.get();
	}
	
	public void setLocation(Vector loc)
	{
		this.location = loc;
	}
	
}
