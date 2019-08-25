package org.hexa.read.ExcelRead;

	import java.io.File;
	import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
	import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Reve {
	public static void main(String[] args) throws IOException {
		System.setProperty("Webdriver.chrome.driver","C:\\Users\\Welcome\\eclipse-workspace\\ExcelRead\\driver\\chromedriver.exe");
		WebDriver d=new ChromeDriver();
		d.get("https://www.facebook.com");
		WebElement month=d.findElement(By.id("month"));
		Select s=new Select(month);
		List<WebElement> op = s.getOptions();
		List<String> list = new ArrayList<String>();
		for (WebElement x : op) {
			String text = x.getText();
			list.add(text);
		}
		File f=new File("C:\\Users\\Welcome\\eclipse-workspace\\TestJava\\excell\\depend.xlsx");
		FileInputStream stream=new FileInputStream(f);
        Workbook w=new XSSFWorkbook(stream);
		Sheet s1=w.createSheet("Sheet1");
		for(int i=0;i<list.size();i++) {
		Row r=s1.createRow(i);
		Cell c = r.createCell(0);
		c.setCellValue("list.get(i)");
		}
		FileOutputStream o=new FileOutputStream(f);
		w.write(o);
		System.out.println("Done");
		
	}
	}
