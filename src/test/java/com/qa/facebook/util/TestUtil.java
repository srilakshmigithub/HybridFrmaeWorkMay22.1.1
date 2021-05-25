package com.qa.facebook.util;

import java.io.File;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.log4testng.Logger;

import com.qa.facebook.base.TestBase;

public class TestUtil extends TestBase {

	public static WebDriver driver = null;

	private static Logger log = Logger.getLogger(TestUtil.class);

	public static String captureScreenShot(String methodname) {
		String fileName = getScreenshotName(methodname);

		String directory = "target/surefire-reports/failedtestscreenshots/";
		log.debug("Creates specified folder structure by mkdirs() ");
		new File(directory).mkdirs();
		String path = fileName + directory;
		try {
			log.debug("Capturing the screen shot using takes screenshot interface and getScreenshotAs()");
			File srcfile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			log.debug("Copying the screenshot file to specified location under my project");
			FileUtils.copyFile(srcfile, new File(path));

			log.debug("*********************************************");
			log.debug("screen shot stored at path");
			log.debug("*********************************************");
		} catch (Exception e) {
			e.printStackTrace();

		}
		return path;
	}

	private static String getScreenshotName(String methodname) {

		// create object for Date class

		Date dc = new Date();
		String filename = methodname + "-" + dc.toString().replace(" ", "_").replace(" ", "_") + ".png";

		return filename;
	}

}
