package com.water.dbclpm.service;

import com.water.dbclpm.entity.CongTo;

public interface CongToService {
	
	CongTo getCongToByqcode(String qcode);
	CongTo getCongToByHoDanId(Integer hodan_id);
	boolean save(CongTo congTo);

}
