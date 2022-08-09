package com.ky.eatsorder.service.impl;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ky.eatsorder.domain.restaurant.RestaurantVO;
import com.ky.eatsorder.domain.review.ReplyVO;
import com.ky.eatsorder.domain.review.ReviewVO;
import com.ky.eatsorder.mapper.ReviewMapper;
import com.ky.eatsorder.service.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	private ReviewMapper mapper;

	@Override
	public boolean checkOrderer(HashMap<String, String> map) {
		// 주문자가 일치하는지 확인
		return mapper.checkOrderer(map);
	}
	
	@Override
	public int writeReview(ReviewVO review) {
		// 리뷰 작성
		int result = mapper.writeReview(review);
		System.out.println("리뷰 작성 결과 : " + result);
		
		return result;
	}

	@Override
	public ReviewVO getReview(int review_number) {
		// 리뷰 조회
		return mapper.getReview(review_number);
	}

	@Override
	public boolean checkReviewWriter(HashMap<String, Object> map) {
		// 리뷰 작성자 체크
		return mapper.checkReviewWriter(map);
	}

	@Override
	public boolean checkUpdatable(int review_number) {
		// 수정 가능한 리뷰인지 체크(작성일로부터 7일 이내)
		return mapper.checkUpdatable(review_number);
	}

	@Override
	public int updateReview(HashMap<String, Object> map) {
		// 리뷰 수정
		int result = mapper.updateReview(map);
		System.out.println("리뷰 수정 결과 : " + result);
		
		return result;
	}

	@Override
	public int deleteReview(int review_number) {
		// 리뷰 삭제
		int result = mapper.deleteReview(review_number);
		System.out.println("리뷰 삭제 결과 : " + result);
		
		return result;
	}

	@Override
	public ArrayList<ReviewVO> getReviews(HashMap<String, Object> map) {
		// 리뷰 목록 조회
		return mapper.getReviews(map);
	}

	@Override
	public int getReviewCount(int rst_id) {
		// 리뷰 개수 조회
		return mapper.getReviewCount(rst_id);
	}

	@Override
	public int getReplyCount(int rst_id) {
		// 댓글 개수 조회
		return mapper.getReplyCount(rst_id);
	}

	@Override
	public ArrayList<ReplyVO> getReplies(int rst_id) {
		// 리뷰댓글 목록 조회
		return mapper.getReplies(rst_id);
	}

	@Override
	public ArrayList<ReviewVO> getMyReviews(String orderer) {
		// 내 리뷰 목록 조회
		return mapper.getMyReviews(orderer);
	}

	@Override
	public String getReviewWriter(int review_number) {
		// 리뷰 작성자 조회
		return mapper.getReviewWriter(review_number);
	}

	@Override
	public RestaurantVO getReviewRst(int review_number) {
		// 리뷰가 작성된 매장 조회
		return mapper.getReviewRst(review_number);
	}

	@Override
	public ArrayList<HashMap<String, Object>> getReviewToWrite(String orderer) {
		// 작성 가능한 리뷰 목록 조회
		return mapper.getReviewToWrite(orderer);
	}
	
	@Override
	public ArrayList<String> getOrderedMenu(String order_number) {
		// 주문한 메뉴 조회
		return mapper.getOrderedMenu(order_number);
	}

}
