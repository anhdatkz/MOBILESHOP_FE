package com.example.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name ="RAM")
public class Ram {
	@Id
	@Column(name="MARAM")
	private Integer maram;
	private String dungluong;
	
	@OneToMany(mappedBy="ram")
	@JsonIgnore
	private List<LoaiSanPham> loaiSanPhams;
	
	public Integer getMaram() {
		return maram;
	}
	public void setMaram(Integer maram) {
		this.maram = maram;
	}
	public String getDungluong() {
		return dungluong;
	}
	public void setDungluong(String dungluong) {
		this.dungluong = dungluong;
	}
	public Ram(Integer maram, String dungluong, List<LoaiSanPham> loaiSanPhams) {
		super();
		this.maram = maram;
		this.dungluong = dungluong;
		this.loaiSanPhams = loaiSanPhams;
	}
	public List<LoaiSanPham> getloaiSanPhams() {
		return loaiSanPhams;
	}
	public void setloaiSanPhams(List<LoaiSanPham> loaiSanPhams) {
		this.loaiSanPhams = loaiSanPhams;
	}
	public Ram() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
