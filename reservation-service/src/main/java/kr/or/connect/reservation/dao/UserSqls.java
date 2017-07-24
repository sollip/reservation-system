package kr.or.connect.reservation.dao;

public class UserSqls {
	final static String SELECT_USER=
			"select id, username,nickname,sns_id,email,tel"
			+" from users"
			+" where sns_id=:snsId ";
	final static String INSERT_USER=
			"insert into users(admin_flag,sns_profile,sns_id,nickname,email,username,create_date)"
			+" values(0,:snsProfile,:snsId,:nickname,:email,:username,sysdate())";
	final static String UPDATE_USER=
			"UPDATE users"
			+ " set sns_profile=:snsProfile,sns_id=:snsId,nickname=:nickname,email=:email,username=:username,create_date=sysdate()"
			+ " where sns_id=:snsId";
	final static String DELETE_USER="";
	
	
}
