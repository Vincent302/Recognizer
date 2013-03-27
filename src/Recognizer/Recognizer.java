

/**                          
* Project:           Recognizer                                
* Comments:          Recognize image file                                          
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
import java.util.List;

import BP.BP;
import ImageTool.ImageProcessor;
import Util.Global;


public class Recognizer {
	
	/**
	 * Recognize province by image.
	 * @param bp_input_array
	 * @return 
	 */
	public static String recogProvince(double [] bp_input_array){
		int province_number = Global.PROVINCE_NUMBER;
		BP bp_province = new BP(Global.PROVINCE_BP_INPUT_LENGTH, Global.PROVINCE_HIDDEN_LAYER_NUMBER, province_number);
		bp_province.loadWeight(Global.PROVINCE_WEIGHT_FILE_PATH);
		
		double [] result = bp_province.check(bp_input_array);
        int index = 0;
        double max_value = Double.MIN_VALUE;
        for(int r=0;r<province_number;r++){
        	if(max_value < result[r]){
        		max_value = result[r];
        		index = r;
        	}
        }
        switch(index){
	        case 0: return "警"; 
	        case 1: return "京";
	        case 2: return "津"; 
	        case 3: return "冀";
	        case 4: return "晋";
	        case 5: return "蒙";
	        case 6: return "辽";
	        case 7: return "吉";
	        case 8: return "黑";
	        case 9: return "沪";
	        case 10: return "苏";
	        case 11: return "浙";
	        case 12: return "皖";
	        case 13: return "闽";
	        case 14: return "竷";
	        case 15: return "鲁";
	        case 16: return "豫";
	        case 17: return "鄂";
	        case 18: return "湘";
	        case 19: return "粤";
	        case 20: return "楏";
	        case 21: return "琼";
	        case 22: return "渝";
	        case 23: return "川";
	        case 24: return "贵";
	        case 25: return "云";
	        case 26: return "藏";
	        case 27: return "陕";
	        case 28: return "甘";
	        case 29: return "青";
	        case 30: return "宁";
	        case 31: return "新";
	        case 32: return "港";
	        case 33: return "奥";
	        case 34: return "使";
	        case 35: return "领";
	        case 36: return "学";
	        default: return "";
        }
	}
	
	
	
	/**
	 * Recognize province by image.
	 * @param buffered_image
	 * @return 
	 */
	public static String recogProvince(BufferedImage buffered_image){
		double [] bp_input_array = ImageProcessor.getProvinceBPInputArray(buffered_image);
	    String result = recogProvince(bp_input_array);
		return result;
	}
	
	
	
	/**
	 * Recognize letter by image.
	 * @param bp_input_array
	 * @return 
	 */
	public static String recogLetter(double [] bp_input_array){
		int letter_number = Global.LETTER_NUMBER;
		BP bp_letter = new BP(Global.LETTER_BP_INPUT_LENGTH, Global.LETTER_HIDDEN_LAYER_NUMBER, letter_number);
		bp_letter.loadWeight(Global.LETTER_WEIGHT_FILE_PATH);
		
		double [] result = bp_letter.check(bp_input_array);
        int index = 0;
        double max_value = Double.MIN_VALUE;
        for(int r=0;r<letter_number;r++){
        	if(max_value < result[r]){
        		max_value = result[r];
        		index = r;
        	}
        }
        switch(index){
	        case 0: return "0"; 
	        case 1: return "1";
	        case 2: return "2"; 
	        case 3: return "3";
	        case 4: return "4";
	        case 5: return "5";
	        case 6: return "6";
	        case 7: return "7";
	        case 8: return "8";
	        case 9: return "9";
	        case 10: return "A";
	        case 11: return "B";
	        case 12: return "C"; 
	        case 13: return "D";
	        case 14: return "E";
	        case 15: return "F";
	        case 16: return "G";
	        case 17: return "H";
	        case 18: return "I";
	        case 19: return "J";
	        case 20: return "K";
	        case 21: return "L";
	        case 22: return "M";
	        case 23: return "N";
	        case 24: return "P";
	        case 25: return "Q";
	        case 26: return "R";
	        case 27: return "S";
	        case 28: return "T";
	        case 29: return "U";
	        case 30: return "V";
	        case 31: return "W";
	        case 32: return "X";
	        case 33: return "Y";
	        case 34: return "Z";
	        default: return "#";
        }
	}
	
	
	
	/**
	 * Recognize letter by image.
	 * @param buffered_image
	 * @return 
	 */
	public static String recogLetter(BufferedImage buffered_image){
		double [] bp_input_array = ImageProcessor.getLetterBPInputArray(buffered_image);
		String result = recogLetter(bp_input_array);
		return result;
	}
	
	
	
	/**
	 * Recognize all car number by image.
	 * @param bp_input_array_list
	 * @return 
	 */
	public static String recogAll(List<double[]> bp_input_array_list){
		String province = recogProvince(bp_input_array_list.get(0));
		String letter_1 = recogLetter(bp_input_array_list.get(1));
		String letter_2 = recogLetter(bp_input_array_list.get(2));
		String letter_3 = recogLetter(bp_input_array_list.get(3));
		String letter_4 = recogLetter(bp_input_array_list.get(4));
		String letter_5 = recogLetter(bp_input_array_list.get(5));
		String letter_6 = recogLetter(bp_input_array_list.get(6));
		String car_number = province + letter_1 + letter_2 + letter_3 + letter_4 + letter_5 + letter_6;
		
		return car_number;
	}
	
	
	
	/**
	 * Recognize all car number by image.
	 * @param buffered_image
	 * @return 
	 */
	public static String recogAll(BufferedImage buffered_image){
		ImageProcessor ipr = new ImageProcessor(buffered_image);
		List<double[]> bp_input_array_list = ipr.getBPInputArrayList();
		String result = recogAll(bp_input_array_list);
		return result;
	}
}
