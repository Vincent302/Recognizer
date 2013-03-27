

/**                          
* Project:           Recognizer                                
* Comments:          BP network trainer                                          
* JDK version used:  JDK1.6                             
* Namespace:         Recognizer                              
* Author：                              Vincent Li                
* Create Date：                2013-03-09
* Modified By：                Vincent Li                                      
* Modified Date:     2013-03-18                  
* Version:           V3.4                       
*/ 


package Recognizer;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import BP.BP;
import ImageTool.ImageProcessor;
import Util.Global;

public class Trainer {
	public static void main(String [] args){
		long start_time = System.currentTimeMillis();
		
		int province_number = Global.PROVINCE_NUMBER;
		int letter_number = Global.LETTER_NUMBER;
		BP bp_province = new BP(Global.PROVINCE_BP_INPUT_LENGTH, Global.PROVINCE_HIDDEN_LAYER_NUMBER, province_number);
		BP bp_letter = new BP(Global.LETTER_BP_INPUT_LENGTH, Global.LETTER_HIDDEN_LAYER_NUMBER, letter_number);
		BufferedImage [] province_images = new BufferedImage[province_number];
		BufferedImage [] letter_images = new BufferedImage[letter_number];
		
		for(int noise=0;noise<Global.NOISE;noise++){
			//Initiate image.
			try {
				for(int i=0;i<province_number;i++){
					province_images[i] = ImageIO.read(new File("car_img/province/" + i + "_" + noise + ".png"));
				}
				for(int i=0;i<letter_number;i++){
					letter_images[i] = ImageIO.read(new File("car_img/letter/" + i + "_" + noise + ".png"));
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			
			//Train province.
			for(int t=0;t<Global.PROVINCE_TRAINING_TIME;t++){
				for(int i=0;i<province_number;i++){
					double [] answer = new double [province_number];
					for(int k=0;k<province_number;k++){
						answer[k] = 0;
					}
					answer[i] = 1;
		            double [] bp_input_array = ImageProcessor.getProvinceBPInputArray(province_images[i]);
		            bp_province.train(bp_input_array, answer);
				}
				System.out.println("Province training: " + t + "noise_" + noise);
			}
			
			//Train letter.
			for(int t=0;t<Global.LETTER_TRAINING_TIME;t++){
				for(int i=0;i<letter_number;i++){
					double [] answer = new double [letter_number];
					for(int k=0;k<letter_number;k++){
						answer[k] = 0;
					}
					answer[i] = 1;
		            double [] bp_input_array = ImageProcessor.getLetterBPInputArray(letter_images[i]);
		            bp_letter.train(bp_input_array, answer);
				}
				System.out.println("Letter training: " + t + "noise_" + noise);
			}
			System.out.println("Training noise : " + noise);
		}
		
		//Store weight.
		bp_province.storeWeight(Global.PROVINCE_WEIGHT_FILE_PATH);
		bp_letter.storeWeight(Global.LETTER_WEIGHT_FILE_PATH);
		
		//Finish.
		System.out.println("Training completed! ");
		long end_time = System.currentTimeMillis();
		System.out.println("Total time : " + ((double)(end_time - start_time) / (double)1000) + " seconds");
	}
}
