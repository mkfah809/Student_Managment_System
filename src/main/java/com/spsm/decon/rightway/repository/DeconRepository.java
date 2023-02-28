package com.spsm.decon.rightway.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spsm.decon.rightway.dto.Decon;

@Repository
public interface DeconRepository extends JpaRepository<Decon, Long>{

}
