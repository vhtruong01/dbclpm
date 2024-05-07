package com.water.dbclpm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.water.dbclpm.entity.HoDan;
import com.water.dbclpm.entity.NhanVien;
import com.water.dbclpm.repository.HoDanRepository;
import com.water.dbclpm.repository.NhanVienRepository;
import com.water.dbclpm.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private HoDanRepository hoDanRepository;

	@Autowired
	private NhanVienRepository nhanVienRepository;

	@Override
	public boolean save(HoDan hoDan) {
		try {
			hoDanRepository.save(hoDan);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	@Override
	public HoDan getHoDanTheoSdt(String sdt) {
		return hoDanRepository.getAccountBySdt(sdt);
	}

	@Override
	public NhanVien getNhanVienBySdt(String sdt) {
		return nhanVienRepository.getNhanVienBySdt(sdt);
	}

}
