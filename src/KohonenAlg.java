import java.util.Vector;

/**
 * @author Igor Krol and shai kaikov
 */
public class KohonenAlg {

	Vector<Node> weights = new Vector<>();  //weights vector
	int iterBound = 100;    //iterations per training activation
	double l = 0.1;     //starting learn rate
	double n0 = 0.2;    //starting for nc function (gaussian function)

	public KohonenAlg(Vector<Node> v){
		weights = v;
	}

	/**
	 * algo for random neurons vector with 30 neurons
	 */
	public KohonenAlg(){
		for(int i=0; i<30; i++){
			weights.add(Node.random());
		}
	}





	/**
	 * Kohonen-SOM algo train for part D in the task
	 */
	public void train_partD(){
		for(int i =0; i < iterBound; i++){
			//            Node d = Node.randomCircle(2);
			//            Node d = Node.randomCircle(2,4);
			Node d = Node.randomCircleProb(2);
			Node bmu = BMU(d);
			for(Node w : weights){
				double tempX = w.x + (h(w,bmu,i) * lr(i) * (d.x - w.x));
				double tempY = w.y + (h(w,bmu,i) * lr(i) * (d.y - w.y));
				w.x = tempX;
				w.y = tempY;
			}
		}
	}


    /**
     * Kohonen-SOM algo train for part A,B,C in the task
     */
	public void train_part_A_B_C(){
		for(int i =0; i < iterBound; i++){
			Node d = Node.randomCircle(2);
			//            Node d = Node.randomCircle(2,4);
			//            Node d = Node.randomCircleProb(2);
			Node bmu = BMU(d);
			for(Node w : weights){
				double tempX = w.x + (h(w,bmu,i) * lr(i) * (d.x - w.x));
				double tempY = w.y + (h(w,bmu,i) * lr(i) * (d.y - w.y));
				w.x = tempX;
				w.y = tempY;
			}
		}
	}

   /**
    * Kohonen-SOM algo train for part E in the task
    */
	public void train_partE(){
		for(int i =0; i < iterBound; i++){
			//            Node d = Node.randomCircle(2);
			Node d = Node.randomCircle(2,4);
			//            Node d = Node.randomCircleProb(2);
			Node bmu = BMU(d);
			for(Node w : weights){
				double tempX = w.x + (h(w,bmu,i) * lr(i) * (d.x - w.x));
				double tempY = w.y + (h(w,bmu,i) * lr(i) * (d.y - w.y));
				w.x = tempX;
				w.y = tempY;
			}
		}
	}

	/**
	 * Best Match Unit search
	 * @param d = input node
	 * @return  = BMU node from vector wieghts
	 */
	Node BMU(Node d){
		double minDistance = d.distance(weights.get(0));
		Node temp = weights.get(0);
		for(Node n : weights){
			if(d.distance(n) < minDistance){
				minDistance = d.distance(n);
				temp = n;
			}
		}
		return temp;
	}

	/**
	 * lr calculation
	 * @param t - iteraion
	 * @return lr
	 */
	double lr(int t){
		return l * Math.pow(Math.E, (t/iterBound));
	}

	/**
	 * neighbours function
	 * @param w - node from weights
	 * @param c - BMU
	 * @param t - iteration
	 * @return neighbours value for updates
	 */
	double h(Node w, Node c, int t){
		return Math.pow(Math.E, -(Math.pow(w.x-c.x,2) + Math.pow(w.y-c.y,2))/(2*Math.pow(nc(t),2)));
	}

	double nc(int t){
		return n0 * Math.pow(Math.E, (-t/iterBound));
	}
}
