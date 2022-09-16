package com.ky.eatsorder.domain.etc_classes;

import java.util.ArrayList;

import com.ky.eatsorder.domain.menu.MenuCategoryVO;
import com.ky.eatsorder.domain.menu.MenuVO;

public class MenuList {
	private MenuCategoryVO category;
	private ArrayList<MenuVO> menuList;

	public MenuList(MenuCategoryVO category, ArrayList<MenuVO> menuList) {
		this.category = category;
		this.menuList = menuList;
	}

	public MenuCategoryVO getCategory() {
		return category;
	}

	public void setCategory(MenuCategoryVO category) {
		this.category = category;
	}

	public ArrayList<MenuVO> getMenuList() {
		return menuList;
	}

	public void setMenuList(ArrayList<MenuVO> menuList) {
		this.menuList = menuList;
	}
}