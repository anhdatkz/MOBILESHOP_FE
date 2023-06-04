package com.example.payload;

import com.example.entities.NhanVien;

public class NhanVienPhanCongResponse {
	private NhanVien nhanVien;
	private Integer sodon;
	
	public NhanVien getNhanVien() {
		return nhanVien;
	}
	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}
	public Integer getSodon() {
		return sodon;
	}
	public void setSodon(Integer sodon) {
		this.sodon = sodon;
	}
	public NhanVienPhanCongResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
