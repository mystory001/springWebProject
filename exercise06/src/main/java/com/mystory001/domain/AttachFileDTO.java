package com.mystory001.domain;

import lombok.Data;

@Data
public class AttachFileDTO {

	private String fileName, uploadPath, uuid;
	private boolean image;
	
}
