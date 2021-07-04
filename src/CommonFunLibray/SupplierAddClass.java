package CommonFunLibray;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

public class SupplierAddClass {
WebDriver driver;
public SupplierAddClass(WebDriver driver)
{
	this.driver=driver;
}
@FindBy(xpath="(//a[text()='Suppliers'])[2]")
WebElement clicksupplier;
@FindBy(xpath="(//span[@data-caption='Add'])[1]")
WebElement ClickAddicon;
@FindBy(name="x_Supplier_Number")
WebElement suppliernum;
@FindBy(name="x_Supplier_Name")
WebElement suppliername;
@FindBy(name="x_Address")
WebElement Address;
@FindBy(name="x_City")
WebElement City;
@FindBy(name="x_Country")
WebElement Country;
@FindBy(name="x_Contact_Person")
WebElement ContactPerson;
@FindBy(name="x_Phone_Number")
WebElement PhoneNumber;
@FindBy(name="x__Email")
WebElement Email;
@FindBy(name="x_Mobile_Number")
WebElement MobileNumber;
@FindBy(name="x_Notes")
WebElement Notes;
@FindBy(name="btnAction")
WebElement ClickAddBtn;
@FindBy(xpath="//button[text()='OK!']")
WebElement ClickConfirmOk;
@FindBy(xpath="(//button[text()='OK'])[6]")
WebElement ClickAlertOk;
@FindBy(xpath="//span[@data-caption='Search']")
WebElement searchpanel;
@FindBy(xpath="//input[@id='psearch']")
WebElement searctextbox;
@FindBy(xpath="//button[@id='btnsubmit']")
WebElement ClickSearchBtn;
@FindBy(xpath="//table[@id='tbl_a_supplierslist']")
WebElement webTablepath;
public String verifySupplier(String Suppliername,String Address,String city,String country,
String contactperson,String phonenumber,String email,String mobilenumber,String notes)
{
	String res="";
	WebDriverWait wait = new WebDriverWait(driver, 20);
	wait.until(ExpectedConditions.elementToBeClickable(clicksupplier));
	this.clicksupplier.click();
	wait.until(ExpectedConditions.elementToBeClickable(ClickAddicon));
	this.ClickAddicon.click();
	wait.until(ExpectedConditions.visibilityOf(suppliernum));
	String expectednum=this.suppliernum.getAttribute("value");
	this.suppliername.sendKeys(Suppliername);
	this.Address.sendKeys(Address);
	this.City.sendKeys(city);
	this.Country.sendKeys(country);
	this.ContactPerson.sendKeys(contactperson);
	this.PhoneNumber.sendKeys(phonenumber);
	this.Email.sendKeys(email);
	this.MobileNumber.sendKeys(mobilenumber);
	this.Notes.sendKeys(notes);
	this.ClickAddBtn.sendKeys(Keys.ENTER);
	wait.until(ExpectedConditions.elementToBeClickable(ClickConfirmOk));
	ClickConfirmOk.click();
	wait.until(ExpectedConditions.elementToBeClickable(ClickAlertOk));
	this.ClickAlertOk.click();
	if(!this.searctextbox.isDisplayed())
		this.searchpanel.click();
	wait.until(ExpectedConditions.visibilityOf(searctextbox));
	this.searctextbox.clear();
	this.searctextbox.sendKeys(expectednum);
	this.ClickSearchBtn.click();
	wait.until(ExpectedConditions.visibilityOf(webTablepath));
	String actualnum= driver.findElement(By.xpath("//table[@id='tbl_a_supplierslist']/tbody/tr[1]/td[6]/div/span/span")).getText();
	if(actualnum.equals(expectednum))
	{
		Reporter.log("Supplier addded::"+expectednum+"   "+actualnum,true);
		res="Pass";
	}
	else
	{
		Reporter.log("Supplier Not addded::"+expectednum+"   "+actualnum,true);
		res="Fail";
	}
	return res;
	}
}
