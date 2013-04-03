import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KNN {

	private final int k;
	private List<ArrayList<String>> arrayTrain;
	private List<ArrayList<String>> arrayTest;
	private List<ArrayList<Double>> attributeMaxMin;
	private List<NearestCustomer> customerNearest;
	private Map<String, ArrayList<Double>> similarityMatrix;

	public KNN(List<ArrayList<String>> arrayTrain,
			List<ArrayList<String>> arrayTest,
			Map<String, ArrayList<Double>> similarityMatrix, int k) {
		this.k = k;
		this.arrayTrain = arrayTrain;
		this.arrayTest = arrayTest;
		this.similarityMatrix = similarityMatrix;
		customerNearest = new ArrayList<NearestCustomer>();
		attributeMaxMin = new ArrayList<ArrayList<Double>>();
		attributeMaxMinCalculation();
	}

	private void attributeMaxMinCalculation() {
		double max;
		double min;
		boolean error;
		for (int column=0; column<arrayTrain.get(0).size(); column++) {
			error = false;
			max = 0;
			min = 9999999999.999;
			try {
				for (int row=0; row<arrayTrain.size(); row++) {
					if (Double.parseDouble(arrayTrain.get(row).get(column)) > max)
						max = Double.parseDouble(arrayTrain.get(row)
								.get(column));
					if (Double.parseDouble(arrayTrain.get(row).get(column)) < min)
						min = Double.parseDouble(arrayTrain.get(row)
								.get(column));
				}
			} catch (Exception e) {
				attributeMaxMin.add(new ArrayList<Double>(Arrays.asList(-1.0, -1.0)));
				error = true;
			}
			if (!error) attributeMaxMin.add(new ArrayList<Double>(Arrays.asList(min, max)));
		}
	}
	
	private double normalize(double value, int attributeIndex) {
		return ((value - attributeMaxMin.get(attributeIndex).get(0)) / (attributeMaxMin
				.get(attributeIndex).get(1) - attributeMaxMin.get(
				attributeIndex).get(0)));
	}

	public void similarityScoreCalculation() {
		double similarityScore;
		for (int testIndex = 0; testIndex < arrayTest.size(); testIndex++) {
			for (int trainIndex = 0; trainIndex < arrayTrain.size(); trainIndex++) {
				similarityScore = 0;
				for (int attributeIndex = 0; attributeIndex < arrayTrain.get(0).size() - 1; attributeIndex++) {
					try {
						similarityScore = similarityScore
								+ Math.pow(
										(normalize(Double
												.parseDouble(arrayTrain.get(
														trainIndex).get(
														attributeIndex)),
												attributeIndex) - normalize(
												Double.parseDouble(arrayTest
														.get(testIndex).get(
																attributeIndex)),
												attributeIndex)), 2);	
					} catch (Exception e) {
						if (arrayTrain
								.get(trainIndex)
								.get(attributeIndex)
								.compareTo(
										arrayTest.get(testIndex).get(
												attributeIndex)) == 0)
							similarityScore = similarityScore
									+ Math.pow(
											similarityMatrix
													.get(arrayTrain.get(
															trainIndex).get(
															attributeIndex))
													.get(1), 2);
						else
							similarityScore = similarityScore
									+ Math.pow(
											similarityMatrix
													.get(arrayTrain.get(
															trainIndex).get(
															attributeIndex))
													.get(0), 2);
					}
				}
				similarityScore = 1 / Math.sqrt(similarityScore);
				customerNearest.add(new NearestCustomer(similarityScore,
						arrayTrain.get(trainIndex).get(
								arrayTrain.get(0).size() - 1)));
			}
			
			//Print disorder list
			/*for (NearestCustomer strings : customerNearest) {
			    System.out.println(strings.getClassType() + " - " + strings.getSimilarityScore());
			}
			System.out.println();*/
			
			Collections.sort(customerNearest);
			
			//Print order list
			/*for (NearestCustomer strings : customerNearest) {
			    System.out.println(strings.getClassType() + " - " + strings.getSimilarityScore());
			}
			System.out.println();
			System.out.println();*/
			
			clasificationCommonClass(testIndex);
			customerNearest.clear(); 
		}
		
		//Print Results
		for (ArrayList<String> strings : arrayTest) {
	    	System.out.println(strings);
		}
	}
	
	public void clasificationCommonClass(int testIndex) {
		Map<String,Integer> classMap = new HashMap<String,Integer>();
		int commonClass = 0;
		int commonClassMax = 0;
		String similarityClass = "";
		for (int numNeighbor = 0; numNeighbor < k; numNeighbor++) {
			if (classMap.get(customerNearest.get(numNeighbor).getClassType()) != null) {
				commonClass = classMap.get(customerNearest.get(
						numNeighbor).getClassType())
						+ 1;
				classMap.put(customerNearest.get(numNeighbor).getClassType(), commonClass);
			} else {
				commonClass = 1;
				classMap.put(customerNearest.get(numNeighbor).getClassType(), commonClass);
			}
			if (commonClass > commonClassMax) {
				commonClassMax = commonClass;
				similarityClass = customerNearest.get(numNeighbor).getClassType();
			}
		}
		arrayTest.get(testIndex).set((arrayTest.get(testIndex).size() - 1), similarityClass);
	}
}