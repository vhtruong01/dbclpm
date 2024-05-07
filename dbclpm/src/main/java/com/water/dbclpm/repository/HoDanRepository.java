package com.water.dbclpm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.water.dbclpm.entity.HoDan;

public interface HoDanRepository extends JpaRepository<HoDan, Integer> {
	
	@Query("select p from HoDan p where p.sdt = ?1")
	HoDan getAccountBySdt(String sdt);
}
