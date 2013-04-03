import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ArffLoader.ArffReader;

public class Main {
	public static void main(String[] args) {
		/*try {
		   BufferedReader reader = new BufferedReader(new FileReader("/Users/basdag/Documents/workspace_eclipse/WEKA/trainProdSelection.arff"));
		   ArffReader arff = new ArffReader(reader, 1);
		   Instances data = arff.getStructure();
		   data.setClassIndex(data.numAttributes() - 1);
		   Instance inst;
		   while ((inst = arff.readInstance(data)) != null) {
			 System.out.println(inst.attribute(0).value(0));
		     data.add(inst);
		   }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		List<ArrayList<String>> trainCustomer = new ArrayList<ArrayList<String>>();
		
		trainCustomer.add(new ArrayList<String>(Arrays.asList("6","40","13.62","3.2804","C1")));
		trainCustomer.add(new ArrayList<String>(Arrays.asList("11","21","15.32","2.0232","C1")));
		trainCustomer.add(new ArrayList<String>(Arrays.asList("7","64","16.55","3.1202","C1")));
		trainCustomer.add(new ArrayList<String>(Arrays.asList("3","47","15.71","3.4022","C1")));
		trainCustomer.add(new ArrayList<String>(Arrays.asList("15","10","16.96","2.2825","C1")));
		
		trainCustomer.add(new ArrayList<String>(Arrays.asList("7","10","18.1751","1.2225","C2")));
		trainCustomer.add(new ArrayList<String>(Arrays.asList("1","3","16.4385","0.9869","C2")));
		trainCustomer.add(new ArrayList<String>(Arrays.asList("2","8","12.4786","0.7506","C2")));
		trainCustomer.add(new ArrayList<String>(Arrays.asList("7","11","15.8298","0.5672","C2")));
		trainCustomer.add(new ArrayList<String>(Arrays.asList("5","11","14.6699","1.0147","C2")));
		
		/*student,spend>saving,6,40,13.62,3.2804,C1
		student,spend>saving,11,21,15.32,2.0232,C1
		student,spend>saving,7,64,16.55,3.1202,C1
		student,spend>saving,3,47,15.71,3.4022,C1
		student,spend>saving,15,10,16.96,2.2825,C1
		
		librarian,spend>saving,7,10,18.1751,1.2225,C2
		librarian,spend>saving,1,3,16.4385,0.9869,C2
		librarian,spend>saving,2,8,12.4786,0.7506,C2
		librarian,spend>saving,7,11,15.8298,0.5672,C2
		librarian,spend>saving,5,11,14.6699,1.0147,C2*/

		List<ArrayList<String>> testCustomer = new ArrayList<ArrayList<String>>();
		testCustomer.add(new ArrayList<String>(Arrays.asList("12","19","14.79","3.7697","")));
		testCustomer.add(new ArrayList<String>(Arrays.asList("29","10","16.19","2.4839","")));
		testCustomer.add(new ArrayList<String>(Arrays.asList("28","60","15.46","1.1885","")));
		testCustomer.add(new ArrayList<String>(Arrays.asList("15","41","21.26","1.4379","")));
		testCustomer.add(new ArrayList<String>(Arrays.asList("2","9","19.7207","0.6913","")));
		
		System.out.println(testCustomer);
		System.out.println();
		System.out.println();
		KNN knn = new KNN(trainCustomer, testCustomer, 3);
		knn.knnCalculation();
	}
}
