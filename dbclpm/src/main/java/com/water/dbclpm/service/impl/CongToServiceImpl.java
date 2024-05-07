package com.water.dbclpm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.water.dbclpm.entity.CongTo;
import com.water.dbclpm.repository.CongToRepository;
import com.water.dbclpm.service.CongToService;

@Service
public class CongToServiceImpl implements CongToService {
	
	@Autowired
	private CongToRepository repository;

	@Override
	public CongTo getCongToByqcode(String qcode) {
		return repository.getCongToByqcode(qcode);
	}

	@Override
	public CongTo getCongToByHoDanId(Integer hodan_id) {
		return repository.getCongToByHoDanId(hodan_id);
	}

	@Override
	public boolean save(CongTo congTo) {
		try {
			repository.save(congTo);
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}

}
