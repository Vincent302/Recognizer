

/**                          
* Project:           Recognizer                                
* Comments:          Test of hand writer system                                         
* JDK version used:  JDK1.6                             
* Namespace:         Util                              
* Author：                              Hun                
* Create Date：                2013-03-09
* Modified By：                Hun                                      
* Modified Date:     2013-03-18                  
* Version:           V3.4                       
*/ 


package Test;

import ImageTool.PicReader;

public class HW_ImageTest {
	public static void main(String [] args){
		String filePath = "img/0.jpg";
		PicReader pr = new PicReader(filePath);
		double [] input = pr.doRun();

		System.out.println(input.length);
		for(int i=0;i<input.length;i++){
			System.out.print(input[i]);
		}
	}
}
