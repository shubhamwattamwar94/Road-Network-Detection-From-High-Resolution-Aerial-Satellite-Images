
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author shubham
 */
public class NewClass1 {
    public static void main(String[] args) throws IOException {
        NewClass1 n=new NewClass1();
        File f1=new File("C:\\Users\\shubham\\Documents\\NetBeansProjects\\Final\\Threshold\\FinalImage.jpg");
        BufferedImage edge=ImageIO.read(f1);
        BufferedImage g=n.erode(edge);
         Edge ed=new Edge();
        g=ed.run(g);
         Dilation d=new Dilation();
            g=d.execute(g);
            threshold th;
           th=new threshold(g, 80);
            th.createNewImage(g,80);
        BufferedImage g1 = th.getResultImage();
        ConnectRoad c;
        c=new ConnectRoad(edge);
            
        BufferedImage clean = c.getResult();
        File f11=new File("C:\\Users\\shubham\\Documents\\NetBeansProjects\\Final\\Image.jpg");
        ImageIO.write(clean, "jpg",f11);
        System.out.println("NewClass1.main()");
    }
    public BufferedImage erode(BufferedImage image)
         {
                      int w=image.getWidth();
                      int h=image.getHeight();
                 //     System.out.println("Width:"+w+" Height:"+h);
			int[][] pix=new int[w][h];
                        int[][] kernel={ {1,1,1} , {1,1,1} ,{1,1,1} };
                        int [][] result=new int[w][h];
                        for(int i=0;i<w;i++)
                           {
                       //     System.out.print(" \n");
                                for(int j=0;j<h;j++)
                                  {
                                        Color color = new Color(image.getRGB(i, j));
                                       // System.out.print(" "+color.getRed());
                                        if(color.getRed()==255)
                                            pix[i][j]=1;
                                        else
                                            pix[i][j]=0;
                                  }
                           }
                        for(int i=0;i<w;i++)
                           {
                                for(int j=0;j<h;j++)
                                  {
                                        result[i][j]=0;
                                  }
                           }
                         //System.out.println("hello");
                        for(int x=1;x<w-1;x++)
                        {
                            for(int y=1;y<h-1;y++)
                            {
                                
                                if(pix[x][y]== kernel[1][1])
                                {
                                    if(pix[x-1][y-1]==1 && kernel[0][0]==1 && pix[x-1][y]==1 && kernel[0][1]==1 && pix[x-1][y+1]==1 && kernel[0][2]==1 && pix[x][y-1]==1 && kernel[1][0]==1
                                            && pix[x][y+1]==1 && kernel[1][2]==1 && pix[x+1][y-1]==1 && kernel[2][0]==1 && pix[x+1][y]==1 && kernel[2][1]==1
                                            && pix[x+1][y+1]==1 && kernel[2][2]==1)
                                        result[x][y]=1;
                                    
                                }

                            }
                        }
                        for(int i=0;i<w;i++)
                           {
                            //System.out.println("\n");
                            for(int j=0;j<h;j++)
                                  {
                                        if(result[i][j]==1)
                                            result[i][j]=255;

                                  }
                             }

                        BufferedImage temp=new BufferedImage(image.getWidth(),image.getHeight(),image.getType());
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
