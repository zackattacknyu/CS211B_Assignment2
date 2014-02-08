package init;

import imageRW.ZachImageWriter;

public class RandomNoise {

	public static void main(String[] args) {
		//dummy image
		String dummyFileName = "startingImages/sunset.jpg";
		
		//noise image
		String renderedImageFileName = "imagesMade/sampleRender2.jpg";
		
		//height and width of image
		int imageHeight = 1200;
		int imageWidth = 1200;
		
		//this array is used to generate the image data
		int[][][] imageData = new int[3][imageWidth][imageHeight];
		double height = imageHeight;
		double width = imageWidth;
		double rowN = 0;
		double colN = 0;
		int circleNumber = 0;
		
		System.out.println("Now Computing Image Data");
		
		//loops through all the pixels generating them
		for(int rowNum = 0; rowNum < imageData[0].length; rowNum++){
			
			for(int colNum = 0; colNum < imageData[0][rowNum].length; colNum++){
				

				rowN = rowNum; colN = colNum;
				
				circleNumber = circleNumber(rowN/width,(height-colN)/height);
				
				imageData[0][rowNum][colNum] = 0;
				imageData[1][rowNum][colNum] = 255;
				imageData[2][rowNum][colNum] = 0;
				
				if(circleNumber%2 == 1){
					imageData[0][rowNum][colNum] = 255;
					imageData[1][rowNum][colNum] = 0;
					imageData[2][rowNum][colNum] = 0;
				}else if(circleNumber != 0){
					imageData[0][rowNum][colNum] = 255;
					imageData[1][rowNum][colNum] = 255;
					imageData[2][rowNum][colNum] = 255;
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
		double radius = 0.1;
		
		double distToCenterSquared = (x-centerX)*(x-centerX) + (y-centerY)*(y-centerY);
		
		int circleNumber = 0;
		
		for(double multiple = 23; multiple > 0; multiple--){
			if(distToCenterSquared <= radius*radius*multiple){
				circleNumber = (int) multiple;
			}
		}
		
		return circleNumber;
		
	}

}