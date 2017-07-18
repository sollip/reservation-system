package kr.or.connect.reservation.dao;

import static org.junit.Assert.assertNotEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservation.domain.Category;
import kr.or.connection.reservation.common.MyException;
@Repository
public class CategoryDao {

	private NamedParameterJdbcTemplate jdbc;
	private SimpleJdbcInsert insertAction;
	private RowMapper<Category> rowMapper= BeanPropertyRowMapper.newInstance(Category.class);
	
	public CategoryDao(DataSource dataSource){
		this.jdbc=new NamedParameterJdbcTemplate(dataSource);
		this.insertAction=new SimpleJdbcInsert(dataSource)
				.withTableName("category").usingGeneratedKeyColumns("id");
	}

	//카테고리 등록
	public int createCategory(Category category)throws MyException{
		//카테고리 중복체크...필요할거같음...================> 서비스로 빼기
		if(selectByName(category.getName())==null){
			SqlParameterSource params=new BeanPropertySqlParameterSource(category);
			return insertAction.executeAndReturnKey(params).intValue();
		}else{
			throw new MyException();//or throw exception
		}
	}

	//select one
	public Category selectByName(String name){
		Map<String,String> params = Collections.singletonMap("name", name);
		System.out.println("selectByName : "+name);
		try{
			return jdbc.queryForObject(CategorySqls.SELECT_BY_NAME, params, rowMapper);
		}catch(EmptyResultDataAccessException e){
			return null;
		}
	}

	//update 
	public int updateCategory(Category category) throws MyException {
		//카테고리 중복체크...필요할거같음...
		if(selectByName(category.getName())==null){
			SqlParameterSource params=new BeanPropertySqlParameterSource(category);
			return jdbc.update(CategorySqls.UPDATE_CATEGORY, params);
		}else{
			throw new MyException();//or throw exception
		}
	}

	//카테고리 리스트 select
	public List<Category> selectAllCategory() {
		return jdbc.query(CategorySqls.SELECT_ALL_CATEGORY, rowMapper);
	}

	//카테고리 삭제
	public int deleteCategory(int id) {
		System.out.println("delete dao");
		Map<String,?> params=Collections.singletonMap("id", id);
		return jdbc.update(CategorySqls.DELETE_CATEGORY,params);
	}
}
