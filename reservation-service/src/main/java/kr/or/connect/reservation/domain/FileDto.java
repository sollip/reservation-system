package kr.or.connect.reservation.domain;

public class FileDto {
	private String fileName;
	private String saveFileName;
	private String id;
	private String contentType;
	private int fileLength;
	
	
	public int getFileLength() {
		return fileLength;
	}
	public void setFileLength(int fileLength) {
		this.fileLength = fileLength;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getSaveFileName() {
		return saveFileName;
	}
	public void setSaveFileName(String saveFileName) {
		this.saveFileName = saveFileName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public FileDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FileDto(String fileName, String saveFileName, String id) {
		super();
		this.fileName = fileName;
		this.saveFileName = saveFileName;
		this.id = id;
	}
	
	
}
