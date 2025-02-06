package com.mystory001.task;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.mystory001.domain.BoardAttachVO;
import com.mystory001.mapper.BoardAttachMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Component
public class FileCheckTask {

	@Setter(onMethod_ = {@Autowired})
	private BoardAttachMapper attachMapper;
	
	private String getFolderYesterDay() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Calendar cal = Calendar.getInstance();
		
		cal.add(Calendar.DATE, -1);
		
		String str = sdf.format(cal.getTime());
		
		return str.replace("-", File.separator);
	}
	
	
	@Scheduled(cron = "0 0 2 * * *")
	public void checkFiles() throws Exception{
		log.info("FileCheckTask checkFiles()...............");
		
		log.warn("File Check Task RUN...............");
		log.warn(new Date());
		List<BoardAttachVO> fileList = attachMapper.getOldFiles();
		
		List<Path> fileListPaths = fileList.stream().map(boardAttachVO -> Paths.get("C:\\upload", boardAttachVO.getUploadPath(), boardAttachVO.getUuid() + "_" + boardAttachVO.getFileName())).collect(Collectors.toList());
		
		fileList.stream().filter(boardAttachVO -> boardAttachVO.isFileType() == true).map(boardAttachVO -> Paths.get("C:\\upload", boardAttachVO.getUploadPath(), "s_" + boardAttachVO.getUuid() + "_" + boardAttachVO.getFileName())).forEach(p -> fileListPaths.add(p));
		
		log.warn("==================================");
		fileListPaths.forEach(p -> log.warn(p));
		
		File targetDir = Paths.get("C:\\upload", getFolderYesterDay()).toFile();
		
		File[] deleteFiles = targetDir.listFiles(file -> fileListPaths.contains(file.toPath()) == false);
		log.warn("--------------------------------------");
		for(File file : deleteFiles) {
			log.warn(file.getAbsolutePath());
			file.delete();
		}
		
		
	}
	
	
}
