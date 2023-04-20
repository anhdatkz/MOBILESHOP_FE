package com.example.entities;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name="CUNGCAP")
public class CungCap {
	@EmbeddedId
	private CungCapKeys id;
	
	@ManyToOne
	@MapsId("manhacccungcap")
	@JoinColumn(name="manhacc")
	private NhaCC nhaCC;
	
	@ManyToOne
	@MapsId("maloaicc")
	@JoinColumn(name="maloai")
	private LoaiSanPham loaiSanPhamCC;

	public CungCap() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CungCap(CungCapKeys id, NhaCC nhaCC, LoaiSanPham loaiSanPhamCC) {
		super();
		this.id = id;
		this.nhaCC = nhaCC;
		this.loaiSanPhamCC = loaiSanPhamCC;
	}

	public CungCapKeys getId() {
		return id;
	}

	public void setId(CungCapKeys id) {
		this.id = id;
	}

	public NhaCC getNhaCC() {
		return nhaCC;
	}

	public void setNhaCC(NhaCC nhaCC) {
		this.nhaCC = nhaCC;
	}

	public LoaiSanPham getLoaiSanPhamCC() {
		return loaiSanPhamCC;
	}

	public void setLoaiSanPhamCC(LoaiSanPham loaiSanPhamCC) {
		this.loaiSanPhamCC = loaiSanPhamCC;
	}
	
	
}
