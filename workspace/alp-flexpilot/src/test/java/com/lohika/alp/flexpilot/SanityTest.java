package com.lohika.alp.flexpilot;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lohika.alp.flexpilot.driver.FlexPilotDriver;
import com.lohika.alp.flexpilot.pagefactory.FlexPilotFactory;
import com.lohika.alp.flexpilot.pagefactory.FlexPilotFactoryJAXB;
import com.lohika.alp.flexpilot.pagefactory.FlexPilotPageFactory;
import com.lohika.alp.flexpilot.view.FormTab;
import com.lohika.alp.flexpilot.view.GridTab;
import com.lohika.alp.selenium.RemoteWebDriverTakeScreenshotFix;

public class SanityTest {
	private Logger				logger	= Logger.getLogger(getClass());
	private FlexPilotFactory	logFactory;
	private WebDriver			wDriver;
	private FlexPilotDriver		fpDriver;
	private Environment			env;
	
	@BeforeClass(alwaysRun = true)
	public void setUp() throws IOException, URISyntaxException {

		env = new Environment();
		DesiredCapabilities cap = new DesiredCapabilities(env.getBrowser(), "",
				Platform.valueOf(env.getPlatform()));
		wDriver = new RemoteWebDriverTakeScreenshotFix(new URL(env.getHubURL()), cap);
		fpDriver = new FlexPilotDriver(wDriver, "testApp");
		logFactory = new FlexPilotFactoryJAXB();
		logger.info("test setUp");
		
        wDriver.get(env.getEnvUrl());

		int timeout = 20;
		while (!fpDriver.isFlexLoaded() && timeout > 0) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {}
			timeout--;
		};

	}
	
	private void selectTab(String tabName) {
		FlexElement tab = fpDriver.findElement(By.chain(String.format(
				"className:CustomTab/text:%s", tabName))); 
		tab.click();
	}
	
	@Test(groups = "alp-flex-pilot")
	public void setSelectionTest() {
		selectTab("Form");
		FormTab formTab = FlexPilotPageFactory.initElements(fpDriver, FormTab.class);;
		formTab.txtName.sendKeys("Hello World!");
		formTab.txtName.setSelection(2, 5);
	}

	@Test(groups = "alp-flex-pilot", dependsOnMethods="setSelectionTest")
	public void contextClickTest() {
		selectTab("Grid");
		GridTab gridTab = FlexPilotPageFactory.initElements(fpDriver, GridTab.class);;
		gridTab.selectRow(1);
		
		FlexElement app = fpDriver.findElement(By.id("testApp"));
		app.contextClick(0);
	}

	@AfterClass(alwaysRun = true)
	public void onFinish() {
		if (wDriver != null) wDriver.quit();
		logger.info("test finish");
	}
}
