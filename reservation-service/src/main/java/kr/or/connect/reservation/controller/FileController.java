package kr.or.connect.reservation.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import kr.or.connect.reservation.domain.FileDto;
import kr.or.connect.reservation.service.FileService;

@Controller
@RequestMapping("/files")
public class FileController {
	private String baseDir="c:"+File.separator+"Users"+File.separator+"sollip"+File.separator+"img"+File.separator;

	@Autowired
	FileService fileService;
	
	@GetMapping
	public String fileform(){
		return ""
				+ "files";
	}

	@PostMapping
	public String create(@RequestParam("product_id") int id, @RequestParam("file") MultipartFile[] files){
		if(files!=null&&files.length>0){
			String formattedDate=baseDir+new SimpleDateFormat("yyyy"+File.separator+"MM"+File.separator+"dd").format(new Date());
			File f=new File(formattedDate);
			if(!f.exists()){//해당 폴더?가 존재하지 않으면 폴더를 만든다
				f.mkdirs(); 
			}
			for(MultipartFile file:files){
				String contentType=file.getContentType();
				String name=file.getName();
				String originalFilename=file.getOriginalFilename();
				long size=file.getSize();

				String uuid=UUID.randomUUID().toString();
				String saveFileName=formattedDate+File.separator+uuid;

				//디비에 저장

				//실제 저장소에 파일 저장
				try{
					InputStream in=file.getInputStream();
					FileOutputStream fos=new FileOutputStream(saveFileName);
					int readCount=0;
					byte[] buffer=new byte[512];
					while((readCount=in.read(buffer))!=-1){
						fos.write(buffer,0,readCount);
					}

				}catch(Exception ex){
					ex.printStackTrace();
				}
			}
		}
		return "redirect:/files";
	}
	
	@GetMapping("/{id}")
	public void downloadImage(@PathVariable int id, HttpServletResponse response){
		
		FileDto file=fileService.selectFileById(id);
		
		String originalFilename=file.getFileName();
		String contentType="image/jpeg";
		int fileSize=file.getFileLength();
		String saveFileName=file.getSaveFileName();

		response.setHeader("Content-Disposition", "attachment; filename=\""+originalFilename+"\";");
		response.setHeader("Content-Transfer-Encoding", "binary");
		response.setHeader("Content-Type", contentType);
		//response.setHeader("Content-Length", ""+ fileSize);
		response.setHeader("Pragma", "no-cache;");
		response.setHeader("Expires", "-1;");

		File readFile=new File(saveFileName);
		if(!readFile.exists()){
			throw new RuntimeException("file not found");
		}

		FileInputStream fis = null;
		try{
			fis = new FileInputStream(readFile);
			FileCopyUtils.copy(fis, response.getOutputStream()); // 파일을 저장할때도 사용할 수 있다.
			response.getOutputStream().flush();
		}catch(Exception ex){
			throw new RuntimeException(ex);
		}finally {
			try {
				fis.close();
			}catch(Exception ex){
				// 아무것도 하지 않음 (필요한 로그를 남기는 정도의 작업만 함.)
			}
		}
	}
}
