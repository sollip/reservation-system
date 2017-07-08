package kr.or.connect.reservation.service;

import java.util.List;

import kr.or.connect.reservation.domain.Category;
import kr.or.connection.reservation.common.MyException;

public interface CategoryService {
	public int createCategory(Category category) throws MyException;
	public int deleteCategory(int name);
	public int updateCategory(Category category) throws MyException;
	public List<Category> selectAllCategory();
	
}
