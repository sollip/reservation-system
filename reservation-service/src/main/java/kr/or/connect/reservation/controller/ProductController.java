package kr.or.connect.reservation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.reservation.domain.Product;
import kr.or.connect.reservation.dto.ProductParam;
import kr.or.connect.reservation.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
	@Autowired
	ProductService productService;
	
//	@GetMapping
//	public List<Product> getProductListAll(){
//		System.out.println("getProductListAll");
//		return productService.selectAllProductList();
//	}
	
//	@GetMapping("/{categoryId}")
//	public List<Product> getProductListByCategoryId(@PathVariable int categoryId){
//		System.out.println("getProductListByCategoryId : "+categoryId);
//		return productService.selectProductListByCategoryId(categoryId);
//	}
	
	@PostMapping
	public List<Product> getProductList(@RequestBody ProductParam productParam){
		return productService.selectProductList(productParam);
	}
	
	@GetMapping("/{categoryId}")
	public int getCategoryCount(@PathVariable int categoryId){
		return productService.countCategoryProductNumber(categoryId);
	}
	
//	@GetMapping("/{categoryId}/{limit}/{offset}")
//	public List<Product> getProductList(@PathVariable int categoryId, @PathVariable int limit, @PathVariable int offset){
//		ProductParam productParam=new ProductParam(categoryId,limit,offset);
//		System.out.println("getProductList :: "+productParam);
//		return productService.selectProductList(productParam);
//	}
	
}
