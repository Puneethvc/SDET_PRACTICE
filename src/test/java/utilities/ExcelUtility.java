package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {
	
	public FileInputStream fis;
	public FileOutputStream fos;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	public XSSFCellStyle style;
	public String path;
	
	
	public ExcelUtility(String path) {
		this.path=path;
	}
	
	public int getRowCount(String sheetname) throws IOException {
		fis= new FileInputStream(path);
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheet(sheetname);
		int rowcount = sheet.getLastRowNum();  

		workbook.close();
		fis.close();
		return rowcount; 
	}
	
	public int getCellCount(String sheetname, int rownum) throws IOException {
		fis= new FileInputStream(path);
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheet(sheetname);
		row = sheet.getRow(rownum);
		int cellcount=row.getLastCellNum();
		workbook.close();
		fis.close();
		return cellcount;
		 
	}
	
	public String getCellData(String sheetname, int rownum,int colnum) throws IOException {
		fis= new FileInputStream(path);
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheet(sheetname);
		row = sheet.getRow(rownum);
		cell=row.getCell(colnum);	
		
		DataFormatter formatter = new DataFormatter();
		String data;
		try {
			data = formatter.formatCellValue(cell);
		}
		catch(Exception e) {
			data="";
		}
		
		workbook.close();
		fis.close();
		return data;
		 
	}
	
	public void setCellData(String sheetname, int rownum, int cellnum, String data) throws IOException, InvalidFormatException {
			
		File xlfile = new File(path);
		
		if(!xlfile.exists()) {
			workbook = new XSSFWorkbook(xlfile);
			fis = new FileInputStream(path);
			workbook.write(fos);
		}
		
		fis = new FileInputStream(path);
		workbook = new XSSFWorkbook(fis);
		
		if(workbook.getSheetIndex(sheetname)==-1) { // if sheet not exist then create new sheet
			workbook.createSheet(sheetname);
			sheet = workbook.getSheet(sheetname);
		}
		
		if(sheet.getRow(rownum) == null) {
			sheet.createRow(rownum);
			row=sheet.getRow(rownum);
			
			cell= row.createCell(cellnum);
			cell.setCellValue(data);
			
			fos= new FileOutputStream(path);
			workbook.write(fos);
			workbook.close();
			fis.close();
			fos.close();
		}				
	}
	
	public void fillGreenColour(String sheetname, int rownum, int colnum) throws IOException {
		
		fis= new FileInputStream(path);
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheet(sheetname);
		
		row = sheet.getRow(rownum);
		cell=row.getCell(colnum);
		
		style = workbook.createCellStyle();
		
		style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		cell.setCellStyle(style);
		workbook.write(fos);
		workbook.close();
		fis.close();
		fos.close();
		
	}
	
	public void fillRedColor(String sheetname, int rownum, int colnum) throws IOException {
		
		fis= new FileInputStream(path);
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheet(sheetname);
		
		row = sheet.getRow(rownum);
		cell = row.getCell(colnum);
		
		style = workbook.createCellStyle();
		
		style.setFillBackgroundColor(IndexedColors.RED.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		cell.setCellStyle(style);
		workbook.write(fos);
		workbook.close();
		fis.close();
		fos.close();
	}
	


}
