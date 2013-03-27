

/**                          
* Project:           Recognizer                                
* Comments:          Image process turn image into binary data                                           
* JDK version used:  JDK1.6                             
* Namespace:         ImageTool                              
* Author：                              Hun                
* Create Date：                2013-03-09
* Modified By：                Hun                                      
* Modified Date:     2013-03-18                  
* Version:           V3.4                       
*/ 


package ImageTool;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import Interface.IImageProcessor;
import Util.Global;

public class ImageProcessor implements IImageProcessor{
	
	private BufferedImage buffered_image;
	private BufferedImage [] buffered_image_array;
	
	/**
	 * Constructor
	 * Initialize image file with default file.
	 */
	public ImageProcessor(){
		this("test/default.jpg");
	}
	
	
	/**
	 * Constructor
	 * @param file_path
	 */
	public ImageProcessor(String file_path){
		File file  = new File(file_path);
        if (!file.exists()){
             System.out.println("File does not exits!");
        }
        try {
            BufferedImage temp = ImageIO.read(file);
            if(temp != null){
            	this.buffered_image = temp;
            }
        } catch (IOException e){
            e.printStackTrace();
        }
	}
	
	
	/**
	 * Constructor
	 * @param buffered_image
	 */
	public ImageProcessor(BufferedImage buffered_image){
		this.buffered_image = buffered_image;
	}
	
	
	/**
	 * Turn image into binary data image.
	 */
	public void binaryProcess(){
		int width = Global.CARD_WIDTH;
		int height = Global.CARD_HEIGHT;
		
		for(int i=0;i<width;i++){
			for(int j=0;j<height;j++){
				int rgb = buffered_image.getRGB(i, j);
				int color_S = rgb & 0xFF000000;
				int color_R = ((rgb & 0xFF0000) >> 16) & 0xFF;
				int color_G = ((rgb & 0x00FF00) >> 8) & 0xFF;
				int color_B = rgb & 0xFF;
				int color_SUM = color_R + color_G + color_B;
				
				int binary_adjustor = Global.BINARY_ADJUSTOR;
				if(color_SUM > binary_adjustor){
					int sRGB = color_S;
					buffered_image.setRGB(i, j, sRGB);
				}else{
					int sRGB = color_S + 0xFFFFFF;
					buffered_image.setRGB(i, j, sRGB);
				}
			}
		}
		
		//Test binary transformation.
		
		
		try {
			ImageIO.write(buffered_image, "png", new File("temp/all.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	
	/**
	 * Cut image and turn them into buffered image array.
	 */
	private void toBufferedImgageArray(){
		this.buffered_image_array = new BufferedImage[7];
		
		//Iniatialize image array.
		buffered_image_array[0] = new BufferedImage(Global.PROVINCE_IMAGE_WIDTH, Global.PROVINCE_IMAGE_HEIGHT, BufferedImage.TYPE_3BYTE_BGR);
		for(int i=1;i<7;i++){
			buffered_image_array[i] = new BufferedImage(Global.LETTER_IMAGE_WIDTH, Global.LETTER_IMAGE_HEIGHT, BufferedImage.TYPE_3BYTE_BGR);
		}
		
		//Draw letter image into image array.
		buffered_image_array[0].getGraphics().drawImage(
				buffered_image, 0, 0, Global.PROVINCE_IMAGE_WIDTH, Global.PROVINCE_IMAGE_HEIGHT, 
				Global.PROVINCE_LEFT, Global.PROVINCE_TOP, 
				Global.PROVINCE_LEFT + Global.PREV_PROVINCE_IMAGE_WIDTH - 1, 
				Global.PROVINCE_TOP + Global.PREV_PROVINCE_IMAGE_HEIGHT - 1, null);
		buffered_image_array[1].getGraphics().drawImage(
				buffered_image, 0, 0, Global.LETTER_IMAGE_WIDTH, Global.LETTER_IMAGE_HEIGHT, 
				Global.LETTER_1_LEFT, Global.LETTER_1_TOP, 
				Global.LETTER_1_LEFT + Global.PREV_LETTER_IMAGE_WIDTH - 1, 
				Global.LETTER_1_TOP + Global.PREV_LETTER_IMAGE_HEIGHT - 1, null);
		buffered_image_array[2].getGraphics().drawImage(
				buffered_image, 0, 0, Global.LETTER_IMAGE_WIDTH, Global.LETTER_IMAGE_HEIGHT, 
				Global.LETTER_2_LEFT, Global.LETTER_2_TOP, 
				Global.LETTER_2_LEFT + Global.PREV_LETTER_IMAGE_WIDTH - 1, 
				Global.LETTER_2_TOP + Global.PREV_LETTER_IMAGE_HEIGHT - 1, null);
		buffered_image_array[3].getGraphics().drawImage(
				buffered_image, 0, 0, Global.LETTER_IMAGE_WIDTH, Global.LETTER_IMAGE_HEIGHT , 
				Global.LETTER_3_LEFT, Global.LETTER_3_TOP, 
				Global.LETTER_3_LEFT + Global.PREV_LETTER_IMAGE_WIDTH - 1, 
				Global.LETTER_3_TOP + Global.PREV_LETTER_IMAGE_HEIGHT - 1, null);
		buffered_image_array[4].getGraphics().drawImage(
				buffered_image, 0, 0, Global.LETTER_IMAGE_WIDTH, Global.LETTER_IMAGE_HEIGHT, 
				Global.LETTER_4_LEFT, Global.LETTER_4_TOP, 
				Global.LETTER_4_LEFT + Global.PREV_LETTER_IMAGE_WIDTH - 1, 
				Global.LETTER_4_TOP + Global.PREV_LETTER_IMAGE_HEIGHT - 1, null);
		buffered_image_array[5].getGraphics().drawImage(
				buffered_image, 0, 0, Global.LETTER_IMAGE_WIDTH, Global.LETTER_IMAGE_HEIGHT, 
				Global.LETTER_5_LEFT, Global.LETTER_5_TOP, 
				Global.LETTER_5_LEFT + Global.PREV_LETTER_IMAGE_WIDTH - 1, 
				Global.LETTER_5_TOP + Global.PREV_LETTER_IMAGE_HEIGHT - 1, null);
		buffered_image_array[6].getGraphics().drawImage(
				buffered_image, 0, 0, Global.LETTER_IMAGE_WIDTH, Global.LETTER_IMAGE_HEIGHT, 
				Global.LETTER_6_LEFT, Global.LETTER_6_TOP, 
				Global.LETTER_6_LEFT + Global.PREV_LETTER_IMAGE_WIDTH - 1, 
				Global.LETTER_6_TOP + Global.PREV_LETTER_IMAGE_HEIGHT - 1, null);
		
		
		//Test image binary.
		try {
			for(int i=0;i<7;i++){
				ImageIO.write(buffered_image_array[i], "png", new File("temp/"+ i + ".png"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * Turn the buffered image array into BP input array list.
	 */
	private void doPrepare(){
		this.binaryProcess();
		this.toBufferedImgageArray();
	}

	
	/**
	 * Turn the buffered image array into BP input array list.
	 * @return
	 */
	@Override
	public List<double[]> getBPInputArrayList(){
		this.doPrepare();
		
		List<double[]> BP_input_array_list = new ArrayList<double[]>();
		BufferedImage temp_buffered_image;
		
		//Turn province image into BP input array.
		temp_buffered_image = buffered_image_array[0];
		BP_input_array_list.add(getProvinceBPInputArray(temp_buffered_image));
		
		//Turn letter image into BP input array.
		for(int letter=1;letter<7;letter++){
			temp_buffered_image = buffered_image_array[letter];
			BP_input_array_list.add(getLetterBPInputArray(temp_buffered_image));
		}
		return BP_input_array_list;
	}


	/**
	 * Turn the province buffered image array into BP input array.
	 * @param buffered_image
	 * @return
	 */
	public static double[] getProvinceBPInputArray(BufferedImage buffered_image){
		double [] province_input_array = new double[Global.PROVINCE_BP_INPUT_LENGTH];
		for(int i=0;i<Global.PROVINCE_X_NUMBER;i++){
			for(int j=0;j<Global.PROVINCE_Y_NUMBER;j++){
				int black_count = 0;
				for(int x=0;x<Global.PROVINCE_IMAGE_BLOCK_WIDTH;x++){
					for(int y=0;y<Global.PROVINCE_IMAGE_BLOCK_HEIGHT;y++){
						int location_x = i * Global.PROVINCE_IMAGE_BLOCK_WIDTH + x;
						int location_y = j * Global.PROVINCE_IMAGE_BLOCK_HEIGHT + y;
						int rgb = buffered_image.getRGB(location_x, location_y) & 0xFFFFFF;
						if(rgb == 0x000000){
							black_count++;
						}
					}
				}
				double black_percentage = (double)black_count / (double)(Global.PROVINCE_IMAGE_BLOCK_WIDTH * Global.PROVINCE_IMAGE_BLOCK_HEIGHT);
				if(black_percentage > Global.BLOCK_WHITE_PERCENTAGE_PROVINCE){
					province_input_array[j * Global.PROVINCE_X_NUMBER + i] = 1;
				}else{
					province_input_array[j * Global.PROVINCE_X_NUMBER + i] = 0;
				}
			}
		}
		return province_input_array;
	}

	
	/**
	 * Turn the letter buffered image array into BP input array.
	 * @param buffered_image
	 * @return
	 */
	public static double[] getLetterBPInputArray(BufferedImage buffered_image){
		double [] letter_input_array = new double[Global.LETTER_BP_INPUT_LENGTH];
		for(int i=0;i<Global.LETTER_X_NUMBER;i++){
			for(int j=0;j<Global.LETTER_Y_NUMBER;j++){
				int black_count = 0;
				for(int x=0;x<Global.LETTER_IMAGE_BLOCK_WIDTH;x++){
					for(int y=0;y<Global.LETTER_IMAGE_BLOCK_HEIGHT;y++){
						int location_x = i * Global.LETTER_IMAGE_BLOCK_WIDTH + x;
						int location_y = j * Global.LETTER_IMAGE_BLOCK_HEIGHT + y;
						int rgb = buffered_image.getRGB(location_x, location_y) & 0xFFFFFF;
						if(rgb == 0x000000){
							black_count++;
						}
					}
				}
				double black_percentage = (double)black_count / (double)(Global.LETTER_IMAGE_BLOCK_WIDTH * Global.LETTER_IMAGE_BLOCK_HEIGHT);
				if(black_percentage > Global.BLOCK_WHITE_PERCENTAGE_LETTER){
					letter_input_array[j * Global.LETTER_X_NUMBER + i] = 1;
				}else{
					letter_input_array[j * Global.LETTER_X_NUMBER + i] = 0;
				}
			}
		}
		return letter_input_array;
	}
}
