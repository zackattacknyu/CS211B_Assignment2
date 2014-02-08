package imageRW;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;

import javax.imageio.ImageIO;

import imageRW.MyImageReader;

public class ZachImageWriter {
	
	public static boolean writeImageUsingImageSize(String inputFileName, String outputFileName, int[][][] imageData){
		BufferedImage inputImage = MyImageReader.readImageIntoBufferedImage( inputFileName );
        if ( inputImage == null )
        {
            System.out.println(" Could not open input image.");
            return false;
        }

        //BufferedImage outputImage = new BufferedImage( inputImage.getWidth(), inputImage.getHeight(),
        //                                               inputImage.getType() );
        BufferedImage outputImage = new BufferedImage( imageData[0].length, imageData[0][0].length,
                inputImage.getType() );
        WritableRaster outputRaster, inputRaster;
        inputRaster = inputImage.getRaster();
        outputRaster = inputRaster.createCompatibleWritableRaster();

        // GRAY
        // when writing color files, we will have to handle multible bands
        int[] pixelData = new int[ outputRaster.getNumBands() ];
        int band = 0;
        int numbands = outputRaster.getNumBands();

        int height, width;
        height = imageData[0][0].length;
        width = imageData[0].length;

        for ( int y = 0; y < height; y++ )
            for ( int x = 0; x < width; x++ )
            {
                for ( band = 0; band < numbands; band++ )
                {
                    pixelData[ band ] = imageData[band][x][y];
                }
                outputRaster.setPixel(x, y, pixelData );
            }
        outputImage.setData( outputRaster );

        File outputFile = new File( outputFileName );
        try
        {
            if ( !ImageIO.write( outputImage, "jpg", outputFile ))
            {
                System.out.println("Could not find image format for output image.");
                return false;
            }
        }
        catch ( Exception e )
        {
            System.out.println("Could not write output file.");
            return false;
        }

        return true;
	}

	public static boolean writeImage(String inputFileName, String outputFileName, int[][][] imageData)
    {
        BufferedImage inputImage = MyImageReader.readImageIntoBufferedImage( inputFileName );
        if ( inputImage == null )
        {
            System.out.println(" Could not open input image.");
            return false;
        }

        BufferedImage outputImage = new BufferedImage( inputImage.getWidth(), inputImage.getHeight(),
                                                       inputImage.getType() );
        WritableRaster outputRaster, inputRaster;
        inputRaster = inputImage.getRaster();
        outputRaster = inputRaster.createCompatibleWritableRaster();

        // GRAY
        // when writing color files, we will have to handle multible bands
        int[] pixelData = new int[ outputRaster.getNumBands() ];
        int band = 0;
        int numbands = outputRaster.getNumBands();

        int height, width;
        height = outputRaster.getHeight();
        width = outputRaster.getWidth();

        for ( int y = 0; y < height; y++ )
            for ( int x = 0; x < width; x++ )
            {
                for ( band = 0; band < numbands; band++ )
                {
                    pixelData[ band ] = imageData[band][y][x];
                }
                outputRaster.setPixel(x, y, pixelData );
            }
        outputImage.setData( outputRaster );

        File outputFile = new File( outputFileName );
        try
        {
            if ( !ImageIO.write( outputImage, "jpg", outputFile ))
            {
                System.out.println("Could not find image format for output image.");
                return false;
            }
        }
        catch ( Exception e )
        {
            System.out.println("Could not write output file.");
            return false;
        }

        return true;
    }
}
