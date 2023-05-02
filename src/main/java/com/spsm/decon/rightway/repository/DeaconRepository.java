package com.spsm.decon.rightway.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.spsm.decon.rightway.dto.Deacon;

@Repository
public interface DeaconRepository extends JpaRepository<Deacon, Long>{

	@Query("select d from Deacon d "
			+ " left join  fetch d.authorities "
			+ "where d.username = :username") 
	Deacon findByUsername(@Param(value = "username") String username);
	
	Deacon findByEmail(String email);
}
