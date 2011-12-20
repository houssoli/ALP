package com.lohika.alp.flexpilot.view;

import com.lohika.alp.flexpilot.FindBy;
import com.lohika.alp.flexpilot.FlexElement;
import com.lohika.alp.flexpilot.driver.FlexDriver;
import com.lohika.alp.selenium.log.LogDescription;

public class FormTab {

	private FlexDriver fDriver;

	@LogDescription(name = "Name", type = "edit")
	@FindBy(chain = "id:nameTxt")
    public FlexElement txtName;

	@LogDescription(name = "Submit", type = "button")
	@FindBy(chain = "id:submitBtn")
    public FlexElement btnSubmit;

}
