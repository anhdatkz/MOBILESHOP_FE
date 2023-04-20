package com.example.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="QUYEN")
public class Quyen {
	@Id
	@Column(name="MAQUYEN")
	private String maquyen;
	private String tenquyen;
	
	@OneToMany(mappedBy="quyen")
	@JsonIgnore
	private List<TaiKhoan> taiKhoans;

	public Quyen() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Quyen(String maquyen, String tenquyen, List<TaiKhoan> taiKhoans) {
		super();
		this.maquyen = maquyen;
		this.tenquyen = tenquyen;
		this.taiKhoans = taiKhoans;
	}

	public String getMaquyen() {
		return maquyen;
	}

	public void setMaquyen(String maquyen) {
		this.maquyen = maquyen;
	}

	public String getTenquyen() {
		return tenquyen;
	}

	public void setTenquyen(String tenquyen) {
		this.tenquyen = tenquyen;
	}

	public List<TaiKhoan> getTaiKhoans() {
		return taiKhoans;
	}

	public void setTaiKhoans(List<TaiKhoan> taiKhoans) {
		this.taiKhoans = taiKhoans;
	}
	
	
}
