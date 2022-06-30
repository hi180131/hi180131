package com.example.demo;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

//ユーザ系コントロール
@Controller
public class AccountControll {

	@Autowired
	HttpSession session;


	@Autowired
	UserRepository userRepository;

	@Autowired
	MenuRepository menuRepository;

	@Autowired
	CategoryRepository categoryRepository;

	//新規登録画面表示
	@RequestMapping("/user/add")
	public String addUser() {
		return "addUser";
	}

	//新規登録を実行
	@RequestMapping(value = "/user/add", method = RequestMethod.POST)
	public ModelAndView doAddUser(@RequestParam("name") String name,
			@RequestParam("email") String email, @RequestParam("password") String password,
			ModelAndView mv) {
		if ((name == null || name.length() == 0) || (email == null || email.length() == 0)
				|| (password == null || password.length() == 0)) {
			mv.addObject("message", "未入力の項目があります");
			mv.setViewName("addUser");
			return mv;
		}
		if(name.length() > 32) {
			mv.addObject("message", "名前は全角16文字、半角32文字以下で入力してください。");
			mv.setViewName("addUser");
			return mv;
		}
		if(email.length() > 255) {
			mv.addObject("message", "メールアドレスが長すぎます。");
			mv.setViewName("addUser");
			return mv;
		}
		if(password.length() > 16 || password.length() < 8) {
			mv.addObject("message", "パスワードは半角8文字以上16文字以下で入力してください。");
			mv.setViewName("addUser");
			return mv;
		}
		userRepository.Insert(name,email,password);
		List<Menu> menuList = menuRepository.findAll();
		List<Category> categoryList = categoryRepository.findAll();
		mv.addObject("menus", menuList);
		mv.addObject("categories", categoryList);
		mv.setViewName("index");
		return mv;
	}

	// ログイン画面表示
	@RequestMapping("/user/login/{check}")
	public ModelAndView login(@PathVariable("check") String check,ModelAndView mv) {
		if (check.equals("post")) {
			session.setAttribute("postCheck", "post");
			mv.addObject("message", "ログイン後、レシピが投稿できます。");
			mv.setViewName("login");
			return mv;
		}
		mv.setViewName("login");
		return mv;
	}

	// ログインを実行
	@RequestMapping(value = "/user/login", method = RequestMethod.POST)
	public ModelAndView doLogin(@RequestParam("email") String email, @RequestParam("password") String password,
			ModelAndView mv) {
		if (email == null || email.length() == 0) {
			mv.addObject("message", "メールアドレスを入力してください");
			mv.setViewName("login");
			return mv;
		}
		if (password == null || password.length() == 0) {
			mv.addObject("message", "パスワードを入力してください");
			mv.setViewName("login");
			return mv;
		}
		if(email.length() > 255) {
			mv.addObject("message", "メールアドレスが長すぎます。");
			mv.setViewName("login");
			return mv;
		}
		if(password.length() > 16 || password.length() < 8) {
			mv.addObject("message", "パスワードは半角8文字以上16文字以下で入力してください。");
			mv.setViewName("login");
			return mv;
		}
		User user = userRepository.LoginCheck(email,password);
		if (user == null) {
			mv.addObject("message", "入力されたメールアドレスは登録されていません");
			mv.setViewName("login");
			return mv;
		}
		session.setAttribute("user", user);
		session.setAttribute("name", user.getName());
		String postCheck = (String) session.getAttribute("postCheck");
		if(postCheck != null && postCheck.equals("post")) {
			List<Category> categoryList = categoryRepository.findAll();
			mv.addObject("categories", categoryList);
			mv.setViewName("postRecipe");
			return mv;
		}
		reMenuAndCategory(mv);
		return mv;
	}

	//ログアウトを実行
	@RequestMapping("/user/logout")
	public ModelAndView logout(ModelAndView mv) {
		session.invalidate();
		reMenuAndCategory(mv);
		return mv;
	}

	//マイページを表示
	@RequestMapping("/user/myPage")
	public ModelAndView myPage(ModelAndView mv) {
		reMyPageMenu(mv);
		return mv;
	}

	//メニューの削除
	@RequestMapping(value="/user/menu/delete" ,method=RequestMethod.POST)
	public ModelAndView deleteMenu(@RequestParam("userCode") int userCode, ModelAndView mv) {
		menuRepository.deleteMenu(userCode);
		reMyPageMenu(mv);
		return mv;
	}

	//ユーザの退会
	@RequestMapping(value="/user/delete",method=RequestMethod.POST)
	public ModelAndView deleteUser(@RequestParam("user") User user,ModelAndView mv) {
		userRepository.deleteUser(user.getUser_code());
		reMenuAndCategory(mv);
		return mv;
	}


	public void reMenuAndCategory(ModelAndView mv) {
		List<Menu> menuList = menuRepository.findAll();
		List<Category> categoryList = categoryRepository.findAll();
		mv.addObject("menus", menuList);
		mv.addObject("categories", categoryList);
		mv.setViewName("index");
	}

	public void reMyPageMenu(ModelAndView mv) {
		User user = (User)session.getAttribute("user");
		List<Menu> menuList = menuRepository.findbyUserCode(user.getUser_code());
		mv.addObject("menus", menuList);
		mv.setViewName("myPage");
	}

}
