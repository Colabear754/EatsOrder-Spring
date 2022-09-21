package com.ky.eatsorder.domain.etc_classes;

import com.ky.eatsorder.domain.menu.OptionGroupVO;
import com.ky.eatsorder.domain.menu.OptionInfoVO;

import java.util.ArrayList;

public class OptionList {
	private OptionGroupVO optionGroup;
	private ArrayList<OptionInfoVO> optionList;
	
	public OptionList(OptionGroupVO optionGroup, ArrayList<OptionInfoVO> optionList) {
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