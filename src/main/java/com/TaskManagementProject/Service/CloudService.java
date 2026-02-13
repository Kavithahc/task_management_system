package com.TaskManagementProject.Service;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class CloudService {
	private static final Logger logger = LoggerFactory.getLogger(CloudService.class);

    @Value("${CLOUDINARY_URL}")
    String cloudUrl;

    public CloudService() {
    }

    @LoggingAnnotation("Created cloud")
    public Map upload(MultipartFile file) throws IOException { // Changed to public and return Map as expected by AttachmentService
        try {
            Cloudinary cloudinary = new Cloudinary(cloudUrl);
            Map uploadedFile = cloudinary.uploader().upload(
                    file.getBytes(),
                    ObjectUtils.asMap("resource_type", "auto")
            );
            return uploadedFile;
        } catch (IOException ex) {
            logger.error("Cloudinary upload failed", ex);
            throw ex;
        }
    }
}
