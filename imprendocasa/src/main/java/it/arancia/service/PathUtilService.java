package it.arancia.service;

import java.nio.file.Path;

/**
 * Service Interface for managing PathUtilService.
 */
public interface PathUtilService {

	/**
	 * Retrieve absolute File Path (filename is included)
	 *  
	 * @param fileName file name
	 * @param entityCategory category of entity
	 * @param entityId entity id
	 * @return Path
	 */
	Path retrieveFilePath(String fileName, String entityCategory, Long entityId);

	/**
	 * Retrieve absolute File Path (filename is excluded)
	 *  
	 * @param entityCategory
	 * @param entityId
	 * @return Path
	 */
	Path retrievePath(String entityCategory, Long entityId);

	/**
	 * Return Prefix Subpath for storage of entity
	 * 
	 * @param entityCategory Entity Category
	 * @param entityId Entity Id
	 * @return String Prefix Subpath 
	 */
	String buildPrefixSubpath(String entityCategory, Long entityId);
	
}
