package com.sap.ariba.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sap.ariba.model.Document;

@Repository
public interface DocumentRepository extends CrudRepository<Document, Long> {
	
	@Query(value="SELECT * FROM document where status='NEW' LIMIT 1", nativeQuery = true)
	Document getUnprocessedRecord();

	@Query(value="SELECT * FROM document where identifier = ?1", nativeQuery = true)
	Document findByIdentifier(String identifier);

	@Query(value="SELECT identifier FROM document", nativeQuery = true)
	List<String> listAllDocuments();
}
