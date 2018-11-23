package EmTest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;
import com.thoughtworks.selenium.webdriven.commands.WaitForPageToLoad;

import Page.EM.ArticleSubmissionPage;
import Page.EM.AuthorMainmenuPage;
import Page.EM.LoginPage;
import base.TestBase;
import utility.ConfigProperties;
import utility.Utils;


public class EmLoginTest extends TestBase{
	
	LoginPage loginpage;
	AuthorMainmenuPage authorpage;
	ArticleSubmissionPage submissionpage;
	
	private static String filePath = System.getProperty("user.dir") + "\\SuiteFiles\\14598_HARWBeta150-D-18-00001.docx";
	
	@BeforeClass
	public void preSuite() throws Exception {
	 
	  Utils.NavigatetoLink(getUrl());
	  
	}
	
	@Test
	public void AuthorLogin() throws InterruptedException
	{		
		loginpage= new LoginPage();
		authorpage = new AuthorMainmenuPage();
		//List<WebElement> Element = getDriverInstance().findElements(By.tagName("frame"));
		 // System.out.println(Element);
		  // System.out.println(driver.findElements(By.tagName("frame")).size());
		
		
		
		Utils.switchToFrame("content");
		Utils.switchToFrame("login");
		
		
		loginpage.login(loginpage.username, loginpage.password, "joeharw", "joe123", loginpage.authorloginbutton);
		
		Utils.waitFor(5);
		//Utils.waitForElement(authorpage.submitnewmanuscript, 60);
		
		//waitForPageLoad();
		
		Utils.switchToFrame("content");
		
		//Utils.waitForElement(authorpage.submitnewmanuscript, 60);
		
		String text = getDriverInstance().findElement(By.xpath(".//*[@id='twoColumnLayout']/div[1]/h3")).getText();
		
		Assert.assertEquals(text, "Author Main Menu");
		System.out.println("Title Verified");
	}
	
	@Test
	public void SubmitNewManuscript() throws InterruptedException, IOException
	{
		loginpage= new LoginPage();
		authorpage = new AuthorMainmenuPage();
		submissionpage = new ArticleSubmissionPage();
		
		
		authorpage.submitnewmanuscript.click();
		
		Utils.waitFor(5);
	
			
		if(Utils.isPresent(authorpage.revisionpopup))
		{
			authorpage.revisionpopup.get(0).click();
	    }
		
	   if(Utils.isPresent(authorpage.multiplerevisionpopup))
	    {
	    authorpage.multiplerevisionpopup.get(0).click();
	    }
	   
	   submissionpage.articletype.click();
	 
	   Select articletype = new Select(submissionpage.articletype);
		
		articletype.selectByVisibleText("Original Article");
		
		submissionpage.proceedbtn.click();
		
		Utils.waitForElement(submissionpage.browsefiles, 10);
		
		submissionpage.browsefiles.click();
		
		//Utils.waitFor(5);
				
		//Thread.sleep(5000);
		
		//Runtime.getRuntime().exec("D:\\Workspace\\TestAutomation\\SuiteFiles\\EMFileUpload.exe");
		
		Utils.uploadFileWithRobot(filePath);
		
		System.out.println("Uploading manuscript...");
		
		Thread.sleep(5000);
		
		Utils.waitForElement(submissionpage.proceedbtn1, 30);
		
		submissionpage.proceedbtn1.click();
		
		Utils.waitForElement(submissionpage.proceedbtn, 10);
	    
		submissionpage.proceedbtn.click();
		
		Utils.waitForElement(submissionpage.selectanswer, 10);
		
		Select answer = new Select(submissionpage.selectanswer);
		
		answer.selectByVisibleText("My manuscript has associated data in a data repository");
		
		submissionpage.proceedbtn.click();
		
		Utils.waitFor(5);
	}
}
