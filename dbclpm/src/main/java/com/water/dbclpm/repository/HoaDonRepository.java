package com.water.dbclpm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.water.dbclpm.entity.HoaDon;

public interface HoaDonRepository extends JpaRepository<HoaDon, Integer> {
	@Query("select p from HoaDon p where p.congTo.hoDan.hodan_id = ?1 order by p.thoiGian desc")
	List<HoaDon> getDsHoaDonByMaHD(Integer hodan_id);
	
	@Query("select p from HoaDon p where (p.congTo.hoDan.sdt = ?1 or p.congTo.qcode = ?1) and p.trangThai = 'Chưa nộp' order by p.thoiGian desc")
	List<HoaDon> getDsHoaDonBySdtDonOrQcode(String keyword);
}
