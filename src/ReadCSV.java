import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import au.com.bytecode.opencsv.CSVReader;

public class ReadCSV {

    private String inputFile;

    public void setInputFile(String inputFile) {
	this.inputFile = inputFile;
    }

    public ArrayList<ArrayList<String>> read() throws IOException {
	String csvFilename = inputFile;
	CSVReader csvReader = new CSVReader(new FileReader(csvFilename));
	ArrayList<ArrayList<String>> stringList = new ArrayList<ArrayList<String>>();
	ArrayList<String> attributes;
	String[] row = csvReader.readNext();
	try {
	    while ((row = csvReader.readNext()) != null) {
		attributes = new ArrayList<String>();
		for (int i = 0; i < row.length; i++) {
		    attributes.add(row[i]);
		}
		stringList.add(attributes);
	    }

	} catch (Exception e) {
	    e.printStackTrace();
	}
	return stringList;
    }

}
