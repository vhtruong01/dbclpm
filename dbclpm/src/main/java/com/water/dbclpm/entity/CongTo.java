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
@Table(name = "congto")
public class CongTo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "congto_id")
	private Integer congto_id;

	@Column(name = "qcode")
	private String qcode;

	@Column(name = "socu")
	private Integer soCu;

	@Column(name = "somoi")
	private Integer soMoi;

	@Column(name = "latest_updated")
	private Date latest_updated;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "hodan_id")
	private HoDan hoDan;

	public CongTo() {
		// TODO Auto-generated constructor stub
	}

	public CongTo(String qcode, Integer soCu, Integer soMoi, Date latest_updated, HoDan hoDan) {
		super();
		this.qcode = qcode;
		this.soCu = soCu;
		this.soMoi = soMoi;
		this.latest_updated = latest_updated;
		this.hoDan = hoDan;
	}

	public Integer getCongto_id() {
		return congto_id;
	}

	public void setCongto_id(Integer congto_id) {
		this.congto_id = congto_id;
	}

	public String getQcode() {
		return qcode;
	}

	public void setQcode(String qcode) {
		this.qcode = qcode;
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

	public Date getLatest_updated() {
		return latest_updated;
	}

	public void setLatest_updated(Date latest_updated) {
		this.latest_updated = latest_updated;
	}

	public HoDan getHoDan() {
		return hoDan;
	}

	public void setHoDan(HoDan hoDan) {
		this.hoDan = hoDan;
	}

	@Override
	public String toString() {
		return "CongTo [congto_id=" + congto_id + ", soCu=" + soCu + ", soMoi=" + soMoi + ", hoDan=" + hoDan + " " + latest_updated + "]";
	}

}
