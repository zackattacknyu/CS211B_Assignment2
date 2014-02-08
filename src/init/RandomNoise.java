package init;

import imageRW.ZachImageWriter;

public class RandomNoise {

	public static void main(String[] args) {
		//dummy image
		String dummyFileName = "startingImages/sunset.jpg";
		
		//noise image
		String renderedImageFileName = "imagesMade/sampleRender.jpg";
		
		//height and width of image
		int imageHeight = 700;
		int imageWidth = 1000;
		
		//this array is used to generate the image data
		int[][][] imageData = new int[3][imageWidth][imageHeight];
		int currentAmp = 0;
		double height = imageHeight;
		double width = imageWidth;
		double rowN = 0;
		double colN = 0;
		double scaleFactor = 0;
		
		System.out.println("Now Computing Image Data");
		
		//loops through all the pixels generating them
		for(int rowNum = 0; rowNum < imageData[0].length; rowNum++){
			
			for(int colNum = 0; colNum < imageData[0][rowNum].length; colNum++){
				
				//currentAmp = (int)(256*Math.random());
				
				rowN = rowNum; colN = colNum;
				scaleFactor = (rowN + colN)/(height + width);
				currentAmp = (int)(scaleFactor*256);
				
				imageData[0][rowNum][colNum] = currentAmp;
				imageData[1][rowNum][colNum] = currentAmp;
				imageData[2][rowNum][colNum] = currentAmp;
			}
		}
		
		
		System.out.println("Image Data computed. Now writing image");
		//this writes the image using the image data
		ZachImageWriter.writeImageUsingImageSize(dummyFileName, renderedImageFileName, imageData);
		
		System.out.println("Image Written Successfully");

	}

}