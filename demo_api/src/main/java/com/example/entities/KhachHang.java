package com.example.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="KHACHHANG")
public class KhachHang {
	@Id
	@Column(name="CMND")
	private String cmnd;
	private String tenkh;
	private String diachi;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+7")
	private Date ngaysinh;
	private String sdt;
	private String email;
	
	@OneToOne(cascade=CascadeType.ALL)
//	@JsonIgnore
	@JoinColumn(name="MATK", referencedColumnName = "matk")
	private TaiKhoan taiKhoanKH;
	
	@OneToOne(mappedBy="khachHangGH")
	@JsonIgnore
	private GioHang gioHang;
	
	@OneToMany(mappedBy="khachHang")
	@JsonIgnore
	private List<DonHang> donHangs;
	
	@OneToMany(mappedBy="khachHangDG")
	@JsonIgnore
	private List<DanhGia> danhGias;

	public KhachHang() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getCmnd() {
		return cmnd;
	}

	public void setCmnd(String cmnd) {
		this.cmnd = cmnd;
	}

	public String getTenkh() {
		return tenkh;
	}

	public void setTenkh(String tenkh) {
		this.tenkh = tenkh;
	}

	public String getDiachi() {
		return diachi;
	}

	public void setDiachi(String diachi) {
		this.diachi = diachi;
	}

	public Date getNgaysinh() {
		return ngaysinh;
	}

	public void setNgaysinh(Date ngaysinh) {
		this.ngaysinh = ngaysinh;
	}

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public TaiKhoan getTaiKhoanKH() {
		return taiKhoanKH;
	}

	public void setTaiKhoanKH(TaiKhoan taiKhoanKH) {
		this.taiKhoanKH = taiKhoanKH;
	}

	public List<DonHang> getDonHangs() {
		return donHangs;
	}

	public void setDonHangs(List<DonHang> donHangs) {
		this.donHangs = donHangs;
	}

	public GioHang getGioHang() {
		return gioHang;
	}

	public void setGioHang(GioHang gioHang) {
		this.gioHang = gioHang;
	}

	public List<DanhGia> getDanhGias() {
		return danhGias;
	}

	public void setDanhGias(List<DanhGia> danhGias) {
		this.danhGias = danhGias;
	}

	
}
