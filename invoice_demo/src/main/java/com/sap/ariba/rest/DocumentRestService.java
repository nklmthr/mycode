package com.sap.ariba.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sap.ariba.model.Document;
import com.sap.ariba.model.DocumentResult;
import com.sap.ariba.service.DocumentService;

@Controller
public class DocumentRestService {

	@Autowired
	private DocumentService service;
	
	@PostMapping("/upload")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file,
            RedirectAttributes redirectAttributes) {
		Document document = service.store(file);
		return ResponseEntity.accepted().body("/document/"+document.getIdentifier());
    }
	
	@GetMapping("/document/{identifier}")
	public ResponseEntity<Document> getDocument(@PathVariable("identifier") String identifier){
		Document document = service.getDocument(identifier);
		return ResponseEntity.ok().body(document);
	}
	
	@GetMapping("/document/{identifier}/results")
	public ResponseEntity<List<DocumentResult>> getDocumentResults(@PathVariable("identifier") String identifier){
		List<DocumentResult> documentResults = service.getDocumentResults(identifier);
		return ResponseEntity.ok().body(documentResults);
	}
	
	@GetMapping("/document")
	public ResponseEntity<List<String>> getDocuments(){
		List<String> documents = service.getDocuments();
		return ResponseEntity.ok().body(documents);
	}
}
