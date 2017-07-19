package kr.or.connect.reservation.service;

import java.util.List;

public interface CommonService {
	//나중에 인터페이스 모으는거 시도해보깅....
	public int count(int id);
	public List<?> selectAll();
	public List<?> selectById(int id);
	
}
