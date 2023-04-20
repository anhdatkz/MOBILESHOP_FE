package com.example.payload;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class DanhGiaRequest {
	private String cmnd;
	private String maloai;
	private Integer diem;
	private String mota;
	private Date ngaybinhluan;
	public String getCmnd() {
		return cmnd;
	}
	public void setCmnd(String cmnd) {
		this.cmnd = cmnd;
	}
	public String getMaloai() {
		return maloai;
	}
	public void setMaloai(String maloai) {
		this.maloai = maloai;
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
	public DanhGiaRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cmnd == null) ? 0 : cmnd.hashCode());
		result = prime * result + ((diem == null) ? 0 : diem.hashCode());
		result = prime * result + ((maloai == null) ? 0 : maloai.hashCode());
		result = prime * result + ((mota == null) ? 0 : mota.hashCode());
		result = prime * result + ((ngaybinhluan == null) ? 0 : ngaybinhluan.hashCode());
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
		DanhGiaRequest other = (DanhGiaRequest) obj;
		if (cmnd == null) {
			if (other.cmnd != null)
				return false;
		} else if (!cmnd.equals(other.cmnd))
			return false;
		if (diem == null) {
			if (other.diem != null)
				return false;
		} else if (!diem.equals(other.diem))
			return false;
		if (maloai == null) {
			if (other.maloai != null)
				return false;
		} else if (!maloai.equals(other.maloai))
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
		return true;
	}
	
	
}
