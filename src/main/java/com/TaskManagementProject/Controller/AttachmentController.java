package com.TaskManagementProject.Controller;
import java.io.InputStream;
import java.net.URL;

import jakarta.validation.ValidationException;

import org.apache.http.HttpHeaders;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.TaskManagementProject.Entity.attachment;
import com.TaskManagementProject.Service.AttachmentService;

@RestController
@RequestMapping("/api/attachments")
public class AttachmentController {
	@Autowired
	private AttachmentService attachmentService;
	
	@PostMapping("/uploadFile/{issueId}")
	public ResponseEntity<attachment>uploadFile(@PathVariable Long issueId
			                                  ,@RequestParam MultipartFile file
			                                  ,@RequestParam String uploadBy) throws FileUploadException, ValidationException, org.apache.commons.fileupload.FileUploadException{
		return ResponseEntity.ok(attachmentService.uploadFile(issueId, file, uploadBy));
	}
	
	@GetMapping("/downlodFile/{id}")
	public ResponseEntity<Void>download(@PathVariable Long id){
		attachment attachment = attachmentService.getFileById(id);
		
		return ResponseEntity.status(HttpStatus.FOUND)
				             .header(HttpHeaders.LOCATION,attachment.getStoragePath())
				             .build();
	}
	
	@GetMapping("/downloadFile/stream/{id}")
	public ResponseEntity<Resource>downloadFileForSystem(@PathVariable Long id) throws Exception{
		
		attachment attach = attachmentService.getFileById(id);
		URL url =new URL(attach.getStoragePath());
		InputStream is= url.openStream();
		
		InputStreamResource resource= new InputStreamResource(is);
		
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_LOCATION, "attachment;FileName=\""+attach.getFileName()+"\"")
				                  .contentType(MediaType.parseMediaType(attach.getContentType()))
				                  .body(resource);
		
	}
	
	@DeleteMapping("/deleteFile/{id}")
	public ResponseEntity<String>deletefile(@PathVariable Long id) throws FileUploadException, org.apache.commons.fileupload.FileUploadException{
		attachmentService.deleteFile(id);
		return ResponseEntity.ok("Attachment deleted successfully");
	}

}
