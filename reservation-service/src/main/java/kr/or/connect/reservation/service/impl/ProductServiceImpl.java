package kr.or.connect.reservation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.reservation.dao.ProductDao;
import kr.or.connect.reservation.domain.ProductImage;
import kr.or.connect.reservation.domain.Comment;
import kr.or.connect.reservation.domain.CommentImage;
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
	public List<Product> selectProductList(ProductParam productParam) {
		if(productParam.getCategoryId()==0){//전체목록
			return productDao.selectAllProductList(productParam);
		}else{
			return productDao.selectProductList(productParam);
		}
	}
	
	@Override
	@Transactional(readOnly=true)
	public Product selectProductByProductId(int id) {
		return productDao.selectProductById(id);
	}
	
	@Override
	@Transactional(readOnly=true)
	public int countCategoryProduct(int categoryId) {
		if(categoryId==0){
			return productDao.countAllCategoryProduct();
		}else{
			return productDao.countCategoryProduct(categoryId);
		}
		
	}
	@Override
	@Transactional(readOnly=true)
	public List<ProductImage> selectProductImageList(int id){
		return productDao.selectProductImageList(id);
	}

	//limit없이 불러오기
	@Override
	public List<Comment> selectCommentListById(int id, int limit) {
		System.out.println("service comment");
		return productDao.selectCommentListByIdLimit(id,limit);
	}
	
	
	//comment 총 갯수
	public int countCommentByProductId(int id){
		return productDao.countComment(id);
	}

	@Override
	public List<CommentImage> selectCommentImageByCommentId(int commentId) {
		return productDao.selectCommentImageByCommentId(commentId);
	}
	
	
}
