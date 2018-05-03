
import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
/**
 *
 * @author Shubham
 */
public class Kmeans extends JFrame{
                BufferedImage image_temp;
		int [] lowerbounds = new int[3];
                static int g=0;
                int[] lb= new int[3];
                int[] ub= new int[3];
                int[] mean= new int[3];
    public Kmeans(BufferedImage image,int bins)
    {
       initialize(image,bins);
       calcbounds();
       processImage(image, bins);
     }
    public void initialize(BufferedImage image,int bins)
    {
                        image_temp = image;			
			mean[0]=173;
                       mean[1]=100;
                       mean[2]=112;

                      
    }
      public int createmean(BufferedImage image,int i,int bins)
    {
                                int pixelindex = 0;
				int sum = 0;
				int value = 0;
				for (int h = 0; h < image.getHeight(); h++)
                                {
					for (int w = 0; w < image.getWidth(); w++)
                                        {
						pixelindex+=1;
						if (pixelindex % bins == i) {
							Color rgb = new Color(image.getRGB(w, h));
							sum+= rgb.getRed();
							value+=1;
						}
					}
                                }
              return sum/value;
                      }
    public void calcbounds()
    {
                                for (int j = 0; j < 3; j++)
                                {
				int lb1 = calculatelb(j);
                                int ub1 = calculateub(j);
				lowerbounds[j] = lb1;
				lb[j]=lb1;
                                ub[j]=ub1;
				}

    }
    private int calculatelb(int index)
    {
			int cMean = mean[index];
			int currentBound = 0;
			for (int i = 0; i< 3; i++)
                        {
					if (cMean > mean[i])
                                        {
						currentBound = Math.max((cMean + mean[i])/2, currentBound);
					}
					else {
					}
		        }
			return currentBound;
   }
    private int calculateub(int index)
    {
			int cMean = mean[index];
			int currentBound = 255;
			for (int i = 0; i< 3; i++)
                        {
					if (cMean < mean[i])
                                        {
						currentBound = Math.min((cMean + mean[i])/2, currentBound);
					}
					else {
					}
		        }
			return currentBound;
   }
    private void processImage(BufferedImage image, int bins) {
			int delta = 255 / (bins-1);
                   for (int h = 0; h < image.getHeight(); h++){
				for (int w = 0; w < image.getWidth(); w++){
					Color rgb = new Color(image.getRGB(w, h));
					int grey = rgb.getRed();

					for (int i = 0; i<3; i++)
                                        {
						if (grey > lb[i] && grey < ub[i])
                                                {
                                                    	 g = i*delta;
                                                         image_temp.setRGB(w,h,(new Color(g, g, g)).getRGB());
                                                }
                                                    else
                                                        image_temp.setRGB(w,h,(new Color(g, g, g)).getRGB());

					}
				}
			}
		}
public BufferedImage returnimage()
    {
        return image_temp;
    }

}

