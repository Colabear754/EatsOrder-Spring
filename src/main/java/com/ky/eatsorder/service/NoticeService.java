package com.ky.eatsorder.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.ky.eatsorder.domain.notice.NoticeVO;

public interface NoticeService {
	public int getNoticeSearchCount(HashMap<String, Object> map);
	public ArrayList<NoticeVO> getSearchNoticeList(HashMap<String, Object> map);
	public void insertNotice(NoticeVO notice);
	public NoticeVO getNotice(int notice_number);
	public void updateNotice(NoticeVO notice);
	public void deleteNotice(int notice_number);
}
