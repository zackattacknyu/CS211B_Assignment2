package main;

import sceneObjects.SceneObject;
import sceneObjects.Triangle;
import imageRW.ZachImageWriter;

public class MainImageGenerator {

	public static void main(String[] args) {
		//dummy image
		String dummyFileName = "startingImages/sunset.jpg";
		
		//noise image
		String renderedImageFileName = "imagesMade/sampleRender3.jpg";
		
		//height and width of image
		int imageHeight = 1200;
		int imageWidth = 1200;
		
		//background color is set here
		int backgroundR = 120;
		int backgroundG = 120;
		int backgroundB = 120;
		
		//this array is used to generate the image data
		int[][][] imageData = new int[3][imageWidth][imageHeight];
		double height = imageHeight;
		double width = imageWidth;
		double rowN = 0;
		double colN = 0;
		double imagePlaneX, imagePlaneY;
		
		System.out.println("Now Computing Image Data");
		
		double[] wall1_vertexA = {0.0,0.0,-10.0};
		double[] wall1_vertexB = {0.0,40.0,-10.0};
		double[] wall1_vertexC = {4.0,-4.0,-2.0};
		double[] wall1_rgbVals = {0.0,1.0,0.0};
		
		double[] wall2_vertexA = {0.0,0.0,-10.0};
		double[] wall2_vertexB = {0.0,40.0,-10.0};
		double[] wall2_vertexC = {-4.0,-4.0,-2.0};
		double[] wall2_rgbVals = {1.0,0.0,0.0};
		
		SceneObject[] objects = new SceneObject[2];
		
		objects[0] = new Triangle(wall1_vertexA,wall1_vertexB,wall1_vertexC);
		objects[0].setRGB(wall1_rgbVals[0], wall1_rgbVals[1], wall1_rgbVals[2]);
		
		objects[1] = new Triangle(wall2_vertexA,wall2_vertexB,wall2_vertexC);
		objects[1].setRGB(wall2_rgbVals[0], wall2_rgbVals[1], wall2_rgbVals[2]);
		
		boolean objectFound = false;
		double[] currentRGB = new double[3];
		
		//loops through all the pixels generating them
		for(int rowNum = 0; rowNum < imageData[0].length; rowNum++){
			
			for(int colNum = 0; colNum < imageData[0][rowNum].length; colNum++){
				

				rowN = rowNum; colN = colNum;
				imagePlaneX = rowN/width;
				imagePlaneY = (height-colN)/height;
				
				//transform from [0,1]x[0,1] range to [-1,-1]x[1,1] range
				imagePlaneX = 2*imagePlaneX - 1;
				imagePlaneY = 2*imagePlaneY - 1;
				
				objectFound = false;
				for(SceneObject obj: objects){
					if(obj.doesItIntersect(imagePlaneX, imagePlaneY, -1)){
						currentRGB = obj.getRGB();
						objectFound = true;
						imageData[0][rowNum][colNum] = (int) (currentRGB[0]*255);
						imageData[1][rowNum][colNum] = (int) (currentRGB[1]*255);
						imageData[2][rowNum][colNum] = (int) (currentRGB[2]*255);
					}
				}
				if(!objectFound){
					imageData[0][rowNum][colNum] = backgroundR;
					imageData[1][rowNum][colNum] = backgroundG;
					imageData[2][rowNum][colNum] = backgroundB;
				}
				
				
			}
		}
		
		
		System.out.println("Image Data computed. Now writing image");
		
		//this writes the image using the image data
		ZachImageWriter.writeImageUsingImageSize(dummyFileName, renderedImageFileName, imageData);
		
		System.out.println("Image Written Successfully");

	}
	
	public static int circleNumber(double x, double y){
		
		double centerX = 0.5;
		double centerY = 0.5;
		double radius = 0.02;
		
		double distToCenterSquared = (x-centerX)*(x-centerX) + (y-centerY)*(y-centerY);
		
		int circleNumber = 0;
		
		for(double multiple = 20; multiple > 0; multiple--){
			if(distToCenterSquared <= radius*radius*multiple*multiple){
				circleNumber = (int) multiple;
			}
		}
		
		return circleNumber;
		
	}

}