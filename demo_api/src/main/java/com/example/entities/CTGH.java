package com.example.entities;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "CTGIOHANG")
public class CTGH {
	@EmbeddedId
	private CTGHKeys id;
	private Integer soluong;
	private float tong;

	
	@ManyToOne
	@MapsId("IDGIOHANG")
	@JsonIgnore
	@JoinColumn(name="IDGIOHANG")
	private GioHang gioHangCTGH;
	
	@ManyToOne
	@JsonIgnore
	@MapsId("MALOAI")
	@JoinColumn(name="MALOAI")
	private LoaiSanPham loaiSanPhamCTGH;

	public CTGHKeys getId() {
		return id;
	}

	public void setId(CTGHKeys id) {
		this.id = id;
	}

	public Integer getSoluong() {
		return soluong;
	}

	public void setSoluong(Integer soluong) {
		this.soluong = soluong;
	}

	public float getTong() {
		return tong;
	}

	public void setTong(float tong) {
		this.tong = tong;
	}

	public GioHang getGioHangCTGH() {
		return gioHangCTGH;
	}

	public void setGioHangCTGH(GioHang gioHangCTGH) {
		this.gioHangCTGH = gioHangCTGH;
	}

	public LoaiSanPham getLoaiSanPhamCTGH() {
		return loaiSanPhamCTGH;
	}

	public void setLoaiSanPhamCTGH(LoaiSanPham loaiSanPhamCTGH) {
		this.loaiSanPhamCTGH = loaiSanPhamCTGH;
	}

	public CTGH() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CTGH(CTGHKeys id, Integer soluong, float tong, GioHang gioHangCTGH, LoaiSanPham loaiSanPhamCTGH) {
		super();
		this.id = id;
		this.soluong = soluong;
		this.tong = tong;
		this.gioHangCTGH = gioHangCTGH;
		this.loaiSanPhamCTGH = loaiSanPhamCTGH;
	}
	
	
}
