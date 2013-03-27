

/**                          
* Project:           Recognizer                                
* Comments:          Hand writer tester.                                         
* JDK version used:  JDK1.6                             
* Namespace:         Util                              
* Author：                              Hun                
* Create Date：                2013-03-09
* Modified By：                Hun                                      
* Modified Date:     2013-03-18                  
* Version:           V3.4                       
*/ 


package Test;

import java.util.ArrayList;
import java.util.List;

import BP.BP;
import ImageTool.PicReader;
import Util.Const;

public class HW_BPTest {
	public static void main(String [] args){
		BP bp = new BP(Const.INPUT_NUMBER, Const.PARAM_HIDDEN, Const.PARAM_OUTPUT);
		
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
		
		
		
		
		for(int i=0;i<Const.TRAINING_TIME;i++){
			for(int j=0;j<10;j++){
				List<double[]> input_list = input_set_list.get(j);
				for(int k=0;k<Const.PARAM_OUTPUT;k++){
					double [] input = input_list.get(k);
					double [] result = new double [Const.PARAM_OUTPUT];
					for(int m=0;m<Const.PARAM_OUTPUT;m++){
						result[m] = 0;
					}
					result[k] = 1;
					bp.train(input, result);
				}
			}
			System.out.println("Trained: "+i);
		}
		System.out.println("Training completed! ");
		
		//Test
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
