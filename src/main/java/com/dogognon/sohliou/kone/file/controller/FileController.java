package com.dogognon.sohliou.kone.file.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.dogognon.sohliou.kone.file.FileStorageService;
import com.dogognon.sohliou.kone.file.data.File;

import jakarta.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/api/file")
public class FileController {



    @Autowired
    private FileStorageService fileStorageService;

    @PostMapping("/upload")
    //@PreAuthorize("hasAuthority('SCOPE_Utilisateur')")
    public File uploadFile(@RequestParam("file") MultipartFile file) throws Exception {
    	
    	//True : save to database false : save on filedir
        File fileName = fileStorageService.Televerser(file,true);
        
        fileName.setFileDownloadUri(ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/file/download")
                .path(fileName.getId())
                .toUriString());
        fileName.setSize(file.getSize());

        return fileName;
    }

//    @PostMapping("/televerserplusieurs")
//    public List<UploadFileResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
//        return Arrays.asList(files)
//                .stream()
//                .map(file -> uploadFile(file))
//                .collect(Collectors.toList());
//    }

	/*
	 * @GetMapping("/telecharger/{fileName:.+}") public ResponseEntity<Resource>
	 * downloadFile(@PathVariable String fileId, HttpServletRequest request) throws
	 * Exception { return fileStorageService.Telecharger(fileId, request); }
	 */
     		
	@GetMapping("/download/{fileId}")
	public ResponseEntity<Resource> downloadFichier(@PathVariable(value = "fileId") String fichierId, HttpServletRequest request) throws Exception {
		return fileStorageService.Telecharger(fichierId, request);

	}

}
