package com.example.payload;

import java.util.Date;

public class LoaiSanPhamRequest {
	private String maloai;
	private String tenloai;
	private String anh;
	private String mota;
	private String chip;
	private Integer ram;
	private Integer rom;
	private String hedieuhanh;
	private Integer pin;
	private String manhinh;
	private Integer soluongton;
	private Integer thoigianbh;
	private String mahang;
	private Integer gia;
	private Date ramat;
	
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
	public Integer getRam() {
		return ram;
	}
	public void setRam(Integer ram) {
		this.ram = ram;
	}
	public Integer getRom() {
		return rom;
	}
	public void setRom(Integer rom) {
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
	
	public Date getRamat() {
		return ramat;
	}
	public void setRamat(Date ramat) {
		this.ramat = ramat;
	}
	public LoaiSanPhamRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
