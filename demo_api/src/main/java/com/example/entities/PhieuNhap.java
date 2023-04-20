package com.example.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="PHIEUNHAP")
public class PhieuNhap {
	@Id
	@Column(name="MAPN")
	private String mapn;
	private Date ngaynhap;
	private float tongtien;
	
	@ManyToOne
	@JoinColumn(name="maddh")
	private DatHang datHangPN;
	
	@OneToMany(mappedBy="phieuNhapSP")
	@JsonIgnore
	private List<SanPham> sanPhamsPN;

	public PhieuNhap() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getMapn() {
		return mapn;
	}

	public void setMapn(String mapn) {
		this.mapn = mapn;
	}

	public Date getNgaynhap() {
		return ngaynhap;
	}

	public void setNgaynhap(Date ngaynhap) {
		this.ngaynhap = ngaynhap;
	}

	public float getTongtien() {
		return tongtien;
	}

	public void setTongtien(float tongtien) {
		this.tongtien = tongtien;
	}

	public DatHang getDatHangPN() {
		return datHangPN;
	}

	public void setDatHangPN(DatHang datHangPN) {
		this.datHangPN = datHangPN;
	}

	public List<SanPham> getSanPhamsPN() {
		return sanPhamsPN;
	}

	public void setSanPhamsPN(List<SanPham> sanPhamsPN) {
		this.sanPhamsPN = sanPhamsPN;
	}
	
	
}
