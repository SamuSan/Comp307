package part1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import flowers.Iris;

public class KCrossFoldValidation {

	private static ArrayList<Iris> flowers = new ArrayList<Iris>();
	private static ArrayList<Iris> trainers = new ArrayList<Iris>();
	private static int K =1;
	public KCrossFoldValidation(ArrayList<Iris> flowers){
		this.flowers = flowers;
		doKCrossFold();
	}
	
	private static void doKCrossFold(){
		
		ArrayList<Iris> test = new ArrayList<Iris>(flowers);
		double [] successRates = new double[500];
		ArrayList<ArrayList<Iris>> sets = new ArrayList<ArrayList<Iris>>();
		ArrayList<Pair> candidates = new ArrayList<Pair>();
		double count = 0;
		double success = 0;
		int numCases=flowers.size();
		int k = getK(numCases);
		int numElements=numCases / k;
		int incr = numElements;
		d(numElements);
		int start = 0;
		for (int i = 1; i <=k; i++) {
			ArrayList<Iris> sample = new ArrayList<Iris>();
			//Add the flowers to this
			for(;start<numElements;start++){
				sample.add(test.get(start));
			}
			numElements+=incr;
			sets.add(sample);
		}
		
		int testSet =0;
		
		//Take one set of data from the asmple sets
		//use this as the training data
		//Take the remaining sets and use as testing data
		
		
		for (int i = testSet; i < k; i++) {
			ArrayList<Iris> trainingSet = sets.get(i);
			for (int j = testSet; i < k; i++) {
				if(i != j){
					ArrayList<Iris> testingSet = sets.get(j);
					
					
					for (Iris x : testingSet) {
					for (Iris e : trainingSet) {
						double dist = EucledianCalc.euclidCalc(x, e);
						// d("Dist = " + dist);
						Pair p = new Pair(dist, e);
						candidates.add(p);
					}
					Collections.sort(candidates, new PairComparator());
				//	d(candidates.size());
					d(x.toString());
					ArrayList<Pair> sublist = new ArrayList<Pair>();
					for (int g = 0; g < K; g++) {
						sublist.add(candidates.get(g));
					}
					String match = doVote(sublist);
					System.out.println(match);
					//d(match);
					count++;
					//d("Chosen Dist = " + candidates.get(0).dist);
					if (x.getClazz().equals(match)) {
						success++;
					}
//					if (i.getClass() == candidates.get(0).i.getClass()) {
//						success++;
//					}
					// d("///////////////////////////S");
					candidates.clear();
				}
				double rate = success / count;
				successRates[i] = rate;
				d(rate + "% success");
				}

			}
		}
	for (int i = 0; i < successRates.length; i++) {
		d(successRates[i]);
	}
	}

	private static String doVote(List<Pair> list) {
		HashMap<String, Integer> voters = new HashMap<String, Integer>();
		// Make a sublist of candidates
		// List<Pair> subList = candidates.subList(0, K);
		// candidates.subList(0, K);
		// from that sublist count how many times a particular class appears
		// The majority wins
		// Return
		for (Pair pair : list) {
			if (!voters.keySet().contains(pair.i.getClazz())) {
				voters.put(pair.i.getClazz(), 1);
			} else {
				int x = voters.remove(pair.i.getClazz());
				voters.put(pair.i.getClazz(), x + 1);
			}
		}
		int maj = 0;
		String choice = "";
		for (String s : voters.keySet()) {
			if (voters.get(s) > maj) {
				maj = voters.get(s);
				choice = s;
			}
		}

		return choice;
	}
	private static  int getK(int numCases) {
		int max =0;
		for (int i = 2; i < 10; i++) {
			if(numCases % i ==0 && i > max){
				max = i;
			}
		}
		return max;
	}
	
private static void d(Object o) {
	System.out.println(o);
}

	
}
