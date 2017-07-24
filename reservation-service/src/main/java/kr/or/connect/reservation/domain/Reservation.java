package kr.or.connect.reservation.domain;

import java.sql.Timestamp;

public class Reservation {
	private int id;
	private int productId;
	private int userId;
	private String generalTicketCount;
	private String youthTicketCount;
	private String childTicketCount;
	private String reservationName;
	private String reservationTel;
	private	String reservationEmail;
	private Timestamp reservationDate;
	private String reservationType;
	private Timestamp createDate;
	public Reservation() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getGeneralTicketCount() {
		return generalTicketCount;
	}
	public void setGeneralTicketCount(String generalTicketCount) {
		this.generalTicketCount = generalTicketCount;
	}
	public String getYouthTicketCount() {
		return youthTicketCount;
	}
	public void setYouthTicketCount(String youthTicketCount) {
		this.youthTicketCount = youthTicketCount;
	}
	public String getChildTicketCount() {
		return childTicketCount;
	}
	public void setChildTicketCount(String childTicketCount) {
		this.childTicketCount = childTicketCount;
	}
	public String getReservationName() {
		return reservationName;
	}
	public void setReservationName(String reservationName) {
		this.reservationName = reservationName;
	}
	public String getReservationTel() {
		return reservationTel;
	}
	public void setReservationTel(String reservationTel) {
		this.reservationTel = reservationTel;
	}
	public String getReservationEmail() {
		return reservationEmail;
	}
	public void setReservationEmail(String reservationEmail) {
		this.reservationEmail = reservationEmail;
	}
	public Timestamp getReservationDate() {
		return reservationDate;
	}
	public void setReservationDate(Timestamp reservationDate) {
		this.reservationDate = reservationDate;
	}
	public String getReservationType() {
		return reservationType;
	}
	public void setReservationType(String reservationType) {
		this.reservationType = reservationType;
	}
	public Timestamp getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}
	@Override
	public String toString() {
		return "Reservation [id=" + id + ", productId=" + productId + ", userId=" + userId + ", generalTicketCount="
				+ generalTicketCount + ", youthTicketCount=" + youthTicketCount + ", childTicketCount="
				+ childTicketCount + ", reservationName=" + reservationName + ", reservationTel=" + reservationTel
				+ ", reservationEmail=" + reservationEmail + ", reservationDate=" + reservationDate
				+ ", reservationType=" + reservationType + ", createDate=" + createDate + "]";
	}
	
	
	
	
}
