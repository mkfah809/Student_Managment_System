package com.spsm.decon.rightway.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spsm.decon.rightway.dto.User;
import com.spsm.decon.rightway.dto.Hymn;

@Repository
public interface HymnRepository extends JpaRepository<Hymn, Long>{

}
