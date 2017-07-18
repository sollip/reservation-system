package kr.or.connect.reservation.dto;

public class ProductParam {
	private int categoryId;
	private int limit;
	private int offset;
	
	public ProductParam(int categoryId, int limit, int offset) {
		super();
		this.categoryId = categoryId;
		this.limit = limit;
		this.offset = offset;
	}
	
	public ProductParam() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	
	
	
	
}
