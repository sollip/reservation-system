package kr.or.connect.reservation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {
//	@GetMapping
//	public String index(){
//		return "redirect:/category";
//	}
	@GetMapping
	public String mainPage(){
		System.out.println("mainpage요청");
		return "mainpage";
	}
}
