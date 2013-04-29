

/**                          
* Project:           Recognizer                                
* Comments:          Some global constants                                         
* JDK version used:  JDK1.6                             
* Namespace:         Util                              
* Author：                              Vincent Li                
* Create Date：                2013-03-09
* Modified By：                Vincent Li                                      
* Modified Date:     2013-03-18                  
* Version:           V3.4                       
*/ 


package Util;

public class Global {
	
	
	//Some rate
	public static double LEARNING_RATE = 0.25;
	public static double MOMENTUM = 0.8;
	
	//Picture size.
	public static int CARD_WIDTH = 320;
	public static int CARD_HEIGHT = 100;
	
	//Dividing line of R+G+B.
	public static int BINARY_ADJUSTOR = 300;
	
	//Letter size.
	public static int PROVINCE_IMAGE_WIDTH = 24;
	public static int PROVINCE_IMAGE_HEIGHT = 48;
	public static int LETTER_IMAGE_WIDTH = 24;
	public static int LETTER_IMAGE_HEIGHT = 48;
	
	//Original image size
	public static int PREV_PROVINCE_IMAGE_WIDTH = 35;
	public static int PREV_PROVINCE_IMAGE_HEIGHT = 65;
	public static int PREV_LETTER_IMAGE_WIDTH = 35;
	public static int PREV_LETTER_IMAGE_HEIGHT = 69;
	
	//Letter start point.
	public static int PROVINCE_LEFT = 10;
	public static int PROVINCE_TOP = 15;
	public static int LETTER_1_LEFT = 50;
	public static int LETTER_1_TOP = 15;
	public static int LETTER_2_LEFT = 110;
	public static int LETTER_2_TOP = 15;
	public static int LETTER_3_LEFT = 150;
	public static int LETTER_3_TOP = 15;
	public static int LETTER_4_LEFT = 193;
	public static int LETTER_4_TOP = 16;
	public static int LETTER_5_LEFT = 234;
	public static int LETTER_5_TOP = 15;
	public static int LETTER_6_LEFT = 277;
	public static int LETTER_6_TOP = 18;
	
	//Block size.
	public static int PROVINCE_IMAGE_BLOCK_WIDTH = 3;
	public static int PROVINCE_IMAGE_BLOCK_HEIGHT = 3;
	public static int LETTER_IMAGE_BLOCK_WIDTH = 3;
	public static int LETTER_IMAGE_BLOCK_HEIGHT = 3;
	
	//Block number.
	public static int PROVINCE_X_NUMBER = PROVINCE_IMAGE_WIDTH / PROVINCE_IMAGE_BLOCK_WIDTH;
	public static int PROVINCE_Y_NUMBER = PROVINCE_IMAGE_HEIGHT / PROVINCE_IMAGE_BLOCK_HEIGHT;
	public static int LETTER_X_NUMBER = LETTER_IMAGE_WIDTH / LETTER_IMAGE_BLOCK_WIDTH;
	public static int LETTER_Y_NUMBER = LETTER_IMAGE_HEIGHT / LETTER_IMAGE_BLOCK_HEIGHT;
	
	//BP input array length
	public static int PROVINCE_BP_INPUT_LENGTH = PROVINCE_X_NUMBER * PROVINCE_Y_NUMBER;
	public static int LETTER_BP_INPUT_LENGTH = LETTER_X_NUMBER * LETTER_Y_NUMBER;
	
	//Block white rate percentage.
	public static double BLOCK_WHITE_PERCENTAGE_PROVINCE = 0.3;
	public static double BLOCK_WHITE_PERCENTAGE_LETTER = 0.2;
	
	//The number of province and letter
	public static int PROVINCE_NUMBER = 37;
	public static int LETTER_NUMBER = 35;
	
	//Layer number.
	public static int PROVINCE_HIDDEN_LAYER_NUMBER = 50;
	public static int LETTER_HIDDEN_LAYER_NUMBER = 50;
	
	//Training time.
	public static int PROVINCE_TRAINING_TIME = 5000000;
	public static int LETTER_TRAINING_TIME = 5000000;
	
	//Weight file path.
	public static String PROVINCE_WEIGHT_FILE_PATH = "data/province_weight";
	public static String LETTER_WEIGHT_FILE_PATH = "data/letter_weight";
	
	//Noise number.
	public static int NOISE = 7;
	
	
	
}
