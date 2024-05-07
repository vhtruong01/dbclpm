package com.water.dbclpm.service;

import com.water.dbclpm.entity.CongTo;
import com.water.dbclpm.entity.HoDan;
import com.water.dbclpm.entity.HoaDon;
import com.water.dbclpm.entity.NVGhiNuoc;
import com.water.dbclpm.entity.NVHanhChinh;

public interface NhanVienService {
	
	NVHanhChinh getNvHanhChinh(int id);
	
	NVGhiNuoc getNvGhiNuoc(int id);
	
	int getPhiPhat(HoaDon hoaDon);
	
	int getngayCham(HoaDon hoaDon);
	
	String genCongToQCode(String quanHuyen, String phuongXa, int hodan_id);
	
	Integer getTongTien(HoDan hoDan, int soNuoc);
	
	boolean isUpdated(CongTo congTo);
}
