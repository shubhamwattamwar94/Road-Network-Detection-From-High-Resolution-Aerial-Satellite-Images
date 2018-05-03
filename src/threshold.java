import java.awt.Color;
import java.awt.image.BufferedImage;
/**
 *
 * @author Shubham
 */
public class threshold {
BufferedImage image_temp;

public threshold(BufferedImage image, int threshold) {
image_temp = image;
createNewImage(image, threshold);
}
void createNewImage(BufferedImage image, int threshold) {
    for (int h = 0; h < image.getHeight(); h++) {
        for (int w = 0; w < image.getWidth(); w++) {
        Color rgb = new Color(image.getRGB(w, h));
        int grey = rgb.getRed();
        if (!(grey < threshold)) {
            grey = 0;
        }
         else {
          grey = 255;
         }
        image_temp.setRGB(w, h, (new Color(grey, grey, grey)).getRGB());
       }
   }
}
 public BufferedImage getResultImage() {
  return image_temp;
 }
}
