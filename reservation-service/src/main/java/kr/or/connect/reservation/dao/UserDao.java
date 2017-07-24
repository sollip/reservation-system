package kr.or.connect.reservation.dao;

import java.util.Collections;
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

import kr.or.connect.reservation.domain.User;

@Repository
public class UserDao {
	private NamedParameterJdbcTemplate jdbc;
	private SimpleJdbcInsert insertAction;
	private RowMapper<User> rowMapper= BeanPropertyRowMapper.newInstance(User.class);

	public UserDao(DataSource dataSource){
		this.jdbc=new NamedParameterJdbcTemplate(dataSource);
		this.insertAction=new SimpleJdbcInsert(dataSource)
				.withTableName("users").usingGeneratedKeyColumns("id");
	}

	public User selectUser(User user){
		SqlParameterSource params=new BeanPropertySqlParameterSource(user);
		try{
			return jdbc.queryForObject(UserSqls.SELECT_USER, params, rowMapper);
		}catch(EmptyResultDataAccessException e){
			return null;
		}
	}
	
	public User selectUser(String snsId){
		Map<String,?>params=Collections.singletonMap("snsId", snsId);
		try{
			return jdbc.queryForObject(UserSqls.SELECT_USER, params, rowMapper);
		}catch(EmptyResultDataAccessException e){
			return null;
		}
	}

	public int updateUser(User user) {
		SqlParameterSource params=new BeanPropertySqlParameterSource(user);
		return jdbc.update(UserSqls.UPDATE_USER, params);
	}

	public int insertUser(User user) {
		user.setAdminFlag(0);
		SqlParameterSource params=new BeanPropertySqlParameterSource(user);
		return insertAction.executeAndReturnKey(params).intValue();
	}
}
