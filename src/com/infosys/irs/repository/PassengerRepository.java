package com.infosys.irs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infosys.irs.entity.PassengerEntity;

public interface PassengerRepository extends JpaRepository<PassengerEntity, Integer>{

}
