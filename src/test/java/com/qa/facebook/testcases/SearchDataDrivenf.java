package com.qa.facebook.testcases;

import java.io.IOException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import com.qa.facebook.base.TestBase;
import com.qa.facebook.page.FaceBookHomePage;
import com.qa.facebook.page.FaceBookLoggedInPage;
import com.qa.facebook.page.SeachResultsPagef;
import com.qa.facebook.util.ExcelUtils;

public class SearchDataDrivenf extends TestBase{
	
	private Logger log = Logger.getLogger(SearchDataDrivenf.class);
	
private String path = System.getProperty("user.dir")+"\\src\\test\\java\\com\\qa\\facebook\\data\\Searchdata.xlsx";

FaceBookHomePage fbhp;

FaceBookLoggedInPage fblp;

SeachResultsPagef srpagef;

@BeforeClass
public void beforeClass() throws InterruptedException, IOException {
log.debug("Pages Initialization---");	
fbhp = new FaceBookHomePage();

fblp = new 	FaceBookLoggedInPage();
srpagef = new SeachResultsPagef();

log.debug("calling title verification methods");
System.out.println("***************FaceBookHomePage***********");
fbhp.verifyHomePageTitle();
fbhp.verifyFacebookHeader();
fbhp.verifySignInPageTitle();
fblp=fbhp.doLogIn(readPropertyValue("username"), readPropertyValue("password"));
System.out.println("**************FaceBookLoggedInPage ***********");
fblp.verifyGroupsIcon();
fblp.clickOnNotifyIcon();
System.out.println("************");
}

@AfterClass
public void afterClass() throws InterruptedException {
	log.debug("perform the signout operation");
	fblp.dosignOut();
	fbhp.verifyHomePageTitle();
}

@Test(dataProvider = "getData")
public void searchdatadriverf(String keyword) throws InterruptedException {
log.debug("Started to executing search data driven");	
	
srpagef = fblp.doPeopleSearch(keyword);
	
srpagef.verifySearchResultsPageTitle();	
srpagef.clickOnHomeTab();
log.debug("*****************Done**********");
}


@DataProvider
public Object[][] getData() throws Exception {

	Object[][] data = new com.qa.facebook.util.ExcelUtils().getTestdata(path, "Sheet1");
	return data;
}

}
