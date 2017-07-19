package kr.or.connect.reservation.service;

import java.util.List;

import kr.or.connect.reservation.domain.Comment;
import kr.or.connect.reservation.domain.CommentImage;
import kr.or.connect.reservation.domain.Product;
import kr.or.connect.reservation.domain.ProductImage;
import kr.or.connect.reservation.dto.ProductParam;

public interface ProductService {

	public List<Product> selectProductList(ProductParam productParam);
	public Product selectProductByProductId(int id);
	public int countCategoryProduct(int categoryId);
	public List<ProductImage> selectProductImageList(int id);
	public List<Comment> selectCommentListById(int id, int limit);
	public int countCommentByProductId(int id);
	public List<CommentImage> selectCommentImageByCommentId(int commentId);
}
