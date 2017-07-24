package kr.or.connect.reservation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.reservation.dao.ProductDao;
import kr.or.connect.reservation.domain.ProductImage;
import kr.or.connect.reservation.domain.Comment;
import kr.or.connect.reservation.domain.CommentImage;
import kr.or.connect.reservation.domain.Price;
import kr.or.connect.reservation.domain.Product;
import kr.or.connect.reservation.dto.ProductParam;
import kr.or.connect.reservation.service.ProductService;

@Service
@Transactional
public class ProductServiceImpl implements ProductService{
	@Autowired
	ProductDao productDao;

	@Transactional(readOnly=true)
	@Override
	public List<Product> selectProductList(int categoryId,int limit,int offset) {
		if(categoryId==0){//전체목록
			return productDao.selectProductList(limit,offset);
		}else{
			return productDao.selectProductList(categoryId,limit,offset);
		}
	}
	
	@Override
	@Transactional(readOnly=true)
	public Product selectProductById(int id) {
		return productDao.selectProductById(id);
	}
	
	@Override
	@Transactional(readOnly=true)
	public int countProductsInCategory(int categoryId) {
		if(categoryId==0){
			return productDao.countProductsInCategory();
		}else{
			return productDao.countProductsInCategory(categoryId);
		}
		
	}
	@Override
	@Transactional(readOnly=true)
	public List<ProductImage> selectProductImageList(int id){
		return productDao.selectProductImageList(id);
	}

	@Override
	public List<Comment> selectCommentListById(int id, int limit) {
		System.out.println("service comment");
		return productDao.selectCommentListById(id,limit);
	}
	
	//comment 총 갯수
	public int countCommentByProductId(int id){
		return productDao.countComment(id);
	}

	@Override
	public List<CommentImage> selectCommentImagesByCommentId(int commentId) {
		return productDao.selectCommentImagesByCommentId(commentId);
	}

	@Override
	public List<Price> selectPriceListById(int id) {
		return productDao.selectPriceListById(id);
		
	}

	@Override
	public ProductImage selectProductMainImage(int id, int type) {
		
		return null;
	}
	
	
	
}
