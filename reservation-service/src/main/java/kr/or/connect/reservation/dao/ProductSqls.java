package kr.or.connect.reservation.dao;

public class ProductSqls {
	final static String SELECT_ALL_PRODUCT = 
			"select p.id, name, description, place_name, save_file_name, file_name"
			+" from product as p, product_detail as pd, file as f, product_image as pi, display_info as di"
			+" where p.id=pd.product_id and p.id=di.product_id and p.id=pi.product_id and pi.file_id=f.id limit :offset, :limit";


	final static String SELECT_PRODUCT_LIST = 
			"select p.id, name, description, place_name, save_file_name, file_name"
			+" from product as p, product_detail as pd, file as f, product_image as pi, display_info as di"
			+" where p.id=pd.product_id and p.id=di.product_id and p.id=pi.product_id and pi.file_id=f.id and p.category_id=:categoryId limit :offset, :limit";

	final static String COUNT_CATEGORY_PRODUCT_NUMBER=
			"select count(id) from product where category_id=:categoryId";

	final static String COUNT_ALL_CATEGORY_PRODUCT_NUMBER=
			"select count(id) from product";




}
