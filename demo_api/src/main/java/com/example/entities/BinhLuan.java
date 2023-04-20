package com.example.entities;

import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name="BINHLUAN")
public class BinhLuan {
	@EmbeddedId
	private BinhLuanKeys id;
	private String noidung;
	private Integer diem;
	private Date ngaybl;
	
	@ManyToOne
	@MapsId("maloaibl")
	@JoinColumn(name="maloai")
	private LoaiSanPham loaiSanPhamBL;
	
	@ManyToOne
	@MapsId("makhbl")
	@JoinColumn(name="makh")
	private KhachHang khachHangBL;

	public BinhLuan() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BinhLuanKeys getId() {
		return id;
	}

	public void setId(BinhLuanKeys id) {
		this.id = id;
	}

	public String getNoidung() {
		return noidung;
	}

	public void setNoidung(String noidung) {
		this.noidung = noidung;
	}

	public Integer getDiem() {
		return diem;
	}

	public void setDiem(Integer diem) {
		this.diem = diem;
	}

	public Date getNgaybl() {
		return ngaybl;
	}

	public void setNgaybl(Date ngaybl) {
		this.ngaybl = ngaybl;
	}

	public LoaiSanPham getLoaiSanPhamBL() {
		return loaiSanPhamBL;
	}

	public void setLoaiSanPhamBL(LoaiSanPham loaiSanPhamBL) {
		this.loaiSanPhamBL = loaiSanPhamBL;
	}

	public KhachHang getKhachHangBL() {
		return khachHangBL;
	}

	public void setKhachHangBL(KhachHang khachHangBL) {
		this.khachHangBL = khachHangBL;
	}
	
	
}
