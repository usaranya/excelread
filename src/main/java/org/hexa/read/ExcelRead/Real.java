package org.hexa.read.ExcelRead;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Real {
	@Test(dataProvider = "Res")
	public void login(String s1, String s2, String s3, String s4,String s5) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Welcome\\eclipse-workspace\\SkyExpress\\driver\\chromedriver.exe");
		WebDriver d = new ChromeDriver();
		d.get("http://demo.automationtesting.in/Register.html");
		d.manage().window().maximize();
		d.findElement(By.xpath("//input[@type='text'][1]")).sendKeys(s1);
		d.findElement(By.xpath("//input[@type='text']")).sendKeys(s2);
		d.findElement(By.xpath("//textarea[@class='form-control ng-pristine ng-untouched ng-valid']")).sendKeys(s3);
		d.findElement(By.xpath("//input[@type='email']")).sendKeys(s4);
		d.findElement(By.xpath("//input[@type='tel']")).sendKeys(s5);
	}

	@DataProvider(name = "Res")
	public Object[][] data() throws IOException{
		return td();
	}

	public Object[][] td() throws IOException {
		File f = new File("C:\\Users\\Welcome\\eclipse-workspace\\ExcelRead\\Excel\\depend.xlsx");
		FileInputStream stream = new FileInputStream(f);
		Workbook w = new XSSFWorkbook(stream);
		Sheet s = w.getSheet("Sheet1");
		String name = null;
		Row r = s.getRow(0);
		Object[][] obj = new Object[s.getPhysicalNumberOfRows()-1][r.getPhysicalNumberOfCells()];
		for (int i = 1; i < s.getPhysicalNumberOfRows(); i++) {
			Row r1 = s.getRow(i);
			for (int j = 0; j < r.getPhysicalNumberOfCells(); j++) {
				Cell c = r1.getCell(j);
				int Type = c.getCellType();
				if (Type == 1) {
					name = c.getStringCellValue();
				} else if (Type == 0) {
					if (DateUtil.isCellDateFormatted(c));
					name = new SimpleDateFormat("dd/mm/yyyy").format(c.getDateCellValue());
				} else {
					double d = c.getNumericCellValue();
					long l = (long) d;
					name = String.valueOf(l);
				}
				obj[i-1][j] = name;
			}
		}
		return obj;
	}
}
