package Page.EM;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import Page.BasePage;

public class AuthorMainmenuPage extends BasePage {
	
	@FindBy(xpath="//div[@id='twoColLayoutItems']/div/div[2]/fieldset[@id='tableContainer']/div/a[1]")
	public static WebElement submitnewmanuscript;
	
	//@FindBy(xpath="//input[contains(@name,'ctl01$BtnNo')]")
	//public static WebElement revisionpopup;
	
	//@FindAll({@FindBy(xpath="//input[contains(@name,'ctl01$BtnNo')]")}) 
	//List<WebElement> revisionpopup;
	
	@FindAll({@FindBy(id = "ctl01_BtnNo")}) 
	public List<WebElement> revisionpopup;
	
	@FindAll({@FindBy(name = "ctl01$ctl00")}) 
	public List<WebElement> multiplerevisionpopup;
	
	
	//@FindBy(xpath="//*[@id='ctl01_PnlMoreThanOneOpenAssignments']/div[2]/div[10]/input")
	//@FindBy(xpath="//input[contains(@name,'ctl01$ctl00')]")
	//public static WebElement multiplerevisionpopup;
	
	
	
	
	@Override
	public void verifyPage() {
		// TODO Auto-generated method stub
		waitForPageLoad();
	}

}
