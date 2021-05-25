package com.qa.facebook.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

//import org.testng.log4testng.Logger;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {
	
	private Logger log = Logger.getLogger(TestBase.class);
	public static WebDriver driver = null;
	public WebDriverWait wait = null;

	public Properties prop = null;

	public String readPropertyValue(String key) throws IOException {
		log.info("create object for properties class" );
		prop = new Properties();
		log.debug("read the properties file");

		FileInputStream fis;
		try {
			fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\java\\com\\qa\\facebook\\config\\config.properties");
			log.info("load all the properties");
			prop.load(fis);

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
		return prop.getProperty(key);

	}

@BeforeSuite
	public void setup() throws IOException {
		System.out.println("SETUP ------");
		log.debug("started to executing the browser launching setup()");
		log.debug("fetch the browser value from the properties file");
		String browserName = readPropertyValue("browser");
		
		System.out.println("BROWSER NAME  ------ " + browserName);
		
		if (browserName.equalsIgnoreCase("chrome")) {
			System.out.println("DRIVER ------ " + driver);
			log.debug("setting my chrome browser exe file by webdrivermanager class");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			
			

		} else if (browserName.equalsIgnoreCase("firefox")) {
			log.debug("setting my firefox browser exe file");
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();

		} else if (browserName.equalsIgnoreCase("edge")) {
			log.debug("setting my edge browser exe file");
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		} else if (browserName.equalsIgnoreCase("ie")) {
			log.debug("setting my ie browser exe file");
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();

		}
		log.debug("maximize the browser");
		driver.manage().window().maximize();
		log.debug("add implicit wait");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		log.debug("create a webdriverwait object explicit wait command ");
		wait = new WebDriverWait(driver, 30);
		log.debug("Open the application url:" + readPropertyValue("applicationUrl"));
		driver.get(readPropertyValue("applicationUrl"));

	}

	@AfterSuite
	public void tearDown() {
		log.debug("Triggering browser closing activity");
		if (driver != null) {
			driver.quit();
		}
	}
}
