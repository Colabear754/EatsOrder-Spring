package com.ky.eatsorder.controller;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.ky.eatsorder.domain.coupon.OwnedCouponVO;
import com.ky.eatsorder.domain.order.OrderDetailVO;
import com.ky.eatsorder.domain.order.OrderHistoryVO;
import com.ky.eatsorder.mapper.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.ky.eatsorder.domain.etc_classes.CartItem;
import com.ky.eatsorder.domain.menu.MenuVO;
import com.ky.eatsorder.domain.menu.OptionInfoVO;
import com.ky.eatsorder.domain.order.CartVO;
import com.ky.eatsorder.domain.restaurant.RestaurantVO;

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
    @Autowired
    private MemberMapper memberDao;
    @Autowired
    private CouponMapper couponDao;


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
    public String insert_cart(@SessionAttribute(name = "eatsorder_uid", required = false) String orderer, @RequestParam HashMap<String, Object> params, @RequestParam(value = "options", required = false) List<String> selectedOptions, Model model) {
        // 장바구니에 메뉴 추가
        int menu_id = Integer.parseInt((String) params.get("menu_id"));
        int new_rst_id = orderDao.getRst_id(menu_id);
        HashMap<String, Object> input = new HashMap<>();
        int result = -1;

        int quantity = Integer.parseInt((String) params.get("quantity"));

        input.put("orderer", orderer);
        input.put("menu_id", menu_id);
        input.put("rst_id", new_rst_id);
        input.put("options", selectedOptions);
        input.put("quantity", quantity);
        input.put("isSelectOption", selectedOptions != null);

        if (orderDao.isExistDifferentRst(input)) {
            result = -2;
        } else {
            if (orderDao.isExistItem(input)) {
                result = -4;
            } else {
                result = orderDao.insertCartItem(input);

                if (selectedOptions != null) {
                    result += orderDao.insertCartItemOptions(selectedOptions);
                }
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

    @RequestMapping(value = "/new_order", method = RequestMethod.POST)
    public String new_order(@SessionAttribute(name = "eatsorder_uid", required = false) String orderer, @RequestParam HashMap<String, String> params, Model model) {
        // 주문 페이지
        if (orderer == null) {
            return "redirect:/main";
        }

        model.addAttribute("address", params.get("address"));
        model.addAttribute("phone", memberDao.getMember(orderer).getPhone());
        model.addAttribute("point", memberDao.getMember(orderer).getPoint());
        model.addAttribute("rst_id", Integer.parseInt(params.get("rst_id")));

        return "order/new_order";
    }

    @RequestMapping(value = "/order_process", method = RequestMethod.POST)
    public String order_process(@SessionAttribute(name = "eatsorder_uid", required = false) String email, @ModelAttribute OrderHistoryVO orderHistory, @RequestParam HashMap<String, String> params, Model model) {
        // 주문하기
        if (email == null) {
            return "redirect:/main";
        }

        String order_number = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MMdd-HHmmssSSS"));
        String orderer = orderHistory.getOrderer();
        String coupon_id = orderHistory.getCoupon_id();
        String rst_id = params.get("rst_id");
        ArrayList<CartVO> items = orderDao.getCartItems(orderer);
        int total_price = 0;    // 포인트 적립을 위한 전체 가격(배달팁 제외)
        HashMap<String, Object> input = new HashMap<>();

        orderHistory.setOrder_number(order_number);

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
        }

        input.put("order_number", order_number);
        input.put("orderer", orderer);
        input.put("email", orderer);
        input.put("used_point", orderHistory.getUsed_point());
        input.put("earned_point", total_price / 100);

        if(orderDao.insertOrder_history(orderHistory) > 0) {
            if (orderDao.insertOrder_detail(input) > 0) {
                orderDao.insertOrder_options(orderer);
            }
        }

        OrderHistoryVO order = orderDao.getOrderHistory(order_number);

        if (order != null) {
            memberDao.updatePoint(input);
            orderDao.cleanCart(orderer);

            if (coupon_id == null || coupon_id.isBlank()) {
                couponDao.useCoupon(new OwnedCouponVO(coupon_id, orderer, 0));
            }
        }

        model.addAttribute("order_number", order_number);
        model.addAttribute("rst_id", rst_id);

        return "order/order_process";
    }

    @RequestMapping("/result")
    public String result(@RequestParam HashMap<String, String> params, Model model) {
        // 주문 결과
        String order_number = params.get("order_number");
        String rst_id = params.get("rst_id");
        OrderHistoryVO orderInfo = orderDao.getOrderHistory(order_number);
        ArrayList<OrderDetailVO> items = orderDao.getOrderedItemList(order_number);
        int delivery_tip = orderDao.getDelivery_tip(items.get(0).getMenu_id());
        int total_price = 0;
        StringBuilder orderedItemString = new StringBuilder();
        String rst_name = rstDao.getRestaurant(Integer.parseInt(rst_id)).getRst_name();

        for (OrderDetailVO item : items) {
            MenuVO menu = menuDao.getMenu(item.getMenu_id());
            ArrayList<OptionInfoVO> orderedOptions = orderDao.getOrderedOptions(item.getBundle_id());
            int option_price = 0;

            orderedItemString.append(menu.getMenu_name());

            for (OptionInfoVO option : orderedOptions) {
                option_price += option.getPrice();
                if (orderedOptions.indexOf(option) == 0) {
                    orderedItemString.append("(").append(option.getOption_name()).append(", ");
                } else if (orderedOptions.indexOf(option) == orderedOptions.size() - 1) {
                    orderedItemString.append(option.getOption_name()).append("), ");
                } else {
                    orderedItemString.append(option.getOption_name()).append(", ");
                }
            }

            orderedItemString.append(", ");

            int quantity = item.getQuantity();
            int price = (menu.getPrice() + option_price) * quantity;
            total_price += price;

        }

        orderedItemString = new StringBuilder(orderedItemString.substring(0, orderedItemString.length() - 2));

        model.addAttribute("orderedItems", orderedItemString.toString());
        model.addAttribute("rst_name", rst_name);
        model.addAttribute("orderInfo", orderInfo);
        model.addAttribute("delivery_tip", delivery_tip);
        model.addAttribute("total_price", total_price);

        return "order/result";
    }

    @RequestMapping("/cancel")
    public String cancel(@SessionAttribute(name = "eatsorder_uid", required = false) String email, @RequestParam HashMap<String, String> params, Model model) {
        // 주문 취소 페이지
        if (email == null) {
            return "redirect:/main";
        }

        String order_number = params.get("order_number");
        ArrayList<OrderDetailVO> items = orderDao.getOrderedItemList(order_number);
        StringBuilder orderedItemString = new StringBuilder();

        for (OrderDetailVO item : items) {
            MenuVO menu = menuDao.getMenu(item.getMenu_id());
            ArrayList<OptionInfoVO> orderedOptions = orderDao.getOrderedOptions(item.getBundle_id());

            orderedItemString.append(menu.getMenu_name());

            for (OptionInfoVO option : orderedOptions) {
                if (orderedOptions.indexOf(option) == 0) {
                    orderedItemString.append("(").append(option.getOption_name()).append(", ");
                } else if (orderedOptions.indexOf(option) == orderedOptions.size() - 1) {
                    orderedItemString.append(option.getOption_name()).append("), ");
                } else {
                    orderedItemString.append(option.getOption_name()).append(", ");
                }
            }

            orderedItemString.append(", ");
        }

        orderedItemString = new StringBuilder(orderedItemString.substring(0, orderedItemString.length() - 2));

        model.addAttribute("order_number", order_number);
        model.addAttribute("order_item_list", orderedItemString.toString());

        return "order/cancel";
    }

    @RequestMapping("/cancel_order")
    public String cancel_order(@SessionAttribute("eatsorder_uid") String orderer, @RequestParam HashMap<String, String> params, Model model) {
        // 주문 취소
        String order_number = params.get("order_number");
        String reason_cancellation = params.get("reason_cancellation");
        ArrayList<OrderDetailVO> items = orderDao.getOrderedItemList(order_number);
        int total_price = 0;
        int result = -1;
        boolean isCancelable = false;
        HashMap<String, Object> input = new HashMap<>();

        for (OrderDetailVO item : items) {
            MenuVO menu = menuDao.getMenu(item.getMenu_id());
            ArrayList<OptionInfoVO> orderedOptions = orderDao.getOrderedOptions(item.getBundle_id());
            int option_price = 0;

            for (OptionInfoVO option : orderedOptions) {
                option_price += option.getPrice();
            }

            int quantity = item.getQuantity();
            int price = (menu.getPrice() + option_price) * quantity;
            total_price += price;
        }

        input.put("email", orderer);
        input.put("used_point", total_price / 100);
        input.put("earned_point", 0);
        input.put("order_number", order_number);
        input.put("orderer", orderer);
        input.put("reason_cancellation", reason_cancellation);

        memberDao.updatePoint(input);
        if (orderDao.isCancelable(input) && (System.currentTimeMillis() - Timestamp.valueOf(orderDao.getPay_date(order_number)).getTime()) / 1000 / 60 < 1) {
            result = orderDao.cancelOrder(input);
        }

        model.addAttribute("result", result);

        return "order/cancel_order";
    }
}
