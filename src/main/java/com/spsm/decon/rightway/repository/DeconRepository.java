package com.spsm.decon.rightway.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.spsm.decon.rightway.dto.Decon;

@Repository
public interface DeconRepository extends JpaRepository<Decon, Long>{

	@Query("select d from Decon d "
			+ " left join  fetch d.authorities "
			+ "where d.username = :username") 
	Decon findByUsername(@Param(value = "username") String username);
}
