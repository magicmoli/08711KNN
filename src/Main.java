import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
	public static void main(String[] args) {		
		ReadExcel readXls = new ReadExcel();
		readXls.setInputFile("/Users/basdag/Desktop/trainProdSelection.xls");
		List<ArrayList<String>> trainData = new ArrayList<ArrayList<String>>();;
		try {
			trainData = readXls.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		readXls.setInputFile("/Users/basdag/Desktop/testProdSelection.xls");
		List<ArrayList<String>> testData = new ArrayList<ArrayList<String>>();
		try {
			testData = readXls.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
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
		
		KNN knn = new KNN(trainData, testData, similarityMatrix, 3);
		knn.similarityScoreCalculation();
	}
}

/*List<ArrayList<String>> trainCustomer = new ArrayList<ArrayList<String>>();
trainCustomer.add(new ArrayList<String>(Arrays.asList("student","spend>saving","6","40","13.62","3.2804","C1")));
trainCustomer.add(new ArrayList<String>(Arrays.asList("student","spend>saving","11","21","15.32","2.0232","C1")));
trainCustomer.add(new ArrayList<String>(Arrays.asList("student","spend>saving","7","64","16.55","3.1202","C1")));
trainCustomer.add(new ArrayList<String>(Arrays.asList("student","spend>saving","3","47","15.71","3.4022","C1")));
trainCustomer.add(new ArrayList<String>(Arrays.asList("student","spend>saving","15","10","16.96","2.2825","C1")));
trainCustomer.add(new ArrayList<String>(Arrays.asList("librarian","spend>saving","7","10","18.1751","1.2225","C2")));
trainCustomer.add(new ArrayList<String>(Arrays.asList("librarian","spend>saving","1","3","16.4385","0.9869","C2")));
trainCustomer.add(new ArrayList<String>(Arrays.asList("librarian","spend>saving","2","8","12.4786","0.7506","C2")));
trainCustomer.add(new ArrayList<String>(Arrays.asList("librarian","spend>saving","7","11","15.8298","0.5672","C2")));
trainCustomer.add(new ArrayList<String>(Arrays.asList("librarian","spend>saving","5","11","14.6699","1.0147","C2")));

List<ArrayList<String>> testCustomer = new ArrayList<ArrayList<String>>();
testCustomer.add(new ArrayList<String>(Arrays.asList("student","spend<saving","12","19","14.79","3.7697","")));
testCustomer.add(new ArrayList<String>(Arrays.asList("student","spend>>saving","29","10","16.19","2.4839","")));
testCustomer.add(new ArrayList<String>(Arrays.asList("student","spend<<saving","28","60","15.46","1.1885","")));
testCustomer.add(new ArrayList<String>(Arrays.asList("engineer","spend>saving","15","41","21.26","1.4379","")));
testCustomer.add(new ArrayList<String>(Arrays.asList("librarian","spend<saving","2","9","19.7207","0.6913","")));*/
