package com.springboot.dhtjdbcdemo.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtil {

	public final static String uploadDir = "src/main/resources/static/images/";
//	public final static String uploadDir = "../book-images/";
	
	
	public static void saveFile(String filename, MultipartFile multipartFile) throws IOException {
		Path uploadPath = Paths.get(uploadDir);
		
		if (!Files.exists(uploadPath)) {
			Files.createDirectories(uploadPath);
		}
		
		try (InputStream inputStream = multipartFile.getInputStream()) {
			Path filePath = uploadPath.resolve(filename);
			Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException ioe) {
			throw new IOException("Could not save image file: " + filename, ioe);
		}
		
	}
	
	public static void deleteFile(String filename) {
		
		File bookImg = new File(uploadDir + filename);
		System.out.println(bookImg);
	    if (bookImg.delete()) { 
	        System.out.println("Deleted the file: " + bookImg.getName());
	    } else {
	      System.out.println("Failed to delete the file.");
	    } 
	}
}
