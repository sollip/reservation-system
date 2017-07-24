package kr.or.connect.reservation.dao;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservation.domain.Comment;
import kr.or.connect.reservation.domain.CommentImage;
import kr.or.connect.reservation.domain.Price;
import kr.or.connect.reservation.domain.Product;
import kr.or.connect.reservation.domain.ProductImage;

@Repository
public class ProductDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<Product> rowMapperForProduct= BeanPropertyRowMapper.newInstance(Product.class);
	private RowMapper<ProductImage> rowMapperForProductImage= BeanPropertyRowMapper.newInstance(ProductImage.class);
	private RowMapper<Comment> rowMapperForComment= BeanPropertyRowMapper.newInstance(Comment.class);
	private RowMapper<CommentImage> rowMapperForCommentImage= BeanPropertyRowMapper.newInstance(CommentImage.class);
	private RowMapper<Price> rowMapperForPrice= BeanPropertyRowMapper.newInstance(Price.class);

	public ProductDao(DataSource dataSource){
		this.jdbc=new NamedParameterJdbcTemplate(dataSource);
	}

	//함수 이름들 수정해주기..!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! 

	//select 전체 상품 리스트
	public List<Product> selectProductList(int limit, int offset) {
		Map<String,Object>params=new HashMap();
		params.put("limit", limit);
		params.put("offset", offset);
		return jdbc.query(ProductSqls.SELECT_PRODUCT_LIST, params,rowMapperForProduct);
	}

	//select 카테고리별 상품 리스트
	public List<Product> selectProductList(int categoryId, int limit, int offset){
		Map<String,Object>params=new HashMap();
		params.put("categoryId", categoryId);
		params.put("limit", limit);
		params.put("offset", offset);
		return jdbc.query(ProductSqls.SELECT_PRODUCT_LIST_IN_CATEGORY,params,rowMapperForProduct);
	}
	
	//count 전체 총 갯수
	public int countProductsInCategory() {
		Map<String,?>params=Collections.emptyMap();
		return jdbc.queryForObject(ProductSqls.COUNT_ALL_CATEGORY_PRODUCT_NUMBER,params,Integer.class);
	}

	//count 카테고리 별 총 갯수 
	public int countProductsInCategory(int categoryId) {
		Map<String,?>params=Collections.singletonMap("categoryId", categoryId);
		return jdbc.queryForObject(ProductSqls.COUNT_CATEGORY_PRODUCT_NUMBER,params,Integer.class);
	}

	
	//select 상품 상세 정보
	public Product selectProductById(int id){
		Map<String,?>params=Collections.singletonMap("id", id);
		return jdbc.queryForObject(ProductSqls.SELECT_PRODUCT_BY_ID,params,rowMapperForProduct);
	}
	
	//select 상품 이미지 리스트
	public List<ProductImage> selectProductImageList(int id){
		Map<String,?> params=Collections.singletonMap("id", id);
		return jdbc.query(ProductSqls.SELECT_PRODUCT_IMAGE_LIST,params,rowMapperForProductImage);
	}

	//select 상품별 comment list
	public List<Comment> selectCommentListById(int id,int limit){
		Map<String,Object>params=new HashMap();
		params.put("id", id);
		params.put("limit", limit);
		return jdbc.query(ProductSqls.SELECT_COMMENT_LIST_BY_ID_LIMIT, params,rowMapperForComment);
	}

//	//select 상품별 comment list
//	public List<Comment> selectCommentListByIdAll(int id){
//		System.out.println("dao comment");
//		Map<String,Object>params=Collections.singletonMap("id", id);
//		return jdbc.query(ProductSqls.SELECT_COMMENT_LIST_BY_ID_ALL, params,rowMapperForComment);
//	}

	public int countComment(int id){
		Map<String,?>params=Collections.singletonMap("id", id);
		return jdbc.queryForObject(ProductSqls.COUNT_COMMENT,params,Integer.class);
	}

	public List<CommentImage> selectCommentImagesByCommentId(int commentId){
		Map<String,?>params=Collections.singletonMap("commentId", commentId);
		return jdbc.query(ProductSqls.SELECT_COMMENT_IMAGES_BY_COMMNET_ID, params,rowMapperForCommentImage);
	}
	
	public List<Price> selectPriceListById(int id){
		Map<String,?>params=Collections.singletonMap("id", id);
		return jdbc.query(ProductSqls.SELECT_PRICE_LIST_BY_ID, params,rowMapperForPrice);
	}
	
}
