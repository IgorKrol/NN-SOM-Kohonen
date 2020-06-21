import javax.imageio.ImageIO;
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

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Polygon p = new Polygon();
        for(Node w : alg.weights){
            int tx = (int)(25*w.x)+200;
            int ty = (int)(25*w.y)+200;
            g.drawOval(tx,ty,5,5);
        }
    }

    public static void main(String[] args) throws InterruptedException, AWTException, IOException {
        BufferedImage imagebuf=null;
        Vector<Node> vec = new Vector<>();
        /*      LINE Topography        */
//        for(int i = -15; i < 15; i++){
//            vec.add(new Node(i/7.0,0));
//        }
        /*      CIRCLE Topography     */
//        for(int i = 0; i < 30; i++){
//            Random rand = new Random();
//        double a = rand.nextDouble() * 2 * Math.PI;
//        double r = 2;
//        vec.add(new Node(r * Math.cos(a), r * Math.sin(a)));
//        }
        /*      5x5 Topography      */
        for(double i = 0; i < 5; i++) {
            for(double j = 0; j < 5; j++) {
                vec.add(new Node(i/5*4-2,j/5*4-2));
            }
        }
        alg = new KohonenAlg(vec);
//        System.out.println(Arrays.deepToString(alg.weights.toArray()));
        for(int i = 0; i<100; i++){     //number of screenshot, number of overall iterations for train= i*iterBound (in Alg)
            /* paint on screen */
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
            alg.train();    //train
        }
    }
}
