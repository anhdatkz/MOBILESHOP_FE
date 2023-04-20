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

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="NHANVIEN")
public class NhanVien {
	@Id
	@Column(name="MANV")
	private String manv;
	private String hoten;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+7")
	private Date ngaysinh;
	private String email;
	private String sdt;
	private String diachi;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="matk")
	@JsonIgnore
	private TaiKhoan taiKhoanNV;
	
	@OneToMany(mappedBy="nhanVienDH")
	@JsonIgnore
	private List<DatHang> datHangsNV;
	
	@OneToMany(mappedBy="nhanVienTDG")
	@JsonIgnore
	private List<ThayDoiGia> thayDoiGiasNV;
	
	@OneToMany(mappedBy="nhanVienGiao")
	@JsonIgnore
	private List<DonHang> donHangsNVG;
	
	
	@OneToMany(mappedBy="nhanVienDuyet")
	@JsonIgnore
	private List<DonHang> donHangsNVD;
	
	@OneToMany(mappedBy="nhanVienPT")
	@JsonIgnore
	private List<PhieuTra> phieuTrasNV;
	
	@OneToMany(mappedBy="nhanVienNhanBH")
	@JsonIgnore
	private List<CTNhanBH> ctNhanBHsNV;

	public NhanVien() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getManv() {
		return manv;
	}

	public void setManv(String manv) {
		this.manv = manv;
	}

	public String getHoten() {
		return hoten;
	}

	public void setHoten(String hoten) {
		this.hoten = hoten;
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

	public String getDiachi() {
		return diachi;
	}

	public void setDiachi(String diachi) {
		this.diachi = diachi;
	}

	public TaiKhoan getTaiKhoanNV() {
		return taiKhoanNV;
	}

	public void setTaiKhoanNV(TaiKhoan taiKhoanNV) {
		this.taiKhoanNV = taiKhoanNV;
	}

	public List<DatHang> getDatHangsNV() {
		return datHangsNV;
	}

	public void setDatHangsNV(List<DatHang> datHangsNV) {
		this.datHangsNV = datHangsNV;
	}

	public List<ThayDoiGia> getThayDoiGiasNV() {
		return thayDoiGiasNV;
	}

	public void setThayDoiGiasNV(List<ThayDoiGia> thayDoiGiasNV) {
		this.thayDoiGiasNV = thayDoiGiasNV;
	}

	public List<DonHang> getDonHangsNVG() {
		return donHangsNVG;
	}

	public void setDonHangsNVG(List<DonHang> donHangsNVG) {
		this.donHangsNVG = donHangsNVG;
	}

	public List<DonHang> getDonHangsNVD() {
		return donHangsNVD;
	}

	public void setDonHangsNVD(List<DonHang> donHangsNVD) {
		this.donHangsNVD = donHangsNVD;
	}

	public List<PhieuTra> getPhieuTrasNV() {
		return phieuTrasNV;
	}

	public void setPhieuTrasNV(List<PhieuTra> phieuTrasNV) {
		this.phieuTrasNV = phieuTrasNV;
	}

	public List<CTNhanBH> getCtNhanBHsNV() {
		return ctNhanBHsNV;
	}

	public void setCtNhanBHsNV(List<CTNhanBH> ctNhanBHsNV) {
		this.ctNhanBHsNV = ctNhanBHsNV;
	}
	
	
 	
}
