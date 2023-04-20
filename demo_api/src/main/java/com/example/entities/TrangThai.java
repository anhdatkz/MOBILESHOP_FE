package com.example.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="TRANGTHAI")
public class TrangThai {
	@Id
	@Column(name="MATRANGTHAI")
	private Integer matrangthai;
	private String trangthai;
	
	@OneToMany(mappedBy="trangThai")
	@JsonIgnore
	private List<DonHang> donHangs;

	public TrangThai() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TrangThai(Integer matrangthai, String trangthai, List<DonHang> donHangs) {
		super();
		this.matrangthai = matrangthai;
		this.trangthai = trangthai;
		this.donHangs = donHangs;
	}

	public Integer getMatrangthai() {
		return matrangthai;
	}

	public void setMatrangthai(Integer matrangthai) {
		this.matrangthai = matrangthai;
	}

	public String getTrangthai() {
		return trangthai;
	}

	public void setTrangthai(String trangthai) {
		this.trangthai = trangthai;
	}

	public List<DonHang> getDonHangs() {
		return donHangs;
	}

	public void setGioHangs(List<DonHang> donHangs) {
		this.donHangs = donHangs;
	}
	
	
}
