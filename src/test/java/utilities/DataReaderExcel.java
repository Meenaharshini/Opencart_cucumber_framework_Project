package utilities;

import java.io.FileInputStream;
import java.util.*;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataReaderExcel {

	public static List<HashMap<String, String>> data(String filepath, String sheetname) throws Exception {
		List<HashMap<String, String>> myData = new ArrayList<>();

		try {
			FileInputStream fi = new FileInputStream(filepath);
			XSSFWorkbook wb = new XSSFWorkbook(fi);
			XSSFSheet sheet = wb.getSheet(sheetname);
			Row headerRow = sheet.getRow(0);

			for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
				XSSFRow currentRow = sheet.getRow(i);

				HashMap<String, String> currentKeyValues = new HashMap<String, String>();

				for (int j = 0; j < currentRow.getPhysicalNumberOfCells(); j++) {
					XSSFCell currentCell = currentRow.getCell(j);

					switch (currentCell.getCellType()) {
					case STRING:
						currentKeyValues.put(headerRow.getCell(j).getStringCellValue(),
								currentCell.getStringCellValue());
						break;
					default: 
						System.out.println("No cell value");
						break;
					}
				}
				myData.add(currentKeyValues);
			}
			fi.close();
			wb.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return myData;
	}

}
