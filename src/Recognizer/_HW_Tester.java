package Recognizer;

import java.util.ArrayList;
import java.util.List;

import BP.BP;
import ImageTool.PicReader;
import Util.Const;

public class _HW_Tester {
	public static void main(String [] args){
		
		BP bp = new BP(Const.INPUT_NUMBER, Const.PARAM_HIDDEN, Const.PARAM_OUTPUT);
		
		//Load image data from harddisk.
		List<List<double[]>> input_set_list =  new ArrayList<List<double[]>>();
		for(int i=0;i<10;i++){
			List<double[]> templist = new ArrayList<double[]>();
			for(int j=0;j<Const.PARAM_OUTPUT;j++){
				String filePath = "img/"+j+i+".jpg";
				PicReader pr = new PicReader(filePath);
				double [] input = pr.doRun();
				templist.add(input);
			}
			input_set_list.add(templist);
		}
		
		//Load weight data from harddisk.
		bp.loadWeight("data/weight");
		
		//Check.
		int count = 0;
		for(int i=0;i<10;i++){
			for(int j=0;j<Const.PARAM_OUTPUT;j++){
				double [] input = input_set_list.get(i).get(j);
				
				double [] result = bp.check(input);
				double max = -Integer.MIN_VALUE;  
		        int idx = -1;
		        for (int k=0; k<Const.PARAM_OUTPUT; k++) {
		            if (result[k] > max) {  
		                max = result[k];  
		                idx = k;  
		            }  
		        }
		        System.out.println("Result is "+ idx +" , " + result[idx]);
				if(idx == j){
					count++;
				}
			}
		}
		System.out.println();
		System.out.println("Correct rate is: " + count/(double)Const.PARAM_OUTPUT/(double)10);
	}
}
