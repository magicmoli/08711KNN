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
	private List<NearestCustomer> customerNearest;
	private Map<String, ArrayList<Double>> similarityMatrix;

	public KNN(List<ArrayList<String>> arrayTrain, List<ArrayList<String>> arrayTest, Map<String, ArrayList<Double>> similarityMatrix, int k) {
		this.k = k;
		this.arrayTrain = arrayTrain;
		this.arrayTest = arrayTest;
		this.similarityMatrix = similarityMatrix;
		customerNearest = new ArrayList<NearestCustomer>();
	}

	private Double minMaxNormalization(Double actual, Double min, Double max) {
		return ((actual - min) / (max - min));
	}

	public void similarityScoreCalculation() {
		double similarityScore;
		for (int testIndex = 0; testIndex < arrayTest.size(); testIndex++) {
			for (int trainIndex = 0; trainIndex < arrayTrain.size(); trainIndex++) {
				similarityScore = 0;
				for (int attributeIndex = 0; attributeIndex < arrayTrain.get(0).size() - 1; attributeIndex++) {
					try {
						similarityScore = similarityScore
								+ Math.pow((Double.parseDouble(arrayTrain.get(
										trainIndex).get(attributeIndex)) - Double
										.parseDouble(arrayTest.get(testIndex)
												.get(attributeIndex))), 2);	
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
													.get(0), 2);
						else
							similarityScore = similarityScore
									+ Math.pow(
											similarityMatrix
													.get(arrayTrain.get(
															trainIndex).get(
															attributeIndex))
													.get(1), 2);
					}
				}
				similarityScore = 1 / Math.sqrt(similarityScore);
				customerNearest.add(new NearestCustomer(similarityScore, arrayTrain.get(trainIndex).get(arrayTrain.get(0).size() - 1)));
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
			
			clasification(testIndex);
			customerNearest.clear(); 
		}
		System.out.println(arrayTest);
	}

	public void clasification(int testIndex) {
		Map<String,Double> classMap = new HashMap<String,Double>();
		double sumSimilarityScore;
		double similarityScoreMax = 0.0;
		String commonClass = "";
		for (int numNeighbor=0; numNeighbor<k; numNeighbor++) {
			sumSimilarityScore = 0.0;
			if (classMap.get(customerNearest.get(numNeighbor).getClassType()) != null) {
				sumSimilarityScore = classMap.get(customerNearest.get(numNeighbor).getClassType()) + customerNearest.get(numNeighbor).getSimilarityScore();
				classMap.put(customerNearest.get(numNeighbor).getClassType(), sumSimilarityScore);
			} else {
				sumSimilarityScore = customerNearest.get(numNeighbor).getSimilarityScore();
				classMap.put(customerNearest.get(numNeighbor).getClassType(), sumSimilarityScore);
			}
			if (sumSimilarityScore > similarityScoreMax) {
				similarityScoreMax = sumSimilarityScore;
				commonClass = customerNearest.get(numNeighbor).getClassType();
			}
		}
		arrayTest.get(testIndex).set((arrayTest.get(testIndex).size() - 1), commonClass);
	}
}