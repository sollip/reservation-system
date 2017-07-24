package kr.or.connect.reservation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/myReservations")
public class MyReservationController {
	@GetMapping
	public String getMyReservationPage(){
		System.out.println("myreservation요청");
		return "myreservation";
	}
}
