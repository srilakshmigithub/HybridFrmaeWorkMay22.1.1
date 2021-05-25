package com.qa.facebook.page;

import org.apache.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class FaceBookLoggedInPage extends BasePageWeb {
private Logger log = Logger.getLogger( FaceBookLoggedInPage .class);
//create a constructor
public  FaceBookLoggedInPage() {
	PageFactory.initElements(driver, this);
}
@FindBy(xpath="//*[contains(@height,'28')]")
private WebElement groupsIcon;

@FindBy(xpath="//a[@href='/notifications/']")

private WebElement notificationsicon;


@FindBy(xpath="(//i[@data-visualcompletion='css-img'])[4]")
private WebElement profileimageicon;

@FindBy(xpath="//span[contains(.,'Log Out')]")
private WebElement signoutlink;

@FindBy(xpath="//input[@type='search']")
private WebElement searchbox;


public void verifyGroupsIcon() throws InterruptedException {
log.debug("Verify the groups icon ");	
wait.until(ExpectedConditions.visibilityOf(groupsIcon))	;
Thread.sleep(3000);
//Assert.assertEquals(groupsIcon, "groups is present");

}

public void clickOnNotifyIcon() throws InterruptedException {
	log.debug("clcik on notification icon");
	click(notificationsicon);
	
}

public void dosignOut() throws InterruptedException {
	log.debug("Perform the logged out operation fisrt click on image then signout");
	click(profileimageicon);
	click(signoutlink);
}
public SeachResultsPagef doPeopleSearch(String keyword) throws InterruptedException {
	
	log.debug("perform the people search");
	sendKey(searchbox, keyword);
	Thread.sleep(2000);
	log.debug("press the enter key send the name");
	searchbox.sendKeys(Keys.ENTER);
	return new SeachResultsPagef();
}


}