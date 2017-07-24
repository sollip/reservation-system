package kr.or.connect.reservation.domain;

public class User {
	private String username;
	private String email;
	private String tel;
	private String nickname;
	private String snsId;
	private String snsType;
	private String snsProfile;
	private int id;
	private int adminFlag;
	
	public int getAdminFlag() {
		return adminFlag;
	}


	public void setAdminFlag(int adminFlag) {
		this.adminFlag = adminFlag;
	}


	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getSnsId() {
		return snsId;
	}
	public void setSnsId(String snsId) {
		this.snsId = snsId;
	}
	public String getSnsType() {
		return snsType;
	}
	public void setSnsType(String snsType) {
		this.snsType = snsType;
	}
	public String getSnsProfile() {
		return snsProfile;
	}
	public void setSnsProfile(String snsProfile) {
		this.snsProfile = snsProfile;
	}
	@Override
	public String toString() {
		return "User [username=" + username + ", email=" + email + ", tel=" + tel + ", nickname=" + nickname
				+ ", snsId=" + snsId + ", snsType=" + snsType + ", snsProfile=" + snsProfile + "]";
	}
}
