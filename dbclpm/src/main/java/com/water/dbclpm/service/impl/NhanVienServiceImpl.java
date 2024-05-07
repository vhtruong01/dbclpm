package com.water.dbclpm.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.water.dbclpm.entity.CongTo;
import com.water.dbclpm.entity.HoDan;
import com.water.dbclpm.entity.HoaDon;
import com.water.dbclpm.entity.NVGhiNuoc;
import com.water.dbclpm.entity.NVHanhChinh;
import com.water.dbclpm.repository.NVGhiNuocRepository;
import com.water.dbclpm.repository.NVHanhChinhRepository;
import com.water.dbclpm.service.NhanVienService;

@Service
public class NhanVienServiceImpl implements NhanVienService {

	@Autowired
	private NVHanhChinhRepository nvHanhChinhRepository;

	@Autowired
	private NVGhiNuocRepository nvGhiNuocRepository;

	@Override
	public NVHanhChinh getNvHanhChinh(int id) {
		return nvHanhChinhRepository.findById(id).get();
	}

	@Override
	public NVGhiNuoc getNvGhiNuoc(int id) {
		try {
			return nvGhiNuocRepository.findById(id).get();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public int getPhiPhat(HoaDon hoaDon) {
		return (int) (hoaDon.getTongTien() * getngayCham(hoaDon) / 1800);
	}

	@Override
	public int getngayCham(HoaDon hoaDon) {
		if (calendar == null)
			calendar = Calendar.getInstance();
		Date curDate = calendar.getTime();
		Calendar c = Calendar.getInstance();
		c.setTime(hoaDon.getThoiGian());
		c.set(Calendar.DAY_OF_MONTH, 22);
		// if(true) return  c.get(Calendar.YEAR)*10000+c.get(Calendar.MONTH)*100+c.get(Calendar.DAY_OF_MONTH);
		Date date = c.getTime();
		calendar = null;
		long delta = curDate.getTime() - date.getTime();
		if (delta > 0) {
			long deltaDate = TimeUnit.MILLISECONDS.toDays(delta);
			return (int) deltaDate;
		}
		return 0;
	}

	@Override
	public String genCongToQCode(String quanHuyen, String phuongXa, int hodan_id) {
		String data1[] = quanHuyen.split("\\s+");
		String data2[] = phuongXa.split("\\s+");
		String output = "";
		for (String i : data1) {
			if (i.toUpperCase().charAt(0) == 'Đ') {
				output += 'D';
			} else {
				output += i.toUpperCase().charAt(0);
			}
		}
		for (String i : data2) {
			if (i.toUpperCase().charAt(0) == 'Đ') {
				output += 'D';
			} else {
				output += i.toUpperCase().charAt(0);
			}
		}
		output += "%04d".formatted(hodan_id);
		return output;
	}

	@Override
	public Integer getTongTien(HoDan hoDan, int soNuoc) {
		int tongTien = 0;
		int muc1 = 0, muc2 = 0, muc3 = 0, muc4 = 0;
		if (soNuoc > 10) {
			muc1 = 10;
			soNuoc -= 10;
		} else {
			muc1 = soNuoc;
			soNuoc = 0;
		}
		if (soNuoc > 10) {
			muc2 = 10;
			soNuoc -= 10;
		} else {
			muc2 = soNuoc;
			soNuoc = 0;
		}
		if (soNuoc > 10) {
			muc3 = 10;
			soNuoc -= 10;
		} else {
			muc3 = soNuoc;
			soNuoc = 0;
		}
		if (soNuoc > 0)
			muc4 = soNuoc;
		if (hoDan.getLoaiHoDan().getTen().equals("Hộ dân khác")) {
			tongTien = 8500 * muc1 + 9900 * muc2 + 16000 * muc3 + 27000 * muc4;
			int vat = tongTien * 5 / 100;
			int phi_bvmt = tongTien * 10 / 100;
			return tongTien + vat + phi_bvmt;
		} else {
			tongTien = 5973 * muc1 + 9900 * muc2 + 16000 * muc3 + 27000 * muc4;
			int vat = tongTien * 5 / 100;
			int phi_bvmt = tongTien * 10 / 100;
			return tongTien + vat + phi_bvmt;
		}
	}

	@Override
	public boolean isUpdated(CongTo congTo) {
		Date date = congTo.getLatest_updated();
		if (date == null)
			return false;
		Date currDate = new Date();
		// set tmp
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(currDate);
		currDate = calendar.getTime();
		//
		if (getThang(currDate) == getThang(date))
			return true;
		return false;
	}

	private int getThang(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd MM yyyy");
		String formattedDate = dateFormat.format(date);
		String data[] = formattedDate.split("\\s+");
		return Integer.parseInt(data[1]);
	}

	public Calendar calendar = Calendar.getInstance();

}
