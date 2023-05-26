package com.srishti.geektrust.metro.station;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StationRepository extends JpaRepository<Station, String>{
	Station findByName(String name);

}
