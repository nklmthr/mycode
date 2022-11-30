package com.nklmthr.project.myfinance.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nklmthr.project.myfinance.model.Institution;

@Repository
public interface InstitutionRepository extends CrudRepository<Institution, String> {

}
