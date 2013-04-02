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
		try {
		   BufferedReader reader = new BufferedReader(new FileReader("/Users/basdag/Documents/workspace_eclipse/WEKA/trainProdSelection.arff"));
		   ArffReader arff = new ArffReader(reader, 100000);
		   Instances data = arff.getStructure();
		   data.setClassIndex(data.numAttributes() - 1);
		   Instance inst;
		   while ((inst = arff.readInstance(data)) != null) {
			 System.out.println(inst);
		     data.add(inst);
		   }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		/*List<ArrayList<String>> testCustomer = new ArrayList<ArrayList<String>>();
		testCustomer.add(new ArrayList<String>(Arrays.asList("12","19","14.79","3.7697","")));
		testCustomer.add(new ArrayList<String>(Arrays.asList("29","10","16.19","2.4839","")));
		testCustomer.add(new ArrayList<String>(Arrays.asList("28","60","15.46","1.1885","")));
		testCustomer.add(new ArrayList<String>(Arrays.asList("15","41","21.26","1.4379","")));
		testCustomer.add(new ArrayList<String>(Arrays.asList("2","9","19.7207","0.6913","")));*/
	}
}
