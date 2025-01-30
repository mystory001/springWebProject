package com.mystory001.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.mystory001.domain.AttachFileDTO;

import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnailator;

@Controller
@Log4j
public class UploadController {
	
	private String getFolder() { 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // 오늘 날짜의 경로를 문자열로 생성
		Date date = new Date();
		String str = sdf.format(date);

		return str.replace("-", File.separator);
	}
	
	// 이미지 파일의 판단
	private boolean checkImageType(File file) {
		try {
			String contentType = Files.probeContentType(file.toPath());
			return contentType.startsWith("image");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@GetMapping("/uploadForm")
	public void uploadForm() {
		log.info("UploadController uploadForm()...............");
	}
	
	@PostMapping("uploadFormAction")
	public void uploadFormAction(MultipartFile[] uploadFile, Model model) {
		log.info("UploadController uploadFormAction()...............");
		
		String uploadFolder = "c:\\upload";
		
		for(MultipartFile multipartFile : uploadFile) {
			log.info("---------------");
			log.info("file.getOriginalFilename() : " + multipartFile.getOriginalFilename());
			log.info("file.getSize() : "+ multipartFile.getSize());
			
			// 파일 저장
			File saveFile = new File(uploadFolder, multipartFile.getOriginalFilename());
			
			try {
				multipartFile.transferTo(saveFile);
			} catch(Exception e) {
				log.info(e.getMessage());
			}
		}
	}
	
	@GetMapping("/uploadAjax")
	public void uploadAjax() {
		log.info("UploadController uploadAjax()...............");
	}
	
	@PostMapping(value = "/uploadAjaxAction", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<List<AttachFileDTO>> uploadAjaxAction(MultipartFile[] uploadFile) {
		log.info("UploadController uploadAjaxAction()...............");
		
		
		List<AttachFileDTO> list = new ArrayList<AttachFileDTO>();
		String uploadFolder = "c:\\upload";
		String uploadFolderPath = getFolder();

		// 폴더 만들기
		File uploadPath = new File(uploadFolder, uploadFolderPath);
		
		// 날짜 폴더 생성
		if(uploadPath.exists() == false) {
			uploadPath.mkdirs();
		}
		
		for(MultipartFile multipartFile : uploadFile) {
			
			AttachFileDTO attachFileDTO = new AttachFileDTO();
			
			// 파일 이름 추출
			String uploadFileName = multipartFile.getOriginalFilename();
			uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\")+1);
			attachFileDTO.setFileName(uploadFileName);
			
			// UUID 추가(파일명 중복 방지)
			UUID uuid = UUID.randomUUID();
			uploadFileName = uuid.toString() + "_" + uploadFileName;

			// 파일 저장 경로 설정
			try {
				File saveFile = new File(uploadPath, uploadFileName);
				multipartFile.transferTo(saveFile);
				
				attachFileDTO.setUuid(uuid.toString());
				attachFileDTO.setUploadPath(uploadFolderPath);
				
				if(checkImageType(saveFile)) {
					
					attachFileDTO.setImage(true);
					
					FileOutputStream thumbnail = new FileOutputStream(new File(uploadPath, "s_" + uploadFileName));
					Thumbnailator.createThumbnail(multipartFile.getInputStream(), thumbnail, 100, 100);
					thumbnail.close();
				}
				
				list.add(attachFileDTO);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return new ResponseEntity<List<AttachFileDTO>>(list, HttpStatus.OK);
	}

}
