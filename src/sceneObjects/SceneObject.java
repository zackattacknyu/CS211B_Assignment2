package sceneObjects;

public abstract class SceneObject {

	protected double red;
	protected double green;
	protected double blue;
	
	public SceneObject(){
		red = 0.0;
		green = 0.0;
		blue = 0.0;
	}
	
	public void setRGB(double redVal, double greenVal, double blueVal){
		red = redVal;
		green = greenVal;
		blue = blueVal;
	}
	
	public double[] getRGB(){
		double[] rgbVals = {red,green,blue};
		return rgbVals;
	}
	
	public abstract boolean doesItIntersect(double rayVectorX, double rayVectorY, double rayVectorZ);
	
}
