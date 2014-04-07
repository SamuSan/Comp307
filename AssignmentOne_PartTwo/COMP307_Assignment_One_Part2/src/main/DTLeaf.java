package main;

public class DTLeaf extends DTNode {

	private int category=-1;
	private Probability probability;
	private double prob =0;
	public DTLeaf(String attribute, DTNode trueNode, DTNode falseNode) {
		super(attribute, trueNode, falseNode);
	}

	public DTLeaf() {
	}
	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public Probability getProbability() {
		return probability;
	}

	public void setProbability(Probability probability) {
		this.probability = probability;
	}

	public double getProb() {
		return prob;
	}

	public void setProb(double prob) {
		this.prob = prob;
	}
	@Override
	public String toString() {
		return "DTLeaf [category=" + category + ", probability=" + probability
				+ ", prob=" + prob + "]";
	}

	public void report(String indent){
		
		String cat = category == 0 ? "Live" : "Die";
System.out.format("%sClass %s, prob=%4.2f\n", indent,cat,prob);
	}

}
