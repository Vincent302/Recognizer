

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
		
		int TEST_IMG_NUMBER = 18;// The number of the test image.
		
		BufferedImage [] buffered_car_image = new BufferedImage[TEST_IMG_NUMBER];//The buffered image that read from harddisk.
		String [] car_number_result = new String [TEST_IMG_NUMBER];//The result of BP netural network.
		String [] car_number_answer = new String [TEST_IMG_NUMBER];
		
		//Initiate the answer car lisence number.
		car_number_answer[0] = "沪H33029";
		car_number_answer[1] = "沪M69197";
		car_number_answer[2] = "鲁BNS376";
		car_number_answer[3] = "沪A428A6";
		car_number_answer[4] = "沪A559B8";
		car_number_answer[5] = "沪A04A22";
		car_number_answer[6] = "沪A272S7";
		car_number_answer[7] = "沪K75112";
		car_number_answer[8] = "苏ABY705";
		car_number_answer[9] = "沪A851T9";
		car_number_answer[10] = "苏A5MB90";
		car_number_answer[11] = "沪A5T580";
		car_number_answer[12] = "津G16083";
		car_number_answer[13] = "沪KB6321";
		car_number_answer[14] = "沪FT7850";
		car_number_answer[15] = "沪A8N802";
		car_number_answer[16] = "沪A4U520";
		car_number_answer[17] = "沪A8T726";
		
		//Read buffered image from harddisk.
		try {
			for(int i=0;i<TEST_IMG_NUMBER;i++){
				buffered_car_image[i] = ImageIO.read(new File("car_img/all/" + i + ".png"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//Recognize using BP netural network.
		for(int i=0;i<TEST_IMG_NUMBER;i++){
			car_number_result[i] = Recognizer.recogAll(buffered_car_image[i]);
		}
		
		int right_count = 0; // The count of the right weight.
		
		for(int i=0;i<TEST_IMG_NUMBER;i++){
			if(car_number_result[i].equals(car_number_answer[i])){
				System.out.println("Answer is: " + car_number_answer[i] + " And Result Is : " + car_number_result[i] + "___" + "Right!!!");
				right_count++;
			}else{
				System.out.println("Answer is: " + car_number_answer[i] + " And Result Is : " + car_number_result[i] + "___" + "Wrong!!! ------------>");
			}
		}
		
		System.out.println();
		System.out.println("Correct Rate Is : " + right_count + "/" + TEST_IMG_NUMBER);
	}
}
