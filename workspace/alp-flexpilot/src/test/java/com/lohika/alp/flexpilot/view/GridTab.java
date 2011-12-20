package com.lohika.alp.flexpilot.view;

import com.lohika.alp.flexpilot.By;
import com.lohika.alp.flexpilot.FlexElement;
import com.lohika.alp.flexpilot.driver.FlexPilotDriver;

public class GridTab {
	//555-219-2270
	private FlexPilotDriver fDriver;
	
	public GridTab(FlexPilotDriver fDriver) {
		this.fDriver = fDriver;
	}
	
	public void selectRow(int index) {
		FlexElement cell = fDriver.findElement(By.chain(
			String.format("id:dg/className:ListBaseContentHolder/child:[%s]",
			5+index*3)));
		cell.click();
	}

}
