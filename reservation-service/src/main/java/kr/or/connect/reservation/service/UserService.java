package kr.or.connect.reservation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.connect.reservation.dao.UserDao;
import kr.or.connect.reservation.domain.User;


public interface UserService {
	
	
	public User loginUser(User user);
	public User selectUser(String snsId);
}
