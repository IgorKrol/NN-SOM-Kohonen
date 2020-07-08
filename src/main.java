import javax.imageio.ImageIO;
import java.util.Scanner;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

public class main extends JPanel {

	public static KohonenAlg alg;
	///do count-- until count=0.with that variable we count every algorithm.
	public static int count=8;
	///count the number of  iterations in every algorithm
    public static int y=0;
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Polygon p = new Polygon();
		for(Node w : alg.weights){
			int tx = (int)(25*w.x)+200;
			int ty = (int)(25*w.y)+200;
			g.drawOval(tx,ty,5,5);
		}
		g.drawLine(200, 5, 200, 360);
		g.drawLine(5, 200, 380, 200);
		Font myFont = new Font ("y", 10, 10);
		Font fo=myFont.deriveFont(myFont.getSize()*50);
		g.setFont(fo);
		g.drawString("Axis X", 275, 220);
		g.drawString("Axis Y", 150, 125);
		g.drawLine(195, 250, 205, 250);
		g.drawLine(195, 300, 205, 300);
		g.drawLine(195, 350, 205, 350);
		g.drawLine(195, 150, 205, 150);
		g.drawLine(195, 100, 205, 100);
		g.drawLine(195, 50, 205, 50);
		g.drawLine(250, 195, 250, 205);
		g.drawLine(300, 195, 300, 205);
		g.drawLine(350, 195, 350, 205);
		g.drawLine(150, 195, 150, 205);
		g.drawLine(100, 195, 100, 205);
		g.drawLine(50, 195, 50, 205);
		g.drawString("2", 210, 153);
		g.drawString("4", 210, 103);
		g.drawString("6", 210, 53);
		g.drawString("-2", 210, 253);
		g.drawString("-4", 210, 303);
		g.drawString("-6", 210, 353);
		g.drawString("2", 248, 190);
		g.drawString("4", 298, 190);
		g.drawString("6", 348, 190);
		g.drawString("-2", 145, 190);
		g.drawString("-4", 95, 190);
		g.drawString("-6", 45, 190);
		g.drawOval(150, 150, 100, 100);
		g.drawString("the neurons that are move:", 20, 20);
		g.drawString("the iterations:"+(y), 20, 30);
		g.drawOval(140,15,5,5);
		
		if (count==1 || count==2) {
			g.drawOval(100,100,200,200);
		}
	}


	public static void main(String[] args) throws InterruptedException, AWTException, IOException {

		task();
	}

	public static void task()throws InterruptedException, AWTException, IOException {
		if(count==0) {
			return;
		}
		BufferedImage imagebuf=null;
		Vector<Node> vec = new Vector<>();
		if(count==8 || count==5 || count==2) {
			/*      LINE Topography        */
			for(int i = -15; i < 15; i++){
				vec.add(new Node(i/7.0,0));
			}
		} 
		else if(count==7 || count==4 || count==1) {
			/*      CIRCLE Topography     */
			for(int i = 0; i < 30; i++){
				Random rand = new Random();
				double a = rand.nextDouble() * 2 * Math.PI;
				double r = 2;
				vec.add(new Node(r * Math.cos(a), r * Math.sin(a)));
			}
		}
		else if(count==6 || count==3) {
			/*      5x5 Topography      */

			for(double i = 0; i < 5; i++) {
				for(double j = 0; j < 5; j++) {
					vec.add(new Node(i/5*4-2,j/5*4-2));
				}
			}
		}

		alg = new KohonenAlg(vec);
		//        System.out.println(Arrays.deepToString(alg.weights.toArray()));
		for(int i = 0; i<100; i++){     //number of screenshot, number of overall iterations for train= i*iterBound (in Alg)
			
			/* paint on screen */
			y++;
			JFrame frame = new JFrame();
			frame.setTitle("Polygon");
			frame.setSize(400, 400);
			frame.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					System.exit(0);
				}
			});
			Container contentPane = frame.getContentPane();
			/* show on screen */
			main p = new main();
			contentPane.add(p);
			frame.setVisible(true);
			/* take screenshot*/
			p.setBounds(0,0,400,400);
			imagebuf = new Robot().createScreenCapture(p.bounds());
			Graphics2D graphics2D = imagebuf.createGraphics();
			ImageIO.write(imagebuf,"jpeg", new File("img"+i+".jpeg"));

			TimeUnit.MILLISECONDS.sleep(500);
			if(count==8 || count==7 || count==6) {
				alg.train_part_A_B_C();    //train
			}
			else if(count==5 || count==4 || count==3) {
				alg.train_partD();
			}
			
			else if(count==2 || count==1) {
				alg.train_partE();
			}

		}
		
		
		y=0;
		count--;
		task();
		return;
	}
}