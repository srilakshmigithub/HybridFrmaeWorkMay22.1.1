package com.qa.facebook.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.log4testng.Logger;

public class FaceBookHomePage extends BasePageWeb {

	private Logger log = Logger.getLogger(FaceBookHomePage.class);

	// create a constructor

	public FaceBookHomePage() {

		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath="//*[contains(@alt,'Facebook')]")
	private WebElement facebookheader;

	@FindBy(xpath="//input[@id='email']")
	private WebElement emaileditbox;

	@FindBy(xpath="//input[contains(@id,'pass')]")
	private WebElement pwdeditbox;
	
	@FindBy(xpath="//button[contains(@name,'login')]")
	private WebElement signInButton;
	
	@FindBy(xpath="//a[contains(.,'Create a Page')]")
	private WebElement createpagelink;

	String homepagetitle = "Facebook - Log In or Sign Up";
	String signInPageTitle = "Facebook - Log In or Sign Up";

	public void verifyFacebookHeader() {
		log.debug("Verify the facebookheadertext on home page");
		wait.until(ExpectedConditions.visibilityOf(facebookheader));

		//Assert.assertSame(facebookheader, "facebook header text is present");
	}

	public void clickSignInbutton() throws InterruptedException {
		log.debug("Verify the SignInButton in facebook homepage");
		click(signInButton);

	}

	public void verifyCreatePageLink() {
		log.debug("verify the craete a page link on facebbok homepage");
		wait.until(ExpectedConditions.visibilityOf(createpagelink));
	}

	public void verifyHomePageTitle() {
		log.debug("verify the facebook home page title");
	wait.until(ExpectedConditions.titleContains(homepagetitle));
		Assert.assertEquals(driver.getTitle(), homepagetitle);
	}

	public void verifySignInPageTitle() {

		log.debug("Veirfy the facebook sign in page title");
		wait.until(ExpectedConditions.titleContains(signInPageTitle));

		Assert.assertEquals(driver.getTitle(), signInPageTitle);
	}

	public FaceBookLoggedInPage doLogIn(String uname, String pword) throws InterruptedException {
		log.debug("Started log in process");
		sendKey(emaileditbox, uname);
		sendKey(pwdeditbox, pword);
		clickSignInbutton();
		return new FaceBookLoggedInPage();
	}

}
