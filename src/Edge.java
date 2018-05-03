

/**
 *
 * @author Shubham
 */
import java.awt.image.BufferedImage;
import java.awt.Color;
import javax.swing.JFrame;
import java.awt.BorderLayout;

class sobelf extends JFrame
{
    JFrame w;
    PicturePanel picpanel;
    
     public sobelf()
        {
                setSize(800, 600);
		setTitle("K-Means:");

                setLayout(new BorderLayout());
                picpanel = new PicturePanel();
                this.getContentPane().add(picpanel, BorderLayout.CENTER);
	/*	addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});*/
	}
}
public class Edge {

    int[][] pix_array1;
    int[][] pix_array2;
     BufferedImage run(BufferedImage image)
        {
            int w=image.getWidth();
            int h=image.getHeight();
            int sum1=0,sum2=0,sum=0;
            int[][] source=new int[w][h];
            int[][] dest=new int[w][h];
            int x[][]={{-1,0,1},
                       {-2,0,2},
                       {-1,0,1}};
            int y[][]={{-1,-2,-1},
                       { 0, 0, 0},
                       { 1, 2, 1}};
            BufferedImage temp=new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
             for(int i=0;i<w;i++)
                           {
                             System.out.print("\n");
                                for(int j=0;j<h;j++)
                                  {
                                        Color color = new Color(image.getRGB(i, j));
                                        source[i][j]=color.getRed();
                                         System.out.print(" "+source[i][j]);
                                        dest[i][j]=0;
                                  }
                           }
            for(int i=1;i<w-1;i++)
                           {
                           
                                for(int j=1;j<h-1;j++)
                                  {
                                       sum1=(source[i-1][j-1]*x[0][0]) + (source[i-1][j]*x[0][1]) + (source[i-1][j+1]*x[0][2]) +
                                            (source[i][j-1]  *x[1][0]) + (source[i][j]  *x[1][1]) + (source[i][j+1]  *x[1][2]) +
                                            (source[i+1][j-1]*x[2][0]) + (source[i+1][j]*x[2][1]) + (source[i+1][j+1]*x[2][2]);

                                       sum2=(source[i-1][j-1]*y[0][0]) + (source[i-1][j]*y[0][1]) + (source[i-1][j+1]*y[0][2]) +
                                            (source[i][j-1]  *y[1][0]) + (source[i][j]  *y[1][1]) + (source[i][j+1]  *y[1][2]) +
                                            (source[i+1][j-1]*y[2][0]) + (source[i+1][j]*y[2][1]) + (source[i+1][j+1]*y[2][2]);

                                       sum = (int)Math.sqrt(sum1*sum1 + sum2*sum2);
                                      
                                       if(sum>=255)
                                           dest[i][j]=255;
                                       else
                                           dest[i][j]=0;
                                  }
                           }
            pix_array1=new int[w][h];
                      for(int i=0;i<w;i++)
                        {
                            for(int j=0;j<h;j++)
                            {
                                int g=dest[i][j];
                               pix_array1[i][j]=dest[i][j];
                               temp.setRGB(i,j,(new Color(g, g, g)).getRGB());
                            }
                       }

            return temp;
            
        }
     BufferedImage prewitt(BufferedImage image)
        {
            int w=image.getWidth();
            int h=image.getHeight();
            int sum1=0,sum2=0,sum=0;
            int[][] source=new int[w][h];
            int[][] dest=new int[w][h];
            int x[][]={{-1,0,1},
                       {-1,0,1},
                       {-1,0,1}};
            int y[][]={{-1,-1,-1},
                       { 0, 0, 0},
                       { 1, 1, 1}};
            BufferedImage temp=new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
             for(int i=0;i<w;i++)
                           {
                             System.out.print("\n");
                                for(int j=0;j<h;j++)
                                  {
                                        Color color = new Color(image.getRGB(i, j));
                                        source[i][j]=color.getRed();
                                         System.out.print(" "+source[i][j]);
                                        dest[i][j]=0;
                                  }
                           }
            for(int i=1;i<w-1;i++)
                           {
                            for(int j=1;j<h-1;j++)
                                  {
                                       sum1=(source[i-1][j-1]*x[0][0]) + (source[i-1][j]*x[0][1]) + (source[i-1][j+1]*x[0][2]) +
                                            (source[i][j-1]  *x[1][0]) + (source[i][j]  *x[1][1]) + (source[i][j+1]  *x[1][2]) +
                                            (source[i+1][j-1]*x[2][0]) + (source[i+1][j]*x[2][1]) + (source[i+1][j+1]*x[2][2]);

                                       sum2=(source[i-1][j-1]*y[0][0]) + (source[i-1][j]*y[0][1]) + (source[i-1][j+1]*y[0][2]) +
                                            (source[i][j-1]  *y[1][0]) + (source[i][j]  *y[1][1]) + (source[i][j+1]  *y[1][2]) +
                                            (source[i+1][j-1]*y[2][0]) + (source[i+1][j]*y[2][1]) + (source[i+1][j+1]*y[2][2]);

                                       sum = (int)Math.sqrt(sum1*sum1 + sum2*sum2);
                                       if(sum>=255)
                                           dest[i][j]=255;
                                       else
                                           dest[i][j]=0;
                                  }
                           }
             pix_array2=new int[w][h];
                      for(int i=0;i<w;i++)
                        {
                            for(int j=0;j<h;j++)
                            {
                                int g=dest[i][j];
                                pix_array2[i][j]=dest[i][j];
                               temp.setRGB(i,j,(new Color(g, g, g)).getRGB());
                            }
                       }

            return temp;

        }

BufferedImage fusion(BufferedImage image)
        {
            int w=image.getWidth();
            int h=image.getHeight();
            int result[][]=new int[w][h];
            BufferedImage temp=new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
            for(int i=0;i<w;i++)
            {
                for(int j=0;j<h;j++)
                {
                    result[i][j]=pix_array1[i][j];
                }
            }
            for(int i=0;i<w;i++)
            {
                for(int j=0;j<h;j++)
                {
                    if(result[i][j]==0 && pix_array2[i][j]==255)
                    result[i][j]=pix_array2[i][j];
                    
                }
            }
             for(int i=0;i<w;i++)
                        {
                            for(int j=0;j<h;j++)
                            {
                                int g=result[i][j];                               
                               temp.setRGB(i,j,(new Color(g, g, g)).getRGB());
                            }
                       }

            return temp; 

        }
}
