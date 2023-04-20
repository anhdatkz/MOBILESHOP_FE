package com.example.entities;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="CTGIAMGIA")
public class CTGiamGia {
	@EmbeddedId
	private CTGiamGiaKeys id;
	private Integer phantram;
	
	@ManyToOne
	@MapsId("madotctgg")
	@JoinColumn(name="madot")
	@JsonIgnore
	private DotGiamGia dotGiamGiaCTGG;
	
	@ManyToOne
	@MapsId("maloaictgg")
	@JoinColumn(name="maloai")
	@JsonIgnore
	private LoaiSanPham loaiSanPhamCTGG;

	public CTGiamGia() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CTGiamGia(CTGiamGiaKeys id, Integer phantram, DotGiamGia dotGiamGiaCTGG, LoaiSanPham loaiSanPhamCTGG) {
		super();
		this.id = id;
		this.phantram = phantram;
		this.dotGiamGiaCTGG = dotGiamGiaCTGG;
		this.loaiSanPhamCTGG = loaiSanPhamCTGG;
	}

	public CTGiamGiaKeys getId() {
		return id;
	}

	public void setId(CTGiamGiaKeys id) {
		this.id = id;
	}

	public Integer getPhantram() {
		return phantram;
	}

	public void setPhantram(Integer phantram) {
		this.phantram = phantram;
	}

	public DotGiamGia getDotGiamGiaCTGG() {
		return dotGiamGiaCTGG;
	}

	public void setDotGiamGiaCTGG(DotGiamGia dotGiamGiaCTGG) {
		this.dotGiamGiaCTGG = dotGiamGiaCTGG;
	}

	public LoaiSanPham getLoaiSanPhamCTGG() {
		return loaiSanPhamCTGG;
	}

	public void setLoaiSanPhamCTGG(LoaiSanPham loaiSanPhamCTGG) {
		this.loaiSanPhamCTGG = loaiSanPhamCTGG;
	}
	
	
}
