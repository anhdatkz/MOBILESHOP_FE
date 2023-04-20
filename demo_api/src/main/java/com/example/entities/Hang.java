package com.example.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "HANG")
public class Hang {
	@Id
	@Column(name = "MAHANG")
	private String mahang;
	private String tenhang;
	private String anh;
	
	@OneToMany(mappedBy="hang")
	@JsonIgnore
	private List<LoaiSanPham> loaiSanPhams;

	public String getMahang() {
		return mahang;
	}

	public void setMahang(String mahang) {
		this.mahang = mahang;
	}

	public String getTenhang() {
		return tenhang;
	}

	public void setTenhang(String tenhang) {
		this.tenhang = tenhang;
	}

	public String getAnh() {
		return anh;
	}

	public void setAnh(String anh) {
		this.anh = anh;
	}

	public List<LoaiSanPham> getLoaiSanPhams() {
		return loaiSanPhams;
	}

	public void setLoaiSanPhams(List<LoaiSanPham> loaiSanPhams) {
		this.loaiSanPhams = loaiSanPhams;
	}
	
	
	
}
