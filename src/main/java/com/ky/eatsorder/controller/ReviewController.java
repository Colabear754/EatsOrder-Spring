package com.ky.eatsorder.controller;

import com.ky.eatsorder.domain.etc_classes.ReviewDetailInfo;
import com.ky.eatsorder.domain.review.ReplyVO;
import com.ky.eatsorder.domain.review.ReviewVO;
import com.ky.eatsorder.mapper.MemberMapper;
import com.ky.eatsorder.mapper.OrderMapper;
import com.ky.eatsorder.mapper.RestaurantMapper;
import com.ky.eatsorder.mapper.ReviewMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;

@Controller
@RequestMapping("/review/*")
public class ReviewController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReviewController.class);
    private static final int PAGESIZE = 10;

    @Autowired
    private ReviewMapper reviewDao;
    @Autowired
    private RestaurantMapper rstDao;
    @Autowired
    private MemberMapper memberDao;
    @Autowired
    private OrderMapper orderDao;

    @RequestMapping("/listform")
    public String listform(@RequestParam String rst_id, Model model) {
        // 리뷰 목록 폼
        int id = Integer.parseInt(rst_id);

        double rating = rstDao.getRating(id);
        int review_count = reviewDao.getReviewCount(id);
        int reply_count = reviewDao.getReplyCount(id);

        model.addAttribute("rst_id", id);
        model.addAttribute("rating", rating);
        model.addAttribute("review_count", review_count);
        model.addAttribute("reply_count", reply_count);

        return "review/listform";
    }

    @RequestMapping("/list")
    public String list(@RequestParam HashMap<String, String> params, Model model) {
        // 리뷰 목록
        String pageNum = params.get("pageNum"); // 현재 페이지 번호
        int rst_id = Integer.parseInt(params.get("rst_id")); // 매장ID
        boolean onlyPhotoReview = Boolean.parseBoolean(params.get("onlyPhotoReview")); // 사진 리뷰만 조회
        HashMap<String, Object> input = new HashMap<>();    // mapper에 전달할 파라미터를 담을 map

        if (pageNum == null) { // 현재 페이지번호가 null이면 1으로 지정
            pageNum = "1";
        }

        // 정수변환 및 불러올 리뷰번호 시작값과 끝값 지정
        int currentPage = Integer.parseInt(pageNum);
        int start = (currentPage - 1) * PAGESIZE + 1;
        int end = currentPage * PAGESIZE;

        input.put("rst_id", rst_id);
        input.put("onlyPhotoReview", onlyPhotoReview);
        input.put("start", start);
        input.put("end", end);

        ArrayList<ReviewVO> reviewList = reviewDao.getReviews(input); // 리뷰 리스트
        ArrayList<ReplyVO> replyList = reviewDao.getReplies(rst_id); // 사장님 댓글 리스트
        ArrayList<ReviewDetailInfo> reviewDetailList = new ArrayList<>();	// 리뷰와 작성자, 좋아요 개수 등을 담을 리스트

        for (ReviewVO review : reviewList) { // 리뷰 리스트에 있는 각 리뷰에 해당하는 작성자 닉네임과 좋아요 개수를 별도의 ArrayList에 저장
            HashMap<String, Object> map = new HashMap<>();
            int number = review.getReview_number();
            map.put("type", "review_number");
            map.put("review_number", number);
            String nickname = memberDao.getNickname(reviewDao.getReviewWriter(number));
            ArrayList<String> orderedItems = orderDao.getOrderedItems(map);
            String orderedItemString = "";

            for (String item : orderedItems) {
                orderedItemString += item + ", ";
            }

            orderedItemString = orderedItemString.substring(0, orderedItemString.length() - 2);

            reviewDetailList.add(new ReviewDetailInfo(review, nickname, null, 0, orderedItemString));
        }

        int reviewCount = reviewList.size(); // 리뷰 개수
        int replyCount = replyList.size(); // 사장님 댓글 개수

        model.addAttribute("reviewCount", reviewCount);
        model.addAttribute("replyCount", replyCount);
        model.addAttribute("reviewDetailList", reviewDetailList);
        model.addAttribute("replyList", replyList);
        model.addAttribute("onlyPhotoReview", onlyPhotoReview);

        return "review/list";
    }
}
