import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ReadExcel {

    private String inputFile;

    public void setInputFile(String inputFile) {
    	this.inputFile = inputFile;
    }

    public ArrayList<ArrayList<String>> read() throws IOException {
		File inputWorkbook = new File(inputFile);
		Workbook w;
		ArrayList<ArrayList<String>> stringList = new ArrayList<ArrayList<String>>();
		ArrayList<String> attributes;
		try {
		    w = Workbook.getWorkbook(inputWorkbook);
		    // Get the first sheet
		    Sheet sheet = w.getSheet(0);
		    // Loop over first 10 column and lines
	
		    for (int j = 1; j < sheet.getRows(); j++) {
		    	attributes = new ArrayList<String>();
				for (int i = 0; i < sheet.getColumns(); i++) {
				    Cell cell = sheet.getCell(i, j);
				    //System.out.println(cell.getContents().toString());
				    attributes.add(cell.getContents());
				}
				stringList.add(attributes);
		    }
		} catch (BiffException e) {
		    e.printStackTrace();
		}
		return stringList;
    }
}
