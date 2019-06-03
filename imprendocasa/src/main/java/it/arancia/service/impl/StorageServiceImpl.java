package it.arancia.service.impl;

import java.nio.file.Files;
import java.nio.file.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import it.arancia.security.SecurityUtils;
import it.arancia.service.FilesService;
import it.arancia.service.PathUtilService;
import it.arancia.service.StorageService;
import it.arancia.service.dto.FilesDTO;
import net.logstash.logback.encoder.org.apache.commons.lang.StringUtils;


/**
 * Service Implementation for managing File Storage.
 */
@Service
@Transactional
public class StorageServiceImpl implements StorageService {
	private final Logger log = LoggerFactory.getLogger(StorageServiceImpl.class);

	@Autowired 
	private FilesService filesService;
	
	@Autowired 
	private PathUtilService pathUtilService;
	
	@Override
	public void saveAndStore(MultipartFile file, String entityCategory, String entityId) {
		try{
			String user = SecurityUtils.getCurrentUserLogin().orElse(null);
			if(StringUtils.isNotEmpty(user)) {
				Long idImmobile = Long.parseLong(entityId);
	
				//Save file on Filesystem
				this.store(file, entityCategory, idImmobile);
				
				//Save file on Database
				this.saveFilesDB(idImmobile, file );
			} else {
				log.warn("Action: saveAndStore skipped due to user null/notAutenticated!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Exception in StorageService.saveAndStore due to " + e.getMessage() );
		}
	}

	@Override
	public void store(MultipartFile file, String entityCategory, Long entityId) {
		try {
			String user = SecurityUtils.getCurrentUserLogin().orElse(null);
			if(StringUtils.isNotEmpty(user)) {
				Path path = pathUtilService.retrievePath(entityCategory, entityId);
				
				log.debug("CreateDirectories path: "+path);
				Files.createDirectories(path);

				Path filePath = pathUtilService.retrieveFilePath(file.getOriginalFilename(), entityCategory, entityId);

				log.debug("Storaging of entityCategory: "+entityCategory+" entityId: "+ entityId+ " file: "+path.toString() + " ....");
				Files.copy(file.getInputStream(), filePath);
				log.debug("File: "+path.toString() + " entityCategory:"+entityCategory+" entityId: "+ entityId+ " file "+path.toString() + " has been succesfully stored.");
			} else {
				log.warn("Action: store skipped due to user null/notAutenticated!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Exception in StorageService.store due to " + e.getMessage() );
		}
	}

	@Override
	public Resource loadFile(String filename, String entityCategory, Long entityId) {
		try {
			Resource resource = null;
			String user = SecurityUtils.getCurrentUserLogin().orElse(null);
			if(StringUtils.isNotEmpty(user)) {
				Path path = pathUtilService.retrieveFilePath(filename, entityCategory, entityId);
				resource = new UrlResource(path.toUri());
				log.debug("risorsa:" + resource.toString());
				if (!resource.exists() || !resource.isReadable()) {
					throw new RuntimeException("Exception in loadFile with filename due to URI not found.");
				}
			} else {
				log.warn("Action: loadFile skipped due to user null/notAutenticated!");
			}
			return resource;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Exception in loadFile with filename: " + filename + " due to " + e.getMessage());
		}
	}

	@Override
	public void deleteAll(String entityCategory, Long entityId) {
		String user = SecurityUtils.getCurrentUserLogin().orElse(null);
		if(StringUtils.isNotEmpty(user)) {
			Path path = pathUtilService.retrievePath(entityCategory, entityId); 
			FileSystemUtils.deleteRecursively(path.toFile());
		} else {
			log.warn("Action: deleteAll skipped due to user null/notAutenticated!");
		}
	}

	/**
	 * Save immobile file on database 
	 * 
	 * @param immobileId Immodile Id
	 * @param file  File 
	 */
	private void saveFilesDB(Long immobileId, MultipartFile file) {
		FilesDTO files = new FilesDTO();
		files.setImmobileId(immobileId);
		files.setEstensione(file.getContentType());
		files.setNome(file.getOriginalFilename());
		files.setDimensione(Long.toString(file.getSize()));
		filesService.save(files);
	}

	@Override
	public void deleteAndUnstore(Long idFile, String entityCategory) {
		try{
			String user = SecurityUtils.getCurrentUserLogin().orElse(null);
			if(StringUtils.isNotEmpty(user)) {
				
				FilesDTO fileToDelete = filesService.findOne(idFile).orElse(null);
				if(fileToDelete!=null) {

					//Delete file on Filesystem
					Path path = pathUtilService.retrieveFilePath(fileToDelete.getNome(), entityCategory, fileToDelete.getImmobileId()); 
					FileSystemUtils.deleteRecursively( path.toFile() );
					
					//Remove file on Database
					filesService.delete(idFile);
				}
				
			} else {
				log.warn("Action: deleteAndUnstore skipped due to user null/notAutenticated!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Exception in StorageService.deleteAndUnstore due to " + e.getMessage() );
		}
	}

}
