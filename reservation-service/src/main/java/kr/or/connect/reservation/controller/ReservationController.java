package kr.or.connect.reservation.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import kr.or.connect.reservation.domain.Price;
import kr.or.connect.reservation.domain.Product;
import kr.or.connect.reservation.domain.ProductImage;
import kr.or.connect.reservation.domain.Reservation;
import kr.or.connect.reservation.domain.User;
import kr.or.connect.reservation.service.ProductService;
import kr.or.connect.reservation.service.ReservationService;
import kr.or.connect.reservation.service.UserService;

@RestController
@RequestMapping("/reservations")
public class ReservationController {
	@Autowired
	ProductService productService;
	@Autowired
	UserService userService;
	@Autowired
	ReservationService reservationService;
	@GetMapping
	public ModelAndView getReservationPagesPyProductId(@RequestParam("id") int id,Model model,HttpSession session ){
		
		Product product=productService.selectProductById(id);
		model.addAttribute("product",product);

		List<Price> list=productService.selectPriceListById(id);
		model.addAttribute("priceList",list);

		//product image정보
		List<ProductImage> imageList=productService.selectProductImageList(id);
		model.addAttribute("image",imageList.get(0)); 
		// 대표이미지만... 실제 네이버 예약에서는 여러이미지 리스트를 가져온다.. 
		//나중에 추가해줄 수 있으면 이부분을 추가하기위해 list의 첫번쨰(대표) 이미지만가져온다
		
		if(session.getAttribute("login")!=null && session.getAttribute("login").equals("loginOK")){ //로그인되어있으면 정보를 default로 뿌려준다..
			User user=(User) session.getAttribute("user");
			System.out.println(user.getTel());
			model.addAttribute("user", user);	
		}
		ModelAndView mav=new ModelAndView("reserve");
		return mav;
	}
	
	
	@PostMapping
	public void setReservation(HttpSession session, @RequestBody Reservation reservation){
		User user=(User) session.getAttribute("user");
		reservation.setUserId(user.getId());
		System.out.println(reservation.toString());
		System.out.println("reservation요청옴");
		SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
		Calendar cal = Calendar.getInstance();
		String now = null;
		now = formatter.format(cal.getTime());
		Timestamp ts = Timestamp.valueOf(now);
		reservation.setReservationDate(ts);
		reservationService.insertReservation(reservation);
		
	}
	
			
}
