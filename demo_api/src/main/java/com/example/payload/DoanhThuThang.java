package com.example.payload;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class DoanhThuThang {
	@DateTimeFormat(pattern = "MM-yyyy")
	private Date thang;
	private float tongtien;
	public Date getThang() {
		return thang;
	}
	public void setThang(Date thang) {
		this.thang = thang;
	}
	public float getTongtien() {
		return tongtien;
	}
	public void setTongtien(float tongtien) {
		this.tongtien = tongtien;
	}
	public DoanhThuThang(Date thang, float tongtien) {
		super();
		this.thang = thang;
		this.tongtien = tongtien;
	}
	public DoanhThuThang() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
