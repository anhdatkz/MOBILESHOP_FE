package com.example.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="DOTGIAMGIA")
public class DotGiamGia {
	@Id
	@Column(name="MADOT")
	private String madot;
	private String noidung;
	private Date ngaybd;
	private Date ngaykt;
	
	@OneToMany(mappedBy="dotGiamGiaCTGG")
	@JsonIgnore
	private List<CTGiamGia> ctGiamGiasDGG;

	public DotGiamGia() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DotGiamGia(String madot, String noidung, Date ngaybd, Date ngaykt, List<CTGiamGia> ctGiamGiasDGG) {
		super();
		this.madot = madot;
		this.noidung = noidung;
		this.ngaybd = ngaybd;
		this.ngaykt = ngaykt;
		this.ctGiamGiasDGG = ctGiamGiasDGG;
	}

	public String getMadot() {
		return madot;
	}

	public void setMadot(String madot) {
		this.madot = madot;
	}

	public String getNoidung() {
		return noidung;
	}

	public void setNoidung(String noidung) {
		this.noidung = noidung;
	}

	public Date getNgaybd() {
		return ngaybd;
	}

	public void setNgaybd(Date ngaybd) {
		this.ngaybd = ngaybd;
	}

	public Date getNgaykt() {
		return ngaykt;
	}

	public void setNgaykt(Date ngaykt) {
		this.ngaykt = ngaykt;
	}

	public List<CTGiamGia> getCtGiamGiasDGG() {
		return ctGiamGiasDGG;
	}

	public void setCtGiamGiasDGG(List<CTGiamGia> ctGiamGiasDGG) {
		this.ctGiamGiasDGG = ctGiamGiasDGG;
	}
	
	
}
