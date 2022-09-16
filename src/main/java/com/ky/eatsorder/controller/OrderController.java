package com.ky.eatsorder.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.ky.eatsorder.domain.etc_classes.CartItem;
import com.ky.eatsorder.domain.menu.MenuVO;
import com.ky.eatsorder.domain.menu.OptionInfoVO;
import com.ky.eatsorder.domain.order.CartVO;
import com.ky.eatsorder.domain.restaurant.RestaurantVO;
import com.ky.eatsorder.mapper.MenuAndOptionMapper;
import com.ky.eatsorder.mapper.OrderMapper;
import com.ky.eatsorder.mapper.RestaurantMapper;

@Controller
@RequestMapping("/order/*")
public class OrderController {
	private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

	@Autowired
	private OrderMapper orderDao;
	@Autowired 
	private MenuAndOptionMapper menuDao;
	@Autowired
	private RestaurantMapper rstDao;
	

	@RequestMapping("/cart")
	public String cart(@RequestParam HashMap<String, String> params,
			@SessionAttribute(name = "eatsorder_uid", required = false) String email, Model model) {
		// 주문표
		boolean isOrderForm = Boolean.parseBoolean(params.get("isOrderForm"));	// 주문 화면인지 아닌지 확인하기 위한 변수
		String point = params.get("using_point");
		ArrayList<CartVO> items = orderDao.getCartItems(email == null ? "" : email);
		ArrayList<CartItem> cartItems = new ArrayList<>();
		int using_point = point != null ? Integer.parseInt(point) : 0;
		int rst_id = 0;
		String rst_name = "";
		int delivery_tip = 0;
		int total_price = 0;
		
		if (!items.isEmpty()) {
			RestaurantVO rst = rstDao.getRestaurantOfMenu(items.get(0).getMenu_id());
			rst_id = rst.getRst_id();
			rst_name = rst.getRst_name();
			delivery_tip = orderDao.getDelivery_tip(items.get(0).getMenu_id());
		}
		
		for (CartVO item : items) {
			MenuVO menu = menuDao.getMenu(item.getMenu_id());
			ArrayList<OptionInfoVO> selectedOptions = orderDao.getSelectedOptions(item.getBundle_id());
			int option_price = 0;
			
			for (OptionInfoVO option : selectedOptions) {
				option_price += option.getPrice();
			}
			
			int quantity = item.getQuantity();
			int price = (menu.getPrice() + option_price) * quantity;
			total_price += price;
			
			cartItems.add(new CartItem(menu.getMenu_id(), menu.getMenu_name(), selectedOptions, quantity, price));
		}
		
		LOGGER.info("장바구니 조회!");
		
		model.addAttribute("cartItems", cartItems);
		model.addAttribute("rst_id", rst_id);
		model.addAttribute("rst_name", rst_name);
		model.addAttribute("using_point", using_point);
		model.addAttribute("delivery_tip", delivery_tip);
		model.addAttribute("total_price", total_price);
		model.addAttribute("isOrderForm", isOrderForm);

		return "order/cart";
	}
}
