package com.infosys.irs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infosys.irs.entity.TicketEntity;

@Repository
public interface TicketRepository extends JpaRepository<TicketEntity, String>{
	
	
}

