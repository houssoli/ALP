package com.lohika.alp.flexpilot.view;

import com.lohika.alp.flexpilot.By;
import com.lohika.alp.flexpilot.FlexElement;
import com.lohika.alp.flexpilot.driver.FlexPilotDriver;
import com.lohika.alp.selenium.log.DescribedElement;
import com.lohika.alp.selenium.log.LogDescriptionBean;

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
		StringBuilder sb = new StringBuilder();
		sb.append("Cell [row:");
		sb.append(index);
		sb.append(",column:");
		sb.append(0);
		sb.append("]");
		((DescribedElement)cell).setDescription(new LogDescriptionBean(
				sb.toString(), ""));

		cell.click();
	}

}
