import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KNN {

	private final int k;
	private double similarityAverage;
	private List<ArrayList<String>> arrayTrain;
	private List<ArrayList<String>> arrayTest;
	private List<ArrayList<Double>> attributeMaxMin;
	private List<NearestCustomer> customerNearest;
	private Map<String, ArrayList<Double>> similarityMatrix;
	private List<Double> attributeWeight;

	public KNN(List<ArrayList<String>> arrayTrain,
			List<ArrayList<String>> arrayTest,
			Map<String, ArrayList<Double>> similarityMatrix, List<Double> attributeWeight, int k) {
		this.k = k;
		this.similarityAverage = 0;
		this.arrayTrain = arrayTrain;
		this.arrayTest = arrayTest;
		this.similarityMatrix = similarityMatrix;
		this.attributeWeight = attributeWeight;
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
		return (((value - attributeMaxMin.get(attributeIndex).get(0)) / (attributeMaxMin
				.get(attributeIndex).get(1) - attributeMaxMin.get(
				attributeIndex).get(0))) * attributeWeight.get(attributeIndex));
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
									+ Math.pow((
											similarityMatrix
													.get(arrayTrain.get(
															trainIndex).get(
															attributeIndex))
													.get(1)) * attributeWeight.get(attributeIndex), 2);
						else
							similarityScore = similarityScore
									+ Math.pow((
											similarityMatrix
													.get(arrayTrain.get(
															trainIndex).get(
															attributeIndex))
													.get(0)) * attributeWeight.get(attributeIndex), 2);
					}
				}
				similarityScore = 1 / Math.sqrt(similarityScore);
				customerNearest.add(new NearestCustomer(similarityScore,
						arrayTrain.get(trainIndex).get(
								arrayTrain.get(0).size() - 1)));
			}
			Collections.sort(customerNearest);
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
			System.out.println(customerNearest.get(numNeighbor).getClassType() + " - " + customerNearest.get(numNeighbor).getSimilarityScore());
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
		System.out.println();
		arrayTest.get(testIndex).set((arrayTest.get(testIndex).size() - 1), similarityClass);
	}
}