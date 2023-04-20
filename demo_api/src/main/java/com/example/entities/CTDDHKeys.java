package com.example.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CTDDHKeys implements Serializable{
	@Column(name="MADDH")
	String maddhctdh;
	@Column(name="MALOAI")
	String maloaictdh;
	public CTDDHKeys() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getMaddhctdh() {
		return maddhctdh;
	}
	public void setMaddhctdh(String maddhctdh) {
		this.maddhctdh = maddhctdh;
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
		result = prime * result + ((maddhctdh == null) ? 0 : maddhctdh.hashCode());
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
		CTDDHKeys other = (CTDDHKeys) obj;
		if (maddhctdh == null) {
			if (other.maddhctdh != null)
				return false;
		} else if (!maddhctdh.equals(other.maddhctdh))
			return false;
		if (maloaictdh == null) {
			if (other.maloaictdh != null)
				return false;
		} else if (!maloaictdh.equals(other.maloaictdh))
			return false;
		return true;
	}
	
	
}
