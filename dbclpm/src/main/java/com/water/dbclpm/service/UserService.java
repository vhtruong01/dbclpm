package com.water.dbclpm.service;

import com.water.dbclpm.entity.HoDan;
import com.water.dbclpm.entity.NhanVien;

public interface UserService {
	
	boolean save(HoDan hoDan);
	
	HoDan getHoDanTheoSdt(String sdt);
	
	NhanVien getNhanVienBySdt(String sdt);
}
