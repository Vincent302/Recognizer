package Other;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Util.Global;

public class ImageTypeConvert {
	public static void main(String [] args){
		try {
			for(int p=0;p<35;p++){
				BufferedImage buffered_image = ImageIO.read(new File("car_img/letter/" + p + ".png"));
				for(int i=0;i<24;i++){
					for(int j=0;j<48;j++){
						int rgb = buffered_image.getRGB(i, j);
						int color_S = rgb & 0xFF000000;
						int color_R = ((rgb & 0xFF0000) >> 16) & 0xFF;
						int color_G = ((rgb & 0x00FF00) >> 8) & 0xFF;
						int color_B = rgb & 0xFF;
						int color_SUM = color_R + color_G + color_B;
						
						int binary_adjustor = Global.BINARY_ADJUSTOR;
						if(color_SUM > binary_adjustor){
							int sRGB = color_S + 0xFFFFFF;
							buffered_image.setRGB(i, j, sRGB);
						}else{
							int sRGB = color_S;
							buffered_image.setRGB(i, j, sRGB);
						}
					}
				}
				ImageIO.write(buffered_image, "png", new File("car_img/letter_new/" + p + ".png"));
			}
			for(int p=0;p<37;p++){
				BufferedImage buffered_image = ImageIO.read(new File("car_img/province/" + p + ".png"));
				for(int i=0;i<24;i++){
					for(int j=0;j<48;j++){
						int rgb = buffered_image.getRGB(i, j);
						int color_S = rgb & 0xFF000000;
						int color_R = ((rgb & 0xFF0000) >> 16) & 0xFF;
						int color_G = ((rgb & 0x00FF00) >> 8) & 0xFF;
						int color_B = rgb & 0xFF;
						int color_SUM = color_R + color_G + color_B;
						
						int binary_adjustor = Global.BINARY_ADJUSTOR;
						if(color_SUM > binary_adjustor){
							int sRGB = color_S + 0xFFFFFF;
							buffered_image.setRGB(i, j, sRGB);
						}else{
							int sRGB = color_S;
							buffered_image.setRGB(i, j, sRGB);
						}
					}
				}
				ImageIO.write(buffered_image, "png", new File("car_img/province_new/" + p + ".png"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
