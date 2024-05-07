package com.water.dbclpm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.water.dbclpm.entity.CongTo;

public interface CongToRepository extends JpaRepository<CongTo, Integer> {
	@Query("select p from CongTo p where p.qcode = ?1")
	CongTo getCongToByqcode(String qcode);
	
	@Query("select p from CongTo p where p.hoDan.hodan_id = ?1")
	CongTo getCongToByHoDanId(Integer hodan_id);
}
