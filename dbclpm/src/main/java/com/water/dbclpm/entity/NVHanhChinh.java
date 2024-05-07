package com.water.dbclpm.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "nvhanhchinh")
public class NVHanhChinh extends NhanVien {

	public NVHanhChinh() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NVHanhChinh(Integer nhanvien_id, String ten, String sdt, String chucVu, String password) {
		super(nhanvien_id, ten, sdt, chucVu, password);
		// TODO Auto-generated constructor stub
	}


}
