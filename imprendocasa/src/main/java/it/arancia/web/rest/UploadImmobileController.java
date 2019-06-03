package it.arancia.web.rest;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import it.arancia.service.FilesService;
import it.arancia.service.StorageService;
import it.arancia.service.dto.FilesDTO;


@RestController
@RequestMapping("/api")
public class UploadImmobileController {
	private final Logger logger = LoggerFactory.getLogger(UploadImmobileController.class);
	
	private static final String IMMOBILI = "immobili"; 

	@Autowired private StorageService storageService;
	@Autowired private FilesService filesService;

	@PostMapping("/deleteFile")
	public ResponseEntity<String> deleteFile(@RequestParam("id") String idFile) {
		String message = "";
		try {
			logger.info("Delete Immobile File has been called with for FileId : "+ idFile);
			long fileId = Long.parseLong(idFile);
			storageService.deleteAndUnstore(fileId, IMMOBILI);
			
			message = "Immobile File with Id: " + idFile + " has been successfully deleted!";
			return ResponseEntity.status(HttpStatus.OK).body(message);
			
		} catch (Exception e) {
			message = "Delete Immobile File Failed for FileId : "+ idFile+" !";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
		}
	}

	@PostMapping("/post")
	public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("idImmobile") String idImmobile) {
		String message = "";
		try {
			logger.info("UploadFile has been called for idImmobile: " + idImmobile + " ...");
			storageService.saveAndStore(file, IMMOBILI, idImmobile);
			
			message = "UploadFile : " + file.getOriginalFilename() + " for idImmobile: "+idImmobile+" has been successfully uploaded!";
			return ResponseEntity.status(HttpStatus.OK).body(message);
			
		} catch (Exception e) {
			message = "upload failed for idImmobile: " + idImmobile + " and file: " + file.getOriginalFilename() + "!";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
		}
	}

	@GetMapping("/getallfiles")
	public ResponseEntity<List<FilesDTO>> getListFiles(@RequestParam("idImmobile") String idImmobile) {
		List<FilesDTO> fileNames = new ArrayList<>();
		logger.info("Getallfiles has been called for idImmobile: "+idImmobile+" ...");

		long immobileId = Long.parseLong(idImmobile);
		List<FilesDTO> immobileFiles = filesService.findByImmobileId( immobileId );
		
		for(FilesDTO immobileFile: immobileFiles) {
			fileNames.add( immobileFile);
		}
		
		return ResponseEntity.ok().body(fileNames);
	}

	@GetMapping("/files/{idImmobile:.+}/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> getFile(@PathVariable Long idImmobile, @PathVariable String filename) {
		logger.info("Chiamata a GET FILE ....");
		Resource file = storageService.loadFile(filename, IMMOBILI, idImmobile); //TODO Passed by Frontend
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}
	
}