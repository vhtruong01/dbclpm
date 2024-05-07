package com.water.dbclpm.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "nvghinuoc")
public class NVGhiNuoc extends NhanVien {

	public NVGhiNuoc() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NVGhiNuoc(Integer nhanvien_id, String ten, String sdt, String chucVu, String password) {
		super(nhanvien_id, ten, sdt, chucVu, password);
		// TODO Auto-generated constructor stub
	}

}
