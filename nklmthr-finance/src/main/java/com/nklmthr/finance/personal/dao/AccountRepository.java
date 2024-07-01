package com.nklmthr.finance.personal.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {

	@Query("select a from Account a where a.name = ?1")
	Account findByName(String name);
	

}
