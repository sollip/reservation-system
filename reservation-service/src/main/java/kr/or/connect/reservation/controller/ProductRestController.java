package kr.or.connect.reservation.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import kr.or.connect.reservation.domain.Comment;
import kr.or.connect.reservation.domain.CommentImage;
import kr.or.connect.reservation.domain.Product;
import kr.or.connect.reservation.domain.ProductImage;
import kr.or.connect.reservation.dto.ProductParam;
import kr.or.connect.reservation.service.FileService;
import kr.or.connect.reservation.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductRestController {
	
	@Autowired
	ProductService productService;
	
	@Autowired
	FileService fileService;
	
	@PostMapping
	public List<Product> getProductList(@RequestBody ProductParam productParam){
		return productService.selectProductList(productParam);
	}
	
	@GetMapping("/{categoryId}")
	public int getCategoryCount(@PathVariable int categoryId){
		return productService.countCategoryProduct(categoryId);
	}
	
	@GetMapping("/detail/{id}")
	public ModelAndView getProductDetailByProductId(@PathVariable int id, Model model){
		//product 정보
		Product product=productService.selectProductByProductId(id);
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
		List<CommentImage> list= productService.selectCommentImageByCommentId(commentId);
		for(CommentImage c:list){
			System.out.println(":::"+c.getFileId());
		}
		return list;
	}
	
	@GetMapping("/commentList/{id}")
	public ModelAndView getCommentListPage(@PathVariable int id){
		System.out.println("....reivew...page...");
		ModelAndView mav=new ModelAndView("review");
		return mav;
	}
	
		
	
	
	
}
