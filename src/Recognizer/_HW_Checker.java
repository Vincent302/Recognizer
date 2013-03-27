package Recognizer;

import BP.BP;
import ImageTool.PicReader;
import Util.Const;

public class _HW_Checker {
	public static void main(String [] args){
		
		BP bp = new BP(Const.INPUT_NUMBER, Const.PARAM_HIDDEN, Const.PARAM_OUTPUT);
		
		//Load weight data from harddisk.
		bp.loadWeight("data/weight");
		
		//Read image data.
		String filePath = "check/check.jpg";
		PicReader pr = new PicReader(filePath);
		double [] input = pr.doRun();
		
		//Get result.
		double [] result = bp.check(input);
		double max = -Integer.MIN_VALUE;  
        int idx = -1;
        for (int k=0; k<Const.PARAM_OUTPUT; k++) {
            if (result[k] > max) {  
                max = result[k];  
                idx = k;  
            }  
        }
        
        int char_number;
        if(idx >= 26){
        	char_number = idx + 71;
        }else{
        	char_number = idx + 65;
        }
        
        char c = (char)char_number;
        
        System.out.println(idx);
        System.out.println("The result is: '" + c + "'");
	}
}
