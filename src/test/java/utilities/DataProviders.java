package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	@DataProvider(name="LoginData")
	public String[][] getData() throws IOException {
		
		String path = ".\\testdata\\DataProvider.xlsx"; // reading data from xlutils 
		ExcelUtility xlutil= new ExcelUtility(path);
		
		int totalrows = xlutil.getRowCount("Sheet1");
		int totalcols= xlutil.getCellCount("Sheet1", 1);
		
		String logInData[][] = new String [totalrows][totalcols];    // created two dimensional array to store data
		
		for(int i=1;i<totalrows;i++) {        // 1  read the data from xl and store in two dimensional aray
			for(int j=0;j<totalcols;j++) {   //0 i is row, j is colmn
				logInData[i-1][j]=xlutil.getCellData("Sheet1", i, j); //1,0
			}
		}
		return logInData;	

}
}
