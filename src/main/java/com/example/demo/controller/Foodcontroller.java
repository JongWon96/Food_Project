package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.domain.Food;
import com.example.demo.domain.FoodDetail;
import com.example.demo.domain.FoodRecipe;
import com.example.demo.service.FoodService;

@Controller
@RequestMapping("/foodproject")
public class Foodcontroller {

	@Autowired
	private FoodService foodService;
	
	// 이름으로 검색한 음식 리스트
	@GetMapping("/foodlist")
	private String FoodListViewByName(Food food, 
			@RequestParam(value="name", defaultValue="") String name,
			@RequestParam(value="page", defaultValue="1") int page,
			@RequestParam(value="size", defaultValue="10") int size,
			Model model) {
		
		Page<Food> foodlist = foodService.getAllFoodList(name, page, size);
		
		model.addAttribute("foodList", foodlist.getContent());
		model.addAttribute("pageInfo", foodlist);
		return "food/FoodList";
	}
	
	// 이름으로 검색한 음식 리스트
	@GetMapping("/foodlist")
	private String FoodListViewByCategory(Food food, 
			@RequestParam(value="category", defaultValue="") String category,
			@RequestParam(value="page", defaultValue="1") int page,
			@RequestParam(value="size", defaultValue="10") int size,
			Model model) {
		
		Page<Food> foodlist = foodService.getAllFoodListByCategory(category, page, size);
		
		model.addAttribute("foodList", foodlist.getContent());
		model.addAttribute("pageInfo", foodlist);
		return "food/FoodList";
	}
	
