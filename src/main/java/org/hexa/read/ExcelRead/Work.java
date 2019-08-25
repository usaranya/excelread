package org.hexa.read.ExcelRead;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Work {
public static void main(String[] args) throws IOException, InterruptedException {
	File f=new File("C:\\Users\\Welcome\\eclipse-workspace\\ExcelRead\\Excel\\depend.xlsx");
	FileInputStream stream=new FileInputStream(f);
	Workbook w=new XSSFWorkbook(stream);
	Sheet s=w.getSheet("Read");
	Thread.sleep(2000);
	Row r=s.getRow(1);
	Cell c=r.getCell(1);
	System.out.println(c);
	int Type = c.getCellType();
			if(Type==1) {
				String k = c.getStringCellValue();	
				System.out.println(k);
			}
			else if(Type==0) {
				if(DateUtil.isCellDateFormatted(c)) {
					Date d = c.getDateCellValue();
					SimpleDateFormat sf=new SimpleDateFormat("dd/mm/yyyy");
					String date = sf.format(d);
					System.out.println(date);}
					else {
						double num = c.getNumericCellValue();
						long l=(long)num;
				String fina = String.valueOf(l);
				System.out.println(fina);
					}
			}
		}
}
