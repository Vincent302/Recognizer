

/**                          
* Project:           Recognizer                                
* Comments:          Check car number by car number picture                                          
* JDK version used:  JDK1.6                             
* Namespace:         Recognizer                              
* Author：                              Hun                
* Create Date：                2013-03-09
* Modified By：                Hun                                      
* Modified Date:     2013-03-18                  
* Version:           V3.4                       
*/ 


package Recognizer;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Checker {
	public static void main(String [] args){
		String filePath = "check/car_check.jpg";
		
		String result = "#######";
		try {
			BufferedImage buffered_car_image = ImageIO.read(new File(filePath));
			result = Recognizer.recogAll(buffered_car_image);			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println(result);
	}
}