	@GetMapping("/foodcategory")
	private String FoodViewByCategory(@RequestParam("category") String category, Model model) {
		
		List<Food> food = foodService.getFoodListByCategory(category);
		
		List<Food> tmpFood = new ArrayList();
		
		// 3개만 추출
		int count = 3;
		int a[] = new int[count];
		Random r = new Random();
		
		for(int i=0; i<count; i++){
			a[i] = r.nextInt(20) + 1; // 1 ~ 20까지의 난수 
			for(int j=0; j<i; j++){
				if(a[i] == a[j]){
					i--;
				}
			}
		}
		for(int k : a){
			tmpFood.add(food.get(k));
		}
		
		model.addAttribute("foodsByCategory", tmpFood);
		
		String comment = "";
		String feature1 = "";
		String feature2 = "";
		String feature3 = "";
		String feature4 = "";
		String comment1 = "";
		String comment2 = "";
		String comment3 = "";
		String comment4 = "";
		String imgsrc = "";
		
		switch (category) {
		case "저지방" :
			comment = "저지방 식단에 대한 소개";
			feature1 = "체중 관리";
			feature2 = "심장병 위험 감소";
			feature3 = "소화 개선";
			feature4 = "암 예방";
			comment1 = "고지방 식품 섭취를 줄이면 전체적으로 칼로리를 덜 섭취하게 되어 체중 감량으로 이어질 수 있습니다.";
			comment2 = "높은 수준의 포화지방과 트랜스지방은 심장병 위험 증가와 관련이 있습니다. 저지방 식단은 이러한 유해한 지방 섭취를 줄이고 심장병 위험을 낮출 수 있습니다.";
			comment3 = "지방이 많은 음식은 소화계에 부담을 주어 산성 역류, 팽창, 변비와 같은 문제를 일으킬 수 있습니다. 섬유질이 풍부한 저지방 식단을 섭취하면 소화를 개선시킬 수 있습니다.";
			comment4 = "연구에 따르면 저지방 식단은 유방암이나 대장암 등 특정 유형의 암 위험을 낮추는 것과 관련이 있습니다.";
			imgsrc = "image/FoodCategory/저지방.jpg";
			break;
		case "저염식" :
			comment = "저염식 식단에 대한 소개";
			feature1 = "단기간의 체중 관리";
			feature2 = "성인병 예방";
			feature3 = "골다공증 예방";
			feature4 = "혈압 관리";
			comment1 = "짠맛은 과식을 유발해 더 많은 칼로리를 섭취하며, 소금을 적게 섭취할 시 몸에서는 염분 농도를 맞추기 위해 수분을 내보내는데 이로 인해 붓기가 빠지고 체중이 감소할 수 있습니다.";
			comment2 = "염분이 체내에 과잉 축적되면 수분 대사를 방해하고 혈압이 높아지면서 고혈압을 비롯해 각종 성인병을 유발할 수 있습니다.";
			comment3 = "섭취한 염분은 혈액에서 칼슘과 결합해 소변으로 나가는데, 이로 인에 칼슘 흡수량이 적어지고 골다공증을 유발할 수 있습니다. ";
			comment4 = "과도한 염분은 체내 농도를 높여 몸속 수분량을 늘리기 때문에 이로 인해 혈압이 높아져 다양한 질병을 유발할 수 있습니다.";
			imgsrc = "image/FoodCategory/저염식.png";
			break;
		case "저칼로리" :
			comment = "저칼로리 식단에 대한 소개";
			feature1 = "체중 감량";
			feature2 = "혈당 관리";
			feature3 = "염증 감소";
			feature4 = "노화 관리";
			comment1 = "섭취 칼로리를 줄이면 자연스럽게 에너지 소비가 섭취를 초과하게 되어 체지방이 소모되고 이는 제중 감소로 이어집니다.";
			comment2 = "칼로리가 낮고 지방이 적은 식단을 섭취하면 인슐린 민감도와 혈당 조절이 개선되는 데 도움이 될 수 있습니다. 이는 당뇨병이 있거나 당뇨병에 걸릴 위험이 있는 사람들에게 중요합니다.";
			comment3 = "저칼로리 식단은 곧 저지방 식단과 많은 부분 일치하기 때문에 지방을 적게 먹는데 도움이 되어 여러 만성 질환과 관련이 있는 신체의 염증을 줄이는 데 도움이 될 수 있습니다.";
			comment4 = "저칼로리 식단을 하게 되면 저탄수화물, 저당 식단을 하게 되기 때문에 저당식단의 가장 큰 장점인 노화 관리를 같이 할 수 있습니다.";
			imgsrc = "image/FoodCategory/저칼로리.jpg";
			break;
		case "저당식품" :
			comment = "저당식품 식단에 대한 소개";
			feature1 = "체중 관리";
			feature2 = "혈당 관리";
			feature3 = "충치 예방";
			feature4 = "노화 관리";
			comment1 = "저당식품은 칼로리를 낮춰주어 체중 관리에 도움을 줍니다. 또한, 저당식품은 당이 적은 만큼 천천히 소화되면서 포만감을 오래 지속해주기 때문에 군것질을 피하기 쉽게 해줍니다.";
			comment2 = "저당식품은 혈당을 서서히 올려주기 때문에 에너지 유지와 함께 혈당 변화를 최소화하는 데 도움이 되며, 꾸준히 섭취함으로써 당뇨 예방 효과도 기대할 수 있습니다.";
			comment3 = "높은 당분 섭취는 입안의 박테리아 활동을 활발하게 하여 충치를 일으킬 가능성이 큰데요, 저당식품을 섭취하면 이런 걱정을 줄일 수 있습니다. 이로 인해 아이들의 간식으로도 좋은 음식입니다.";
			comment4 = "저당 식품을 먹어 혈당 관리를 하게 되면 자연스럽게 인슐린 분배가 조절되는데 이 인슐린 분배량이 적어지면 세포의 손상과 쌓이는 불량 단백질의 양이 적어지면서 노화 속도가 줄어들게 됩니다.";
			imgsrc = "image/FoodCategory/저당식품.webp";
			break;
		}
		List<String> features = new ArrayList<String>();
		features.add(feature1);
		features.add(feature2);
		features.add(feature3);
		features.add(feature4);
		
		List<String> comments = new ArrayList<String>();
		comments.add(comment1);
		comments.add(comment2);
		comments.add(comment3);
		comments.add(comment4);
		
		model.addAttribute("foodVO", food);
		model.addAttribute("category", category);
		model.addAttribute("introduce", comment);
		model.addAttribute("features", features);
		model.addAttribute("comments", comments);
		model.addAttribute("imgsrc", imgsrc);
		
		return "food/FoodCategory";
	}
	
	@GetMapping("/fooddetail")
	private String FoodDetailView(Food food, Model model) {

		FoodDetail fdInfo = foodService.getFoodDetail(food.getFUid());
		Food fInfo = foodService.getFood(food.getFUid());
		
		model.addAttribute("fInfo", fInfo);
		model.addAttribute("fdInfo", fdInfo);

		String[] category = new String[4];
		if (fInfo.getFCategory() != null) {
			String fullCategory = fInfo.getFCategory();
			for(int i=0 ; i<4; i++) {
				String kind = fullCategory.split(", ")[i];
				category[i] = kind;
			}
		} else {
			category = null;
		}
		model.addAttribute("categories", category);
		
		return "food/FoodDetail";
	}
	
	@GetMapping("/foodrecipe")
	private String FoodRecipeView(@RequestParam("rfName") String rfName , Model model) {
		
		FoodRecipe foodrecipe= foodService.getFoodRecipe(rfName);
		
		model.addAttribute("foodRecipe", foodrecipe);
		
		return "food/FoodRecipe";
	}
	
}
