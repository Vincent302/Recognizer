

/**                          
* Project:           Recognizer                                
* Comments:          Picture reader interface                                           
* JDK version used:  JDK1.6                             
* Namespace:         Interface                              
* Author：                              Vincent Li                
* Create Date：                2013-03-09
* Modified By：                Vincent Li                                      
* Modified Date:     2013-03-18                  
* Version:           V3.4                       
*/ 


package Interface;

import java.awt.image.BufferedImage;

public interface IPicReader {
	BufferedImage getExpendedImage(BufferedImage bi);
	BufferedImage getImage();
	int [][] getImagePix(BufferedImage bi);
	double [] getBPImageInput(int [][] imagePix);
	double [] doRun();
}
