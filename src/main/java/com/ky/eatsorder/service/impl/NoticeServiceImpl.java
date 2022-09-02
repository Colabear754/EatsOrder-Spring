package com.ky.eatsorder.service.impl;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ky.eatsorder.domain.notice.NoticeVO;
import com.ky.eatsorder.mapper.NoticeMapper;
import com.ky.eatsorder.service.NoticeService;

@Service
public class NoticeServiceImpl implements NoticeService {

	@Autowired
	private NoticeMapper mapper;

	@Override
	public int getNoticeSearchCount(HashMap<String, Object> map) {
		// 검색된 공지 개수
		int result = mapper.getNoticeSearchCount(map);
		System.out.println("검색된 공지 개수 : " + result);
		
		return result;
	}

	@Override
	public ArrayList<NoticeVO> getSearchNoticeList(HashMap<String, Object> map) {
		// 검색된 공지 목록
		return mapper.getSearchNoticeList(map);
	}

	@Override
	public void insertNotice(NoticeVO notice) {
		// 공지 등록
		mapper.insertNotice(notice);
	}

	@Override
	public NoticeVO getNotice(int notice_number) {
		// 공지 조회
		return mapper.getNotice(notice_number);
	}

	@Override
	public void updateNotice(NoticeVO notice) {
		// 공지 수정
		mapper.updateNotice(notice);
	}

	@Override
	public void deleteNotice(int notice_number) {
		// 공지 삭제
		mapper.deleteNotice(notice_number);
	}

}
