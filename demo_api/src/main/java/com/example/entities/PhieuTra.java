package com.example.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="PHIEUTRA")
public class PhieuTra {
	@Id
	@Column(name="MAPHIEUTRA")
	private String maphieutra;
	private Date ngaylap;
	
	@ManyToOne
	@JoinColumn(name="manv")
	private HoaDon hoaDonPT;
	
	@ManyToOne
	@JoinColumn(name="mahd")
	private NhanVien nhanVienPT;
	
	@OneToOne(mappedBy="phieuTraSP")
	private SanPham sanPhamTra;

	public PhieuTra() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getMaphieutra() {
		return maphieutra;
	}

	public void setMaphieutra(String maphieutra) {
		this.maphieutra = maphieutra;
	}

	public Date getNgaylap() {
		return ngaylap;
	}

	public void setNgaylap(Date ngaylap) {
		this.ngaylap = ngaylap;
	}

	public HoaDon getHoaDonPT() {
		return hoaDonPT;
	}

	public void setHoaDonPT(HoaDon hoaDonPT) {
		this.hoaDonPT = hoaDonPT;
	}

	public NhanVien getNhanVienPT() {
		return nhanVienPT;
	}

	public void setNhanVienPT(NhanVien nhanVienPT) {
		this.nhanVienPT = nhanVienPT;
	}

	public SanPham getSanPhamTra() {
		return sanPhamTra;
	}

	public void setSanPhamTra(SanPham sanPhamTra) {
		this.sanPhamTra = sanPhamTra;
	}
	
	
}
