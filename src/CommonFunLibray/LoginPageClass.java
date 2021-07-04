package CommonFunLibray;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPageClass {
WebDriver driver;
public LoginPageClass(WebDriver driver)
{
	this.driver=driver;
}
//Repository
@FindBy(name="username")
WebElement Enter_Username;
@FindBy(name="password")
WebElement Enter_password;
@FindBy(name="btnsubmit")
WebElement ClickLoginBtn;
@FindBy(name="btnreset")
WebElement ClickResetBtn;
public void verifyLogin(String username,String password)
{
	WebDriverWait wait = new WebDriverWait(driver, 20);
	wait.until(ExpectedConditions.elementToBeClickable(ClickResetBtn));
	this.ClickResetBtn.click();
	this.Enter_Username.sendKeys(username);
	this.Enter_password.sendKeys(password);
	this.ClickLoginBtn.click();
}
}
