package com.example.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="SANPHAM")
public class SanPham {
	@Id
	@Column(name="SOSERI")
	private String soseri;
	
	@ManyToOne
	@JoinColumn(name="maloai")
	private LoaiSanPham loaiSanPham;
	
	@ManyToOne
	@JoinColumn(name="idgiohang")
	private GioHang gioHang;
	
	@ManyToOne
	@JoinColumn(name="mapn")
	private PhieuNhap phieuNhapSP;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="masobh")
	private PhieuBH phieuBHSP;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="maphieutra")
	private PhieuTra phieuTraSP;

	public SanPham() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SanPham(String soseri, LoaiSanPham loaiSanPham, GioHang gioHang, PhieuNhap phieuNhap, PhieuBH phieuBH,
			PhieuTra phieuTra) {
		super();
		this.soseri = soseri;
		this.loaiSanPham = loaiSanPham;
		this.gioHang = gioHang;
		this.phieuNhapSP = phieuNhap;
		this.phieuBHSP = phieuBH;
		this.phieuTraSP = phieuTra;
	}

	public String getSoseri() {
		return soseri;
	}

	public void setSoseri(String soseri) {
		this.soseri = soseri;
	}

	public LoaiSanPham getLoaiSanPham() {
		return loaiSanPham;
	}

	public void setLoaiSanPham(LoaiSanPham loaiSanPham) {
		this.loaiSanPham = loaiSanPham;
	}

	public GioHang getGioHang() {
		return gioHang;
	}

	public void setGioHang(GioHang gioHang) {
		this.gioHang = gioHang;
	}

	public PhieuNhap getPhieuNhap() {
		return phieuNhapSP;
	}

	public void setPhieuNhap(PhieuNhap phieuNhap) {
		this.phieuNhapSP = phieuNhap;
	}

	public PhieuBH getPhieuBH() {
		return phieuBHSP;
	}

	public void setPhieuBH(PhieuBH phieuBH) {
		this.phieuBHSP = phieuBH;
	}

	public PhieuTra getPhieuTra() {
		return phieuTraSP;
	}

	public void setPhieuTra(PhieuTra phieuTra) {
		this.phieuTraSP = phieuTra;
	}
	
	
}
