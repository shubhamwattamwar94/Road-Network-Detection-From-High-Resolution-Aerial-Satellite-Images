
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author shubham
 */
public class ConvertIntoGray {
    
    final public static BufferedImage convertColorspace(
		BufferedImage image,
		int newType) {
 
	try {
		BufferedImage raw_image = image;
		image =
				new BufferedImage(
						raw_image.getWidth(),
						raw_image.getHeight(),
						newType);
		ColorConvertOp xformOp = new ColorConvertOp(null);
		xformOp.filter(raw_image, image);
	} catch (Exception e) {
		LogWriter.writeLog("Exception " + e + " converting image");
 
	}
 
	return image;
}       
 
}
