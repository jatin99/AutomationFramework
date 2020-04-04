package com.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class XLUtils {

	public static FileInputStream fi;
	public static FileOutputStream fo;
	public static XSSFWorkbook wb;
	public static XSSFSheet ws;
	public static XSSFRow row;
	public static XSSFCell cell;

	public static int getRowCount(String xlfile, String xlsheet) throws IOException {
		File file=new File(xlfile);
		fi = new FileInputStream(file);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(xlsheet);
		int rowcount = ws.getLastRowNum();
		wb.close();
		fi.close();
		return rowcount;
	}

	public static int getCellCount(String xlfile, String xlsheet, int rownum) throws IOException {
		fi = new FileInputStream(xlfile);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(xlsheet);
		row = ws.getRow(rownum);
		int cellcount = row.getLastCellNum();
		wb.close();
		fi.close();
		return cellcount;
	}

	public static String getCellData(String xlfile, String xlsheet, int rownum, int colnum) throws IOException {
		fi = new FileInputStream(xlfile);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(xlsheet);
		row = ws.getRow(rownum);
		cell = row.getCell(colnum);
		String data;
		try {
			DataFormatter formatter = new DataFormatter();
			String cellData = formatter.formatCellValue(cell);
			return cellData;
		} catch (Exception e) {
			data = "";
		}
		wb.close();
		fi.close();
		return data;
	}

	public static void setCellData(String xlfile, String xlsheet, int rownum, int colnum, String data)
			throws IOException {
		fi = new FileInputStream(xlfile);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(xlsheet);
		row = ws.getRow(rownum);
		cell = row.createCell(colnum);
		cell.setCellValue(data);
		fo = new FileOutputStream(xlfile);
		wb.write(fo);
		wb.close();
		fi.close();
		fo.close();
	}

	public Connection getConnection() throws Exception {
		String driver = "sun.jdbc.odbc.JdbcOdbcDriver";
		String url = "jdbc:odbc:excelDB";
//	    String username = "yourName";
//	    String password = "yourPass";
		Class.forName(driver);
		return DriverManager.getConnection(url);// , username, password);
	}

	public void excelDBtoCheckAmazonBrandExist(String excelQuery) throws Exception {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		conn = getConnection();
		stmt = conn.createStatement();
		excelQuery = "select symptoms_1 from [Sheet1$]";
		rs = stmt.executeQuery(excelQuery);

		while (rs.next()) {
			// Fill the data for your drop-down list
		}

		rs.close();
		stmt.close();
		conn.close();
	}
	
	@DataProvider(name="LoginAmazonData")
	public String [][] getData() throws IOException
	{
		String path=System.getProperty("user.dir")+"\\Data\\Data.xlsx";
		int rownum=getRowCount(path, "login");
		int colcount=getCellCount(path,"login",1);
		
		String logindata[][]=new String[rownum][colcount];
		
		for(int i=1;i<=rownum;i++)
		{
			for(int j=0;j<colcount;j++)
			{
				logindata[i-1][j]=getCellData(path,"login", i,j);//1 0
				
			}
				
		}
	return logindata;
	}
//	public static void main(String[] args) throws IOException {
//		// TODO Auto-generated method stub
//		// readXLSFileValues();
//
//		System.out.println(getCellData(
//				"C://Users//vp1515//eclipse-workspace//AttEco//src//test//java//com//eco//testData//LoginData.xls",
//				"login", 1, 0));
//	}

}