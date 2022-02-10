package org.nklmthr.controllers;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.nklmthr.models.AccountType;
import org.nklmthr.models.FileType;
import org.nklmthr.models.FileUploadModel;
import org.nklmthr.models.Institution;
import org.nklmthr.repositories.AccountTypeRepository;
import org.nklmthr.repositories.FileTypeRepository;
import org.nklmthr.repositories.InstitutionRepository;
import org.nklmthr.repositories.FileUploadRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sap.cloud.sdk.cloudplatform.logging.CloudLoggerFactory;

@RestController
@RequestMapping("/statement")
public class StatementController {
	private static final Logger logger = CloudLoggerFactory.getLogger(StatementController.class);
	@Autowired
	private FileUploadRepository repo;

	@Autowired
	private InstitutionRepository institutionRepo;

	@Autowired
	private AccountTypeRepository accTypeRepository;

	@Autowired
	private FileTypeRepository fileTypeRepository;

	@RequestMapping(method = RequestMethod.GET, path = "/institutions")
	public ResponseEntity<List<Institution>> getStatements(
			@RequestParam(name = "institution", required = false) Integer institution) {
		List<Institution> list = (List<Institution>) institutionRepo.findAll();
		return ResponseEntity.ok(list);
	}

	@RequestMapping(method = RequestMethod.GET, path = "/accountTypes")
	public ResponseEntity<List<AccountType>> getAccountTypes(
			@RequestParam(name = "accountTypes", required = false) Integer fileTypeId) {
		List<AccountType> list = (List<AccountType>) accTypeRepository.findAll();
		return ResponseEntity.ok(list);
	}

	@RequestMapping(method = RequestMethod.GET, path = "/fileTypes")
	public ResponseEntity<List<FileType>> getFileTypes(
			@RequestParam(name = "fileTypes", required = false) Integer fileTypeId) {
		List<FileType> list = (List<FileType>) fileTypeRepository.findAll();
		return ResponseEntity.ok(list);
	}

	@PostMapping("/upload")
	public String handleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes,
			@RequestParam(name = "accountTypes", defaultValue = "") String accountType,
			@RequestParam(name = "fileTypes", defaultValue = "") String fileType,
			@RequestParam("institution") String institution) throws IOException {
		FileUploadModel model = new FileUploadModel();
		model.setContents(file.getBytes());
		model.setFileName(file.getOriginalFilename());
		model.setInstitution(institutionRepo.findById(institution).get());
		model.setFileType(fileTypeRepository.findById(fileType).get());
		model.setAccountType(accTypeRepository.findById(accountType).get());
		model.setStatus("NEW");
		model.setId(UUID.randomUUID().toString());
		repo.save(model);
		redirectAttributes.addFlashAttribute("message", "You successfully uploaded " + model.getFileName() + "!");

		return redirectAttributes.getFlashAttributes().entrySet().toString();
	}

}
