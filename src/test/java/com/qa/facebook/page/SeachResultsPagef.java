package com.qa.facebook.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.log4testng.Logger;

public class SeachResultsPagef extends BasePageWeb {
	private Logger log = Logger.getLogger(SeachResultsPagef.class);
	
	//create a constructor
	public SeachResultsPagef() {
		PageFactory.initElements(driver, this);
	}

@FindBy(xpath="//h1[contains(@dir,'auto')]")
private WebElement searchresultstext;

@FindBy(xpath="(//span[contains(@class,'l9j0dhe7')])[1]")
private WebElement hometab;

	
String resultspagetitle = "Facebook";	
	
public void verifySearchResultsPageTitle() throws InterruptedException	{
	log.debug("Verify serarch results page title");
	wait.until(ExpectedConditions.titleContains(resultspagetitle));
	Thread.sleep(2000);
	Assert.assertTrue(driver.getPageSource().contains(resultspagetitle));
}

public void VerifysearchResults() {
	log.debug("verify the search results text");
	wait.until(ExpectedConditions.visibilityOf(searchresultstext));
	log.debug("Get the serach results text from webpage");
	String txt = searchresultstext.getText();
	System.out.println("Results text is:"+ txt);
}
	
public void clickOnHomeTab() throws InterruptedException {
log.debug("Click on home tab ");	
clickUsingJsExecutor(hometab);
}
	
	
	
	
	
	
	
	
	
}
