package com.example.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CTGiamGiaKeys implements Serializable{
	@Column(name="MADOT")
	String madotctgg;
	@Column(name="MALOAI")
	String maloaictgg;
	public CTGiamGiaKeys() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getMadotctgg() {
		return madotctgg;
	}
	public void setMadotctgg(String madotctgg) {
		this.madotctgg = madotctgg;
	}
	public String getMaloaictgg() {
		return maloaictgg;
	}
	public void setMaloaictgg(String maloaictgg) {
		this.maloaictgg = maloaictgg;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((madotctgg == null) ? 0 : madotctgg.hashCode());
		result = prime * result + ((maloaictgg == null) ? 0 : maloaictgg.hashCode());
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
		CTGiamGiaKeys other = (CTGiamGiaKeys) obj;
		if (madotctgg == null) {
			if (other.madotctgg != null)
				return false;
		} else if (!madotctgg.equals(other.madotctgg))
			return false;
		if (maloaictgg == null) {
			if (other.maloaictgg != null)
				return false;
		} else if (!maloaictgg.equals(other.maloaictgg))
			return false;
		return true;
	}
	
	
}
