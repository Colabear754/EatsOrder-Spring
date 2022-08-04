package com.ky.eatsorder.service.impl;

import java.util.HashMap;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ky.eatsorder.domain.menu.*;
import com.ky.eatsorder.mapper.MenuAndOptionMapper;
import com.ky.eatsorder.service.MenuAndOptionService;

@Service
public class MenuAndOptionServiceImpl implements MenuAndOptionService {

	@Autowired
	private MenuAndOptionMapper mapper;
	
	@Override
	public ArrayList<MenuCategoryVO> getMenuCategories(int rst_id) {
		// 메뉴 카테고리 목록 조회
		return mapper.getMenuCategories(rst_id);
	}

	@Override
	public ArrayList<MenuVO> getMenuList(HashMap<String, Integer> map) {
		// 메뉴 목록 조회
		return mapper.getMenuList(map);
	}

	@Override
	public ArrayList<MenuRstVO> searchMenuList(HashMap<String, String> map) {
		// 메뉴로 매장 검색
		return mapper.searchMenuList(map);
	}

	@Override
	public MenuVO getMenu(int menu_id) {
		// 메뉴 조회
		return mapper.getMenu(menu_id);
	}

	@Override
	public ArrayList<OptionGroupVO> getMenuOptionGroups(int menu_id) {
		// 메뉴에 연결된 옵션그룹 조회
		return mapper.getMenuOptionGroups(menu_id);
	}

	@Override
	public ArrayList<OptionInfoVO> getOptionList(int group_id) {
		// 옵션 목록 조회
		return mapper.getOptionList(group_id);
	}
}
