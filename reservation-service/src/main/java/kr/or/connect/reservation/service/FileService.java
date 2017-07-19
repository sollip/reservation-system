package kr.or.connect.reservation.service;

import java.util.List;

import kr.or.connect.reservation.domain.FileDto;

public interface FileService {
	public int countFileNumber(int id);
	public List<FileDto> selectFileList(int id);
	public FileDto selectFileById(int id);
}
