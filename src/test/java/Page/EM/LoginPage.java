package Page.EM;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import Page.BasePage;
import base.TestBase;

public class LoginPage extends BasePage {
	
	@FindBy(xpath="//input[contains(@id,'username')]")
	public static WebElement username;
	
	@FindBy(xpath="//input[contains(@name,'password')]")
	public static WebElement password;
	
	@FindBy(xpath="//input[contains(@name,'authorLogin')]")
	public static WebElement authorloginbutton;
	
	
	public static void login(WebElement username, WebElement password, String str1, String str2, WebElement button)
	{
		username.click();
		username.sendKeys(str1);
		
		password.clear();
		password.sendKeys(str2);
		
		button.click();
		
	}
	
	@Override
	public void verifyPage() {
		// TODO Auto-generated method stub
		waitForPageLoad();
	}

}
