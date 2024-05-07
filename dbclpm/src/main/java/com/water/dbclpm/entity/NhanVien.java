package com.water.dbclpm.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

@Entity
@Table(name = "nhanvien")
@Inheritance(strategy = InheritanceType.JOINED)
public class NhanVien {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "nhanvien_id")
	private Integer nhanvien_id;

	@Column(name = "ten")
	private String ten;

	@Column(name = "sdt")
	private String sdt;

	@Column(name = "chucvu")
	private String chucVu;

	@Column(name = "password")
	private String password;

	public NhanVien() {

	}

	public Integer getNhanvien_id() {
		return nhanvien_id;
	}

	public void setNhanvien_id(Integer nhanvien_id) {
		this.nhanvien_id = nhanvien_id;
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}

	public String getChucVu() {
		return chucVu;
	}

	public void setChucVu(String chucVu) {
		this.chucVu = chucVu;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public NhanVien(Integer nhanvien_id, String ten, String sdt, String chucVu, String password) {
		super();
		this.nhanvien_id = nhanvien_id;
		this.ten = ten;
		this.sdt = sdt;
		this.chucVu = chucVu;
		this.password = password;
	}

	@Override
	public String toString() {
		return "NhanVien [nhanvien_id=" + nhanvien_id + ", ten=" + ten + ", sdt=" + sdt + ", chucVu=" + chucVu + "]";
	}
}
