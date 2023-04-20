package com.example.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CTDHKeys implements Serializable{
	@Column(name="MADH")
	Integer madhctdh;
	@Column(name="MALOAI")
	String maloaictdh;
	public Integer getMadhctdh() {
		return madhctdh;
	}
	public void setMadhctdh(Integer madhctdh) {
		this.madhctdh = madhctdh;
	}
	public String getMaloaictdh() {
		return maloaictdh;
	}
	public void setMaloaictdh(String maloaictdh) {
		this.maloaictdh = maloaictdh;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((madhctdh == null) ? 0 : madhctdh.hashCode());
		result = prime * result + ((maloaictdh == null) ? 0 : maloaictdh.hashCode());
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
		CTDHKeys other = (CTDHKeys) obj;
		if (madhctdh == null) {
			if (other.madhctdh != null)
				return false;
		} else if (!madhctdh.equals(other.madhctdh))
			return false;
		if (maloaictdh == null) {
			if (other.maloaictdh != null)
				return false;
		} else if (!maloaictdh.equals(other.maloaictdh))
			return false;
		return true;
	}
	
	
}
