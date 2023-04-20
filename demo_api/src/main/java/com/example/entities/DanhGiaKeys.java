package com.example.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class DanhGiaKeys implements Serializable{
	@Column(name="CMND")
	String cmnddg;
	@Column(name="MALOAI")
	String maloaidg;
	public DanhGiaKeys() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getCmnddg() {
		return cmnddg;
	}
	public void setCmnddg(String cmnddg) {
		this.cmnddg = cmnddg;
	}
	public String getMaloaidg() {
		return maloaidg;
	}
	public void setMaloaidg(String maloaidg) {
		this.maloaidg = maloaidg;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cmnddg == null) ? 0 : cmnddg.hashCode());
		result = prime * result + ((maloaidg == null) ? 0 : maloaidg.hashCode());
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
		DanhGiaKeys other = (DanhGiaKeys) obj;
		if (cmnddg == null) {
			if (other.cmnddg != null)
				return false;
		} else if (!cmnddg.equals(other.cmnddg))
			return false;
		if (maloaidg == null) {
			if (other.maloaidg != null)
				return false;
		} else if (!maloaidg.equals(other.maloaidg))
			return false;
		return true;
	}
	
	
}
