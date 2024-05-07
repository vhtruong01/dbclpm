package com.water.dbclpm.controller;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.water.dbclpm.entity.CongTo;
import com.water.dbclpm.entity.HoDan;
import com.water.dbclpm.entity.HoaDon;
import com.water.dbclpm.security.PasswordEncodeService;
import com.water.dbclpm.service.CongToService;
import com.water.dbclpm.service.HoaDonService;
import com.water.dbclpm.service.LoaiHoDanService;
import com.water.dbclpm.service.NhanVienService;
import com.water.dbclpm.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class NvHanhChinhController {
	@Autowired
	private UserService userService;
	
	@Autowired 
	private LoaiHoDanService loaiHoDanService;
	
	@Autowired
	private NhanVienService nhanVienService;
	
	@Autowired 
	private CongToService congToService;
	
	@Autowired
	private HoaDonService hoaDonService;
	
	@GetMapping("/signup")
	public String signUpForm(Model model) {
		model.addAttribute("user", new HoDan());
		model.addAttribute("dsLoai", loaiHoDanService.getDsLoaiHoDan());
		return "signup";
	}
	
	@PostMapping("/signup/check-again")
	public String checkUserCreateForm(Model model, HoDan hoDan, @RequestParam("repeatpassword") String repass) {
		if(userService.getHoDanTheoSdt(hoDan.getSdt()) != null) {
			model.addAttribute("existederror", "Số điện thoại này đã được đăng ký!");
			model.addAttribute("user", hoDan);
			model.addAttribute("dsLoai", loaiHoDanService.getDsLoaiHoDan());
			model.addAttribute("repass", repass);
			return "signup";
		}
		model.addAttribute("user", hoDan);
		System.err.println(hoDan);
		model.addAttribute("dsLoai", loaiHoDanService.getDsLoaiHoDan());
		return "signup-check";
	}
	
	
	@PostMapping("/signup/save")
	public String createUser(HoDan hoDan, Model model) {
		if(userService.getHoDanTheoSdt(hoDan.getSdt()) != null) {
			model.addAttribute("existed", "true");
			model.addAttribute("user", hoDan);
			model.addAttribute("dsLoai", loaiHoDanService.getDsLoaiHoDan());
			return "signup";
		}
		hoDan.setNvHanhChinh(nhanVienService.getNvHanhChinh(1));
		Date date = new Date();
		hoDan.setNgayduyet(date);
		hoDan.setPassword(PasswordEncodeService.encodeService(hoDan.getPassword()));
		boolean saveSuccessHoDan = userService.save(hoDan);
		CongTo congTo = new CongTo(nhanVienService.genCongToQCode(hoDan.getQuanHuyen(), hoDan.getXaPhuong(), hoDan.getHodan_id()), 0, 0, null, hoDan);
		boolean saveSuccessCongTo = congToService.save(congTo);
		if(saveSuccessCongTo && saveSuccessHoDan) {
			return "success";
		}
		return "error";
	}
	
	
	@GetMapping("/timhodan")
	public String timHoDanBySdtOrQcode() {
		return "tim-ho-dan-sdt-qcode";
	}
	
	@PostMapping("/search-plus")
	public String getHoDanBySDTOrQcode(Model model, HttpSession session, @RequestParam String keyword) {
		List<HoaDon> dsHoaDon = hoaDonService.getDsHoaDonBySdtDonOrQcode(keyword.trim());
		if(dsHoaDon.isEmpty()) {
			model.addAttribute("notExised", "Không có hộ dân phù hợp (Hoặc hiện tại không có hóa đơn nào cần thanh toán)");
			model.addAttribute("keyword", keyword);
			return "tim-ho-dan-sdt-qcode";
		}
		model.addAttribute("dsHoaDon", dsHoaDon);
		model.addAttribute("keyword", keyword);
		return "dsHoaDon";
	}
	
	@GetMapping("/xem-hoa-don")
	public String getDsHoaDon(Model model, HttpSession session) {
		HoDan hoDan = (HoDan) session.getAttribute("hodan");
		List<HoaDon> dsHoaDon = hoaDonService.getDsHoaDonByMaHD(hoDan.getHodan_id());
		model.addAttribute("dsHoaDon", dsHoaDon);
		return "dshoadon";
	}
	
	@GetMapping("/detail/{id}")
	public String getChiTietHoaDon(@PathVariable Integer id, Model model) {
		HoaDon hoaDon = hoaDonService.getHoaDon(id);
		int muc1 = 0, muc2 = 0, muc3 = 0, muc4 = 0;
		int soNuoc = hoaDon.getSoMoi() - hoaDon.getSoCu(); 
		
		if(soNuoc > 10) {
			muc1 = 10; soNuoc -= 10;
		} else {
			muc1 = soNuoc; soNuoc = 0;
		}
		if(soNuoc > 10) {
			muc2 = 10; soNuoc -= 10;
		} else {
			muc2 = soNuoc; soNuoc = 0;
		}
		if(soNuoc > 10) {
			muc3 = 10; soNuoc -= 10;
		} else {
			muc3 = soNuoc; soNuoc = 0;
		}
		if(soNuoc > 0) muc4 = soNuoc;
		int tong_tmp = 0;
		if(hoaDon.getCongTo().getHoDan().getLoaiHoDan().getLoai_id() == 1){
			tong_tmp = muc1 * 5973 + muc2 * 9900 + muc3 * 16000 + muc4 * 27000;
		} else {
			tong_tmp = muc1 * 8500 + muc2 * 9900 + muc3 * 16000 + muc4 * 27000;
		}
		int vat = tong_tmp * 5 /100;
		int bvmt = tong_tmp * 10 / 100;
		model.addAttribute("hoadon", hoaDon);
		model.addAttribute("muc1", muc1);
		model.addAttribute("muc2", muc2);
		model.addAttribute("muc3", muc3);
		model.addAttribute("muc4", muc4);
		model.addAttribute("tongtmp", tong_tmp);
		model.addAttribute("vat", vat);
		model.addAttribute("bvmt", bvmt);
		model.addAttribute("urlnext", "/cap-nhat-so-nuoc");
		model.addAttribute("txtBtn", "Cập nhật cho hộ khác");
		model.addAttribute("ngaycham", nhanVienService.getngayCham(hoaDon));
		model.addAttribute("tienphat", nhanVienService.getPhiPhat(hoaDon));
		return "hoadon";
	}
	
	@PostMapping("/thanhtoan")
	public String thanhToan(@RequestParam(name = "idHoadon") Integer idHoaDon, Model model) {
		HoaDon hoaDon = hoaDonService.getHoaDon(idHoaDon);
		hoaDon.setTrangThai("Đã nộp");
		if(nhanVienService.getngayCham(hoaDon) > 0) {
			hoaDon.setTongTien(hoaDon.getTongTien() + nhanVienService.getPhiPhat(hoaDon));
			hoaDon.setGhiChu("Hóa đơn này bị chậm đóng so với thời hạn " + nhanVienService.getngayCham(hoaDon) + " ngày và bị phạt " + nhanVienService.getPhiPhat(hoaDon) + " đ");
		}
		boolean success =  hoaDonService.luuHoaDon(hoaDon);
		if(success) {
			model.addAttribute("hoadon", hoaDon);
			return "hoadon";
		}
		return "error";
	}
	
}
