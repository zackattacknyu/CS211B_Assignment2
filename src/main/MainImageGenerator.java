package main;

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
		
		double[] vertexACoords = {0.0,0.0,-2.0};
		double[] vertexBCoords = {0.0,1.0,-2.0};
		double[] vertexCCoords = {1.0,0.0,-2.0};
		
		double[] rgbVal = {0.0,1.0,0.0};
		
		Triangle wallOne = new Triangle(vertexACoords,vertexBCoords,vertexCCoords);
		
		//loops through all the pixels generating them
		for(int rowNum = 0; rowNum < imageData[0].length; rowNum++){
			
			for(int colNum = 0; colNum < imageData[0][rowNum].length; colNum++){
				

				rowN = rowNum; colN = colNum;
				imagePlaneX = rowN/width;
				imagePlaneY = (height-colN)/height;
				
				if(wallOne.doesItIntersect(imagePlaneX, imagePlaneY, -1)){
					imageData[0][rowNum][colNum] = 0;
					imageData[1][rowNum][colNum] = 255;
					imageData[2][rowNum][colNum] = 0;
				}
				
				imageData[0][rowNum][colNum] = backgroundR;
				imageData[1][rowNum][colNum] = backgroundG;
				imageData[2][rowNum][colNum] = backgroundB;
				
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