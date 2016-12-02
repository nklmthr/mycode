package org.personal.myapp.mydriver.dao;

import java.math.BigInteger;

import org.personal.myapp.mydriver.dto.DriverDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface DriverDAO extends JpaRepository<DriverDTO, Long>, JpaSpecificationExecutor<DriverDTO>{

	@Query("Select d from DriverDTO d where d.id in :driverId")
	public DriverDTO getDriverDetails(@Param("driverId") BigInteger driverId);
}
