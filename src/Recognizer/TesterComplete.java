

/**                          
* Project:           Recognizer                                
* Comments:          Test of complete image file                                          
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

public class TesterComplete {
	public static void main(String [] args){
		
		int test_image_number = 6;
		BufferedImage [] buffered_car_image = new BufferedImage[test_image_number];
		
		try {
			for(int i=0;i<test_image_number;i++){
				buffered_car_image[i] = ImageIO.read(new File("car_img/all/" + i + ".png"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String [] car_number_result = new String [test_image_number];
		for(int i=0;i<test_image_number;i++){
			car_number_result[i] = Recognizer.recogAll(buffered_car_image[i]);
		}
		
		int right_count = 0;
		if(car_number_result[0].equals("沪A559B8")){
			System.out.println("Answer is: " + "沪A559B8" + " And Resule Is : " + car_number_result[0] + "___" + "Right!!!");
			right_count++;
		}else{
			System.out.println("Answer is: " + "沪A559B8" + " And Resule Is : " + car_number_result[0] + "___" + "Wrong!!!");
		}
		if(car_number_result[1].equals("沪H33029")){
			System.out.println("Answer is: " + "沪H33029" + " And Resule Is : " + car_number_result[1] + "___" + "Right!!!");
			right_count++;
		}else{
			System.out.println("Answer is: " + "沪H33029" + " And Resule Is : " + car_number_result[1] + "___" + "Wrong!!!");
		}
		if(car_number_result[2].equals("沪A04A22")){
			System.out.println("Answer is: " + "沪A04A22" + " And Resule Is : " + car_number_result[2] + "___" + "Right!!!");
			right_count++;
		}else{
			System.out.println("Answer is: " + "沪A04A22" + " And Resule Is : " + car_number_result[2] + "___" + "Wrong!!!");
		}
		if(car_number_result[3].equals("鲁BNS376")){
			System.out.println("Answer is: " + "鲁BNS376" + " And Resule Is : " + car_number_result[3] + "___" + "Right!!!");
			right_count++;
		}else{
			System.out.println("Answer is: " + "鲁BNS376" + " And Resule Is : " + car_number_result[3] + "___" + "Wrong!!!");
		}
		if(car_number_result[4].equals("沪A428A6")){
			System.out.println("Answer is: " + "沪A428A6" + " And Resule Is : " + car_number_result[4] + "___" + "Right!!!");
			right_count++;
		}else{
			System.out.println("Answer is: " + "沪A428A6" + " And Resule Is : " + car_number_result[4] + "___" + "Wrong!!!");
		}
		if(car_number_result[5].equals("沪A272S7")){
			System.out.println("Answer is: " + "沪A272S7" + " And Resule Is : " + car_number_result[5] + "___" + "Right!!!");
			right_count++;
		}else{
			System.out.println("Answer is: " + "沪A272S7" + " And Resule Is : " + car_number_result[5] + "___" + "Wrong!!!");
		}
		
		
		System.out.println();
		System.out.println("Correct Rate Is : " + right_count + "/" + test_image_number);
	}
}
