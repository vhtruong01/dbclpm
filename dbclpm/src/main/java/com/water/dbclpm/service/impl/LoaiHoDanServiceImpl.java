package com.water.dbclpm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.water.dbclpm.entity.LoaiHoDan;
import com.water.dbclpm.repository.LoaiHoDanRepository;
import com.water.dbclpm.service.LoaiHoDanService;

@Service
public class LoaiHoDanServiceImpl implements LoaiHoDanService {
	
	@Autowired
	private LoaiHoDanRepository loaiHoDanRepository;

	@Override
	public List<LoaiHoDan> getDsLoaiHoDan() {
		return loaiHoDanRepository.findAll();
	}

}
