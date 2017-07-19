package kr.or.connect.reservation.domain;

public class ProductImage {
	private int fileId;
	private int type;
	
	public ProductImage() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ProductImage(int fileId, int type) {
		super();
		this.fileId = fileId;
		this.type = type;
	}
	
	public int getFileId() {
		return fileId;
	}
	public void setFileId(int fileId) {
		this.fileId = fileId;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	

	
	
}
