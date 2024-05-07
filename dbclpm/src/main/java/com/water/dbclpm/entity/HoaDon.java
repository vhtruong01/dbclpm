package com.water.dbclpm.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "hoadon")
public class HoaDon {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "hoadon_id")
	private Integer hoadon_id;

	@Column(name = "tongtien")
	private Integer tongTien;

	@Column(name = "thoigian")
	private Date thoiGian;

	@Column(name = "trangthai")
	private String trangThai;

	@Column(name = "ghichu")
	private String ghiChu;

	@Column(name = "socu")
	private Integer soCu;

	@Column(name = "somoi")
	private Integer soMoi;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "congto_id")
	private CongTo congTo;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "nhanvien_id")
	private NVGhiNuoc nvGhiNuoc;

	public HoaDon() {
		// TODO Auto-generated constructor stub
	}

	public HoaDon(Integer tongTien, Date thoiGian, String trangThai, String ghiChu, Integer soCu,
			Integer soMoi, CongTo congTo, NVGhiNuoc nvGhiNuoc) {
		super();
		this.tongTien = tongTien;
		this.thoiGian = thoiGian;
		this.trangThai = trangThai;
		this.ghiChu = ghiChu;
		this.soCu = soCu;
		this.soMoi = soMoi;
		this.congTo = congTo;
		this.nvGhiNuoc = nvGhiNuoc;
	}

	public Integer getHoadon_id() {
		return hoadon_id;
	}

	public void setHoadon_id(Integer hoadon_id) {
		this.hoadon_id = hoadon_id;
	}

	public Integer getTongTien() {
		return tongTien;
	}

	public void setTongTien(Integer tongTien) {
		this.tongTien = tongTien;
	}

	public Date getThoiGian() {
		return thoiGian;
	}

	public void setThoiGian(Date thoiGian) {
		this.thoiGian = thoiGian;
	}

	public String getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}

	public String getGhiChu() {
		return ghiChu;
	}

	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}

	public Integer getSoCu() {
		return soCu;
	}

	public void setSoCu(Integer soCu) {
		this.soCu = soCu;
	}

	public Integer getSoMoi() {
		return soMoi;
	}

	public void setSoMoi(Integer soMoi) {
		this.soMoi = soMoi;
	}

	public CongTo getCongTo() {
		return congTo;
	}

	public void setCongTo(CongTo congTo) {
		this.congTo = congTo;
	}

	public NVGhiNuoc getNvGhiNuoc() {
		return nvGhiNuoc;
	}

	public void setNvGhiNuoc(NVGhiNuoc nvGhiNuoc) {
		this.nvGhiNuoc = nvGhiNuoc;
	}

	@Override
	public String toString() {
		return "HoaDon [hoadon_id=" + hoadon_id + ", tongTien=" + tongTien + ", thoiGian=" + thoiGian + ", trangThai="
				+ trangThai + ", ghiChu=" + ghiChu + ", congTo=" + congTo + ", nvGhiNuoc=" + nvGhiNuoc + "]";
	}
}
