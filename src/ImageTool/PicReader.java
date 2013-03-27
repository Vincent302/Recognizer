

/**                          
* Project:           Recognizer                                
* Comments:          Read image from image file                                           
* JDK version used:  JDK1.6                             
* Namespace:         ImageTool                              
* Author：                              Vincent Li               
* Create Date：                2013-03-09
* Modified By：                Vincent Li                                     
* Modified Date:     2013-03-18                  
* Version:           V3.4                       
*/ 


package ImageTool;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Interface.IPicReader;
import Util.Const;

public class PicReader implements IPicReader {
	private String filePath;
	
	public PicReader(String filePath){
		this.filePath = filePath;
	}
	
	
	/**
	  * Get Image by the file path.
      * @param filePath
      * @return
	  */
	@Override
	public BufferedImage getImage(){
		File file  = new File(this.filePath);
        if (!file.exists()) {
             return null;
        }
        try {
            BufferedImage bufImg = ImageIO.read(file);
            if(bufImg != null){
            	return bufImg;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
		return null;
	}
	
	
	/**
	  * Get Extended Image by the black region.
      * @param bi
      * @return
	  */
	@Override
	public BufferedImage getExpendedImage(BufferedImage bi){
        int width = bi.getWidth();
		int height = bi.getHeight();
		
		int left1 = 0;
		int left2 = width;
		int top1 = 0;
		int top2 = height;
		
		//Get the pic region
L1:		for(int i=0;i<width;i++){
			for(int j=0;j<height;j++){
				int rgb = bi.getRGB(i, j) & 0xFFFFFF;
				if(rgb == 0x000000){
					left1 = i;
					break L1;
				}
			}
			
		}
L2:		for(int i=width-1;i>=0;i--){
			for(int j=0;j<height;j++){
				int rgb = bi.getRGB(i, j) & 0xFFFFFF;
				if(rgb == 0x000000){
					left2 = i;
					break L2;
				}
			}
			
		}
L3:		for(int j=0;j<height;j++){
	    	for(int i=0;i<width;i++){
	    		int rgb = bi.getRGB(i, j) & 0xFFFFFF;
				if(rgb == 0x000000){
					top1 = j;
					break L3;
				}
	    	}
        }
L4:		for(int j=height-1;j>=0;j--){
	    	for(int i=0;i<width;i++){
	    		int rgb = bi.getRGB(i, j) & 0xFFFFFF;
				if(rgb == 0x000000){
					top2 = j;
					break L4;
				}
	    	}
        }
        
        //Draw image.
        BufferedImage result = new BufferedImage(Const.PIC_WIDTH,Const.PIC_HEIGHT,BufferedImage.TYPE_3BYTE_BGR);
        result.getGraphics().drawImage(bi, 0, 0, Const.PIC_WIDTH, Const.PIC_HEIGHT, left1, top1, left2, top2, null);
//
//        try {
//			ImageIO.write(result, "jpg", new File("img/kkk.jpg"));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		return result;
	}

	
	/**
	  * Turn the pix into array.
      * @param bi
      * @return
	  */
	@Override
	public int[][] getImagePix(BufferedImage bi){
		int [][] pix = new int [Const.PIC_HEIGHT][Const.PIC_WIDTH];
		for(int i=0;i<Const.PIC_WIDTH;i++){
			for(int j=0;j<Const.PIC_HEIGHT;j++){
				int rgb = bi.getRGB(i, j) & 0xFFFFFF;
				if(rgb == 0x000000){
					pix[j][i] = 1;
				}else{
					pix[j][i] = 0;
				}
			}
		}
		return pix;
	}

	
	/**
	  * Get BP input parameter.
      * @param imagePix
      * @return
	  */
	@Override
	public double[] getBPImageInput(int[][] imagePix){
		double [] input = new double [Const.INPUT_NUMBER];
		int block_width = Const.INPUT_WIDTH;
		int block_height = Const.INPUT_HEIGHT;
		
		for(int i=0;i<block_height;i++){
L5:			for(int j=0;j<block_width;j++){
				for(int x=0;x<Const.STEP;x++){
					for(int y=0;y<Const.STEP;y++){
						int row = i * Const.STEP + x;
						int col = j * Const.STEP + y;
						if(imagePix[row][col] == 1){
							input[block_width * i + j] = 1;
							continue L5;
						}
					}
				}
				input[block_width * i + j] = 0;
			}
		}
		return input;
	}

	
	/**
	  * Run this class.
      * @return
	  */
	@Override
	public double[] doRun(){
		BufferedImage bi = this.getImage();
		BufferedImage bi2 = this.getExpendedImage(bi);
		int [][] imagePix = this.getImagePix(bi2);
		double [] input = this.getBPImageInput(imagePix);
		return input;
	}
}
