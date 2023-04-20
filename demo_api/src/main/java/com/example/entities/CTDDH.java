package com.example.entities;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name="CTDDH")
public class CTDDH {
	@EmbeddedId
	private CTDDHKeys id;
	private Integer soluong;
	private float tonggia;
	
	@ManyToOne
	@MapsId("maddhctdh")
	@JoinColumn(name="maddh")
	private DatHang datHangCTDH;
	
	@ManyToOne
	@MapsId("maloaictdh")
	@JoinColumn(name="maloai")
	private LoaiSanPham loaiSanPhamCTDH;

	public CTDDH() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CTDDHKeys getId() {
		return id;
	}

	public void setId(CTDDHKeys id) {
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

	public DatHang getDatHangCTDH() {
		return datHangCTDH;
	}

	public void setDatHangCTDH(DatHang datHangCTDH) {
		this.datHangCTDH = datHangCTDH;
	}

	public LoaiSanPham getLoaiSanPhamCTDH() {
		return loaiSanPhamCTDH;
	}

	public void setLoaiSanPhamCTDH(LoaiSanPham loaiSanPhamCTDH) {
		this.loaiSanPhamCTDH = loaiSanPhamCTDH;
	}

}
