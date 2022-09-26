package com.ky.eatsorder.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.ky.eatsorder.domain.order.CartVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.ky.eatsorder.domain.etc_classes.RestaurantDetailInfo;
import com.ky.eatsorder.domain.etc_classes.ReviewDetailInfo;
import com.ky.eatsorder.domain.member.MemberInfoVO;
import com.ky.eatsorder.domain.member.MemberLoginVO;
import com.ky.eatsorder.domain.restaurant.RestaurantVO;
import com.ky.eatsorder.domain.review.ReviewVO;
import com.ky.eatsorder.mapper.CouponMapper;
import com.ky.eatsorder.mapper.MemberMapper;
import com.ky.eatsorder.mapper.OrderMapper;
import com.ky.eatsorder.mapper.RestaurantMapper;
import com.ky.eatsorder.mapper.ReviewMapper;

import oracle.sql.TIMESTAMP;

@Controller
@RequestMapping("/member/*")
public class MemberController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MemberController.class);
    private static final int PAGESIZE = 10;

    @Autowired
    private MemberMapper memberDao;
    @Autowired
    private CouponMapper couponDao;
    @Autowired
    private OrderMapper orderDao;
    @Autowired
    private RestaurantMapper rstDao;
    @Autowired
    private ReviewMapper reviewDao;

    @RequestMapping("/login")
    public String login(Locale locale) {
        // 로그인 페이지
        LOGGER.info("로그인 페이지 접속! 클라이언트 위치 : {}.", locale);

        return "member/login";
    }

    @RequestMapping(value = "/loginCheck", method = RequestMethod.POST)
    public String loginCheck(@ModelAttribute MemberLoginVO member, HttpSession session, Model model) {
        // 로그인 처리
        boolean result = memberDao.login(member);

        if (result) {
            session.setAttribute("eatsorder_uid", member.getEmail());
        }

        LOGGER.info("로그인 결과 : {}.", result);
        model.addAttribute("result", result);

        return "member/loginCheck";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        // 로그아웃
        request.getSession().removeAttribute("eatsorder_uid");

        return "redirect:" + request.getHeader("Referer");
    }

    @RequestMapping("/create_account")
    public String registForm(Locale locale) {
        // 회원가입 페이지
        LOGGER.info("회원가입 페이지 접속! 클라이언트 위치 : {}.", locale);

        return "member/create_account";
    }

    @RequestMapping(value = "/check_for_duplicate", method = RequestMethod.POST)
    public String check_for_duplicate(@RequestParam HashMap<String, String> params, Model model) {
        // 중복 확인
        boolean result = memberDao.checkDuplicateMember(params);

        LOGGER.info("{} 중복확인 결과 : {}.", params.get("type"), result ? "중복" : "사용가능");
        model.addAttribute("result", result);

        return "member/check_for_duplicate";
    }

    @RequestMapping(value = "/regist", method = RequestMethod.POST)
    public String regist(@RequestParam HashMap<String, Object> params, Model model) {
        // 회원가입
        if (!((String) params.get("phone")).contains("-")) { // 전화번호에 하이픈(-)이 없는 형식일 경우 하이픈을 넣음
            params.put("phone", ((String) params.get("phone")).replaceAll("(\\d{3})(\\d{3,4})(\\d{4})", "$1-$2-$3"));
        }

        MemberInfoVO info = new MemberInfoVO((String) params.get("email"), (String) params.get("nickname"),
                (String) params.get("phone"), null, 0, null, null,
                Boolean.parseBoolean((String) params.get("receive_marketing")) ? 1 : 0); // 회원 정보
        MemberLoginVO login = new MemberLoginVO((String) params.get("email"), (String) params.get("password")); // 로그인 정보
        int result = -1;

        if (memberDao.registInfo(info) > 0) {
            result = memberDao.registLogin(login);
        }

        LOGGER.info("회원가입 결과 : {}.", result > 0 ? "성공" : "실패");
        model.addAttribute("result", result);

        return "member/regist";
    }

    @RequestMapping("/update_account")
    public String update_account(@SessionAttribute(name = "eatsorder_uid", required = false) String email, Model model) {
        // 회원정보 수정 페이지
        if (email == null) {
            return "redirect:/main";
        }

//		String email = (String) request.getSession().getAttribute("eatsorder_uid");
        MemberInfoVO member = memberDao.getMember(email);
        int coupon_count = couponDao.getCouponCount(email);

        model.addAttribute("member", member);
        model.addAttribute("coupon_count", coupon_count);

        return "member/update_account";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@RequestParam HashMap<String, Object> params, Model model) {
        // 회원정보 수정
        if (!((String) params.get("phone")).contains("-")) { // 전화번호에 하이픈(-)이 없는 형식일 경우 하이픈을 넣음
            params.put("phone", ((String) params.get("phone")).replaceAll("(\\d{3})(\\d{3,4})(\\d{4})", "$1-$2-$3"));
        }

        params.put("receive_marketing", Boolean.parseBoolean((String) params.get("receive_marketing")) ? 1 : 0);

        int result = -1;

        if (memberDao.login(new MemberLoginVO((String) params.get("email"), (String) params.get("password")))) {
            if (((String) params.get("newPassword")).isBlank()) {
                result = memberDao.updateInfo(params);
            } else {
                if (memberDao.updateInfo(params) > 0) {
                    result = memberDao.updatePassword(
                            new MemberLoginVO((String) params.get("email"), (String) params.get("newPassword")));
                }
            }
        }

        LOGGER.info("회원정보 수정 결과 : {}.", result > 0 ? "성공" : "실패");
        model.addAttribute("result", result);

        return "member/update";
    }

    @RequestMapping("/find_email")
    public String find_email() {
        // 이메일 찾기 페이지
        return "member/find_email";
    }

    @RequestMapping("/find_result")
    public String find_result(@RequestParam String phone, Model model) {
        // 이메일 찾기 결과
        model.addAttribute("result", memberDao.findEmail(phone));

        return "member/find_result";
    }

    @RequestMapping("/find_password")
    public String find_password() {
        // 비밀번호 찾기 페이지
        return "member/find_password";
    }

    @RequestMapping("/check_member")
    public String check_member(@RequestParam HashMap<String, String> params, Model model) {
        // 유효한 회원인지 확인한 결과
        boolean result = memberDao.checkValidMember(params);

        LOGGER.info("유효한 회원 여부 : {}.", result);
        model.addAttribute("result", result);

        return "member/check_member";
    }

    @RequestMapping("/reset_password")
    public String reset_password(@RequestParam String email, Model model) {
        // 비밀번호 재설정 페이지
        if (email == null || email.isBlank()) {
            return "redirect:/main";
        }

        model.addAttribute("email", email);

        return "member/reset_password";
    }

    @RequestMapping("/reset_result")
    public String reset_result(@ModelAttribute MemberLoginVO member, Model model) {
        // 비밀번호 재설정 결과
        int result = memberDao.resetPassword(member);

        LOGGER.info("비밀번호 재설정 결과 : {}.", result > 0 ? "성공" : "실패");

        return "member/reset_result";
    }

    @RequestMapping("/delete_account")
    public String delete_account(@SessionAttribute(name = "eatsorder_uid", required = false) String email, Model model) {
        // 회원탈퇴 페이지
        if (email == null) {
            return "redirect:/main";
        }

        model.addAttribute("email", email);

        return "member/delete_account";
    }

    @RequestMapping(value = "/withdraw", method = RequestMethod.POST)
    public String withdraw(@RequestParam HashMap<String, String> params, Model model) {
        // 회원탈퇴
        int result = -1;
        String email = params.get("email");

        if (memberDao.login(new MemberLoginVO(email, params.get("password")))) {
            if (memberDao.deleteLogin(email) > 0) {
                if (memberDao.updateWithdraw_date(email) > 0) {
                    result = memberDao.insertWithdrawMember(params);
                }
            }
        }

        LOGGER.info("회원탈퇴 결과 : {}.", result > 0 ? "성공" : "실패");
        model.addAttribute("result", result);

        return "member/withdraw";
    }

    @RequestMapping("/orderlist")
    public String orderlist(@SessionAttribute(name = "eatsorder_uid", required = false) String email, @RequestParam HashMap<String, String> params, Model model) throws SQLException {
        // 주문내역
        if (email == null) {
            return "redirect:/main";
        }

        String pageNum = params.get("pageNum");
        MemberInfoVO member = memberDao.getMember(email);
        int coupon_count = couponDao.getCouponCount(email);

        if (pageNum == null) {
            pageNum = "1";
        }

        int currentPage = Integer.parseInt(pageNum);
        int start = (currentPage - 1) * PAGESIZE + 1;
        int end = currentPage * PAGESIZE;
        HashMap<String, Object> map = new HashMap<>();

        map.put("orderer", email);
        map.put("start", start);
        map.put("end", end);

        ArrayList<HashMap<String, Object>> orderList = orderDao.getOrderList(map);

        for (HashMap<String, Object> order : orderList) {
            long elapsed_time = (System.currentTimeMillis() - ((TIMESTAMP) order.get("PAY_DATE")).timestampValue().getTime()) / 1000 / 60; // 주문으로부터 경과한 시간(분)

            String orderedMenuString = "";
            ArrayList<String> orderedMenu = orderDao.getMenu_name((String) order.get("ORDER_NUMBER"));

            for (String menu : orderedMenu) {
                orderedMenuString += menu + ", ";
            }

            orderedMenuString = orderedMenuString.substring(0, orderedMenuString.length() - 2);

            order.put("elapsed_time", elapsed_time);
            order.put("MENU_NAME", orderedMenuString);
        }
        model.addAttribute("member", member);
        model.addAttribute("coupon_count", coupon_count);
        model.addAttribute("orderList", orderList);

        return "member/orderlist";
    }

    @RequestMapping("/couponlist")
    public String couponlist(@SessionAttribute(name = "eatsorder_uid", required = false) String email, Model model) {
        // 쿠폰함
        if (email == null) {
            return "redirect:/main";
        }

        MemberInfoVO member = memberDao.getMember(email);
        int coupon_count = couponDao.getCouponCount(email);
        ArrayList<HashMap<String, Object>> coupon_list = couponDao.getCoupons(email);

        model.addAttribute("member", member);
        model.addAttribute("coupon_count", coupon_count);
        model.addAttribute("counpon_list", coupon_list);

        return "member/couponlist";
    }

    @RequestMapping("/favorite_rst")
    public String favorite_rst(@SessionAttribute(name = "eatsorder_uid", required = false) String email, Model model) {
        // 찜매장 목록
        if (email == null) {
            return "redirect:/main";
        }

        ArrayList<RestaurantDetailInfo> favorite_rst = new ArrayList<>();
        ArrayList<RestaurantVO> rstList = rstDao.getFavoriteRestaurants(email);
        MemberInfoVO member = memberDao.getMember(email);
        int coupon_count = couponDao.getCouponCount(email);

        for (RestaurantVO rst : rstList) {
            int rst_id = rst.getRst_id();
            favorite_rst.add(new RestaurantDetailInfo(rst, 0, 0, rstDao.getRating(rst_id)));
        }

        model.addAttribute("member", member);
        model.addAttribute("coupon_count", coupon_count);
        model.addAttribute("favorite_rst", favorite_rst);

        return "member/favorite_rst";
    }

    @RequestMapping("/reviewlist")
    public String reviewlist(@SessionAttribute(name = "eatsorder_uid", required = false) String email, Model model) throws SQLException {
        // 내 리뷰 목록
        if (email == null) {
            return "redirect:/main";
        }

        MemberInfoVO member = memberDao.getMember(email);
        int coupon_count = couponDao.getCouponCount(email);
        ArrayList<ReviewDetailInfo> reviewData = new ArrayList<>();
        ArrayList<HashMap<String, Object>> orderList = reviewDao.getReviewToWrite(email);
        long overDate;

        ArrayList<ReviewVO> revewList = reviewDao.getMyReviews(email);

        for (ReviewVO review : revewList) {
            overDate = (System.currentTimeMillis() - review.getRegist_date().getTime()) / 1000 / 60 / 60 / 24;

            reviewData.add(new ReviewDetailInfo(review, null, reviewDao.getReviewRst(review.getReview_number()), overDate, null));
        }

        for (HashMap<String, Object> order : orderList) {
            long elapsed_time = (System.currentTimeMillis() - ((TIMESTAMP) order.get("PAY_DATE")).timestampValue().getTime()) / 1000 / 60; // 주문으로부터 경과한 시간(분)

            order.put("elapsed_time", elapsed_time);
        }

        model.addAttribute("email", email);
        model.addAttribute("member", member);
        model.addAttribute("coupon_count", coupon_count);
        model.addAttribute("reviewData", reviewData);
        model.addAttribute("orderList", orderList);

        return "member/reviewlist";
    }

    @RequestMapping("/cart")
    public String cart(@SessionAttribute(name = "eatsorder_uid", required = false) String orderer, Model model) {
        // 헤더 장바구니 버튼
        ArrayList<CartVO> items = orderDao.getCartItems(orderer);
        int rst_id = items.isEmpty() ? 0 : rstDao.getRestaurantOfMenu(items.get(0).getMenu_id()).getRst_id();

        model.addAttribute("result", rst_id);

        return "member/cart";
    }
}
