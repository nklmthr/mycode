package com.nklmthr.finance.personal.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionAttachmentRepository extends JpaRepository<TransactionAttachment, String> {

	@Query("select ta from TransactionAttachment ta where transaction.id = ?1")
	List<TransactionAttachment> findByTransactionId(String id);
		

}
