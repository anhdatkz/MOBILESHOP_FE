package com.example.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ThayDoiGiaKeys implements Serializable{
	@Column(name="MANV")
	String manvthaydoi;
	@Column(name="MALOAI")
	String maloaithaydoi;
	@Column(name="NGAYAPDUNG")
	Date ngayapdung;
	public ThayDoiGiaKeys() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getManvthaydoi() {
		return manvthaydoi;
	}
	public void setManvthaydoi(String manvthaydoi) {
		this.manvthaydoi = manvthaydoi;
	}
	public String getMaloaithaydoi() {
		return maloaithaydoi;
	}
	public void setMaloaithaydoi(String maloaithaydoi) {
		this.maloaithaydoi = maloaithaydoi;
	}
	public Date getNgayapdung() {
		return ngayapdung;
	}
	public void setNgayapdung(Date ngayapdung) {
		this.ngayapdung = ngayapdung;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((maloaithaydoi == null) ? 0 : maloaithaydoi.hashCode());
		result = prime * result + ((manvthaydoi == null) ? 0 : manvthaydoi.hashCode());
		result = prime * result + ((ngayapdung == null) ? 0 : ngayapdung.hashCode());
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
		ThayDoiGiaKeys other = (ThayDoiGiaKeys) obj;
		if (maloaithaydoi == null) {
			if (other.maloaithaydoi != null)
				return false;
		} else if (!maloaithaydoi.equals(other.maloaithaydoi))
			return false;
		if (manvthaydoi == null) {
			if (other.manvthaydoi != null)
				return false;
		} else if (!manvthaydoi.equals(other.manvthaydoi))
			return false;
		if (ngayapdung == null) {
			if (other.ngayapdung != null)
				return false;
		} else if (!ngayapdung.equals(other.ngayapdung))
			return false;
		return true;
	}
	
	
}
