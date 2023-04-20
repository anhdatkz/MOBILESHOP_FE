package com.example.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="NHACC")
public class NhaCC {
	@Id
	@Column(name="MANHACC")
	private String manhacc;
	private String tenncc;
	private String diachi;
	private String sdt;
	private String email;
	private String website;
	
	@OneToMany(mappedBy="nhaCC")
	@JsonIgnore
	private List<CungCap> cungCapsncc;
	
	@OneToMany(mappedBy="nhaCCDH")
	@JsonIgnore
	private List<DatHang> datHangsncc;
	
	
	public List<CungCap> getCungCapsncc() {
		return cungCapsncc;
	}
	public void setCungCapsncc(List<CungCap> cungCapsncc) {
		this.cungCapsncc = cungCapsncc;
	}
	public List<DatHang> getDatHangsncc() {
		return datHangsncc;
	}
	public void setDatHangsncc(List<DatHang> datHangsncc) {
		this.datHangsncc = datHangsncc;
	}
	public String getManhacc() {
		return manhacc;
	}
	public void setManhacc(String manhacc) {
		this.manhacc = manhacc;
	}
	public String getTenncc() {
		return tenncc;
	}
	public void setTenncc(String tenncc) {
		this.tenncc = tenncc;
	}
	public String getDiachi() {
		return diachi;
	}
	public void setDiachi(String diachi) {
		this.diachi = diachi;
	}
	public String getSdt() {
		return sdt;
	}
	public void setSdt(String sdt) {
		this.sdt = sdt;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	
	
}
