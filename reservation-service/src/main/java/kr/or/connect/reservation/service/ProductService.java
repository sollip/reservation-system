package kr.or.connect.reservation.service;

import java.util.List;

import kr.or.connect.reservation.domain.Comment;
import kr.or.connect.reservation.domain.CommentImage;
import kr.or.connect.reservation.domain.Price;
import kr.or.connect.reservation.domain.Product;
import kr.or.connect.reservation.domain.ProductImage;

public interface ProductService {

	public List<Product> selectProductList(int categoryId, int limit, int offset); 
	public Product selectProductById(int id);
	public int countProductsInCategory(int categoryId);
	
	public List<ProductImage> selectProductImageList(int id);
	public ProductImage selectProductMainImage(int id, int type);
	
	
	public List<Comment> selectCommentListById(int id, int limit);
	public int countCommentByProductId(int id);
	public List<CommentImage> selectCommentImagesByCommentId(int commentId);
	
	public List<Price> selectPriceListById(int id);
}
