package com.lohika.alp.flexpilot.view;

import com.lohika.alp.flexpilot.FindBy;
import com.lohika.alp.flexpilot.FlexElement;
import com.lohika.alp.selenium.log.LogDescription;


public class SliderTab {

	@LogDescription(name = "Name", type = "edit")
	@FindBy(chain = "id:vSlider/className:VSliderSkin/className:Button/className:VSliderThumbSkin")
    public FlexElement sliderv;

	}
