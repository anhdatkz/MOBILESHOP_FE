package com.example.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CungCapKeys implements Serializable{
	@Column(name="MANHACC")
	String manhacccungcap;
	@Column(name="MALOAI")
	String maloaicc;
	public CungCapKeys() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getManhacccungcap() {
		return manhacccungcap;
	}
	public void setManhacccungcap(String manhacccungcap) {
		this.manhacccungcap = manhacccungcap;
	}
	public String getMaloaicc() {
		return maloaicc;
	}
	public void setMaloaicc(String maloaicc) {
		this.maloaicc = maloaicc;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((maloaicc == null) ? 0 : maloaicc.hashCode());
		result = prime * result + ((manhacccungcap == null) ? 0 : manhacccungcap.hashCode());
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
		CungCapKeys other = (CungCapKeys) obj;
		if (maloaicc == null) {
			if (other.maloaicc != null)
				return false;
		} else if (!maloaicc.equals(other.maloaicc))
			return false;
		if (manhacccungcap == null) {
			if (other.manhacccungcap != null)
				return false;
		} else if (!manhacccungcap.equals(other.manhacccungcap))
			return false;
		return true;
	}
	
	
}
