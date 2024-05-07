package com.water.dbclpm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.water.dbclpm.entity.HoDan;
import com.water.dbclpm.entity.NhanVien;
import com.water.dbclpm.security.PasswordEncodeService;
import com.water.dbclpm.service.UserService;
import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	
	@Autowired
	private UserService userService;

	@GetMapping("/")
	public String getHome() {
		return "home";
	}
	
	@GetMapping("/login")
	public String loginForm(Model model) {
		model.addAttribute("user", new HoDan());
		return "login";
	}
	
	@PostMapping("/login/check")
	public String checkLogin(Model model, HttpSession session, HoDan hoDan) {
		HoDan hoDan2 = userService.getHoDanTheoSdt(hoDan.getSdt().trim());
		if(hoDan2 != null && PasswordEncodeService.matches(hoDan.getPassword(), hoDan2.getPassword())) {
			session.setAttribute("hodan", hoDan2);
			session.setMaxInactiveInterval(30*60);
			return "redirect:/";
		}
		model.addAttribute("error", "true");
		model.addAttribute("user", hoDan);
		return "login";
	}
	
	@GetMapping("/login-nv")
	public String loginForm_nv(Model model) {
		model.addAttribute("user", new NhanVien());
		return "login-nv";
	}
	
	@PostMapping("/login-nv/check")
	public String checkLogin_nv(Model model, HttpSession session, NhanVien nhanVien) {
		NhanVien nhanVien2 = userService.getNhanVienBySdt(nhanVien.getSdt().trim());
		if(nhanVien2 != null) {
			session.setAttribute("nv", nhanVien2);
			session.setMaxInactiveInterval(30*60);
			return "redirect:/";
		}
		model.addAttribute("error", "true");
		model.addAttribute("user", nhanVien);
		return "login-nv";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
}
