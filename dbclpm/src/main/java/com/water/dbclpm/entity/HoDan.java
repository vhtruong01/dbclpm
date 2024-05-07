package com.water.dbclpm.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.FetchType;

@Entity
@Table(name = "hodan")
public class HoDan {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "hodan_id")
	private Integer hodan_id;

	@Column(name = "tenchuho")
	private String tenChuHo;

	@Column(name = "sdt")
	private String sdt;

	@Column(name = "ngayduyet")
	private Date ngayduyet;

	@Column(name = "quanhuyen")
	private String quanHuyen;

	@Column(name = "xaphuong")
	private String xaPhuong;

	@Column(name = "diachichitiet")
	private String diaChiChiTiet;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "loai_id")
	private LoaiHoDan loaiHoDan;

	@Column(name = "password")
	private String password;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "nhanvien_id")
	private NVHanhChinh nvHanhChinh;

	public HoDan() {

	}

	public HoDan(Integer hodan_id, String tenChuHo, String sdt, Date ngayduyet, String quanHuyen,
			String xaPhuong, String diaChiChiTiet, LoaiHoDan loaiHoDan, String password, NVHanhChinh nvHanhChinh) {
		super();
		this.hodan_id = hodan_id;
		this.tenChuHo = tenChuHo;
		this.sdt = sdt;
		this.ngayduyet = ngayduyet;
		this.quanHuyen = quanHuyen;
		this.xaPhuong = xaPhuong;
		this.diaChiChiTiet = diaChiChiTiet;
		this.loaiHoDan = loaiHoDan;
		this.password = password;
		this.nvHanhChinh = nvHanhChinh;
	}

	public Integer getHodan_id() {
		return hodan_id;
	}

	public void setHodan_id(Integer hodan_id) {
		this.hodan_id = hodan_id;
	}

	public String getTenChuHo() {
		return tenChuHo;
	}

	public void setTenChuHo(String tenChuHo) {
		this.tenChuHo = tenChuHo;
	}

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}

	public Date getNgayduyet() {
		return ngayduyet;
	}

	public void setNgayduyet(Date ngayduyet) {
		this.ngayduyet = ngayduyet;
	}

	public String getQuanHuyen() {
		return quanHuyen;
	}

	public void setQuanHuyen(String quanHuyen) {
		this.quanHuyen = quanHuyen;
	}

	public String getXaPhuong() {
		return xaPhuong;
	}

	public void setXaPhuong(String xaPhuong) {
		this.xaPhuong = xaPhuong;
	}

	public String getDiaChiChiTiet() {
		return diaChiChiTiet;
	}

	public void setDiaChiChiTiet(String diaChiChiTiet) {
		this.diaChiChiTiet = diaChiChiTiet;
	}

	public LoaiHoDan getLoaiHoDan() {
		return loaiHoDan;
	}

	public void setLoaiHoDan(LoaiHoDan loaiHoDan) {
		this.loaiHoDan = loaiHoDan;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public NVHanhChinh getNvHanhChinh() {
		return nvHanhChinh;
	}

	public void setNvHanhChinh(NVHanhChinh nvHanhChinh) {
		this.nvHanhChinh = nvHanhChinh;
	}

	@Override
	public String toString() {
		return "HoDan [hodan_id=" + hodan_id + ", tenChuHo=" + tenChuHo + ", sdt=" + sdt + ", ngayduyet=" + ngayduyet
				+ ", quanHuyen=" + quanHuyen + ", xaPhuong=" + xaPhuong + ", diaChiChiTiet="
				+ diaChiChiTiet + ", loaiHoDan=" + loaiHoDan + ", nvHanhChinh=" + nvHanhChinh + "]";
	}

}
