package com.example.demo;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

//料理表示系コントロール
@Controller
public class CooksControll {
	@Autowired
	HttpSession session;

	@Autowired
	MenuRepository menuRepository;

	@Autowired
	CategoryRepository categoryRepository;

	// 料理一覧表示
	@RequestMapping("/index")
	public ModelAndView index(ModelAndView mv) {
		List<Menu> menuList = menuRepository.findAll();
		mv.addObject("menus", menuList);
		reCategory(mv);
		mv.setViewName("index");
		return mv;
	}

	// カテゴリ別料理一覧表示
	@RequestMapping("/cooks/category/{code}")
	public ModelAndView categoryList(@PathVariable(name = "code") int categoryCode,ModelAndView mv) {
		List<Menu> menuList = menuRepository.findByCategoryCode(categoryCode);
		mv.addObject("menus", menuList);
		reCategory(mv);
		mv.setViewName("index");
		return mv;
	}

	//ワード検索別料理一覧表示
	@RequestMapping("/cooks/search")
	public ModelAndView searchList(@RequestParam(name = "keyword") String keyword, ModelAndView mv) {
		List<Menu> menuList = menuRepository.findByKeyword(keyword);
		mv.addObject("menus", menuList);
		reCategory(mv);
		mv.setViewName("index");
		return mv;
	}

	public void reCategory(ModelAndView mv) {
		List<Category> categoryList = categoryRepository.findAll();
		mv.addObject("categories", categoryList);
	}




}
