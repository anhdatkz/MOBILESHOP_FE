package com.example.payload;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class DanhGiaResponse {
	private String tenkh;
	private String tenloai;
	private Integer diem;
	private String mota;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date ngaybinhluan;
	public String getTenkh() {
		return tenkh;
	}
	public void setTenkh(String tenkh) {
		this.tenkh = tenkh;
	}
	public String getTenloai() {
		return tenloai;
	}
	public void setTenloai(String tenloai) {
		this.tenloai = tenloai;
	}
	public Integer getDiem() {
		return diem;
	}
	public void setDiem(Integer diem) {
		this.diem = diem;
	}
	public String getMota() {
		return mota;
	}
	public void setMota(String mota) {
		this.mota = mota;
	}
	public Date getNgaybinhluan() {
		return ngaybinhluan;
	}
	public void setNgaybinhluan(Date ngaybinhluan) {
		this.ngaybinhluan = ngaybinhluan;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((diem == null) ? 0 : diem.hashCode());
		result = prime * result + ((mota == null) ? 0 : mota.hashCode());
		result = prime * result + ((ngaybinhluan == null) ? 0 : ngaybinhluan.hashCode());
		result = prime * result + ((tenkh == null) ? 0 : tenkh.hashCode());
		result = prime * result + ((tenloai == null) ? 0 : tenloai.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DanhGiaResponse other = (DanhGiaResponse) obj;
		if (diem == null) {
			if (other.diem != null)
				return false;
		} else if (!diem.equals(other.diem))
			return false;
		if (mota == null) {
			if (other.mota != null)
				return false;
		} else if (!mota.equals(other.mota))
			return false;
		if (ngaybinhluan == null) {
			if (other.ngaybinhluan != null)
				return false;
		} else if (!ngaybinhluan.equals(other.ngaybinhluan))
			return false;
		if (tenkh == null) {
			if (other.tenkh != null)
				return false;
		} else if (!tenkh.equals(other.tenkh))
			return false;
		if (tenloai == null) {
			if (other.tenloai != null)
				return false;
		} else if (!tenloai.equals(other.tenloai))
			return false;
		return true;
	}
	public DanhGiaResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
