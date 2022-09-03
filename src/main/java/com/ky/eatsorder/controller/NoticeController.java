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

import com.ky.eatsorder.domain.notice.NoticeVO;
import com.ky.eatsorder.mapper.NoticeMapper;

@Controller
@RequestMapping("/notice/*")
public class NoticeController {
	private static final Logger LOGGER = LoggerFactory.getLogger(MemberController.class);

	@Autowired
	private NoticeMapper noticeDao;

	@RequestMapping("/noticelist")
	public String noticelist(@RequestParam HashMap<String, String> params, Model model) {
		// 공지 목록
		String pageNum = params.get("pageNum");
		String type = params.get("type");
		String data = params.get("data");
		int category = Integer.parseInt(params.get("category"));
		HashMap<String, Object> map = new HashMap<>();

		map.put("type", type);
		map.put("data", data == null ? "" : data);
		map.put("category", category);

		int count = noticeDao.getNoticeSearchCount(map);

		final int pageSize = 20; // 페이지당 보여주는 게시물수(=레코드수) 
		final int blockSize = 10; // 블럭당 보여주는 페이지수 

		if (pageNum == null || pageNum.isBlank()) {
			pageNum = "1";
		}

		int currentPage = count == 0 ? 0 : Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = currentPage * pageSize;
		int number = count - (currentPage - 1) * pageSize;
		int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1);
		int startPage = 0; // 시작 페이지

		if (currentPage != 0) {
			if (currentPage % blockSize != 0) {
				startPage = currentPage / blockSize * blockSize + 1;
			} else {
				startPage = ((currentPage / blockSize) - 1) * blockSize + 1;
			}
		}

		int endPage = startPage + blockSize - 1; // 종료 페이지

		if (endPage > pageCount) {
			endPage = pageCount;
		}

		map.put("start", startRow);
		map.put("end", endRow);

		ArrayList<NoticeVO> noticeList = noticeDao.getSearchNoticeList(map);
		
		LOGGER.info("공지 목록 페이지 접속!");

		model.addAttribute("type", type);
		model.addAttribute("data", data);
		model.addAttribute("noticeList", noticeList);
		model.addAttribute("category", category);

		model.addAttribute("pageSize", pageSize);
		model.addAttribute("blockSize", blockSize);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("startRow", startRow);
		model.addAttribute("endRow", endRow);
		model.addAttribute("count", count);
		model.addAttribute("number", number);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("pageCount", pageCount);
		
		return "notice/noticelist";
	}
	
	@RequestMapping("/notice")
	public String notice(@RequestParam HashMap<String, String> params, Model model) {
		// 공지 내용
		int notice_number = Integer.parseInt(params.get("notice_number"));
		String pageNum = params.get("pageNum");
		String pre_category = params.get("category");
		
		NoticeVO notice = noticeDao.getNotice(notice_number);
		
		LOGGER.info("조회중인 공지 번호 : {}.", notice_number);
		
		model.addAttribute("notice_number", notice_number);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("notice", notice);
		model.addAttribute("pre_category", pre_category);
		
		return "notice/notice";
	}
}
