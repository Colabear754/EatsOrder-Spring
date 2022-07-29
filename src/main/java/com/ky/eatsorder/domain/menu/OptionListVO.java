package com.ky.eatsorder.domain.menu;

import java.util.ArrayList;

public class OptionListVO {
	OptionGroupVO optionGroup;
	ArrayList<OptionInfoVO> optionList;
	
	public OptionListVO(OptionGroupVO optionGroup, ArrayList<OptionInfoVO> optionList) {
		this.optionGroup = optionGroup;
		this.optionList = optionList;
	}

	public OptionGroupVO getOptionGroup() {
		return optionGroup;
	}

	public void setOptionGroup(OptionGroupVO optionGroup) {
		this.optionGroup = optionGroup;
	}

	public ArrayList<OptionInfoVO> getOptionList() {
		return optionList;
	}

	public void setOptionList(ArrayList<OptionInfoVO> optionList) {
		this.optionList = optionList;
	}
}