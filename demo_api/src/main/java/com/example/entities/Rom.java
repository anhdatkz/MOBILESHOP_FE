package com.example.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name ="ROM")
public class Rom {
	@Id
	@Column(name="MAROM")
	private Integer marom;
	private String dungluong;
	
	@OneToMany(mappedBy="rom")
	@JsonIgnore
	private List<LoaiSanPham> loaiSanPhams;
	
	public Integer getMarom() {
		return marom;
	}
	public void setMarom(Integer marom) {
		this.marom = marom;
	}
	public String getDungluong() {
		return dungluong;
	}
	public void setDungluong(String dungluong) {
		this.dungluong = dungluong;
	}
	public List<LoaiSanPham> getloaiSanPhams() {
		return loaiSanPhams;
	}
	public void setloaiSanPhams(List<LoaiSanPham> loaiSanPhams) {
		this.loaiSanPhams = loaiSanPhams;
	}
	public Rom() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Rom(Integer marom, String dungluong, List<LoaiSanPham> loaiSanPhams) {
		super();
		this.marom = marom;
		this.dungluong = dungluong;
		this.loaiSanPhams = loaiSanPhams;
	}
	
	
}
