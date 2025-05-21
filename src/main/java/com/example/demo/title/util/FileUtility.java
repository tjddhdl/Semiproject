package com.example.demo.title.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUtility {

	String filepath = "C:\\uploadfile";

	public String fileUpload(MultipartFile file) {

		if (file.isEmpty()) {
			return null;
		}
		String name = StringUtils.cleanPath(file.getOriginalFilename());

		Path path = Paths.get(filepath + File.separator + name);

		try {
			Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return name;
	}
}
