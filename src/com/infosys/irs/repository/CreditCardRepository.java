package com.infosys.irs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.infosys.irs.entity.CreditCardEntity;

public interface CreditCardRepository extends JpaRepository<CreditCardEntity, String>{
	
	 @Modifying(clearAutomatically = true)
	 @Transactional
	 @Query(value="update CreditCardEntity set totalBill=totalBill+:fare where cardNumber=:cardNumber")
	void updateSeatCount(@Param("cardNumber") String cardNumber, @Param("fare") String fare);
	 
	 
	 

}
