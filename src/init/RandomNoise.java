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
		
		//this is used to generate the image data
		int[][][] imageData = new int[3][imageWidth][imageHeight];
		for(int channelNo = 0; channelNo < imageData.length; channelNo++){
			for(int rowNum = 0; rowNum < imageData[channelNo].length; rowNum++){
				
				for(int colNum = 0; colNum < imageData[channelNo][rowNum].length; colNum++){
					//imageData[channelNo][rowNum][colNum] = (int)(256*Math.random());
					imageData[channelNo][rowNum][colNum] = (int)((rowNum+colNum)/2);
				}
			}
		}
		

		//this writes the image using the image data
		ZachImageWriter.writeImageUsingImageSize(dummyFileName, renderedImageFileName, imageData);
		
		System.out.println("Image Written Successfully");

	}

}