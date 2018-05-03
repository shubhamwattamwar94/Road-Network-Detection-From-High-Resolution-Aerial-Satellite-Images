
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import static javax.swing.Spring.height;
import static javax.swing.Spring.width;


public class Aggregation
{
  public void combineALLImages(String str,String screenNames, int screens) throws IOException, InterruptedException {
    System.out.println("screenNames --> D:\\screenshots\\screen   screens --> 0,1,2 to 10/..");
    int cols = 5;
    int rows = 6;
    
    int chunks = rows * cols ; 
    //String src="C:\\Users\\shubham\\Documents\\NetBeansProjects\\JavaApplication2\\Split";
     File[] imgFiles = new File[chunks];
    String files = "";
    for (int i = 0; i < chunks; i++) {
        files = str+screenNames + i + ".jpg";
        imgFiles[i] = new File(files);          
        System.out.println(screenNames + i + ".jpg"+"\t Screens : "+screens);    

    }
    String pat=str;
    System.out.println(str);    
    File f = new File(str+screenNames+"0.jpg");
    BufferedImage sample = ImageIO.read(f);
    //Initializing the final image
    BufferedImage finalImg = new BufferedImage(sample.getWidth() * cols, sample.getHeight() * rows, sample.getType());

    int index = 0;
    for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
            File f1 = new File(str+screenNames+index+".jpg");
            BufferedImage temp = ImageIO.read(f1);
            finalImg.createGraphics().drawImage(temp, sample.getWidth() * j, sample.getHeight() * i, null);
            System.out.println(screenNames + index + ".jpg");
            index++;
        }
    }
    File final_Image = new File(str+"FinalImage.jpg");
    //ImageIO.write(abc, "jpg",f21);
    ImageIO.write(finalImg, "jpg", final_Image);

}
}