package runner;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.testng.TestNG;
import org.testng.xml.Parser;
import org.testng.xml.XmlSuite;
import org.xml.sax.SAXException;

public class TestNgParallel {

	public static void main(String args[]) throws ParserConfigurationException, SAXException, IOException

	{
		TestNG testng = new TestNG();
		testng.setXmlSuites((List<XmlSuite>) (new Parser(System.getProperty("user.dir")+"\\TestNgSuites\\testngParallel.xml").parse()));
		testng.setSuiteThreadPoolSize(3);
		testng.run();
	}

}