package com.water.dbclpm.service;

import java.util.List;

import com.water.dbclpm.entity.HoaDon;

public interface HoaDonService {
	
	boolean luuHoaDon(HoaDon hoaDon);
	
	HoaDon getHoaDon(Integer hoadon_id);
	
	List<HoaDon> getDsHoaDonByMaHD(Integer hodan_id);
	
	List<HoaDon> getDsHoaDonBySdtDonOrQcode(String keyword);

}
