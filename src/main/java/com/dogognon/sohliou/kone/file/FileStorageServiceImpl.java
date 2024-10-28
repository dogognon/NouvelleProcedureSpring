package com.dogognon.sohliou.kone.file;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.dogognon.sohliou.kone.file.data.File;
import com.dogognon.sohliou.kone.file.data.FileRepository;
import com.dogognon.sohliou.kone.security.exception.InternalServerErrorException;
import com.dogognon.sohliou.kone.security.exception.MyFileNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class FileStorageServiceImpl implements FileStorageService {

	 @Value("${app.file-dir}")
	 private String fileDir;
	
	 
	@Autowired
	FileRepository fileRepository;

	@Override
	public ResponseEntity<Resource> Telecharger(String fichierId, HttpServletRequest request) throws Exception {
		//on recupere la ligne du fichier dans la database.
		File file = fileRepository.findById(fichierId).orElseThrow(() -> new Exception("File not found with Id: " + fichierId));
		System.out.println(file.toString());
		// on regarde le taille de data
		if(file.getData()!=null) {
			// si elle n'est pas null c'est que le fichier est ici et non dans le dossier
			return  ResponseEntity.ok()
	                .contentType(MediaType.parseMediaType(file.getFileType()))
	                .header(HttpHeaders.CONTENT_DISPOSITION,
	                        "attachment; filename=\"" + file.getFileName()
	                + "\"")
	                .body(new ByteArrayResource(file.getData()));
		}else {
			// le fichier est dans le dossier 
			Resource resource = null;
			// on gere le path avant la recuperation
			Path fileStorageLocation = Paths.get(fileDir,String.valueOf(ZonedDateTime.ofInstant(file.getCreatedAt(),ZoneId.systemDefault()).getYear()),String.valueOf(ZonedDateTime.ofInstant(file.getCreatedAt(),ZoneId.systemDefault()).getMonth().getValue())).toAbsolutePath().normalize();
			// TODO la recuperation par annee et mois a gerer pour les dossiers
	    	Path  filePath = fileStorageLocation.resolve(file.getFileName()).normalize();
	    	try {
	            resource = new UrlResource(filePath.toUri());
	            if(resource.exists()) {
	               // return resource;
	            } else {
	                throw new MyFileNotFoundException("FileDirectory not found " + filePath);
	            }
	        } catch (MalformedURLException ex) {
	            throw new MyFileNotFoundException("FileDirectory not found " + filePath, ex);
	        }
	    	  
			// on envoi la photo maintenant
		 // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
        	new InternalServerErrorException("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
		}
		
		
		
		
		
		
	
	}

	
	
	
	
	
	
	@SuppressWarnings("null")
	@Override
	public File Televerser(MultipartFile file, Boolean dirOrDatabase) throws Exception {
		if (file.isEmpty()) {
		//on verifie ou on enregistera
		if(dirOrDatabase) {
			//save to dir
		final Path fileStorageLocation = Paths.get(fileDir,String.valueOf(Calendar.getInstance().get(Calendar.YEAR)),String.valueOf(Calendar.getInstance().get(Calendar.MONTH)+1))
                .toAbsolutePath().normalize();
    	// Check si le dossier exists ou on le creer
        try {
        	Boolean testDir = Files.isDirectory(fileStorageLocation);
        	if(testDir) {
        		
        	}else {
            Files.createDirectories(fileStorageLocation);
            }
        } catch (Exception ex) {
            throw new InternalServerErrorException("Could not create the directory where the uploaded files will be stored.", ex);
        }
        File newfile = new File();
        // Normalize file name
        //String fileName = StringUtils.cleanPath();
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        newfile.setFileName(fileName);
        newfile.setFileType(file.getContentType());
        
        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new InternalServerErrorException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = fileStorageLocation.resolve(fileName);
            newfile.setFilePath(targetLocation.toString());
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            // save to directory
            //File newfile = new File(fileName,file.getContentType(),null,targetLocation.toString());
            return fileRepository.save(newfile);
        } catch (IOException ex) {
            throw new InternalServerErrorException("Could not store file " + fileName + ". Please try again!", ex);
        }
	}else {
		//Save to database
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
	       try {
	            if(fileName.contains("..")) {
	                throw  new Exception("Filename contains invalid path sequence "
	                + fileName);
	            }

	            File newfile
	                    = new File(fileName,
	                    file.getContentType(),
	                    file.getBytes(),fileName,file.getSize());
	            return fileRepository.save(newfile);

	       } catch (Exception e) {
	            throw new Exception("Could not save File: " + fileName);
	       }
	}
		
	} else{
		return null;
}
	}
	
}
