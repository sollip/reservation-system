package kr.or.connect.reservation.dao;

public class ProductSqls {
	final static String SELECT_PRODUCT_LIST = 
			"select DISTINCT p.id, name, description, content, event ,place_name, place_street, place_lot, homepage, email, tel, sales_flag, sales_end, file_id"
			+" from product as p, product_detail as pd,product_image as pi, display_info as di"
			+" where p.id=pd.product_id and p.id=di.product_id and p.id=pi.product_id and pi.type=1 limit :offset, :limit";


	final static String SELECT_PRODUCT_LIST_IN_CATEGORY = 
			"select DISTINCT p.id, name, description, content, event, place_name, place_street, place_lot, homepage, email, tel, sales_flag, sales_end, file_id"
			+" from product as p, product_detail as pd, product_image as pi, display_info as di"
			+" where p.id=pd.product_id and p.id=di.product_id and p.id=pi.product_id and p.category_id=:categoryId and pi.type=1 limit :offset, :limit";

	final static String COUNT_CATEGORY_PRODUCT_NUMBER=
			"select count(id) from product where category_id=:categoryId";

	final static String COUNT_ALL_CATEGORY_PRODUCT_NUMBER=
			"select count(id) from product";
	
	final static String SELECT_PRODUCT_BY_ID=
			"select DISTINCT p.id, name, description, content, event, place_name, place_street, place_lot, homepage, email, tel, sales_flag, sales_end"
			+" from product as p, product_detail as pd, product_image as pi, display_info as di"
			+" where p.id=pd.product_id and p.id=di.product_id and p.id=pi.product_id and p.id=:id" ;

	final static String SELECT_PRODUCT_IMAGE_LIST=
			"select file_id,type"
			+" from product_image as pi"
			+" where pi.product_id=:id order by type";
	
	final static String SELECT_COMMENT_LIST_BY_ID_ALL=
			"select rr.id, product_id,username,score,comment,DATE_FORMAT(rr.create_date, '%Y-%m-%d') create_date,file_id,image_count"
			+" from(select DISTINCT r.id,product_id,user_id,score,comment,r.create_date,file_id,image_count"
					+" from reservation_user_comment as r"
					+" left join (select DISTINCT r2.id,file_id,count(r2.id) as image_count"
								+" from reservation_user_comment as r2, reservation_user_comment_image as ri2"
								+" where r2.id=ri2.reservation_user_comment_id group by r2.id)as a"
					+" on r.id=a.id)as rr, users as u"
			+" where u.id=rr.user_id and rr.product_id=:id order by rr.id";
	
	final static String SELECT_COMMENT_LIST_BY_ID_LIMIT=
			"select rr.id, product_id,username,score,comment,DATE_FORMAT(rr.create_date, '%Y-%m-%d') create_date,file_id,image_count"
			+" from(select DISTINCT r.id,product_id,user_id,score,comment,r.create_date,file_id,image_count"
			+" from reservation_user_comment as r"
			+" left join (select DISTINCT r2.id,file_id, count(r2.id) as image_count"
			+" from reservation_user_comment as r2, reservation_user_comment_image as ri2"
			+" where r2.id=ri2.reservation_user_comment_id group by r2.id)as a"
			+" on r.id=a.id)as rr, users as u"
			+" where u.id=rr.user_id and rr.product_id=:id order by rr.id limit :limit";
	
	final static String COUNT_COMMENT=
			"select count(id) from reservation_user_comment where product_id=:id";
	
	final static String SELECT_COMMENT_IMAGES_BY_COMMNET_ID=
			"select file_id"
			+" from reservation_user_comment_image"
			+" where reservation_user_comment_id=:commentId";

	final static String SELECT_PRICE_LIST_BY_ID=
			"select id,product_id,price,price_type,discount_rate"
			+" from product_price"
			+" where product_id=:id";

}
