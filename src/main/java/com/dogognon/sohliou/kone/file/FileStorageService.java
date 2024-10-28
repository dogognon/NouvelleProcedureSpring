package com.dogognon.sohliou.kone.file;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.dogognon.sohliou.kone.file.data.File;

import jakarta.servlet.http.HttpServletRequest;

public interface FileStorageService {
	
	File Televerser(MultipartFile file,Boolean dirOrDatabase) throws Exception;
	
	ResponseEntity<Resource> Telecharger(String fichierId, HttpServletRequest request) throws Exception;
	
}
