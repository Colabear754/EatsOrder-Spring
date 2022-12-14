package com.ky.eatsorder.controller;

import java.util.ArrayList;
import java.util.HashMap;

import com.ky.eatsorder.domain.menu.MenuVO;
import com.ky.eatsorder.domain.menu.OptionGroupVO;
import com.ky.eatsorder.domain.etc_classes.OptionList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ky.eatsorder.domain.etc_classes.MenuList;
import com.ky.eatsorder.domain.menu.MenuCategoryVO;
import com.ky.eatsorder.mapper.MenuAndOptionMapper;

@Controller
@RequestMapping("/menu/*")
public class MenuController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MenuController.class);

    @Autowired
    private MenuAndOptionMapper menuDao;

    @RequestMapping("/menulist")
    public String menulist(@RequestParam("rst_id") String id, Model model) {
        // 메뉴 목록
        int rst_id = Integer.parseInt(id);
        ArrayList<MenuCategoryVO> categoryList = menuDao.getMenuCategories(rst_id);
        ArrayList<MenuList> menuList = new ArrayList<>();
        HashMap<String, Integer> map = new HashMap<>();

        map.put("rst_id", rst_id);

        for (MenuCategoryVO category : categoryList) {
            map.put("category_id", category.getCategory_id());
            MenuList categoryMenuList = new MenuList(category, menuDao.getMenuList(map));
            menuList.add(categoryMenuList);
        }

        LOGGER.info("메뉴 목록 조회! 매장ID : {}.", rst_id);

        model.addAttribute("menuList", menuList);

        return "menu/menulist";
    }

    @RequestMapping("/item")
    public String info(@RequestParam String menu_id, Model model) {
        // 메뉴 조회
        int id = Integer.parseInt(menu_id);
        MenuVO menu = menuDao.getMenu(id);
        ArrayList<OptionGroupVO> optionGroupList = menuDao.getMenuOptionGroups(id);
        ArrayList<OptionList> optionList = new ArrayList<>();

        for (OptionGroupVO optionGroup : optionGroupList) {
            OptionList list = new OptionList(optionGroup, menuDao.getOptionList(optionGroup.getGroup_id()));
            optionList.add(list);
        }

        model.addAttribute("menu", menu);
        model.addAttribute("optionList", optionList);

        return "menu/item";
    }
}
