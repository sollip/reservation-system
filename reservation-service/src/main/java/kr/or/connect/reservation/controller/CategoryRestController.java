package kr.or.connect.reservation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import kr.or.connect.reservation.domain.Category;
import kr.or.connect.reservation.service.CategoryService;
import kr.or.connection.reservation.common.MyException;

@RestController
@RequestMapping("/category")
public class CategoryRestController {
	@Autowired
	private CategoryService categoryService;

	//모든 카테고리 리스트 출력
	@GetMapping("/selectAll")
	public ModelAndView selectAllCategory(Model model){
		System.out.println("RestController selectAll");
		List<Category> list=categoryService.selectAllCategory();
		model.addAttribute("list", list);
		ModelAndView mav=new ModelAndView("viewList");
		return mav;
	}	

	//카테고리 등록
	@GetMapping("/create")
	public Category createCategory(Category category) throws MyException{
		System.out.println("create");
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

	
	@PutMapping("/{id}")
	public void update(@PathVariable int id, @RequestBody Category category) throws MyException {
		System.out.println("update id:"+id+"name:"+category.getName());
		category.setId(id);
		categoryService.updateCategory(category);
	}

}
