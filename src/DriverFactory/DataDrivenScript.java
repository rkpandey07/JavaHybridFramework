package DriverFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import CommonFunLibray.LoginPageClass;
import CommonFunLibray.SupplierAddClass;
import Utilities.ExcelFileUtil;

public class DataDrivenScript {
WebDriver driver;
String inputpath ="C:\\ERP_StockAccounting\\TestInput\\Supplierdata.xlsx";
String outputpath ="C:\\ERP_StockAccounting\\TestOutPut\\DataDrivenResults.xlsx";
@BeforeTest
public void adminLogin()
{
System.setProperty("webdriver.chrome.driver", "C:\\ERP_StockAccounting\\CommonDrivers\\chromedriver.exe");
driver= new ChromeDriver();
driver.get("http://projects.qedgetech.com/webapp/login.php");
driver.manage().window().maximize();
LoginPageClass login = PageFactory.initElements(driver, LoginPageClass.class);
login.verifyLogin("admin", "master");
}
@Test
public void addSupplier()throws Throwable
{
	//create object for accessing xl methods
	ExcelFileUtil xl = new ExcelFileUtil(inputpath);
	int rc =xl.rowCount("Suppliers");
	Reporter.log("No of rows ::"+rc,true);
	for(int i=1; i<=rc; i++)
	{
	String sname= xl.getCellData("Suppliers", i, 0);
	String address= xl.getCellData("Suppliers", i, 1);
	String city= xl.getCellData("Suppliers", i, 2);
	String country= xl.getCellData("Suppliers", i, 3);
	String cperson= xl.getCellData("Suppliers", i, 4);
	String pnumber= xl.getCellData("Suppliers", i, 5);
	String email= xl.getCellData("Suppliers", i, 6);
	String mnumber= xl.getCellData("Suppliers", i, 7);
	String notes= xl.getCellData("Suppliers", i, 8);
	SupplierAddClass sup =PageFactory.initElements(driver, SupplierAddClass.class);
	String results=sup.verifySupplier(sname, address, city, country, cperson, pnumber, email, mnumber, notes);
	xl.setCellData("Suppliers", i, 9, results, outputpath);
	}
}
@AfterTest
public void tearDown()
{
	driver.close();
}
}
