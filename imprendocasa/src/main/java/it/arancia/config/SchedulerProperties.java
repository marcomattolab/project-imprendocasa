package it.arancia.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "application.schedulerworkflow", ignoreUnknownFields = false)
public class SchedulerProperties {
	private Boolean enabled;
	private Integer hours;

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Integer getHours() {
		return hours;
	}

	public void setHours(Integer minutes) {
		this.hours = minutes;
	}
	
}
