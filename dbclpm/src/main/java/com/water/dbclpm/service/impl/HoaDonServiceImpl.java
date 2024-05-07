package com.water.dbclpm.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.water.dbclpm.entity.HoaDon;
import com.water.dbclpm.repository.HoaDonRepository;
import com.water.dbclpm.service.HoaDonService;

@Service
public class HoaDonServiceImpl implements HoaDonService {
	
	@Autowired
	private HoaDonRepository hoaDonRepository;

	@Override
	public boolean luuHoaDon(HoaDon hoaDon) {
		try {
			hoaDon=hoaDonRepository.save(hoaDon);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public HoaDon getHoaDon(Integer hoadon_id) {
		try {
			return hoaDonRepository.findById(hoadon_id).get();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<HoaDon> getDsHoaDonByMaHD(Integer hodan_id) {
		return hoaDonRepository.getDsHoaDonByMaHD(hodan_id);
	}

	@Override
	public List<HoaDon> getDsHoaDonBySdtDonOrQcode(String keyword) {
		return hoaDonRepository.getDsHoaDonBySdtDonOrQcode(keyword);
	}
	
}
