import java.util.Random;

/**
 * @author Igor Krol
 */
public class Node {
    double x,y;

    public Node(double x,double y) {
        this.x = x;
        this.y = y;
    }

    /**
    returns Euclidean distance between this->other nodes
     */
    double distance(Node other){
        return Tools.EuclideanDistance(this.x - other.x, this.y-other.y);
    }
    /**
    new random Node in range (-2,2)
     */
    public static Node random(){
        Random rand = new Random();
        return new Node(rand.nextDouble()*4-2,rand.nextDouble()*4-2);
    }
    /**
    random node within circle with R=r1
     */
    public static Node randomCircle(int r1){
        Random rand = new Random();
        double a = rand.nextDouble() * 2 * Math.PI;
        double r = r1 * Math.sqrt(rand.nextDouble());
        return new Node(r * Math.cos(a), r * Math.sin(a));
    }
    /**
    random node between r1-r2 circles
     */
    public static Node randomCircle(int r1, int r2){
        Random rand = new Random();
        double a = rand.nextDouble() * 2 * Math.PI;
        double r = 0;
        while (r < 2)
          r = r2 * Math.sqrt(rand.nextDouble());
        return new Node(r * Math.cos(a), r * Math.sin(a));
    }
    /**
    random node with probability distribution
     */
    public static Node randomCircleProb(int r1){
        Random rand = new Random();
        double a = rand.nextDouble() * 2 * Math.PI;
        double r = r1 * rand.nextDouble();
        Node n = new Node(r * Math.cos(a), r * Math.sin(a));
        double d = n.distance(new Node(0,0));
        n.x = n.x / (d/r1);
        return n;
    }
    /**
    print point (x,y)
     */
    public String toString(){
        return "("+x+","+y+")";
    }

}
