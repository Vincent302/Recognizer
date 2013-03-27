

/**                          
* Project:           Recognizer                                
* Comments:          BP network interface                                           
* JDK version used:  JDK1.6                             
* Namespace:         Interface                              
* Author：                              Vincent Li                
* Create Date：                2013-03-09
* Modified By：                Vincent Li                                      
* Modified Date:     2013-03-18                  
* Version:           V3.4                       
*/ 


package Interface;

public interface IBP {

	public void train(double[] input, double[] answer);
	public double[] check(double[] input_date);
	public void loadWeight(String file_path);
	public void storeWeight(String file_path);
}
