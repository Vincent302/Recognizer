

/**                          
* Project:           Recognizer                                
* Comments:          Test of single letter file                                          
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

public class TesterSingle {
	public static void main(String [] args){
		
		int province_number = Global.PROVINCE_NUMBER;
		int letter_number = Global.LETTER_NUMBER;
		BP bp_province = new BP(Global.PROVINCE_BP_INPUT_LENGTH, Global.PROVINCE_HIDDEN_LAYER_NUMBER, province_number);
		BP bp_letter = new BP(Global.LETTER_BP_INPUT_LENGTH, Global.LETTER_HIDDEN_LAYER_NUMBER, letter_number);
		BufferedImage [] province_images = new BufferedImage[province_number];
		BufferedImage [] letter_images = new BufferedImage[letter_number];
		
		
		//Initiate BP weight.
		bp_province.loadWeight(Global.PROVINCE_WEIGHT_FILE_PATH);
		bp_letter.loadWeight(Global.LETTER_WEIGHT_FILE_PATH);
		
		
		int province_right_count = 0;
		int letter_right_count = 0;
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
			
			//Test province.
			for(int i=0;i<province_number;i++){
	            double [] bp_input_array = ImageProcessor.getProvinceBPInputArray(province_images[i]);
	            double [] result = bp_province.check(bp_input_array);
	            int index = 0;
	            double max_value = Double.MIN_VALUE;
	            for(int r=0;r<province_number;r++){
	            	if(max_value < result[r]){
	            		max_value = result[r];
	            		index = r;
	            	}
	            }
	            if(i == index){
	            	province_right_count++;
	            }
	            System.out.println("Province " + i + " : " + index + " ___ " + max_value);
			}
			System.out.println();
			
			
			//Test letter.
			for(int i=0;i<letter_number;i++){
	            double [] bp_input_array = ImageProcessor.getLetterBPInputArray(letter_images[i]);
	            double [] result = bp_letter.check(bp_input_array);
	            int index = 0;
	            double max_value = Double.MIN_VALUE;
	            for(int r=0;r<letter_number;r++){
	            	if(max_value < result[r]){
	            		max_value = result[r];
	            		index = r;
	            	}
	            }
	            if(i == index){
	            	letter_right_count++;
	            }
	            System.out.println("Letter " + i + " : " + index + " ___ " + max_value);
			}
			System.out.println();
			
			//Show result.
			
		}
		System.out.println("Provincr right rate: " +  province_right_count + "/" + province_number*Global.NOISE);
		System.out.println("Letter Right rate: " +  letter_right_count + "/" + letter_number*Global.NOISE);
	}
}
