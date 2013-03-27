

/**                          
* Project:           Recognizer                                
* Comments:          BP natural network                                           
* JDK version used:  JDK1.6                             
* Namespace:         BP                              
* Author：                              Vincent Li             
* Create Date：                2013-03-09
* Modified By：                Vincent Li                                     
* Modified Date:     2013-03-18                  
* Version:           V3.4                       
*/ 


package BP;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;  

import Interface.IBP;
import Util.Const;
  
public class BP implements IBP{
	
    private double[] input; 
    private double[] hidden_layer;  
    private double[] output;  
    private double[] answer;  

    private double[][] input_to_hidden_weight;  
    private double[][] hidden_to_output_weight;  
    private double[][] input_to_hidden_update_weight;  
    private double[][] hidden_to_output_update_weight;  
    
    private final double[] hidden_delta;  
    private double[] output_delta;  
  

    private double learning_rate; 
    private double momentum;  
    
    public double output_error = 0;  
    public double hidden_error = 0;  
  
    private  Random random;  
  
    
    /** 
     * Constructor. 
     *  
     * @param input_size 
     * @param hidden_size 
     * @param output_size 
     * @param learning_rate 
     * @param momentum 
     */  
    public BP(int input_size, int hidden_size, int output_size, double learning_rate, double momentum){  
  
        input = new double[input_size + 1];  
        hidden_layer = new double[hidden_size + 1];  
        output = new double[output_size + 1];  
        answer = new double[output_size + 1];  
  
        input_to_hidden_weight = new double[input_size + 1][hidden_size + 1];  
        hidden_to_output_weight = new double[hidden_size + 1][output_size + 1];  
        input_to_hidden_update_weight = new double[input_size + 1][hidden_size + 1];  
        hidden_to_output_update_weight = new double[hidden_size + 1][output_size + 1];  
        
        hidden_delta = new double[hidden_size + 1];  
        output_delta = new double[output_size + 1];  
  
        random = new Random(19881211);  
        initWeight(input_to_hidden_weight);  
        initWeight(hidden_to_output_weight);  
  
        this.learning_rate = learning_rate;  
        this.momentum = momentum;  
    }  
  

