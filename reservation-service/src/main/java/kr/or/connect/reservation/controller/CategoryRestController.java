package kr.or.connect.reservation.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import kr.or.connect.reservation.domain.Category;
import kr.or.connect.reservation.service.CategoryService;
import kr.or.connection.reservation.common.MyException;

@RestController
@RequestMapping("/categories")//restAPI사용할때 유알엘이 복수로 사용하면 좋다
public class CategoryRestController {
	@Autowired
	private CategoryService categoryService;
	
//	//모든 카테고리 리스트 출력
//	@GetMapping
//	public ModelAndView selectAllCategory(Model model){
//		System.out.println("RestController selectAll");
//		List<Category> list=categoryService.selectAllCategory();
//		model.addAttribute("list", list);
//		ModelAndView mav=new ModelAndView("viewList");
//		return mav;
//	}	
	
	//mainPage의 카테고리 요청 
	@GetMapping("/getCategoryList")
	public Collection<Category> getCategoryList(){
		List<Category> list=categoryService.selectAllCategory();
		return list;
	}

	//카테고리 등록
	@PostMapping
	public Category createCategory(Category category) throws MyException{
		int id=categoryService.createCategory(category);
		category.setId(id);
		return category;
	}

	//카테고리 삭제
	@DeleteMapping("/{id}")
	public void deleteCategory(@PathVariable int id){
		System.out.println("delete "+id);
		categoryService.deleteCategory(id);
	}

	//카테고리 수정
	@PutMapping("/{id}")
	public void update(@PathVariable int id, @RequestBody Category category) throws MyException {
		System.out.println("update id:"+id+"name:"+category.getName());
		category.setId(id);
		categoryService.updateCategory(category);
	}
}
