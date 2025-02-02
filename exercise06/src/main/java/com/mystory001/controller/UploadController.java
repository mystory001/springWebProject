package com.mystory001.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.mystory001.domain.AttachFileDTO;

import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnailator;

@Controller
@Log4j
public class UploadController {
	
	// 년/월/일 폴더 생성
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
		log.info("UploadController uploadAjax...............");
	}
	
	@PostMapping(value = "/uploadAjaxAction", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<List<AttachFileDTO>> uploadAjaxAction(MultipartFile[] uploadFile) {
		log.info("UploadController uploadAjaxAction...............");
		
		List<AttachFileDTO> list = new ArrayList<AttachFileDTO>();
		String uploadFolder = "C:\\upload";
		
		String uploadFolderPath = getFolder();
		
		File uploadPath = new File(uploadFolder, getFolder()); // 폴더 만들기
		log.info("uploadPath : " + uploadPath);
		
		if(uploadPath.exists() == false) {
			uploadPath.mkdirs();
		}
		
		for(MultipartFile multipartFile : uploadFile) {
			
			AttachFileDTO attachFileDTO = new AttachFileDTO();
			String uploadFileName = multipartFile.getOriginalFilename();
			
//			log.info("======================================");
//			log.info("Upload File Name : " + multipartFile.getOriginalFilename());
//			log.info("Upload File Size : " + multipartFile.getSize());
			
			
			// IE has file path
			uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\") + 1); // IE의 경우 전체 파일 경로가 전송 → 마지막 '\' 기준으로 잘라낸 문자열이 실제 파일의 이름
			log.info("uploadFileName : " + uploadFileName);
			attachFileDTO.setFileName(uploadFileName);
			
			// UUID 추가(파일명 중복 방지)
			UUID uuid = UUID.randomUUID(); // 임의의 값을 생성
			uploadFileName = uuid.toString() + "_" + uploadFileName;
			log.info("UUID_uploadFileName : " + uploadFileName);
			
			// 파일 저장 경로 설정
			try {
				File saveFile = new File(uploadPath, uploadFileName);
				multipartFile.transferTo(saveFile);
				
				attachFileDTO.setUuid(uuid.toString());
				attachFileDTO.setUploadPath(uploadFolderPath);
				
				if(checkImageType(saveFile)) {
					attachFileDTO.setImage(true);
					FileOutputStream thumbnail = new FileOutputStream(new File(uploadPath, "s_" + uploadFileName));
					Thumbnailator.createThumbnail(multipartFile.getInputStream(), thumbnail, 150, 150);
					thumbnail.close();
				}
				list.add(attachFileDTO);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	// 섬네일 데이터 전송
	@GetMapping("/display")
	@ResponseBody
	public ResponseEntity<byte[]> getFile(String fileName){ // 문자열로 파일의 경로가 포함된 fileName을 파라미터로 받고, byte[]를 전송. byte[]로 이미지 파일의 데이터를 존송할 때 신경쓰는 것은 브라우저에 보내주는 MIME 타입이 파일의 종류에 따라 달라지는 점. 이 부분을 해결하기 위해 probeContentType()을 이용해서 적절한 MIME 타입 데이터를 Http 헤더 메시지에 포함할 수 있도록 처리
		log.info("UploadController display...............");
		log.info("fileName : " + fileName);
		File file = new File("C:\\upload\\"+ fileName);
		ResponseEntity<byte[]> result = null;
		
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Type", Files.probeContentType(file.toPath()));
			result = new ResponseEntity<byte[]>(FileCopyUtils.copyToByteArray(file), headers, HttpStatus.OK);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

}