    /** 
     * Constructor init learning rate and monument by default value. 
     *  
     * @param input_size 
     * @param hidden_size 
     * @param output_size 
     */  
    public BP(int input_size, int hidden_size, int output_size){
        this(input_size, hidden_size, output_size, Const.LEARNING_RATE, Const.MONUMENT);  
    }  
    
    
    /** 
     * Initiate weight array by random double value. 
     *  
     * @param weight
     */ 
    private void initWeight(double[][] weight){  
    	int length_x = weight.length;
    	int length_y = weight[0].length;
        for(int i=0;i<length_x;i++){
        	for (int j=0;j<length_y;j++){ 
                double real = random.nextDouble(); 
                weight[i][j] = random.nextDouble() > 0.5 ? real : -real;  
            }
        }
    }
  
  
    /** 
     * Training method. 
     *  
     * @param input 
     * @param answer 
     */ 
	@Override 
    public void train(double[] input, double[] answer){
        loadInput(input);  
        loadAnswer(answer);  
        calculateWeight();  
        calculateError();  
        adjustAll();  
    }  
  
    
    /** 
     * Checking method. 
     *  
     * @param input_date 
     * 
     * @return 
     */  
	@Override
    public double[] check(double[] input_date){
        if (input_date.length != input.length - 1){
            throw new IllegalArgumentException("Wrong input.");  
        }  
        System.arraycopy(input_date, 0, input, 1, input_date.length);  
        calculateWeight();  
        return getOutput();  
    }
    
    
    /** 
     * Load weight data from harddisk. 
     */
	@Override
    public void loadWeight(String file_path){
    	try {
    		File file = new File(file_path);
			FileInputStream fis = new FileInputStream(file);
			DataInputStream dis = new DataInputStream(fis);
			
			int hidden_length = hidden_layer.length;
			for(int i=0;i<hidden_length;i++){
				hidden_layer[i] = dis.readDouble();
			}
			
			int input_to_hidden_weight_length_x = input_to_hidden_weight.length;
			int input_to_hidden_weight_length_y = input_to_hidden_weight[0].length;
			for(int i=0;i<input_to_hidden_weight_length_x;i++){
				for(int j=0;j<input_to_hidden_weight_length_y;j++){
					input_to_hidden_weight[i][j] = dis.readDouble();
				}
			}
			
			int hidden_to_output_weight_length_x = hidden_to_output_weight.length;
			int hidden_to_output_weight_length_y = hidden_to_output_weight[0].length;
			for(int i=0;i<hidden_to_output_weight_length_x;i++){
				for(int j=0;j<hidden_to_output_weight_length_y;j++){
					hidden_to_output_weight[i][j] = dis.readDouble();
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    
    /** 
     * Store weight data in hard disk. 
     */
	@Override
    public void storeWeight(String file_path){
    	try {
    		File file = new File(file_path);
			FileOutputStream fos = new FileOutputStream(file);
			DataOutputStream dos = new DataOutputStream(fos);
			
			int hidden_length = hidden_layer.length;
			for(int i=0;i<hidden_length;i++){
				dos.writeDouble(hidden_layer[i]);
			}
			
			int input_to_hidden_weight_length_x = input_to_hidden_weight.length;
			int input_to_hidden_weight_length_y = input_to_hidden_weight[0].length;
			for(int i=0;i<input_to_hidden_weight_length_x;i++){
				for(int j=0;j<input_to_hidden_weight_length_y;j++){
					dos.writeDouble(input_to_hidden_weight[i][j]);
				}
			}
			
			int hidden_to_output_weight_length_x = hidden_to_output_weight.length;
			int hidden_to_output_weight_length_y = hidden_to_output_weight[0].length;
			for(int i=0;i<hidden_to_output_weight_length_x;i++){
				for(int j=0;j<hidden_to_output_weight_length_y;j++){
					dos.writeDouble(hidden_to_output_weight[i][j]);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    
    /** 
     * Return the output value. 
     *  
     * @return 
     */  
    private double[] getOutput(){
        int len = output.length; 
        double[] temp = new double[len - 1];  
        for (int i=1;i<len;i++){
        	temp[i - 1] = output[i];
        }  
        return temp;  
    }  
  
    
    /** 
     * Load answer. 
     *  
     * @param answer_input 
     */  
    private void loadAnswer(double[] answer_input){
        if (answer_input.length != answer.length - 1){  
            throw new IllegalArgumentException("Wrong input.");  
        }  
        System.arraycopy(answer_input, 0, answer, 1, answer_input.length);  
    }  
  
    
    /** 
     * Load input. 
     *  
     * @param input_data 
     */  
    private void loadInput(double[] input_data) {
        if (input_data.length != input.length - 1) {
            throw new IllegalArgumentException("Wrong input.");  
        }  
        System.arraycopy(input_data, 0, input, 1, input_data.length);  
    }  
  
    
    /** 
     * Calculate weight function. 
     *  
     * @param first 
     * @param second 
     * @param weight 
     */  
    private void calculate(double[] first, double[] second, double[][] weight){
        first[0] = 1;
        int first_length = first.length;
        int second_length = second.length;
        
        for (int j=1;j<second_length;++j){  
            double sum = 0;  
            for (int i = 0;i<first_length;++i){
            	sum += weight[i][j] * first[i];
            }
            second[j] = sigmoid(sum);
        }  
    }
    
  
    /** 
     * Calculate three layers. 
     */  
    private void calculateWeight(){  
        calculate(input, hidden_layer, input_to_hidden_weight);  
        calculate(hidden_layer, output, hidden_to_output_weight);  
    }  
  
    
    /** 
     * Calculate output error. 
     */  
    private void calculateOutErr(){  
        double error_sum = 0;
        int length = output_delta.length;
        for (int i=1;i<length;++i) {  
            double o = output[i];  
            output_delta[i] = o * (1 - o) * (answer[i] - o);  
            error_sum += Math.abs(output_delta[i]);  
        }  
        output_error = error_sum;  
    }  
 
    
    /** 
     * Calculate hidden errors. 
     */  
    private void calculateHidErr(){ 
        double error_sum = 0;
        int hidden_delta_length = hidden_delta.length;
        
        for (int j=1;j<hidden_delta_length;++j){  
            double o = hidden_layer[j];  
            double sum = 0;
            int output_delta_length = output_delta.length;
            for (int k=1;k<output_delta_length;++k){
            	sum += hidden_to_output_weight[j][k] * output_delta[k]; 
            }
            hidden_delta[j] = o * (1 - o) * sum;  
            error_sum += Math.abs(hidden_delta[j]);  
        }  
        hidden_error = error_sum;
    }  
  
    
    /** 
     * Calculate all layers' error. 
     */  
    private void calculateError(){  
        calculateOutErr();  
        calculateHidErr();  
    }  

    
    /** 
     * Adjust weight. 
     *  
     * @param delta 
     * @param layer 
     * @param weight 
     * @param update_weight 
     */  
    private void adjustWeight(double[] delta, double[] layer, double[][] weight, double[][] update_weight){
        layer[0] = 1;
        int delta_length = delta.length;
        int layer_length = layer.length;
        for (int i=1;i<delta_length;++i){
            for (int j=0;j<layer_length;++j){  
                double value = momentum * update_weight[j][i] + learning_rate * delta[i] * layer[j];  
                weight[j][i] += value;  
                update_weight[j][i] = value;  
            }
        }
    }
  
    
    /** 
     * Adjust all weight matrices. 
     */  
    private void adjustAll(){
        adjustWeight(output_delta, hidden_layer, hidden_to_output_weight, hidden_to_output_update_weight);  
        adjustWeight(hidden_delta, input, input_to_hidden_weight, input_to_hidden_update_weight);  
    }  
  
    
    /** 
     * Sigmoid. 
     *  
     * @param value 
     * @return 
     */  
    private double sigmoid(double value){  
        return 1d / (1d + Math.exp(-value));  
    }  
} 