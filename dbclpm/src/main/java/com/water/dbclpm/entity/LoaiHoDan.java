package com.water.dbclpm.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="loaihodan")
public class LoaiHoDan {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "loai_id")
	private Integer loai_id;
	
	@Column(name = "ten")
	private String ten;
	
	public LoaiHoDan() {
		
	}

	public LoaiHoDan(Integer loai_id, String ten) {
		super();
		this.loai_id = loai_id;
		this.ten = ten;
	}

	public Integer getLoai_id() {
		return loai_id;
	}

	public void setLoai_id(Integer loai_id) {
		this.loai_id = loai_id;
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	@Override
	public String toString() {
		return "LoaiHoDan [loai_id=" + loai_id + ", ten=" + ten + "]";
	}
	
	
}
