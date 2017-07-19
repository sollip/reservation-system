package kr.or.connect.reservation.dao;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservation.domain.FileDto;

@Repository
public class FileDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<FileDto> rowMapper= BeanPropertyRowMapper.newInstance(FileDto.class);

	public FileDao(DataSource dataSource){
		this.jdbc=new NamedParameterJdbcTemplate(dataSource);
	}
	
	public int countFileNumber(int id){
		Map<String,?> params=Collections.singletonMap("id", id);
		return jdbc.queryForObject(FileSqls.COUNT_FILE_NUMBER,params,Integer.class);
	}
	
	//user id 도 필요한가..?!!!?!?!?!?
	public List<FileDto> selectFileList(int id){
		Map<String,?> params=Collections.singletonMap("id", id);
		return jdbc.query(FileSqls.SELECT_FILE_LIST,params,rowMapper);
	}
	
	public FileDto selectFileById(int id){
		Map<String,?>params=Collections.singletonMap("id",id);
		return jdbc.queryForObject(FileSqls.SELECT_FILE_BY_ID,params,rowMapper);
	}
	
}
