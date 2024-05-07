package com.water.dbclpm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.water.dbclpm.entity.NhanVien;

public interface NhanVienRepository extends JpaRepository<NhanVien, Integer> {
	
	@Query("select p from NhanVien p where p.sdt = ?1")
	NhanVien getNhanVienBySdt(String sdt);

}
