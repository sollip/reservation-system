package kr.or.connect.reservation.dao;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservation.domain.Product;
import kr.or.connect.reservation.domain.Reservation;


@Repository
public class ReservationDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<Reservation> rowMapperForProduct= BeanPropertyRowMapper.newInstance(Reservation.class);
	private SimpleJdbcInsert insertAction;
	
	public ReservationDao(DataSource dataSource){
		this.jdbc=new NamedParameterJdbcTemplate(dataSource);
		this.insertAction=new SimpleJdbcInsert(dataSource)
				.withTableName("reservation_info").usingGeneratedKeyColumns("id");
	}
	
	public int insertReservation(Reservation reservation){
		SqlParameterSource params=new BeanPropertySqlParameterSource(reservation);
		return insertAction.executeAndReturnKey(params).intValue();
	}
}
