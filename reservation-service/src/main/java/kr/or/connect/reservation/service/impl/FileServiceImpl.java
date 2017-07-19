package kr.or.connect.reservation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.reservation.dao.FileDao;
import kr.or.connect.reservation.domain.FileDto;
import kr.or.connect.reservation.service.FileService;

@Service
public class FileServiceImpl implements FileService{

	@Autowired
	FileDao fileDao;
	
	@Override
	@Transactional(readOnly=true)
	public int countFileNumber(int id) {
		return fileDao.countFileNumber(id);
	}

	@Override
	@Transactional(readOnly=true)
	public List<FileDto> selectFileList(int id) {
		return null;
	}

	@Override
	public FileDto selectFileById(int id) {
		return fileDao.selectFileById(id);
	}
	
	
	
}
