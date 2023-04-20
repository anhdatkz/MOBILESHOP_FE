package com.example.entities;

import java.io.Serializable;

import javax.persistence.Column;

public class CTGHKeys implements Serializable{
	@Column(name="IDGIOHANG")
	Integer idgiohangctgh;
	@Column(name="MALOAI")
	String maloaictgh;
	
	
	public CTGHKeys() {
		super();
		// TODO Auto-generated constructor stub
	}


	public CTGHKeys(Integer idgiohangctgh, String maloaictgh) {
		super();
		this.idgiohangctgh = idgiohangctgh;
		this.maloaictgh = maloaictgh;
	}


	public Integer getIdgiohangctgh() {
		return idgiohangctgh;
	}


	public void setIdgiohangctgh(Integer idgiohangctgh) {
		this.idgiohangctgh = idgiohangctgh;
	}


	public String getMaloaictgh() {
		return maloaictgh;
	}


	public void setMaloaictgh(String maloaictgh) {
		this.maloaictgh = maloaictgh;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idgiohangctgh == null) ? 0 : idgiohangctgh.hashCode());
		result = prime * result + ((maloaictgh == null) ? 0 : maloaictgh.hashCode());
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
		CTGHKeys other = (CTGHKeys) obj;
		if (idgiohangctgh == null) {
			if (other.idgiohangctgh != null)
				return false;
		} else if (!idgiohangctgh.equals(other.idgiohangctgh))
			return false;
		if (maloaictgh == null) {
			if (other.maloaictgh != null)
				return false;
		} else if (!maloaictgh.equals(other.maloaictgh))
			return false;
		return true;
	}
	
}
