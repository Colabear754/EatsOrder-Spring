package com.ky.eatsorder.mapper;

import java.util.HashMap;
import java.util.ArrayList;

import com.ky.eatsorder.domain.menu.*;

public interface MenuAndOptionMapper {
	public ArrayList<MenuCategoryVO> getMenuCategories(int rst_id);
	public ArrayList<MenuVO> getMenuList(HashMap<String, Integer> map);
	public ArrayList<MenuRstVO> searchMenuList(HashMap<String, String> map);
	public MenuVO getMenu(int menu_id);
	public ArrayList<OptionGroupVO> getMenuOptionGroups(int menu_id);
	public ArrayList<OptionInfoVO> getOptionList(int group_id);
}
