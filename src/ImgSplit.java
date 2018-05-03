import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.awt.*;
import static java.awt.image.BufferedImage.TYPE_BYTE_GRAY;
import java.awt.image.ColorConvertOp;

public class ImgSplit {
    
   public BufferedImage GrayScale(File f) {
   int width = 0,height = 0;
   BufferedImage image;
      try {
         //File input = new File("digital_image_processing.jpg");
         image = ImageIO.read(f);
         width = image.getWidth();
         height = image.getHeight();
         
         for(int i=0; i<height; i++){
         
            for(int j=0; j<width; j++){
            
               Color c = new Color(image.getRGB(j, i));
               int red = (int)(c.getRed() * 0.299);
               int green = (int)(c.getGreen() * 0.587);
               int blue = (int)(c.getBlue() *0.114);
               Color newColor = new Color(red+green+blue,
               
               red+green+blue,red+green+blue);
               
               image.setRGB(j,i,newColor.getRGB());
            }
         }
         
         File ouptut = new File("grayscale.jpg");
         ImageIO.write(image, "jpg", ouptut);
        return image; 
      } 
      catch (Exception e) 
      {
          System.out.println("Exce"+e);
          return null;}
       
   }
   public void split(BufferedImage image) throws Exception
   {
       //FileInputStream fis = new FileInputStream(f);
        //BufferedImage image = ImageIO.read(fis); //reading the image file

        int rows = 6;//image.getWidth()/100; //You should decide the values for rows and cols variables
        int cols = 5;//image.getHeight()/100;
        int chunks = rows * cols;
        //System.out.println("widthccccc="+rows);
        int chunkWidth1 = image.getWidth();
        int chunkHeight1 = image.getHeight();
       // System.out.println("width="+chunkWidth1);
       // System.out.println("height="+chunkHeight1);
        int chunkWidth = image.getWidth() / cols; // determines the chunk width and height
        int chunkHeight = image.getHeight() / rows;
        //System.out.println("height="+chunkHeight);
        //System.out.println("width="+chunkWidth);
        int count = 0;
        BufferedImage imgs[] = new BufferedImage[chunks]; //Image array to hold image chunks
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
                //Initialize the image array with image chunks
                imgs[count] = new BufferedImage(chunkWidth, chunkHeight, image.getType());
                
                // draws the image chunk
                Graphics2D gr = imgs[count++].createGraphics();
                gr.drawImage(image, 0, 0, chunkWidth, chunkHeight, chunkWidth * y, chunkHeight * x, chunkWidth * y + chunkWidth, chunkHeight * x + chunkHeight, null);
                
                gr.dispose();
            }
        }
        System.out.println("Splitting done");
        //System.out.println("Splitting ="+imgs[5].getWidth());

        //writing mini images into image files
        for (int i = 0; i < imgs.length; i++) {
            
            ImageIO.write(imgs[i], "jpg", new File("C:\\Users\\shubham\\Documents\\NetBeansProjects\\Final\\Split\\img" + i + ".jpg"));
        }
        System.out.println("Mini images created");
   }
   
    public static void main(String[] args) throws IOException {
        try{
        BufferedImage out;
        ImgSplit img=new ImgSplit();
        File f = new File("C:\\Users\\shubham\\Documents\\NetBeansProjects\\thre.jpg"); // I have bear.jpg in my working directory
        //out=img.GrayScale(f);
        BufferedImage edg=ImageIO.read(f);
        ConvertIntoGray g=new ConvertIntoGray();
        out=g.convertColorspace(edg,TYPE_BYTE_GRAY);
       File f11=new File("C:\\Users\\shubham\\Documents\\NetBeansProjects\\thre.jpg");
           
        ImageIO.write(out, "jpg",f11);
        //img.split(out);
        }
        catch (Exception e)
        {
            System.out.println("Exception "+e);
        }
    }

    BufferedImage convertColorspace(BufferedImage image, int TYPE_BYTE_GRAY) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}