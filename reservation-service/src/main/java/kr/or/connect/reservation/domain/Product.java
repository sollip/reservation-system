package kr.or.connect.reservation.domain;

import java.sql.Timestamp;
import java.util.Date;

public class Product {
	private int id;
	private int ctegoryId;
	private String name;
	private String description;
	private String event;
	private String content;
	private String salesFlag;
	private Timestamp salesEnd;
	private String fileId;
	
	private String placeName;
	private String placeStreet;
	private String placeLot;
	private String tel;
	private String homepage;
	private String email;
	private String displayEnd;

	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCtegoryId() {
		return ctegoryId;
	}
	public void setCtegoryId(int ctegoryId) {
		this.ctegoryId = ctegoryId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPlaceName() {
		return placeName;
	}
	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}
	public String getSalesFlag() {
		return salesFlag;
	}

	public void setSalesFlag(String salesFlag) {
		this.salesFlag = salesFlag;
	}

	public Timestamp getSalesEnd() {
		return salesEnd;
	}

	public void setSalesEnd(Timestamp salesEnd) {
		this.salesEnd = salesEnd;
	}

	public String getPlaceStreet() {
		return placeStreet;
	}
	public void setPlaceStreet(String placeStreet) {
		this.placeStreet = placeStreet;
	}
	public String getPlaceLot() {
		return placeLot;
	}
	public void setPlaceLot(String placeLot) {
		this.placeLot = placeLot;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getHomepage() {
		return homepage;
	}
	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDisplayEnd() {
		return displayEnd;
	}
	public void setDisplayEnd(String displayEnd) {
		this.displayEnd = displayEnd;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	
	
	
	
}
