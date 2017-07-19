package kr.or.connect.reservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import kr.or.connect.reservation.service.ProductService;

@RestController
@RequestMapping("/details")
public class DetailRestController {
	@Autowired
	ProductService productService;
	
	@GetMapping("/{id}")
	public ModelAndView loadDatailPage(@PathVariable int id, Model model){
		model.addAttribute("product", productService.selectProductByProductId(id));
		ModelAndView mav=new ModelAndView("detail");
		return mav;
	}
}
