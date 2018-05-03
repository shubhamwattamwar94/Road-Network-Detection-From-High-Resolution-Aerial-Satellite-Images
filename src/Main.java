
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import static java.awt.image.BufferedImage.TYPE_BYTE_GRAY;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.image.*;
import static javafx.scene.paint.Color.color;
import static javafx.scene.paint.Color.color;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author shubham
 */
public class Main {
    public static BufferedImage imageToBufferedImage(final Image image)
   {
      final BufferedImage bufferedImage =
         new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
      final Graphics2D g2 = bufferedImage.createGraphics();
      g2.drawImage(image, 0, 0, null);
      g2.dispose();
      return bufferedImage;
    }
    public static Image makeColorTransparent(final BufferedImage im,  Color color)
   {
      final ImageFilter filter = new RGBImageFilter()
      {
         // the color we are looking for (white)... Alpha bits are set to opaque
         public int markerRGB = color.getRGB() | 0xFFFFFFFF;

         public final int filterRGB(final int x, final int y, final int rgb)
         {
            if ((rgb | 0xFF000000) == markerRGB)
            {
               // Mark the alpha bits as zero - transparent
               return 0x00FFFFFF & rgb;
            }
            else
            {
               // nothing to do
               return rgb;
            }
         }
      };

      final ImageProducer ip = new FilteredImageSource(im.getSource(), filter);
      return Toolkit.getDefaultToolkit().createImage(ip);
   }

    
    public void execute(BufferedImage image)
    {
        try {
            BufferedImage out;
            ConvertIntoGray cn=new ConvertIntoGray();
            ImgSplit img=new ImgSplit();
            out=cn.convertColorspace(image,TYPE_BYTE_GRAY);
             img.split(out);
             Kmeans km;
             File f;
             threshold th;
            CannyEdge edg=new CannyEdge();
            edg.setLowThreshold(0.5f);
           edg.setHighThreshold(0.5f);
             ConnectRoad c;
             Aggregation a2=new Aggregation();
             Graphics2D grp;
             BufferedImage split,homo,thre,dil,edge,clean;
             Edge ed=new Edge();
             for(int i=0;i<30;i++)
            {
            f=new File("C:\\Users\\shubham\\Documents\\NetBeansProjects\\Final\\Split\\img"+i+".jpg");
            split=ImageIO.read(f);
            km=new Kmeans(split, 3);
            homo=km.returnimage();
            f=new File("C:\\Users\\shubham\\Documents\\NetBeansProjects\\Final\\Homogenity\\homo"+i+".jpg");
            ImageIO.write(homo, "jpg",f);
                System.out.println("after homo"+i);
            f=new File("C:\\Users\\shubham\\Documents\\NetBeansProjects\\Final\\Threshold\\thre"+i+".jpg");
            th=new threshold(homo, 60);
            th.createNewImage(homo, 60);
            thre=th.getResultImage();
            ImageIO.write(thre, "jpg",f);
            Dilation d=new Dilation();
            dil=d.execute(thre);
                System.out.println("after dilation"+i);
            f=new File("C:\\Users\\shubham\\Documents\\NetBeansProjects\\Final\\Dilate\\Dil"+i+".jpg");
            ImageIO.write(dil, "jpg",f);
            edg.setSourceImage(dil);
            edg.process();
            edge=ed.run(thre);
                System.out.println("after edge"+i);
            f=new File("C:\\Users\\shubham\\Documents\\NetBeansProjects\\Final\\Edge\\edge"+i+".jpg");
            ImageIO.write(edge, "jpg",f);
            c=new ConnectRoad(edge);
            
            clean=c.getResult();
           /* int col=clean.getRGB(0, 0);
           Image imo=makeColorTransparent(clean,new Color(Col));
           BufferedImage k=imageToBufferedImage(imo);
            /* grp=clean.createGrap1hics();
            grp.setColor(Color.red);
            grp.setBackground(Color.WHITE);
            grp.dispose();*/
                System.out.println("after clean"+i);
            f=new File("C:\\Users\\shubham\\Documents\\NetBeansProjects\\Final\\Clean\\clean"+i+".jpg");
            ImageIO.write(clean, "jpg",f);
            }
        a2.combineALLImages("C:\\Users\\shubham\\Documents\\NetBeansProjects\\Final\\Split\\","img", TYPE_BYTE_GRAY);
        a2.combineALLImages("C:\\Users\\shubham\\Documents\\NetBeansProjects\\Final\\Edge\\","edge", TYPE_BYTE_GRAY);
        a2.combineALLImages("C:\\Users\\shubham\\Documents\\NetBeansProjects\\Final\\Dilate\\","Dil", TYPE_BYTE_GRAY);
        a2.combineALLImages("C:\\Users\\shubham\\Documents\\NetBeansProjects\\Final\\Homogenity\\","homo", TYPE_BYTE_GRAY);
        a2.combineALLImages("C:\\Users\\shubham\\Documents\\NetBeansProjects\\Final\\Threshold\\","thre", TYPE_BYTE_GRAY);
        a2.combineALLImages("C:\\Users\\shubham\\Documents\\NetBeansProjects\\Final\\Clean\\","clean", TYPE_BYTE_GRAY);
        
        }
        catch (Exception e)
        {
            System.out.println("Exception "+e);
        }
           
    }
            
    
}
