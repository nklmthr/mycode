package org.nklmthr.repositories;

import org.nklmthr.models.Institution;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstitutionRepository extends CrudRepository<Institution, String>{

}
