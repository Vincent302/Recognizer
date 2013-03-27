

/**                          
* Project:           Recognizer                                
* Comments:          Some global constants                                         
* JDK version used:  JDK1.6                             
* Namespace:         Util                              
* Author：                              Hun                
* Create Date：                2013-03-09
* Modified By：                Hun                                      
* Modified Date:     2013-03-18                  
* Version:           V3.4                       
*/ 


package Util;

public class Const {
	public static int PIC_WIDTH = 200;
	public static int PIC_HEIGHT = 200;
	public static int STEP = 40;
	public static int INPUT_WIDTH = PIC_WIDTH / STEP;
	public static int INPUT_HEIGHT = PIC_HEIGHT / STEP;
	public static int INPUT_NUMBER = INPUT_WIDTH * INPUT_HEIGHT;
	

	public static int PARAM_HIDDEN = INPUT_NUMBER;
	public static int PARAM_OUTPUT = 52;
	
	public static int TRAINING_TIME = 1000;
	
	
	public static double LEARNING_RATE = 0.25;
	public static double MONUMENT = 0.8;
}
