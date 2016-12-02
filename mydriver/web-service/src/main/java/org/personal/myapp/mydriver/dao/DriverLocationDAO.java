package org.personal.myapp.mydriver.dao;

import org.personal.myapp.mydriver.dto.DriverLocationDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DriverLocationDAO extends JpaRepository<DriverLocationDTO, Long>, JpaSpecificationExecutor<DriverLocationDTO>{
	
	@Query("Select l from DriverLocationDTO l where l.driverId in :driverId")
	public DriverLocationDTO getDriverDetails(@Param("driverId") String driverId);
}
