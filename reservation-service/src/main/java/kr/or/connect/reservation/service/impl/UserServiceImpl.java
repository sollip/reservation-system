package kr.or.connect.reservation.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.connect.reservation.dao.UserDao;
import kr.or.connect.reservation.domain.User;
import kr.or.connect.reservation.service.UserService;
@Service
public class UserServiceImpl implements UserService{
	@Autowired
	UserDao userDao;

	@Override
	public User loginUser(User user) {
		User userInfo=userDao.selectUser(user.getSnsId());
		if(userInfo!=null){
			if(0==userDao.updateUser(user)){
				//throw new MyException();	
				System.out.println("업데이트 실패");
				return null;
			}
			System.out.println("업데이트 성궁");
		}else{
			if(0==userDao.insertUser(user)){
				//throw new MyException();	
				System.out.println("삽입 실패");
				return null;
			}
				System.out.println("삽입 성공");
		}
		return userInfo;
	}

	@Override
	public User selectUser(String snsId) {
		return userDao.selectUser(snsId);
	}
}
