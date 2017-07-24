package kr.or.connect.reservation.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.connect.reservation.dao.ReservationDao;
import kr.or.connect.reservation.domain.Reservation;
import kr.or.connect.reservation.service.ReservationService;
@Service
public class ReservationServiceImpl implements ReservationService{
	@Autowired
	ReservationDao reservationDao;
	
	@Override
	public int insertReservation(Reservation reservation) {
		return reservationDao.insertReservation(reservation);
	}

}
