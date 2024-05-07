package com.water.dbclpm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.water.dbclpm.entity.CongTo;
import com.water.dbclpm.entity.HoDan;
import com.water.dbclpm.service.CongToService;
import jakarta.servlet.http.HttpSession;

@Controller
public class HoDanController {
	
	@Autowired 
	private CongToService congToService;
	
	@GetMapping("/xem-dong-ho")
	public String xemDongHo(HttpSession session, Model model) {
		HoDan hoDan = (HoDan) session.getAttribute("hodan");
		CongTo congTo = congToService.getCongToByHoDanId(hoDan.getHodan_id());
		model.addAttribute("congto", congTo);
		return "congto";
	}
}
