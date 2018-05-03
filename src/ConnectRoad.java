

import java.awt.image.BufferedImage;
import java.util.*;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
/**
 *
 * @author Shubham
 */
public class ConnectRoad extends JFrame{

   public static int width = 0;
   public static int height = 0;
   public static int type = 0;
   public static int[][] ori2DImage;
   public static ArrayList<ArrayList<Integer>> eqList;
   public static ArrayList<Integer> neighborArray;
   int[][] secondPassImage;
   public static int currentLabel = 0;   

   public ConnectRoad(BufferedImage image) {
        
      try
        {
             width=image.getWidth();
             height=image.getHeight();
             type=image.getType();
             ori2DImage=new int[width][height];
             for(int i=0;i<width;i++)
             {
                 for(int j=0;j<height;j++)
                 {
                     Color color = new Color(image.getRGB(i, j));
                          if(color.getRed()==255)
                                ori2DImage[i][j]=1;
                          else
                                 ori2DImage[i][j]=0;
                          
                 }
             }
            int[][] firstPassImage = new int[width][width];
           firstPassImage = firstPass(ori2DImage);
             secondPassImage = new int[width][width];
           //  secondPassImage=firstPassImage;
           secondPassImage = secondPass(firstPassImage);

      }
        catch (Exception e)
        {
        }
    }//end constr
    public BufferedImage getResult()
    {
        int max=0;
        int kz,k1,k2;
       // BufferedImage temp=new BufferedImage(width,height,type);
     for (int i =1;i<width-1;i++)
      {
           for (int j =1;j<height-1;j++)
           {
                       k1=(int)secondPassImage[i-1][j];
                       k2=(int)secondPassImage[i+1][j];
                       //System.out.println("k1: "+k1+" k2: "+k2);
                       if(k1==k2 && (int)secondPassImage[i][j]!=k1 && k1!=0 && k2!=0)
                       {
                       secondPassImage[i][j]=k1;
                      //System.out.println("change image value k1: "+k1 +" image :"+secondPassImage[i][j]);
                       }

           }

        }
           for(int i=1;i<width-1;i++)
             {
               System.out.print("\n");
                 for(int j=1;j<height-1;j++)
                 {
                     if(max<secondPassImage[i][j])
                        {
                          max=secondPassImage[i][j];
                        }
                      // int g=(int)secondPassImage[i][j];
                     //temp.setRGB(i,j,new Color(g,g,g).getRGB());
                  // System.out.print(" "+secondPassImage[i][j]);
                 }
             }
        //variable max strores the maximum index i.e. label
        int max_cnt[][]=new int[max+1][3]; // column 1) index 2)no. of pixels 3)distance
        double fx=0,fy=0,lx=0,ly=0,tj=0,tk=0;
        for(int i=0;i<=max;i++)
            max_cnt[i][1]=0;
        for(int i=1;i<=max;i++)
        {
            max_cnt[i][0]=i;
            for(int j=0;j<width;j++)
            {
                for(int k=0;k<height;k++)
                {
                    if(secondPassImage[j][k]==i)
                    {
                     if(max_cnt[i][1]==0)
                       {
                         fx=k; fy=j;
                       }
                         max_cnt[i][1]++;
                    }
                     if(secondPassImage[j][k]==i && max_cnt[i][1]!=0){
                        lx=k; ly=j;
                    }
                    // tk=k;
                }
                //tj=j;
            }
           // lx=tj; ly=tk;
            //...........

            double dist=euclidean(fx,fy,lx,ly);
            max_cnt[i][2]=(int)dist;
            
         //   System.out.println(" \nIndex :"+max_cnt[i][0]+" Pixel:"+max_cnt[i][1]+"dist:"+dist);
            //System.out.println("x1:"+fx+" y1:"+fy+" x2:"+lx+" y2:"+ly);
        }

        //*************sort***************
            for(int i=0;i<=max;i++)
            {
                for(int j=i+1;j<=max;j++)
                {
                    if(max_cnt[i][2]<max_cnt[j][2])
                    {
                        int tc=max_cnt[j][1];
                        int ti=max_cnt[j][0];
                        int td=max_cnt[j][2];
                            max_cnt[j][1]=max_cnt[i][1];
                            max_cnt[j][0]=max_cnt[i][0];
                            max_cnt[j][2]=max_cnt[i][2];
                            max_cnt[i][1]=tc;
                            max_cnt[i][0]=ti;
                            max_cnt[i][2]=td;
                    }
                }
            }
        int sum=0,threshold;
        int max_arr[][]=new int[max][3];
        int c_arr=0;
        
        BufferedImage im=new BufferedImage(width,height,type);
        int result[][]=new int[width][height];
        for(int i=0;i<max;i++)
        {
                          if(max_cnt[i][1]!=0)
                          {
                            max_arr[c_arr][1] = max_cnt[i][1];
                            max_arr[c_arr][0] = max_cnt[i][0];
                            max_arr[c_arr][2] = max_cnt[i][2];
                            System.out.println(""+max_arr[c_arr][0]+"\t"+max_arr[c_arr][1]+"\t"+max_arr[c_arr][2]);
                            sum = sum + max_arr[c_arr][2];
                            c_arr++;
                          }
            
        }
        threshold=sum/c_arr;
         System.out.println("max: "+c_arr+" Threshold:"+threshold);
         int euclid[]=new int [200];
         int e_cnt=0;
         for(int i=0;i<max;i++)
         {
             if(max_arr[i][2]>threshold)//&& max_arr[i][1]>1000)
             {
                 euclid[e_cnt]=max_arr[i][0];
                 System.out.println("E:"+max_cnt[i][0]+e_cnt);
                 e_cnt++;
             }
         }
          for(int i=0;i<width;i++)
        {
            for(int j=0;j<height;j++)
            {
                for(int k=0;k<e_cnt;k++)
                {
                    if(secondPassImage[i][j]==euclid[k])
                    {

                     result[i][j]=255;
                     im.setRGB(i,j,new Color(255,255,255).getRGB());
                     break;
                    }
                    else
                    {
                     result[i][j]=0;
                     im.setRGB(i,j,new Color(0,0,0).getRGB());
                    }
               }
           }
        }
         
         return im;

    }
    public double euclidean(double x1,double y1,double x2,double y2)
    {       
        double res=Math.sqrt(Math.pow(x2-x1,2)+Math.pow(y2-y1,2));
        return res;
    }
   public static int[][] firstPass(int[][] image)
   {       
       eqList = new ArrayList<ArrayList<Integer>>();
       int nw,n,ne,w,c = 0;
      

       for (int i = 1; i < image.length-1; ++i)
       {           
           for (int j = 1; j < image[0].length-1; ++j)
           {
                nw=0; n=0; ne=0; w=0;
               neighborArray = new ArrayList<Integer>();
               try
               {
                    nw = (int)image[i-1][j-1];
                    neighborArray.add(nw);
               }
               catch(Exception e)
               {
                    nw = 0;
               }              
               try
               {
                    n = (int)image[i-1][j];
                    neighborArray.add(n);

               }

               catch(Exception e)
               {
                    n = 0;
               }         
              try
               {
                    ne = (int)image[i-1][j+1];
                    neighborArray.add(ne);
               }
               catch(Exception e)
               {
                    ne = 0;
               } 
               try
               {
                    w = (int)image[i][j-1];
                    neighborArray.add(w);
               }
               catch(Exception e)
               {
                    w = 0;
               } 
                   c = (int)image[i][j];
                     if (c > 0)
               {
                   if (nw == 0 && n == 0 && ne == 0 && w == 0)//case 1 : all are zeros
                   {
                       ArrayList<Integer> al = new ArrayList<Integer>();
                       al.add(++currentLabel);
                       eqList.add(al);               
                       image[i][j] = currentLabel;
                   }
                   else if (!neighborArray.isEmpty() && samelabel(neighborArray))// case 2 : all the same values
                   {                   
                       image[i][j] = findMin(neighborArray); //take value from one of the neighbors
                      

                   }
                   else if (!neighborArray.isEmpty() && !samelabel(neighborArray)) //case 3 : not all are the same
                   {
                       image[i][j] = findMin(neighborArray);
                       
                   }     
               }
           }
       }
       return image;

   }
    public static int[][] secondPass(int[][] image)
   {
     eqList = new ArrayList<ArrayList<Integer>>();
    int ea,sw,s,se,c = 0;
     
      for (int i = image.length-1; i > 0 ; --i)
      {           
           for (int j = image[0].length-1; j > 0; --j)
           {
               ea=0; sw=0; s=0; se=0;
               neighborArray = new ArrayList<Integer>();
              try
               {
                    ea = (int)image[i][j+1];
                    neighborArray.add(ea);
               }
               catch(Exception e)
               {
                    ea = 0;
               }                
               try
               {
                    sw = (int)image[i+1][j-1];
                    neighborArray.add(sw);
               }
               catch(Exception e)
               {
                    sw = 0;
               } 
               try
               {
                    s = (int)image[i+1][j];
                   neighborArray.add(s);
               }
               catch(Exception e)
               {
                    s = 0;
               } 
              try
               {
                    se = (int)image[i+1][j+1];
                    neighborArray.add(se);
               }
               catch(Exception e)
               {
                    se = 0;
               } 
                   c = (int)image[i][j];
                if (c > 0)
               {
                  if (ea == 0 && sw == 0 && s == 0 && se == 0)//case 1 : all are zeros
                   {
                       //do nothing
                   }
                   else if (!neighborArray.isEmpty() && samelabel(neighborArray))// case 2 : all the same values
                   {                   
                       image[i][j] = findMin(neighborArray); //take value from one of the neighbors
                   }
                   else if (!neighborArray.isEmpty() && !samelabel(neighborArray)) //case 3 : not all are the same
                   {
                       neighborArray.add(c);
                       image[i][j] = findMinEqual(findMin(neighborArray));
                   }     
               }
           }
       }


       return image;
   }

//This method checks whether neighbors have the same label.
   public static boolean samelabel(ArrayList<Integer> al)
   {
       boolean allTheSame = true;
       Collections.sort(al); //sort ascending
       for (int i = 0; i < al.size(); ++i)
       {
           if (i < al.size()-1 && al.get(i)!=0)
           {
               if (al.get(i)!= al.get(i+1))
               {
                   allTheSame = false;
                  break;
               }               
           }
       }
       return allTheSame;
   }

//This method traverse a neighbor list and return the minimum value.
   public static int findMin(ArrayList<Integer> neighborArray)
   {
       int min = 0;
       Collections.sort(neighborArray);
       for (int i =0; i < neighborArray.size(); ++i)
       {
           if (neighborArray.get(i)!=0)
           {
               min = neighborArray.get(i);
               break;
           }
       }
       return min;
   }
//this method takes label number as an input and look up the same label with  less value.
   public static int findMinEqual(int label)
   {
       int min = 0;       
       for (int i =0; i < eqList.size(); ++i)
       {
           if (eqList.get(i).get(0) == label)
           {
               Collections.sort(eqList.get(i));
               min = eqList.get(i).get(0);               
               break;
           }
       }

       return min;
   }
    public static void main(String[] args) throws IOException {
        File f11 = new File("C:\\Users\\shubham\\Documents\\NetBeansProjects\\Final\\FinalImage.jpg");
        BufferedImage image = ImageIO.read(f11);
        
        ConnectRoad c=new ConnectRoad(image);
       BufferedImage clean = c.getResult();
       /* int col=clean.getRGB(0, 0);
       Image imo=makeColorTransparent(clean,new Color(Col));
       BufferedImage k=imageToBufferedImage(imo);
       /* grp=clean.createGrap1hics();
       grp.setColor(Color.red);
       grp.setBackground(Color.WHITE);
       grp.dispose();*/
       //   System.out.println("after clean"+i);
       File f = new File("C:\\Users\\shubham\\Documents\\NetBeansProjects\\Final\\clean.jpg");
            ImageIO.write(clean, "jpg",f);
          
        
    }
 }
 
