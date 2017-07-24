package kr.or.connect.reservation.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import kr.or.connect.reservation.domain.Comment;
import kr.or.connect.reservation.domain.CommentImage;
import kr.or.connect.reservation.domain.Price;
import kr.or.connect.reservation.domain.Product;
import kr.or.connect.reservation.domain.ProductImage;
import kr.or.connect.reservation.domain.User;
import kr.or.connect.reservation.service.FileService;
import kr.or.connect.reservation.service.ProductService;
import kr.or.connect.reservation.service.UserService;

@RestController
@RequestMapping("/products")
public class ProductRestController {

	@Autowired
	ProductService productService;
	
	@Autowired
	UserService userService;

	@Autowired
	FileService fileService;

	@GetMapping
	public List<Product> getProductList(@RequestParam("categoryId") Integer categoryId, @RequestParam("limit") Integer limit,
			@RequestParam("offset") Integer offset){
		List<Product> list=productService.selectProductList(categoryId,limit,offset);
		System.out.println(list.toString());
		return list;
	}

	@GetMapping("/productCount")
	public int getProductCount(@RequestParam("categoryId") int categoryId){
		return productService.countProductsInCategory(categoryId);
	}
	
	//상세정보 페이지 
	@GetMapping("/detail")///detail?id={id}
	public ModelAndView getDetailByProductId(@RequestParam("id") int id, Model model){
		//product 정보
		Product product=productService.selectProductById(id);
		model.addAttribute("product", product);

		//product image정보
		List<ProductImage> imageList=productService.selectProductImageList(id);
		model.addAttribute("imageList",imageList);
		model.addAttribute("imageCount",imageList.size());

		//comment 정보
		List<Comment> commentList=productService.selectCommentListById(id,3);
		model.addAttribute("commentList", commentList);
		//model.addAttribute("commentCount",commentList.size());

		//comment image 정보
		int commentCount=productService.countCommentByProductId(id);
		model.addAttribute("commentCount", commentCount);

		//score 평균
		double result=0;
		for(Comment comment:commentList){
			result+=comment.getScore();
		}
		double averScore=Double.parseDouble(String.format("%.1f",result/(double)commentList.size()));
		System.out.println(averScore);
		model.addAttribute("averScore",averScore);

		SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
		Calendar cal = Calendar.getInstance();
		String now = formatter.format(cal.getTime());
		Timestamp ts = Timestamp.valueOf(now);
		System.out.println(product.getSalesEnd());
		if(product.getSalesEnd().compareTo(ts)<0){
			model.addAttribute("salesEnd", true);
		}else{
			model.addAttribute("salesEnd", false);
		}		
		ModelAndView mav=new ModelAndView("detail");
		return mav;
	}

	@GetMapping("/commentImages")
	public List<CommentImage> getCommentImageByCommentId(@RequestParam("commentId") Integer commentId){
		System.out.println("commentId : "+commentId);
		List<CommentImage> list= productService.selectCommentImagesByCommentId(commentId);
		for(CommentImage c:list){
			System.out.println(":::"+c.getFileId());
		}
		return list;
	}

	@GetMapping("/commentList")
	public ModelAndView getCommentListPageByProductId(@RequestParam("id") int id){
		System.out.println("....reivew...page...");
		ModelAndView mav=new ModelAndView("review");
		return mav;
	}
	
	//예약페이지 
	@GetMapping("/reservation")
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
			System.out.println("로그인되어있당~!");
			String userId= (String) session.getAttribute("userId");
			User user=userService.selectUser(userId);
			System.out.println(user.getTel());
			model.addAttribute("user", user);	
		}

		ModelAndView mav=new ModelAndView("reserve");
		return mav;
	}
}
