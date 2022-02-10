package org.nklmthr.repositories;

import org.nklmthr.models.AccountType;
import org.nklmthr.models.FileType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileTypeRepository extends CrudRepository<FileType, String> {

}
