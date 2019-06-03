package it.arancia.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "application.upload", ignoreUnknownFields = false)
public class UploadProperties {
	private String filepath;
	private Long maxsize;
	
	public String getFilepath() {
		return filepath;
	}
	
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	
	public Long getMaxsize() {
		return maxsize;
	}
	
	public void setMaxsize(Long maxsize) {
		this.maxsize = maxsize;
	}
}
