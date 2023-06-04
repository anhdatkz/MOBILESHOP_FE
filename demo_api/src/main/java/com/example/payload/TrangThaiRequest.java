package com.example.payload;

public class TrangThaiRequest {
	private Integer madh;
	private Integer matrangthai;
	public Integer getMadh() {
		return madh;
	}
	public void setMadh(Integer madh) {
		this.madh = madh;
	}
	public Integer getMatrangthai() {
		return matrangthai;
	}
	public void setMatrangthai(Integer matrangthai) {
		this.matrangthai = matrangthai;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((madh == null) ? 0 : madh.hashCode());
		result = prime * result + ((matrangthai == null) ? 0 : matrangthai.hashCode());
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
		TrangThaiRequest other = (TrangThaiRequest) obj;
		if (madh == null) {
			if (other.madh != null)
				return false;
		} else if (!madh.equals(other.madh))
			return false;
		if (matrangthai == null) {
			if (other.matrangthai != null)
				return false;
		} else if (!matrangthai.equals(other.matrangthai))
			return false;
		return true;
	}
	public TrangThaiRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
