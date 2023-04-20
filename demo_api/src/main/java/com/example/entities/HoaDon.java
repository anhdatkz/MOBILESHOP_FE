package com.example.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="HOADON")
public class HoaDon {
	@Id
	@Column(name="MAHD")
	private String mahd;
	private String masothue;
	private Date ngaylap;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="madh")
	private DonHang donHang;
	
	@OneToMany(mappedBy="hoaDonPT")
	@JsonIgnore
	private List<PhieuTra> phieuTras;
	
	public HoaDon() {
		super();
		// TODO Auto-generated constructor stub
	}

	public HoaDon(String mahd, String masothue, Date ngaylap, DonHang gioHang, List<PhieuTra> phieuTras) {
		super();
		this.mahd = mahd;
		this.masothue = masothue;
		this.ngaylap = ngaylap;
		this.donHang = gioHang;
		this.phieuTras = phieuTras;
	}



	public String getMahd() {
		return mahd;
	}

	public void setMahd(String mahd) {
		this.mahd = mahd;
	}

	public String getMasothue() {
		return masothue;
	}

	public void setMasothue(String masothue) {
		this.masothue = masothue;
	}

	public Date getNgaylap() {
		return ngaylap;
	}

	public void setNgaylap(Date ngaylap) {
		this.ngaylap = ngaylap;
	}

	public DonHang getGioHang() {
		return donHang;
	}

	public void setGioHang(DonHang gioHang) {
		this.donHang = gioHang;
	}

	public List<PhieuTra> getPhieuTras() {
		return phieuTras;
	}

	public void setPhieuTras(List<PhieuTra> phieuTras) {
		this.phieuTras = phieuTras;
	}
	
	
}
