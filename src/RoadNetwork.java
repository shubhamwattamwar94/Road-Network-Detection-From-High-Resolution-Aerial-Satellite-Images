import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;


public class RoadNetwork extends JFrame
{

	JButton jbtn1,jbtn2;
	//final JLabel jlabel1;
	
	public RoadNetwork() {
		 final Container ct = getContentPane();
		
		Color cl1 = new Color(255,255,255);

		Color cl2 = new Color(220,220,220);

		
		Color rbl = new Color(65,105,225);

		ct.setBackground(cl1);
		
		ct.setLayout(null);
		
		Font f1 = new Font("Times New Roman",Font.ITALIC|Font.BOLD,22);

		JLabel jlb1 = new JLabel("Road Network Detection");
			ct.add(jlb1);
			jlb1.setBounds(250,100,230,30);
			jlb1.setFont(f1);
			jlb1.setForeground(rbl);
			
			
			jbtn1 = new JButton("Browse");
			ct.add(jbtn1);
			jbtn1.setBounds(150,140,80,25);
			jbtn1.setBackground(rbl);
			jbtn1.setForeground(cl1);
			
			
			jbtn2 = new JButton("Extract");
			ct.add(jbtn2);
			jbtn2.setBounds(300,620,100,35);
			jbtn2.setBackground(rbl);
			jbtn2.setForeground(cl1);
			
			
			final JLabel statusbar = 
	                 new JLabel("Output of your selection will go here");
			ct.add(statusbar);
			statusbar.setBounds(150,180,300,25);
			
			
			//final JLabel label = new JLabel();
	       // label.setIcon(new ImageIcon("a.jpg"));
	        //ct.add(label);
	        //label.setBounds(200, 200, 200, 200);
			
			
			
			jbtn1.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					
					
                                JFileChooser chooser = new JFileChooser();
			        chooser.setMultiSelectionEnabled(true);
			        int option = chooser.showOpenDialog(RoadNetwork.this);
			        if (option == JFileChooser.APPROVE_OPTION) {
			        File[] sf = chooser.getSelectedFiles();
			        File fil = chooser.getCurrentDirectory();
			        String filelist = "nothing";
			          if (sf.length > 0) filelist = sf[0].getName();
			          for (int i = 1; i < sf.length; i++) {
			            filelist += ", " + sf[i].getName();
			          }
			          
					try {
						String path1=fil+"\\"+filelist;
						String path = "C:/Users/Akshay/Pictures/cougar.jpg";
				          File file = new File(path1);
				          BufferedImage image = ImageIO.read(file);
				          JLabel label = new JLabel(new ImageIcon(image));
				          ct.add(label);
					        label.setBounds(150, 210, 400, 400);
				          statusbar.setText("You chose " + path1);

					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			          
			          //label.setIcon();
			        }
			        else {
			          statusbar.setText("You canceled.");
			        }
					/*JFileChooser chooser=new JFileChooser();
					FileNameExtensionFilter filter=new FileNameExtensionFilter("JPG Images","jpg");
					chooser.setFileFilter(filter);
					int returnVal=chooser.showOpenDialog(new JFrame());
					if(returnVal==JFileChooser.APPROVE_OPTION){
						//jlabel1=new JLabel();
						statusbar.setText("Selected File :"+chooser.getSelectedFile().getName());
						//jlabel1.setBounds(150,170,100,25);
						//ct.add(jlabel1);
						//jlabel1.setBackground(rbl);
						//jlabel1.setForeground(cl1);
						
						
						
					}*/

					
				}
			});

	}


/*public RoadNetwork()
{

JFrame f=new JFrame("Road Network Detection");

f.setSize(700,700);
f.setVisible(true);
f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//Container c=getContentPane();
//c.setLayout(new FlowLayout());
JPanel jPanel=new JPanel();
final JLabel jLabel=new JLabel();
JButton openButton = new JButton("Open");

jPanel.add(openButton);
jPanel.add(jLabel);
f.add(jPanel);



openButton.addActionListener(new ActionListener() {
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		JFileChooser chooser=new JFileChooser();
		FileNameExtensionFilter filter=new FileNameExtensionFilter("JPG Images","jpg");
		chooser.setFileFilter(filter);
		int returnVal=chooser.showOpenDialog(new JFrame());
		if(returnVal==JFileChooser.APPROVE_OPTION){
			
			jLabel.setText("Selected File :"+chooser.getSelectedFile().getName());
		}
		
	}
});


}


private Container getContentPane() {
	// TODO Auto-generated method stub
	return null;
}*/


/*public static void main(String[] ars)throws NullPointerException
{
	RoadNetwork network=new RoadNetwork();
	network.setVisible(true);
	network.setSize(700,700);
}*/


}
