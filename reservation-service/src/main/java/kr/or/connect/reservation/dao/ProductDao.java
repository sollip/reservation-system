package kr.or.connect.reservation.dao;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservation.domain.Product;
import kr.or.connect.reservation.dto.ProductParam;

@Repository
public class ProductDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<Product> rowMapper= BeanPropertyRowMapper.newInstance(Product.class);

	public ProductDao(DataSource dataSource){
		this.jdbc=new NamedParameterJdbcTemplate(dataSource);
	}

	//select 전체 상품 리스트
	public List<Product> selectAllProductList(ProductParam productParam) {
		SqlParameterSource params=new BeanPropertySqlParameterSource(productParam);
		return jdbc.query(ProductSqls.SELECT_ALL_PRODUCT, params,rowMapper);
	}
	
	//select 카테고리별 상품 리스트
	public List<Product> selectProductList(ProductParam productParam){
		SqlParameterSource params=new BeanPropertySqlParameterSource(productParam);
		return jdbc.query(ProductSqls.SELECT_PRODUCT_LIST,params,rowMapper);
	}

	//전체 총 갯수
		public int countAllCategoryProductNumber() {
			Map<String,?>params=Collections.emptyMap();
			return jdbc.queryForObject(ProductSqls.COUNT_ALL_CATEGORY_PRODUCT_NUMBER,params,Integer.class);
		}
		
	//카테고리 별 총 갯수 
	public int countCategoryProductNumber(int categoryId) {
		Map<String,?>params=Collections.singletonMap("categoryId", categoryId);
		return jdbc.queryForObject(ProductSqls.COUNT_CATEGORY_PRODUCT_NUMBER,params,Integer.class);
	}
}
