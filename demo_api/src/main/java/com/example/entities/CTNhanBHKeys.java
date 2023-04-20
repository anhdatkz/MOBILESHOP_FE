package com.example.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CTNhanBHKeys implements Serializable{
	@Column(name="MASOBH")
	String masobhct;
	@Column(name="MANV")
	String manvnhan;
	@Column(name="NGAYNHAN")
	Date ngaynhan;
	public CTNhanBHKeys() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getMasobhct() {
		return masobhct;
	}
	public void setMasobhct(String masobhct) {
		this.masobhct = masobhct;
	}
	public String getManvnhan() {
		return manvnhan;
	}
	public void setManvnhan(String manvnhan) {
		this.manvnhan = manvnhan;
	}
	public Date getNgaynhan() {
		return ngaynhan;
	}
	public void setNgaynhan(Date ngaynhan) {
		this.ngaynhan = ngaynhan;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((manvnhan == null) ? 0 : manvnhan.hashCode());
		result = prime * result + ((masobhct == null) ? 0 : masobhct.hashCode());
		result = prime * result + ((ngaynhan == null) ? 0 : ngaynhan.hashCode());
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
		CTNhanBHKeys other = (CTNhanBHKeys) obj;
		if (manvnhan == null) {
			if (other.manvnhan != null)
				return false;
		} else if (!manvnhan.equals(other.manvnhan))
			return false;
		if (masobhct == null) {
			if (other.masobhct != null)
				return false;
		} else if (!masobhct.equals(other.masobhct))
			return false;
		if (ngaynhan == null) {
			if (other.ngaynhan != null)
				return false;
		} else if (!ngaynhan.equals(other.ngaynhan))
			return false;
		return true;
	}
	
	
}
