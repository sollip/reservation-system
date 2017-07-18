package kr.or.connect.reservation.dao;

public class CategorySqls {
	final static String DELETE_CATEGORY = 
			"delete from category where id=:id";
	final static String SELECT_ALL_CATEGORY = 
			"select id,name from category";
	final static String SELECT_BY_NAME = 
			"select id, name from category where name=:name";
	final static String UPDATE_CATEGORY =
			"update category set name= :name where id= :id";
	
	//select사용할때 *를 사용하는 것 보다 컬럼명을 하나씩 명시적으로 써주는 것이 좋다.
	
}
