package com.example.entities;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name ="LOAISANPHAM")
public class LoaiSanPham {
	@Id
	@Column(name="MALOAI")
	private String maloai;
	private String tenloai;
	private String anh;
	private String mota;
	private String chip;
	private String mahdh;
	private Integer pin;
	private String manhinh;
	private Integer soluongton;
	private Integer thoigianbh;
	private Integer gia;
	@DateTimeFormat(pattern = "MM-yyyy")
	@JsonFormat(pattern = "MM-yyyy", timezone = "GMT+7")
	private Date ramat;
	
	@ManyToOne
	@JoinColumn(name="RAM")
	private Ram ram;
	
	@ManyToOne
	@JoinColumn(name="ROM")
	private Rom rom;
	
	@ManyToOne
	@JoinColumn(name="MAHANG")
	private Hang hang;
	
	@OneToMany(mappedBy="loaiSanPhamCTDH")
	@JsonIgnore
	private List<CTDDH> ctddhLSP;
	
	@OneToMany(mappedBy="loaiSanPhamCTGG")
//	@JsonIgnore
	private List<CTGiamGia> ctGiamGiaLSP;
	
	@OneToMany(mappedBy = "loaiSanPham")
	@JsonIgnore
	private List<SanPham> sanPhams;
	
//	@OneToMany(mappedBy="loaiSanPhamBL")
//	@JsonIgnore
//	private List<BinhLuan> binhLuansLSP;
	
	@OneToMany(mappedBy="loaiSanPhamCC")
	@JsonIgnore
	private List<CungCap> cungCapsLSP;
	
	@OneToMany(mappedBy="loaiSanPhamTDG")
	private List<ThayDoiGia> thayDoiGiasLSP;
	
	@OneToMany(mappedBy="loaiSanPhamCTGH")
	@JsonIgnore
	private List<CTGH> ctghLSP;
	
	@OneToMany(mappedBy="loaiSanPhamDG")
	@JsonIgnore
	private List<DanhGia> danhGias;
	
	
	public LoaiSanPham() {
		super();
		// TODO Auto-generated constructor stub
	}


	public String getMaloai() {
		return maloai;
	}

	public void setMaloai(String maloai) {
		this.maloai = maloai;
	}

	public String getTenloai() {
		return tenloai;
	}

	public void setTenloai(String tenloai) {
		this.tenloai = tenloai;
	}

	public String getAnh() {
		return anh;
	}

	public void setAnh(String anh) {
		this.anh = anh;
	}

	public String getMota() {
		return mota;
	}

	public void setMota(String mota) {
		this.mota = mota;
	}

	public String getChip() {
		return chip;
	}

	public void setChip(String chip) {
		this.chip = chip;
	}


	public Ram getRam() {
		return ram;
	}


	public void setRam(Ram ram) {
		this.ram = ram;
	}


	public Rom getRom() {
		return rom;
	}


	public void setRom(Rom rom) {
		this.rom = rom;
	}


//	public HeDieuHanh getHedieuhanh() {
//		return hedieuhanh;
//	}
//
//
//	public void setHedieuhanh(HeDieuHanh hedieuhanh) {
//		this.hedieuhanh = hedieuhanh;
//	}
	

	public Integer getPin() {
		return pin;
	}

	public String getMahdh() {
		return mahdh;
	}


	public void setMahdh(String mahdh) {
		this.mahdh = mahdh;
	}


	public void setPin(Integer pin) {
		this.pin = pin;
	}
	
	
	public String getManhinh() {
		return manhinh;
	}


	public void setManhinh(String manhinh) {
		this.manhinh = manhinh;
	}


	public Integer getSoluongton() {
		return soluongton;
	}

	public void setSoluongton(Integer soluongton) {
		this.soluongton = soluongton;
	}

	public Integer getThoigianbh() {
		return thoigianbh;
	}

	public void setThoigianbh(Integer thoigianbh) {
		this.thoigianbh = thoigianbh;
	}
	
	

	public Integer getGia() {
		return gia;
	}


	public void setGia(Integer gia) {
		this.gia = gia;
	}


	public Date getRamat() {
		return ramat;
	}


	public void setRamat(Date ramat) {
		this.ramat = ramat;
	}


	public Hang getHang() {
		return hang;
	}

	public void setHang(Hang hang) {
		this.hang = hang;
	}


	public List<CTDDH> getCtddhLSP() {
		return ctddhLSP;
	}


	public void setCtddhLSP(List<CTDDH> ctddhLSP) {
		this.ctddhLSP = ctddhLSP;
	}


	public List<CTGiamGia> getCtGiamGiaLSP() {
		return ctGiamGiaLSP;
	}


	public void setCtGiamGiaLSP(List<CTGiamGia> ctGiamGiaLSP) {
		this.ctGiamGiaLSP = ctGiamGiaLSP;
	}


	public List<SanPham> getSanPhams() {
		return sanPhams;
	}


	public void setSanPhams(List<SanPham> sanPhams) {
		this.sanPhams = sanPhams;
	}


//	public List<BinhLuan> getBinhLuansLSP() {
//		return binhLuansLSP;
//	}


//	public void setBinhLuansLSP(List<BinhLuan> binhLuansLSP) {
//		this.binhLuansLSP = binhLuansLSP;
//	}


	public List<CungCap> getCungCapsLSP() {
		return cungCapsLSP;
	}


	public void setCungCapsLSP(List<CungCap> cungCapsLSP) {
		this.cungCapsLSP = cungCapsLSP;
	}


	public List<ThayDoiGia> getThayDoiGiasLSP() {
		return thayDoiGiasLSP;
	}


	public void setThayDoiGiasLSP(List<ThayDoiGia> thayDoiGiasLSP) {
		this.thayDoiGiasLSP = thayDoiGiasLSP;
	}


	public List<CTGH> getCtghLSP() {
		return ctghLSP;
	}


	public void setCtghLSP(List<CTGH> ctghLSP) {
		this.ctghLSP = ctghLSP;
	}


	public List<DanhGia> getDanhGias() {
		return danhGias;
	}


	public void setDanhGias(List<DanhGia> danhGias) {
		this.danhGias = danhGias;
	}
	
	
}
