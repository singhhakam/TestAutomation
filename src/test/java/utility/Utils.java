package utility;


import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.custommonkey.xmlunit.DetailedDiff;
import org.custommonkey.xmlunit.Difference;
import org.custommonkey.xmlunit.XMLUnit;
import org.custommonkey.xmlunit.Diff;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import base.TestBase;


public class Utils {

	

	 public static boolean waitFor(int iSeconds)
	    {
	        try
	        {
	            Thread.sleep(iSeconds * 1000);
	        }
	        catch (Exception e)
	        {
	           
	            return false;
	        }
	        return true;
	    }
	
	
	

	 static public void NavigatetoLink(String sURLLink)
	    {

	        try
	        {
	            long start = System.currentTimeMillis();
	            waitFor(2);
	            TestBase.getDriverInstance().get(sURLLink);
	            long finish = System.currentTimeMillis();
	            double totalTime = (finish - start);
	            double loadtime = (totalTime / 1000);
	            String sTotalTime = Double.toString(loadtime);
	            System.out.println("Total Time for page load : " + sTotalTime + " sec");
	        }
	        catch (AssertionError e)
	        {
	           // Log.warn("ERROR: Cannot navigate to link");
	        	e.printStackTrace();
	        }
	   
	    }	   
	 
	

	 public static void waitForElement(WebElement element,int timeout)
	    {
	        WebDriverWait wait = new WebDriverWait(TestBase.getDriverInstance(), timeout);
	        wait.until(ExpectedConditions.elementToBeClickable(element));
	    }


static HashMap<String, String> fileNameAndPath= new HashMap<String,String>();

public static HashMap<String,String> listFilesAndFilesSubDirectories(String directoryName){
	
	//HashMap<String, String> fileNameAndPath= new HashMap<String,String>();
	
	File directory = new File(directoryName);
    //get all the files from a directory
    File[] fList = directory.listFiles();
   // System.out.println(fList);
    for (File file : fList){
        if (file.isFile()){
            fileNameAndPath.put(file.getName(),file.getAbsolutePath());
            System.out.println(fileNameAndPath);
         } else if (file.isDirectory()){
            listFilesAndFilesSubDirectories(file.getPath());
           // System.out.println(file.getPath());
        }
      }
    return fileNameAndPath;
}

public static void stringToDom(String xmlSource, String FileName) throws SAXException, ParserConfigurationException, IOException, TransformerException{
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder = factory.newDocumentBuilder();
    Document doc = builder.parse(new InputSource(new StringReader(xmlSource)));
    // Use a Transformer for output
    TransformerFactory tFactory = TransformerFactory.newInstance();
    Transformer transformer = tFactory.newTransformer();

    DOMSource source = new DOMSource(doc);
    StreamResult result = new StreamResult(new File("C:\\AutomationFramework\\TestAutomation\\xml\\"+""+FileName+".xml"));
    transformer.transform(source, result);
}  

static String xmlFileReportName;
private static List <Difference> differences;
private static List <Difference> filterDifferences= new ArrayList<Difference>() ;



public static void compareXML(String inputXmlPath,String expectedXmlPath) throws
SAXException, IOException{
	
	FileInputStream fis1 = new FileInputStream(inputXmlPath);
    FileInputStream fis2 = new FileInputStream(expectedXmlPath);
 
    // using BufferedReader for improved performance
    BufferedReader  source = new BufferedReader(new InputStreamReader(fis1));
    BufferedReader  target = new BufferedReader(new InputStreamReader(fis2));
 
    //configuring XMLUnit to ignore white spaces
    XMLUnit.setIgnoreWhitespace(true);
    
    Diff xmlDiff = new Diff(source, target);
    
    //for getting detailed differences between two xml files
    DetailedDiff detailXmlDiff = new DetailedDiff(xmlDiff);
    
    differences=detailXmlDiff.getAllDifferences();

    Utils.printDifferences(differences);
    
}



public static void printDifferences(List allDifferences) throws IOException{
	
	Date date = new Date();
	//date.setHours(date.getHours() + 8);
	DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmssss");
	//System.out.println("Date");
	//PrintWriter outputfile = new PrintWriter(new BufferedWriter(new FileWriter("C:\\AutomationFramework\\Automation_AccuracyResult_"+""+dateFormat.format(date)+".txt",true)));
	PrintWriter outputfile = new PrintWriter(new BufferedWriter(new FileWriter("C:\\AutomationFramework\\Automation_AccuracyResult_Batch9.txt",true)));
	int totalDifferences = allDifferences.size();
    System.out.println("===============================");
    outputfile.println("===============================");
    System.out.println(xmlFileReportName);
    outputfile.println(xmlFileReportName);
    System.out.println("Total differences : " + totalDifferences);
    outputfile.println("Total differences : " + totalDifferences);
    System.out.println("================================");
    outputfile.println("================================");
    
    Iterator<Difference> iterator = allDifferences.iterator();
	while (iterator.hasNext()) {
		System.out.println(xmlFileReportName);
		  outputfile.println(xmlFileReportName);
	   Difference difflist=iterator.next();
	   // String filterResult=difflist.toString();
	   // if(!(filterResult.contains("application")))
	   // {
	    System.out.println(difflist);
		outputfile.println(difflist);
		 
		//System.out.println(xmlFileReportName);
	    }
	outputfile.close();
}

public static void switchToFrame(String frameName)
{
	TestBase.getDriverInstance().switchTo().frame(frameName);
}

public static boolean isPresent(List<WebElement> popup)
{
	return popup != null && popup.size() > 0;
}


public static void uploadFileWithRobot (String imagePath) throws InterruptedException {
    StringSelection stringSelection = new StringSelection(imagePath);
    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
    clipboard.setContents(stringSelection, null);

    Robot robot = null;

    try {
        robot = new Robot();
    } catch (AWTException e) {
        e.printStackTrace();
    }

    robot.delay(250);
    robot.keyPress(KeyEvent.VK_ENTER);
    robot.keyRelease(KeyEvent.VK_ENTER);
    robot.keyPress(KeyEvent.VK_CONTROL);
    robot.keyPress(KeyEvent.VK_V);
    robot.keyRelease(KeyEvent.VK_V);
    robot.keyRelease(KeyEvent.VK_CONTROL);
    Thread.sleep(5000);
    robot.keyPress(KeyEvent.VK_ENTER);
    robot.delay(150);
    robot.keyRelease(KeyEvent.VK_ENTER);
}


}











