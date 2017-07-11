package kr.or.connect.reservation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.reservation.dao.CategoryDao;
import kr.or.connect.reservation.domain.Category;
import kr.or.connect.reservation.service.CategoryService;
import kr.or.connection.reservation.common.MyException;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService{
	@Autowired
	private CategoryDao categoryDao;
	
	@Override
	@Transactional(readOnly=true)
	public List<Category> selectAllCategory() {
		return categoryDao.selectAllCategory();
	}
	
	@Override
	public int createCategory(Category category) throws MyException {
		return categoryDao.createCategory(category);
	}

	@Override
	public void deleteCategory(int id) {
		categoryDao.deleteCategory(id);
		
	}

	@Override
	public int updateCategory(Category category) throws MyException {
		return categoryDao.updateCategory(category);
	}
}
