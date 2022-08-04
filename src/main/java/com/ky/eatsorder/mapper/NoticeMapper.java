package com.ky.eatsorder.mapper;

import java.util.ArrayList;
import java.util.HashMap;

import com.ky.eatsorder.domain.notice.NoticeVO;

public interface NoticeMapper {
	public int getNoticeCount();
	public int getNoticeSearchCount(HashMap<String, Object> map);
	public ArrayList<NoticeVO> getNotices(HashMap<String, Integer> map);
	public ArrayList<NoticeVO> getSearchNotices(HashMap<String, Object> map);
	public int insertNotice(NoticeVO notice);
	public NoticeVO getNotice(int notice_number);
	public int updateNotice(NoticeVO notice);
	public int deleteNotice(int notice_number);
}
