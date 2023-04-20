package com.example.entities;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="CTDH")
public class CTDH {
	@EmbeddedId
	private CTDHKeys id;
	private Integer soluong;
	private float tonggia;
	
	@ManyToOne
	@MapsId("MADH")
	@JsonIgnore
	@JoinColumn(name="MADH")
	private DonHang donHangCTDH;
	
	@ManyToOne
	@MapsId("MALOAI")
	@JsonIgnore
	@JoinColumn(name="MALOAI")
	private LoaiSanPham loaiSanPhamCTDH;

	public CTDHKeys getId() {
		return id;
	}

	public void setId(CTDHKeys id) {
		this.id = id;
	}

	public Integer getSoluong() {
		return soluong;
	}

	public void setSoluong(Integer soluong) {
		this.soluong = soluong;
	}

	public float getTonggia() {
		return tonggia;
	}

	public void setTonggia(float tonggia) {
		this.tonggia = tonggia;
	}

	public DonHang getDonHangCTDH() {
		return donHangCTDH;
	}

	public void setDonHangCTDH(DonHang donHangCTDH) {
		this.donHangCTDH = donHangCTDH;
	}

	public LoaiSanPham getLoaiSanPhamCTDH() {
		return loaiSanPhamCTDH;
	}

	public void setLoaiSanPhamCTDH(LoaiSanPham loaiSanPhamCTDH) {
		this.loaiSanPhamCTDH = loaiSanPhamCTDH;
	}
	
	
}
