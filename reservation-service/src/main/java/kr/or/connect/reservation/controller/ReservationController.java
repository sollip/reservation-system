package kr.or.connect.reservation.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/reservations")
public class ReservationController {
	
	@GetMapping("{id}")
	public ModelAndView getReservationPage(@PathVariable("id")int id){
		System.out.println("예약하기 페이지");
		ModelAndView mav=new ModelAndView("reserve");
		return mav;
	}

}
