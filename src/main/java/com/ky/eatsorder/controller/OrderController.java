package com.ky.eatsorder.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
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
        boolean isOrderForm = Boolean.parseBoolean(params.get("isOrderForm"));    // 주문 화면인지 아닌지 확인하기 위한 변수
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

    @RequestMapping("/clean_cart")
    public String clean_cart(@SessionAttribute(name = "eatsorder_uid", required = false) String orderer, Model model) {
        // 장바구니 비우기
        model.addAttribute("result", orderDao.cleanCart(orderer));
        return "order/clean_cart";
    }

    @RequestMapping("/insert_cart")
    public String insert_cart(@SessionAttribute(name = "eatsorder_uid", required = false) String orderer, @RequestParam HashMap<String, Object> params, Model model) {
        // 장바구니에 메뉴 추가
        int menu_id = Integer.parseInt((String) params.get("menu_id"));
        int new_rst_id = orderDao.getRst_id(menu_id);
        String[] selectedOptions = (String[]) params.get("options");
        int[] options = {};
        HashMap<String, Object> input = new HashMap<>();
        int result = -1;

        if (selectedOptions != null) {
            options = Arrays.stream(selectedOptions).mapToInt(Integer::parseInt).toArray();
        }

        int quantity = Integer.parseInt((String) params.get("quantity"));

        input.put("orderer", orderer);
        input.put("menu_id", menu_id);
        input.put("rst_id", new_rst_id);
        input.put("options", options);
        input.put("quantity", quantity);

        if (orderDao.isExistDifferentRst(input)) {
            result = -2;
        } else {
            if (orderDao.isExistItem(input)) {
                result = -4;
            } else {
                result = orderDao.insertCartItem(input);
            }
        }

        model.addAttribute("result", result);

        return "order/insert_cart";
    }

    @RequestMapping("/delete_cart")
    public String delete_cart(@SessionAttribute(name = "eatsorder_uid", required = false) String orderer, @RequestParam String menu_id, Model model) {
        // 장바구니 메뉴 삭제
        int id = Integer.parseInt(menu_id);
        HashMap<String, Object> input = new HashMap<>();

        input.put("orderer", orderer);
        input.put("menu_id", menu_id);

        int result = orderDao.deleteCartItem(input);

        model.addAttribute("result", result);

        return "order/delete_cart";
    }

    @RequestMapping("/update_cart")
    public String update_cart(@SessionAttribute(name = "eatsorder_uid", required = false) String orderer, @RequestParam HashMap<String, String> params, Model model) {
        // 장바구니 메뉴 수량 변경
        int menu_id = Integer.parseInt(params.get("menu_id"));
        int quantity = Integer.parseInt(params.get("quantity"));
        HashMap<String, Object> input = new HashMap<>();

        input.put("orderer", orderer);
        input.put("menu_id", menu_id);
        input.put("quantity", quantity);

        int result = orderDao.updateCartItem(input);

        model.addAttribute("result", result);

        return "order/update_cart";
    }
}
