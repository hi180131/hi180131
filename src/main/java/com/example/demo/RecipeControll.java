package com.example.demo;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

//レシピ系コントロール
@Controller
public class RecipeControll {

	@Autowired
	HttpSession session;
//
//	@Autowired
//	private AppConfig appConfig;

	@Autowired
	MaterialRepository MaterialRepository;

	@Autowired
	TitleMenuRepository titleMenuRepository;

	@Autowired
	OrderRecipeRepository orderRecipeRepository;

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	RecipeRepository recipeRepository;

	@Autowired
	MenuRepository menuRepository;

	// 写真クリックでレシピ表示
	@RequestMapping("/cooks/detail/{menuCode}")
	public ModelAndView showDetail(@PathVariable("menuCode") int menuCode, ModelAndView mv) {
		TitleMenu titleMenu = titleMenuRepository.findByMenuCode(menuCode);
		List<Material> menuMaterials = MaterialRepository.findByMenuCode(menuCode);
		List<OrderRecipe> recipes = orderRecipeRepository.findByMenuCode(menuCode);
		mv.addObject("recipes", recipes);
		mv.addObject("titleMenu", titleMenu);
		mv.addObject("menuMaterials", menuMaterials);
		mv.setViewName("detail");
		return mv;
	}

	// レシピ投稿画面表示
	@RequestMapping("/recipe/postRecipe")
	public ModelAndView postRecipe(ModelAndView mv) {
		List<Category> categoryList = categoryRepository.findAll();
		mv.addObject("categories", categoryList);
		mv.setViewName("postRecipe");
		return mv;
	}

	// レシピ投稿を実行
	@RequestMapping(value = "recipe/postRecipe", method = RequestMethod.POST)
	public ModelAndView doPostRecipe(@RequestParam(name = "recipeName", defaultValue = "") String recipeName,
			@RequestParam(name = "recipeComment", defaultValue = "") String recipeComment,
			@RequestParam(name = "recipeCategory", defaultValue = "") int recipeCategory,
			@RequestParam(name = "materialName", defaultValue = "") List<String> materialNames,
			@RequestParam(name = "materialNum", defaultValue = "") List<String> materialNums,
			@RequestParam(name = "recipeDetail", defaultValue = "") List<String> recipeDetails,
			ModelAndView mv) {
		if(recipeName == null || recipeName.length() == 0) {
			mv.addObject("message", "献立名を入力してください");
			reCategory(mv);
			mv.setViewName("postRecipe");
			return mv;
		}
		for(String recipeDetail:recipeDetails) {
			if(recipeDetail == null || recipeDetail.length() == 0) {
				mv.addObject("message", "空白箇所の手順を入力してください");
				reCategory(mv);
				mv.setViewName("postRecipe");
				return mv;
			}
		}
		for(String materialName:materialNames) {
			if(materialName.length() > 32) {
				mv.addObject("message", "材料名は全角16文字、半角32文字以下で入力してください。");
				reCategory(mv);
				mv.setViewName("postRecipe");
				return mv;
			}
		}
		for(String materialNum:materialNums) {
			if(materialNum.length() > 32) {
				mv.addObject("message", "材料数は全角16文字、半角32文字以下で入力してください。");
				reCategory(mv);
				mv.setViewName("postRecipe");
				return mv;
			}
		}
		User user = (User) session.getAttribute("user");
		recipeRepository.MenuInsert(recipeCategory,user.getUser_code(),recipeName, recipeComment);
		int menuCode = recipeRepository.MenuCodeNextval() -1;
		int materialCode = recipeRepository.MaterialCodeNextval() + 1;
		for(String materialName:materialNames) {
			recipeRepository.MaterialInsert(materialName);
		}
		for(String materialNum:materialNums) {
			recipeRepository.MenuDetailInsert(menuCode,materialCode,materialNum);
			materialCode++;
		}
		int recipeOrder = 1;
		for(String recipeDetail:recipeDetails) {
			recipeRepository.RecipeInsert(menuCode,recipeOrder,recipeDetail);
			recipeOrder++;
		}
		List<Menu> menuList = menuRepository.findAll();
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
