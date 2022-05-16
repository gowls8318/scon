package com.scon.project.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{

	private final String uploadFilesPath;
	
	public WebConfig(@Value("${custom.path.upload-files}") String uploadFilesPath) {
		this.uploadFilesPath = uploadFilesPath;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/uploadFiles/**")
			.addResourceLocations("file:///" + uploadFilesPath);
	}
	
	
	
}
