package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class Data {
	
	@DataProvider	
	public String[][] getData() throws IOException {
		
		String path = ".\\testdata\\DataProvider.xlsx"; // reading data from xlutils 
		ExcelUtility xlutil= new ExcelUtility(path);
		
		int totalrow = xlutil.getRowCount("Sheet1");
		int totalcols= xlutil.getCellCount("Sheet1", 1);
		
		String logInData[][] = new String [totalrow][totalcols];    // created two dimensional array to store data
		
		for(int i=0;i<totalrow;i++) {        // 1  read the data from xl and store in two dimensional aray
			for(int j=0;j<totalcols;j++) {   //0 i is row, j is colmn
				logInData[i-1][j]=xlutil.getCellData("Sheet1", i, j); //1,0
			}
		}
		return logInData;

}
}
