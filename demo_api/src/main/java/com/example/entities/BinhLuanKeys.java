package com.example.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class BinhLuanKeys implements Serializable{
	@Column(name="MALOAI")
	String maloaibl;
	@Column(name="CMND")
	String cmndbl;
	public BinhLuanKeys() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getMaloaibl() {
		return maloaibl;
	}
	public void setMaloaibl(String maloaibl) {
		this.maloaibl = maloaibl;
	}
	public String getCmndbl() {
		return cmndbl;
	}
	public void setCmndbl(String cmndbl) {
		this.cmndbl = cmndbl;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cmndbl == null) ? 0 : cmndbl.hashCode());
		result = prime * result + ((maloaibl == null) ? 0 : maloaibl.hashCode());
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
		BinhLuanKeys other = (BinhLuanKeys) obj;
		if (cmndbl == null) {
			if (other.cmndbl != null)
				return false;
		} else if (!cmndbl.equals(other.cmndbl))
			return false;
		if (maloaibl == null) {
			if (other.maloaibl != null)
				return false;
		} else if (!maloaibl.equals(other.maloaibl))
			return false;
		return true;
	}
	
	
}
