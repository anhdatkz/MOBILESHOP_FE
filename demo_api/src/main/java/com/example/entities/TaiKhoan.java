package com.example.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="TAIKHOAN")
public class TaiKhoan {
	@Id
	@Column(name="MATK")
	private String matk;
	private String password;
	
	@OneToOne(mappedBy="taiKhoanKH")
	@JsonIgnore
	private KhachHang khachHang;
	
	@OneToOne(mappedBy="taiKhoanNV")
	@JsonIgnore
	private NhanVien nhanVien;
	
	@ManyToOne
	@JoinColumn(name="maquyen")
	private Quyen quyen;

	public TaiKhoan() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public TaiKhoan(String matk, String password, KhachHang khachHang, NhanVien nhanVien, Quyen quyen) {
		super();
		this.matk = matk;
		this.password = password;
		this.khachHang = khachHang;
		this.nhanVien = nhanVien;
		this.quyen = quyen;
	}



	public String getMatk() {
		return matk;
	}

	public void setMatk(String matk) {
		this.matk = matk;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public KhachHang getKhachHang() {
		return khachHang;
	}

	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}

	public NhanVien getNhanVien() {
		return nhanVien;
	}

	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}

	public Quyen getQuyen() {
		return quyen;
	}

	public void setQuyen(Quyen quyen) {
		this.quyen = quyen;
	}
	
	
}
