package sceneObjects;

public class Sphere {

	private double centerX;
	private double centerY;
	private double centerZ;
	private double radius;
	
	public Sphere(double centerX, double centerY, double centerZ, double radius){
		this.centerX = centerX;
		this.centerY = centerY;
		this.centerZ = centerZ;
		this.radius = radius;
	}

	public double getCenterX() {
		return centerX;
	}

	public double getCenterY() {
		return centerY;
	}

	public double getCenterZ() {
		return centerZ;
	}

	public double getRadius() {
		return radius;
	}
}
