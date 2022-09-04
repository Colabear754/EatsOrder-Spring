package com.ky.eatsorder.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ky.eatsorder.domain.etc_classes.RestaurantDetailInfo;
import com.ky.eatsorder.domain.restaurant.FavoriteRestaurantVO;
import com.ky.eatsorder.domain.restaurant.RestaurantVO;
import com.ky.eatsorder.mapper.RestaurantMapper;
import com.ky.eatsorder.mapper.ReviewMapper;

@Controller
@RequestMapping("/restaurant/*")
public class RestaurantController {
	private static final Logger LOGGER = LoggerFactory.getLogger(RestaurantController.class);
	private static final int PAGESIZE = 12;
	
	@Autowired
	private RestaurantMapper rstDao;
	@Autowired
	private ReviewMapper reviewDao;
	
	@RequestMapping("/list")
	public String list(@RequestParam HashMap<String, String> params, Model model) {
		// 매장 목록
		String pageNum = params.get("pageNum");
		int category_id = params.get("category_id") == null ? 0 :Integer.parseInt(params.get("category_id"));
		int orderBy = params.get("orderBy") == null ? 1 : Integer.parseInt(params.get("orderBy"));
		String address = params.get("address");
		String sido = params.get("sido");
		String sigungu = params.get("sigungu");
		String bname = params.get("bname");
		String searchText = params.get("searchText") != null ? params.get("searchText") : "";
		ArrayList<RestaurantDetailInfo> rstData = new ArrayList<>();
		HashMap<String, Object> map = new HashMap<>();
		
		if (pageNum == null) {
			pageNum = "1";
		}

		int currentPage = Integer.parseInt(pageNum);
		int start = (currentPage - 1) * PAGESIZE + 1;
		int end = currentPage * PAGESIZE;

		map.put("category_id", category_id);
		map.put("orderBy", orderBy);
		map.put("sido", sido);
		map.put("sigungu", sigungu);
		map.put("bname", bname);
		map.put("searchText", searchText == null ? "" : searchText);
		map.put("start", start);
		map.put("end", end);

		ArrayList<RestaurantVO> rstList = rstDao.getRestaurantList(map);
		
		for (RestaurantVO rst : rstList) {
			int rst_id = rst.getRst_id();
			rstData.add(new RestaurantDetailInfo(rst, reviewDao.getReviewCount(rst_id),
					reviewDao.getReplyCount(rst_id), rstDao.getRating(rst_id)));
		}
		
		int rst_count = rstDao.getRestaurantCount(map);
		
		LOGGER.info("매장 목록 페이지 접속!");
		
		model.addAttribute("category_id", category_id);
		model.addAttribute("orderBy", orderBy);
		model.addAttribute("address", address);
		model.addAttribute("sido", sido);
		model.addAttribute("sigungu", sigungu);
		model.addAttribute("bname", bname);
		model.addAttribute("searchText", searchText);
		model.addAttribute("rstData", rstData);
		model.addAttribute("rst_count", rst_count);
		
		return "restaurant/list";
	}
	
	@RequestMapping("/morelist")
	public String morelist(@RequestParam HashMap<String, String> params, Model model) {
		// 매장 목록 더보기
		String pageNum = params.get("pageNum");
		int category_id = params.get("category_id") == null ? 0 :Integer.parseInt(params.get("category_id"));
		int orderBy = params.get("orderBy") == null ? 1 : Integer.parseInt(params.get("orderBy"));
		String sido = params.get("sido");
		String sigungu = params.get("sigungu");
		String bname = params.get("bname");
		String searchText = params.get("searchText") != null ? params.get("searchText") : "";
		ArrayList<RestaurantDetailInfo> rstData = new ArrayList<>();
		HashMap<String, Object> map = new HashMap<>();
		
		if (pageNum == null) {
			pageNum = "1";
		}

		int currentPage = Integer.parseInt(pageNum);
		int start = (currentPage - 1) * PAGESIZE + 1;
		int end = currentPage * PAGESIZE;

		map.put("category_id", category_id);
		map.put("orderBy", orderBy);
		map.put("sido", sido);
		map.put("sigungu", sigungu);
		map.put("bname", bname);
		map.put("searchText", searchText == null ? "" : searchText);
		map.put("start", start);
		map.put("end", end);

		ArrayList<RestaurantVO> rstList = rstDao.getRestaurantList(map);
		
		for (RestaurantVO rst : rstList) {
			int rst_id = rst.getRst_id();
			rstData.add(new RestaurantDetailInfo(rst, reviewDao.getReviewCount(rst_id),
					reviewDao.getReplyCount(rst_id), rstDao.getRating(rst_id)));
		}
		
		LOGGER.info("추가 매장 목록 조회!");
		model.addAttribute("rstData", rstData);
		
		return "restaurant/morelist";
	}
	
	@RequestMapping("/info")
	public String info(@RequestParam HashMap<String, String> params, Model model) {
		
		return "restaurant/info";
	}
	
	@RequestMapping("/detail")
	public String detail(@RequestParam String rst_id, Model model) {
		
		return "restaurant/detail";
	}
	
	@RequestMapping("/favorite")
	public String favorite(@ModelAttribute FavoriteRestaurantVO vo, Model model) {
		int result = 0;
		
		if (rstDao.isFavorite(vo)) {
			result = rstDao.removeFavoriteRestaurant(vo) + 1;
		} else {
			result = rstDao.registFavoriteRestaurant(vo);
		}
		
		model.addAttribute("result", result);
		
		return "restaurant/favorite";
	}
}
