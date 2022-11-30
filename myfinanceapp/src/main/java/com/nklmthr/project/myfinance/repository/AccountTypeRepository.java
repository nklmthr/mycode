package com.nklmthr.project.myfinance.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nklmthr.project.myfinance.model.AccountType;

@Repository
public interface AccountTypeRepository extends CrudRepository<AccountType, String> {

}
