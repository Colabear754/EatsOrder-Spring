package com.ky.eatsorder.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.ky.eatsorder.domain.restaurant.RestaurantVO;
import com.ky.eatsorder.domain.review.ReplyVO;
import com.ky.eatsorder.domain.review.ReviewVO;

public interface ReviewService {
	public boolean checkOrderer(HashMap<String, String> map);
	public int writeReview(ReviewVO review);
	public ReviewVO getReview(int review_number);
	public boolean checkReviewWriter(HashMap<String, Object> map);
	public boolean checkUpdatable(int review_number);
	public int updateReview(HashMap<String, Object> map);
	public int deleteReview(int review_number);
	public ArrayList<ReviewVO> getReviews(HashMap<String, Object> map);
	public int getReviewCount(int rst_id);
	public int getReplyCount(int rst_id);
	public ArrayList<ReplyVO> getReplies(int rst_id);
	public ArrayList<ReviewVO> getMyReviews(String orderer);
	public String getReviewWriter(int review_number);
	public RestaurantVO getReviewRst(int review_number);
	public ArrayList<HashMap<String, Object>> getReviewToWrite(String orderer);
	public ArrayList<String> getOrderedMenu(String order_number);
}
