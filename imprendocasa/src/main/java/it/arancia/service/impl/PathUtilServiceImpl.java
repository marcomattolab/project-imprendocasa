package it.arancia.service.impl;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.arancia.config.UploadProperties;
import it.arancia.service.PathUtilService;

/**
 * Service Implementation for managing Path Util Service.
 */
@Service
@Transactional
public class PathUtilServiceImpl implements PathUtilService {
	private final Logger log = LoggerFactory.getLogger(PathUtilServiceImpl.class);
	private static final String SEPARATOR = "_";

    @Autowired 
    private UploadProperties uploadProperties;
    
	@Override
	public Path retrieveFilePath(String fileName, String entityCategory, Long entityId) {
		return Paths.get( retrievePath(entityCategory, entityId).toString(), fileName) ;
	}

	@Override
	public Path retrievePath(String entityCategory, Long entityId) {
		return Paths.get(uploadProperties.getFilepath(), buildPrefixSubpath(entityCategory, entityId)) ;
	}

	@Override
	public String buildPrefixSubpath(String entityCategory, Long entityId) {
		return entityCategory + SEPARATOR + entityId;
	}

}
