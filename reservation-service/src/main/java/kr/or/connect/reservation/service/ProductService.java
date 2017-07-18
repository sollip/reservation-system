package kr.or.connect.reservation.service;

import java.util.List;

import kr.or.connect.reservation.domain.Product;
import kr.or.connect.reservation.dto.ProductParam;

public interface ProductService {
//	public List<Product> selectAllProductList();
//	public List<Product> selectProductListByCategoryId(int categoryId);
	public int countCategoryProductNumber(int categoryId);
	public List<Product> selectProductList(ProductParam productParam);
	
}
