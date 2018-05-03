
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author shubham
 */
public class ResizeImage {
    public BufferedImage resize(BufferedImage image,int w1,int h1)
    {
        int h=image.getHeight();
        int w=image.getWidth();
        BufferedImage image1=new BufferedImage(w1,h1,image.getType());
        Graphics2D g=image1.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(image,0,0,w1,h1,0,0,w,h,null);
        g.dispose();
                                         
        return image1;
        
    }
    
}
