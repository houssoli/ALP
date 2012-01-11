package com.lohika.alp.flexpilot;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.log4j.Logger;
import org.apache.xmlcommons.Version;
import org.openqa.selenium.Platform;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.lohika.alp.flexpilot.driver.FlexPilotDriver;
import com.lohika.alp.flexpilot.pagefactory.FlexPilotFactory;
import com.lohika.alp.flexpilot.pagefactory.FlexPilotFactoryJAXB;
import com.lohika.alp.flexpilot.pagefactory.FlexPilotPageFactory;
import com.lohika.alp.flexpilot.view.ButtonTab;
import com.lohika.alp.flexpilot.view.FormTab;
import com.lohika.alp.flexpilot.view.GridTab;
import com.lohika.alp.flexpilot.view.SliderTab;
import com.lohika.alp.selenium.RemoteWebDriverTakeScreenshotFix;
import com.lohika.alp.selenium.log.DescribedElement;
import com.lohika.alp.selenium.log.LogDescriptionBean;
import com.lohika.alp.selenium.log.LoggingWebElement;
import com.lohika.alp.selenium.AlpWebDriverFactory;

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
		//wDriver = new RemoteWebDriverTakeScreenshotFix(new URL(env.getHubURL()), cap);
		wDriver = AlpWebDriverFactory.getDriver(env.getHubURL(), cap);
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
		((DescribedElement)tab).setDescription(new LogDescriptionBean(
				"Tab '"+tabName+"'"));
		tab.click();
	}
	
	@Test(groups = "alp-flex-pilot")
	public void isFlexLoaded(){
		boolean flexloading = fpDriver.isFlexLoaded();
		
		if (flexloading == true){
			
		
			String version = fpDriver.getVersion();
			final String versionFlex = "FlexPilot (Stable): v0.9";
			if (version.equals(versionFlex))
				logger.info("Version Ok");
			else
				logger.error("Version Error");
			}
		
		else
		logger.error("It is no flex on the page");
	
}
	
	@Test(groups = "alp-flex-pilot", dependsOnMethods="isFlexLoaded")
	public void setSelectionTest() {
		selectTab("List");
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
		((DescribedElement)app).setDescription(new LogDescriptionBean("Context Menu", ""));
		app.contextClick(0);
		
		FlexElement btnOk = fpDriver.findElement(By.name("OK"));
		btnOk.click();
		
	}

	@Test(groups = "alp-flex-pilot", dependsOnMethods="contextClickTest")
	public void isDisplayedTest() {
		selectTab("Button");
		ButtonTab buttonTab = FlexPilotPageFactory.initElements(fpDriver, ButtonTab.class);;
		if (buttonTab.btnStandard.isDisplayed())
			logger.info("Ok");
		else
			logger.error("Error");
		
	}
	
	@Test(groups = "alp-flex-pilot", dependsOnMethods="isDisplayedTest")
	public void mouseUp() {
		selectTab("Slider");
		SliderTab sliderTab = FlexPilotPageFactory.initElements(fpDriver, SliderTab.class);;
	    sliderTab.sliderv.mouseDown();
	    sliderTab.sliderv.mouseUp();
	    sliderTab.sliderv.mouseOver();
	    sliderTab.sliderv.mouseOut();
	}
	
	@Test(groups = "alp-flex-pilot", dependsOnMethods="mouseUp")
	public void dragAndDrop() {
		selectTab("List");

		FlexElement from = fpDriver.findElement(By.chain(String.format(
				"text:%s", "Boots")));
		FlexElement to = fpDriver.findElement(By.id("destlist"));
		((DescribedElement)to).setDescription(
				new LogDescriptionBean("Destination List", "list"));
		
		// TODO: Need to add checking for element's description on null
		from.dragAndDrop(to, new Point(5,5));
		
	}
		
	@AfterClass(alwaysRun = true)
	public void onFinish() {
		if (wDriver != null) wDriver.quit();
		logger.info("test finish");
	}
}
