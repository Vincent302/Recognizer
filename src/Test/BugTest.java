package Test;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import ImageTool.ImageProcessor;
import Recognizer.Recognizer;

public class BugTest {
	public static void main(String [] args){
		/*
		try {
			
			BufferedImage image_1;
			BufferedImage image_2;
			BufferedImage temp_image = ImageIO.read(new File("car_img/all/0.jpg"));
			
			ImageProcessor ipr = new ImageProcessor(temp_image);
			ipr.doPrepare();
			image_1 = ipr.buffered_image_array[5];
			
			image_2 = ImageIO.read(new File("temp/5.png"));
			System.out.println();

			double [] array_1 = ImageProcessor.getLetterBPInputArray(image_1);
			double [] array_2 = ImageProcessor.getLetterBPInputArray(image_2);
			
			
			int length = array_1.length;
			for(int i=0;i<length;i++){
				if(array_1[i]==array_2[i]){
					System.out.println("same");
				}else{
					System.out.println("different");
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		*/
	}
}
