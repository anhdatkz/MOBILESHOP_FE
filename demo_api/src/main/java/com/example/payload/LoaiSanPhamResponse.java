package com.example.payload;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.example.entities.CTGiamGia;
import com.fasterxml.jackson.annotation.JsonFormat;

public class LoaiSanPhamResponse {
	private String maloai;
	private String tenloai;
	private String anh;
	private String mota;
	private String chip;
	private String ram;
	private String rom;
	private String hedieuhanh;
	private Integer pin;
	private String manhinh;
	private Integer soluongton;
	private Integer thoigianbh;
	private String mahang;
	@DateTimeFormat(pattern = "MM-yyyy")
	@JsonFormat(pattern = "MM-yyyy", timezone = "GMT+7")
	private Date ramat;
	private List<CTGiamGia> giamgia;
	private Integer gia;
	
	public Date getRamat() {
		return ramat;
	}
	public void setRamat(Date ramat) {
		this.ramat = ramat;
	}
	public List<CTGiamGia> getGiamgia() {
		return giamgia;
	}
	public void setGiamgia(List<CTGiamGia> giamgia) {
		this.giamgia = giamgia;
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
	public String getRam() {
		return ram;
	}
	public void setRam(String ram) {
		this.ram = ram;
	}
	public String getRom() {
		return rom;
	}
	public void setRom(String rom) {
		this.rom = rom;
	}
	public String getHedieuhanh() {
		return hedieuhanh;
	}
	public void setHedieuhanh(String hedieuhanh) {
		this.hedieuhanh = hedieuhanh;
	}
	public Integer getPin() {
		return pin;
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
	public String getMahang() {
		return mahang;
	}
	public void setMahang(String mahang) {
		this.mahang = mahang;
	}
	public Integer getGia() {
		return gia;
	}
	public void setGia(Integer gia) {
		this.gia = gia;
	}
	
}
