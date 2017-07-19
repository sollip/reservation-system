package kr.or.connect.reservation.dao;

public class FileSqls {
	final static String COUNT_FILE_NUMBER=
			"select file_name,save_file_name"
			+" from product_image as pi, file as f"
			+" where pi.product_id=:id and pi.file_id=f.id ";
	
	final static String SELECT_FILE_LIST=
			"select file_name,save_file_name,type,file_id"
			+" from product_image as pi, file as f"
			+" where pi.product_id=1 and pi.file_id=f.id order by type";
	
	final static String SELECT_FILE_BY_ID=
			"select id, file_name, save_file_name, content_type,file_length"
			+" from file"
			+" where id=:id";

}
