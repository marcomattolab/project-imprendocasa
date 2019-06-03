package it.arancia.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

/**
 * Service Interface for managing File Storage.
 */
public interface StorageService {
	
	/**
	 * Delete file on filesystem and on database
	 * 
	 * @param idFile
	 * @param entityCategory
	 */
	public void deleteAndUnstore(Long idFile, String entityCategory);

	/**
	 * Save file on filesystem and on database
	 * 
	 * @param file
	 * @param entityCategory
	 * @param entityId
	 */
	public void saveAndStore(MultipartFile file, String entityCategory, String entityId);

	/**
	 * Save file on filesystem
	 * 
	 * @param file
	 * @param entityCategory
	 * @param entityId
	 */
	public void store(MultipartFile file,  String entityCategory, Long entityId);

	/**
	 * Load file content on filesystem
	 * 
	 * @param filename
	 * @param entityCategory
	 * @param entityId
	 * @return Resource
	 */
	public Resource loadFile(String filename, String entityCategory, Long entityId);

	/**
	 * Delete all files about that entity category and id
	 * 
	 * @param entityCategory
	 * @param entityId
	 */
	public void deleteAll(String entityCategory, Long entityId);
	
}
