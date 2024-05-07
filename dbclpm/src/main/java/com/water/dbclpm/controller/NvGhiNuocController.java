package com.water.dbclpm.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.water.dbclpm.entity.CongTo;
import com.water.dbclpm.entity.HoaDon;
import com.water.dbclpm.entity.NVGhiNuoc;
import com.water.dbclpm.entity.NhanVien;
import com.water.dbclpm.service.CongToService;
import com.water.dbclpm.service.HoaDonService;
import com.water.dbclpm.service.NhanVienService;
import jakarta.servlet.http.HttpSession;

@Controller
public class NvGhiNuocController {
	
	@Autowired
	private NhanVienService nhanVienService;
	
	@Autowired 
	private CongToService congToService;
	
	@Autowired
	private HoaDonService hoaDonService;
	
	@GetMapping("/cap-nhat-so-nuoc")
	public String searchForm() {
		return "tim-ho-dan";
	}
	
	@PostMapping("/search")
	public String getHoDan(Model model, HttpSession session, @RequestParam(name = "keyword") String qcode) {
		CongTo congTo = congToService.getCongToByqcode(qcode.trim());
		if(congTo == null) {
			model.addAttribute("notExised", "Không tồn tại hộ dân thỏa mãn với mã công tơ trên");
			model.addAttribute("keyword", qcode);
			return "tim-ho-dan";
		}
		System.err.println(congTo);
		model.addAttribute("congto", congTo);
		if(nhanVienService.isUpdated(congTo)) {
			model.addAttribute("updated", "Đã cập nhật");
			model.addAttribute("thang", getThang(congTo.getLatest_updated()));
		}
		return "cap-nhat";
	}
	
	@PutMapping("/update-so-nuoc")
	public String updateCongTo(HttpSession session, @RequestParam(name = "so-moi") Integer soMoi, @ModelAttribute(name="congto") CongTo congTo, Model model) {
		System.err.println(congTo);
		NhanVien nhanVien = (NhanVien) session.getAttribute("nv");
		congTo.setSoCu(congTo.getSoMoi());
		congTo.setSoMoi(soMoi);
		NVGhiNuoc nvGhiNuoc = nhanVienService.getNvGhiNuoc(nhanVien.getNhanvien_id());
		Date date = new Date();
		// set tmp 
		Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        date = calendar.getTime();
        //
		congTo.setLatest_updated(date);
		boolean successCongTo =  congToService.save(congTo);
		if(successCongTo) {
			Integer tongTien = nhanVienService.getTongTien(congTo.getHoDan(), congTo.getSoMoi() - congTo.getSoCu());
			HoaDon hoaDon = new HoaDon(tongTien, date, "Chưa nộp", " ", congTo.getSoCu(), congTo.getSoMoi(), congTo, nvGhiNuoc);
			if(tongTien == 0) hoaDon.setTrangThai("Đã nộp");
			System.err.println(hoaDon);
			Boolean successHoaDon = hoaDonService.luuHoaDon(hoaDon);
			if(successHoaDon) {
				model.addAttribute("congto", congTo);
				model.addAttribute("updated", "true");
				model.addAttribute("thang", getThang(congTo.getLatest_updated()));
				return "cap-nhat";
			}
		}
		return "error";
	}
	
	private int getThang(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd MM yyyy");
        String formattedDate = dateFormat.format(date);
        String data[] = formattedDate.split("\\s+");
        return Integer.parseInt(data[1]);
	}
}
