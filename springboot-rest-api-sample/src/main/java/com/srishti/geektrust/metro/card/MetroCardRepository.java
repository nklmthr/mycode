package com.srishti.geektrust.metro.card;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MetroCardRepository extends JpaRepository<MetroCard, String> {

}
