package Page.EM;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import Page.BasePage;

public class ArticleSubmissionPage extends BasePage {

	@FindBy(xpath=".//input[@id='btnChooseFiles']")
	public static WebElement browsefiles;
	
	@FindBy(xpath=".//*[@id='ddlArticleType']")
	public static WebElement articletype;
	
	@FindBy(xpath=".//button[@id='proceedButton']")
	public static WebElement proceedbtn;
	
	@FindBy(xpath=".//button[@id='proceedButton']")
	public static WebElement proceedbtn1;
	
	@FindBy(xpath=".//*[@id='QR1_1_Q3_RSP_3']")
	public static WebElement selectanswer;
	
	@Override
	public void verifyPage() {
		// TODO Auto-generated method stub
		waitForPageLoad();
}
}