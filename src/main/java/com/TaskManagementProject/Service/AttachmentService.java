package com.TaskManagementProject.Service;
import com.TaskManagementProject.Entity.attachment;
import com.TaskManagementProject.Repository.AttachmentRepository;
import com.cloudinary.Cloudinary;

import jakarta.annotation.PostConstruct;
import jakarta.validation.ValidationException;
import org.apache.commons.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AttachmentService {
	@Autowired
	private AttachmentRepository attachmentRepo;
	
	@Autowired
	private Cloudinary cloudinary;
	@PostConstruct
	public void checkConfig() {
		System.out.println("====================================");
        System.out.println("Cloudinary Cloud Name: " + cloudinary.config.cloudName);
        System.out.println("====================================");
    }
	
	
	public attachment uploadFile(Long issue,MultipartFile file,String uploadBy) throws FileUploadException, ValidationException {
		
		validateFile(file);
		try {
			
			Map<String,Object> uploadOptions = new HashMap<>();
			uploadOptions.put("Resouce-Type", "auto");
			
			Map uploadResults = cloudinary.uploader().upload(file.getBytes(),uploadOptions );
			
			
			attachment attach =new attachment();
			attach.setIssueId(issue);
			attach.setFileName(file.getOriginalFilename());
			attach.setContentType(file.getContentType());
			attach.setFileSize(file.getSize());
			attach.setStoragePath(uploadResults.get("source_url").toString());
			attach.setCloudId(uploadResults.get("Clouud_Id").toString());
			attach.setUploadedBy(uploadBy);
					
			return attachmentRepo.save(attach);
			
		} catch (Exception e) {
			throw new FileUploadException("File not found",e) ;
		}
		
	}
	
	
	
	
	public List<attachment>getfileByIssueId(Long issueId){
		return attachmentRepo.findByIssueId(issueId);
	}
	
	public attachment getFileById(Long id) {
		return attachmentRepo.findById(id).orElseThrow(()-> new RuntimeException("Attachment not found"));
	}
	
	public void deleteFile(Long id) throws FileUploadException {
		attachment att = attachmentRepo.findById(id).orElseThrow(()-> new RuntimeException("Attachment not found"));
		
		try {
			
			Map<String,Object> options = new HashMap<>();
			
			options.put("Resource_Type", "auto");
			cloudinary.uploader().destroy(att.getCloudId(), options);
			attachmentRepo.delete(att);
			
			
		} catch (Exception e) {
			throw new FileUploadException("Delete failed",e);
		}
	}
	
	private void validateFile(MultipartFile file) throws ValidationException {
		
		if(file.isEmpty()) {
			throw new ValidationException("file can not be empty");
		}
		
		long Max = 5*1024*1024;
		
		if(file.getSize()>Max) {
			throw new ValidationException("Max file size is 5MB");
		}
		
		List<String> allowed=Arrays.asList("image/png","image/png","application/pdf","text/plain");
		
		if(!allowed.contains(file.getContentType())) {
			throw new ValidationException("Invalid file format");
		}
	}

	

}
