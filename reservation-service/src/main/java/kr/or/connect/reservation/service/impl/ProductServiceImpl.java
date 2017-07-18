package kr.or.connect.reservation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.reservation.dao.ProductDao;
import kr.or.connect.reservation.domain.Product;
import kr.or.connect.reservation.dto.ProductParam;
import kr.or.connect.reservation.service.ProductService;

@Service
@Transactional
public class ProductServiceImpl implements ProductService{
	@Autowired
	ProductDao productDao;
	
//	@Override //사용x
//	@Transactional(readOnly=true)
//	public List<Product> selectAllProductList() {
//		return productDao.selectAllProductList();
//	}
//
//	@Override //사용x
//	@Transactional(readOnly=true)
//	public List<Product> selectProductListByCategoryId(int categoryId) {
//		return productDao.selectProductListByCategoryId(categoryId);
//	}
	
	/////////////////////////////////////////////////////////////////
	@Transactional(readOnly=true)
	@Override
	public List<Product> selectProductList(ProductParam productParam) {
		if(productParam.getCategoryId()==0){//전체목록
			return productDao.selectAllProductList(productParam);
		}else{
			return productDao.selectProductList(productParam);
		}
	}
	
	@Override
	@Transactional(readOnly=true)
	public int countCategoryProductNumber(int categoryId) {
		if(categoryId==0){
			return productDao.countAllCategoryProductNumber();
		}else{
			return productDao.countCategoryProductNumber(categoryId);
		}
		
	}
}
