import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KNN {

	private final int k;
	private List<ArrayList<String>> customerTrain;
	private List<ArrayList<String>> customerTest;
	private List<NearestCustomer> customerNearest;

	public KNN(List<ArrayList<String>> customerTrain, List<ArrayList<String>> customerTest, int k) {
		this.customerTrain = customerTrain;
		this.customerTest = customerTest;
		customerNearest = new ArrayList<NearestCustomer>();
		this.k = k;
	}

	private Double minMaxNormalization(Double actual, Double min, Double max) {
		return ((actual - min) / (max - min));
	}

	private void knnCalculation() {
		double distance;
		for (int testIndex = 0; testIndex < customerTest.size(); testIndex++) {
			for (int trainIndex = 0; trainIndex < customerTrain.size(); trainIndex++) {
				distance = 0;
				for (int attributeIndex = 0; attributeIndex < customerTrain.get(0).size() - 1; attributeIndex++) {
					distance = distance
							+ Math.pow((Double.parseDouble(customerTrain.get(
									trainIndex).get(attributeIndex)) - Double
									.parseDouble(customerTest.get(testIndex)
											.get(attributeIndex))), 2);
				}
				distance = Math.sqrt(distance);
				customerNearest.add(new NearestCustomer(distance, customerTrain.get(trainIndex).get(customerTrain.get(0).size() - 1))); // Class column
			}
			Collections.sort(customerNearest);
			clasification(testIndex);
		}
	}

	public void clasification(int newCustomerIndex) {
		Map<String,Integer> classMap = new HashMap<String,Integer>();
		int classCount;
		int classCountMax = 0;
		String commonClass = "";
		for (int numNeighbor=0; numNeighbor<k; numNeighbor++) {
			classCount = 0;
			if (classMap.get(customerNearest.get(numNeighbor).getClassType()) != null) {
				classCount = (Integer) classMap.get(customerNearest.get(numNeighbor).getClassType()) + 1;
				classMap.put(customerNearest.get(numNeighbor).getClassType(), classCount);
			} else {
				classCount = 1;
				classMap.put(customerNearest.get(numNeighbor).getClassType(), classCount);
			}
			
			if (classCount > classCountMax) {
				classCountMax = classCount;
				commonClass = customerNearest.get(numNeighbor).getClassType();
			}
		}
		customerTest.get(newCustomerIndex).set((customerTest.get(newCustomerIndex).size() - 1), commonClass);
	}
}