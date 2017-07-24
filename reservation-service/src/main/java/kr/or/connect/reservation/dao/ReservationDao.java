package kr.or.connect.reservation.dao;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservation.domain.Product;
import kr.or.connect.reservation.domain.Reservation;


@Repository
public class ReservationDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<Reservation> rowMapperForProduct= BeanPropertyRowMapper.newInstance(Reservation.class);
	
	public ReservationDao(DataSource dataSource){
		this.jdbc=new NamedParameterJdbcTemplate(dataSource);
	}
}
