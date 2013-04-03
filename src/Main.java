import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
	public static void main(String[] args) {		
		ReadCSV readCSV = new ReadCSV();
		readCSV.setInputFile("/Users/basdag/Desktop/trainProdSelection.csv");
		List<ArrayList<String>> trainData = new ArrayList<ArrayList<String>>();
		try {
			trainData = readCSV.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		readCSV.setInputFile("/Users/basdag/Desktop/testProdSelection.csv");
		List<ArrayList<String>> testData = new ArrayList<ArrayList<String>>();
		try {
			testData = readCSV.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		List<Double> attributeWeight = new ArrayList<Double>(Arrays.asList(1.0, 1.0, 1.0, 1.0, 1.0, 1.0));
		
		Map<String, ArrayList<Double>> similarityMatrix = new HashMap<String, ArrayList<Double>>();
		similarityMatrix.put("spend<<saving", new ArrayList<Double>(Arrays.asList(1.0, 0.0)));
		similarityMatrix.put("spend<saving", new ArrayList<Double>(Arrays.asList(1.0, 0.0)));
		similarityMatrix.put("spend>saving", new ArrayList<Double>(Arrays.asList(1.0, 0.0)));
		similarityMatrix.put("spend>>saving", new ArrayList<Double>(Arrays.asList(1.0, 0.0)));
		similarityMatrix.put("student", new ArrayList<Double>(Arrays.asList(1.0, 0.0)));
		similarityMatrix.put("engineer", new ArrayList<Double>(Arrays.asList(1.0, 0.0)));
		similarityMatrix.put("librarian", new ArrayList<Double>(Arrays.asList(1.0, 0.0)));
		similarityMatrix.put("professor", new ArrayList<Double>(Arrays.asList(1.0, 0.0)));
		similarityMatrix.put("doctor", new ArrayList<Double>(Arrays.asList(1.0, 0.0)));
		
		System.out.println(testData);
		System.out.println();
		System.out.println();
		
		KNN knn = new KNN(trainData, testData, similarityMatrix, attributeWeight, 3);
		knn.similarityScoreCalculation();
	}
}