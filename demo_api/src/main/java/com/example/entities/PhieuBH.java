package com.example.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="PHIEUBAOHANH")
public class PhieuBH {
	@Id
	@Column(name="MASOBH")
	private String masobh;
	private Date ngaybd;
	private Date ngaykt;
	
	@OneToMany(mappedBy="phieuBHCT")
	@JsonIgnore
	private List<CTNhanBH> ctNhanBHs;
	
	@OneToOne(mappedBy="phieuBHSP")
	private SanPham sanPhamBH;

	public PhieuBH() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getMasobh() {
		return masobh;
	}

	public void setMasobh(String masobh) {
		this.masobh = masobh;
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

	public List<CTNhanBH> getCtNhanBHs() {
		return ctNhanBHs;
	}

	public void setCtNhanBHs(List<CTNhanBH> ctNhanBHs) {
		this.ctNhanBHs = ctNhanBHs;
	}

	public SanPham getSanPhamBH() {
		return sanPhamBH;
	}

	public void setSanPhamBH(SanPham sanPhamBH) {
		this.sanPhamBH = sanPhamBH;
	}

	
	
}
