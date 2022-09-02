package com.ky.eatsorder.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ky.eatsorder.domain.administrator.AdministratorVO;
import com.ky.eatsorder.domain.notice.NoticeVO;
import com.ky.eatsorder.mapper.AdministratorMapper;
import com.ky.eatsorder.mapper.NoticeMapper;

@Controller
@RequestMapping("/admin/*")
public class AdministratorController {
	private static final Logger LOGGER = LoggerFactory.getLogger(MemberController.class);

	@Autowired
	AdministratorMapper adminDao;
	@Autowired
	NoticeMapper noticeDao;

	@RequestMapping("/main")
	public String main() {
		LOGGER.info("관리자 메인 페이지 접속!");

		return "admin/main";
	}

	@RequestMapping("/login")
	public String login() {
		LOGGER.info("관리자 로그인 페이지 접속!");

		return "admin/login";
	}

	@RequestMapping("/loginCheck")
	public String loginCheck(@ModelAttribute AdministratorVO admin, HttpSession session, Model model) {
		// 관리자 로그인
		boolean result = adminDao.login(admin);

		if (result) {
			session.setAttribute("admin_id", admin.getAdmin_id());
		}

		LOGGER.info("관리자 로그인 결과 : {}.", result);
		model.addAttribute("result", result);

		return "member/loginCheck";
	}

	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		// 관리자 로그아웃
		session.removeAttribute("admin_id");

		return "redirect:login";
	}

	@RequestMapping("/noticelist")
	public String noticelist(@RequestParam HashMap<String, String> params, Model model) {
		// 관리자 공지 목록
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

		return "admin/noticelist";
	}

	@RequestMapping("/notice")
	public String notice(@RequestParam HashMap<String, String> params, Model model) {
		// 관리자 공지 내용
		int notice_number = Integer.parseInt(params.get("notice_number"));
		String pageNum = params.get("pageNum");
		String pre_category = params.get("category");
		
		NoticeVO notice = noticeDao.getNotice(notice_number);
		
		model.addAttribute("notice_number", notice_number);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("notice", notice);
		model.addAttribute("pre_category", pre_category);
		
		return "admin/notice";
	}
	
	@RequestMapping("write_notice")
	public String write_notice(@RequestParam HashMap<String, String> params, Model model) {
		// 관리자 공지 작성 폼
		int category = Integer.parseInt(params.get("category"));
		
		model.addAttribute("category", category);
		
		return "admin/write_notice";
	}
	
	@RequestMapping(value = "write_process", method = RequestMethod.POST)
	public String write_process(@ModelAttribute NoticeVO notice, HttpServletRequest request, Model model) throws IOException {
		// 공지 작성
		String directory = request.getServletContext().getRealPath("\\resources\\notice\\img\\");	// 파일 저장 경로
		String filename = null;
		MultipartFile upload_file = notice.getUpload_file();
		
		Files.createDirectories(Paths.get(directory));	// 파일을 저장할 디렉토리 생성
		
		if (!upload_file.isEmpty()) {
			directory += "\\";
			String extension = FilenameUtils.getExtension(upload_file.getOriginalFilename());
			long time_mills = System.currentTimeMillis();
			int random = (int) (Math.random() * 100000);
			filename = "" + random + time_mills + "." + extension;
			upload_file.transferTo(new File(directory + filename));
		}
		
		notice.setFilename(filename);
		LOGGER.info("공지 작성 결과 : {}", noticeDao.insertNotice(notice));
		
		return "redirect:/admin/noticelist?category=1";
	}
	
	@RequestMapping("delete_notice")
	public String delete_notice(@RequestParam HashMap<String, String> params, HttpServletRequest request, Model model) {
		// 공지 삭제
		int notice_number = Integer.parseInt(params.get("notice_number"));
		String directory = request.getServletContext().getRealPath("\\resources\\notice\\img");
		File img = new File(directory + "\\" + noticeDao.getNotice(notice_number).getFilename());	// 공지의 이미지
		int result = noticeDao.deleteNotice(notice_number);
		
		img.delete();
		
		model.addAttribute("result", result);
		
		return "admin/delete_notice";
	}
	
	@RequestMapping("update_notice")
	public String update_notice(@RequestParam HashMap<String, String> params, Model model) {
		// 관리자 공지 수정 폼
		int notice_number = Integer.parseInt(params.get("notice_number"));
		
		NoticeVO notice = noticeDao.getNotice(notice_number);
		
		model.addAttribute("notice", notice);
		
		return "admin/update_notice";
	}
	
	@RequestMapping(value = "update_process", method = RequestMethod.POST)
	public String update_process(@ModelAttribute NoticeVO notice, HttpServletRequest request, Model model) throws IOException {
		// 공지 수정
		String directory = request.getServletContext().getRealPath("\\resources\\notice\\img");	// 파일 저장 경로
		String filename = null;
		File pre_img = null;
		MultipartFile upload_file = notice.getUpload_file();
		
		Files.createDirectories(Paths.get(directory));	// 파일을 저장할 디렉토리 생성
		
		if (!upload_file.isEmpty()) {
			directory += "\\";
			pre_img = new File(directory + notice.getFilename());
			String extension = FilenameUtils.getExtension(upload_file.getOriginalFilename());	// 파일 확장자
			long time_mills = System.currentTimeMillis();
			int random = (int) (Math.random() * 100000);
			filename = "" + random + time_mills + "." + extension;	// 파일의 새로운 이름
			
			upload_file.transferTo(new File(directory + filename));
			pre_img.delete();
		}
		
		notice.setFilename(filename);
		LOGGER.info("공지 수정 결과 : {}", noticeDao.updateNotice(notice));
		
		return "redirect:/admin/notice?notice_number=" + notice.getNotice_number() + "&category=" + notice.getCategory();
	}
}
