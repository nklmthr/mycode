package com.nklmthr.project.myfinance.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nklmthr.project.myfinance.model.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {

}
