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
@Table(name="DATHANG")
public class DatHang {
	@Id
	@Column(name="MADDH")
	private String maddh;
	private Date ngaylap;
	private float tongtien;
	
	@OneToMany(mappedBy="datHangCTDH")
	@JsonIgnore
	private List<CTDDH> ctddhsDH;
	
	@OneToMany(mappedBy="datHangPN")
	@JsonIgnore
	private List<PhieuNhap> phieuNhapDH;
	
	@ManyToOne
	@JoinColumn(name="manv")
	private NhanVien nhanVienDH;
	
	@ManyToOne
	@JoinColumn(name="manhacc")
	private NhaCC nhaCCDH;

	public DatHang() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getMaddh() {
		return maddh;
	}

	public void setMaddh(String maddh) {
		this.maddh = maddh;
	}

	public Date getNgaylap() {
		return ngaylap;
	}

	public void setNgaylap(Date ngaylap) {
		this.ngaylap = ngaylap;
	}

	public float getTongtien() {
		return tongtien;
	}

	public void setTongtien(float tongtien) {
		this.tongtien = tongtien;
	}

	public List<CTDDH> getCtddhsDH() {
		return ctddhsDH;
	}

	public void setCtddhsDH(List<CTDDH> ctddhsDH) {
		this.ctddhsDH = ctddhsDH;
	}

	public List<PhieuNhap> getPhieuNhapDH() {
		return phieuNhapDH;
	}

	public void setPhieuNhapDH(List<PhieuNhap> phieuNhapDH) {
		this.phieuNhapDH = phieuNhapDH;
	}

	public NhanVien getNhanVienDH() {
		return nhanVienDH;
	}

	public void setNhanVienDH(NhanVien nhanVienDH) {
		this.nhanVienDH = nhanVienDH;
	}

	public NhaCC getNhaCCDH() {
		return nhaCCDH;
	}

	public void setNhaCCDH(NhaCC nhaCCDH) {
		this.nhaCCDH = nhaCCDH;
	}
	
	
	
}
