package org.nklmthr.repositories;

import java.util.List;
import java.util.Optional;

import org.nklmthr.models.FileUploadModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileUploadRepository extends CrudRepository<FileUploadModel, String> {

	@Query("SELECT u FROM FileUploadModel u WHERE u.status = 'NEW' ")
	Optional<List<FileUploadModel>> findFirstStatement();

}
