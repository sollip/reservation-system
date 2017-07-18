package kr.or.connect.reservation.domain;

public class Product {
	private int id;
	private int ctegoryId;
	private String name;
	private String description;
	private String event;
	private String content;
	private String placeName;
	private String saveFileName;
	private String fileName;
	

	public Product(int id, int ctegoryId, String name, String description, String event, String content,
			String placeName, String saveFileName, String fileName) {
		super();
		this.id = id;
		this.ctegoryId = ctegoryId;
		this.name = name;
		this.description = description;
		this.event = event;
		this.content = content;
		this.placeName = placeName;
		this.saveFileName = saveFileName;
		this.fileName = fileName;
	}
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
	public String getSaveFileName() {
		return saveFileName;
	}
	public void setSaveFileName(String saveFileName) {
		this.saveFileName = saveFileName;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	
	
	
}
