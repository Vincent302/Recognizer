package Recognizer;

import java.util.ArrayList;
import java.util.List;

import BP.BP;
import ImageTool.PicReader;
import Util.Const;

public class _HW_Trainer {
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
		
		//Train.
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
		
		//Store weight data in harddisk.
		bp.storeWeight("data/weight");
		
		//Finish.
		System.out.println("Training completed! ");
	}
}
