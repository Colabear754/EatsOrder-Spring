package com.ky.eatsorder.domain.menu;

import java.util.ArrayList;

public class MenuListVO {
	MenuCategoryVO category;
	ArrayList<MenuVO> menuList;

	public MenuListVO(MenuCategoryVO category, ArrayList<MenuVO> menuList) {
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