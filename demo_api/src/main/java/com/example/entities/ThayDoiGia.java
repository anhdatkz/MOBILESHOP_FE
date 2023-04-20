package com.example.entities;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="THAYDOIGIA")
public class ThayDoiGia {
	@EmbeddedId
	private ThayDoiGiaKeys id;
	private float giamoi;
	
	@ManyToOne
	@MapsId("MANV")
	@JoinColumn(name="manv")
	@JsonIgnore
	private NhanVien nhanVienTDG;
	
	@ManyToOne
	@MapsId("MALOAI")
	@JoinColumn(name="maloai")
	@JsonIgnore
	private LoaiSanPham loaiSanPhamTDG;

	public ThayDoiGia() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ThayDoiGiaKeys getId() {
		return id;
	}

	public void setId(ThayDoiGiaKeys id) {
		this.id = id;
	}

	public float getGiamoi() {
		return giamoi;
	}

	public void setGiamoi(float giamoi) {
		this.giamoi = giamoi;
	}

	public NhanVien getNhanVienTDG() {
		return nhanVienTDG;
	}

	public void setNhanVienTDG(NhanVien nhanVienTDG) {
		this.nhanVienTDG = nhanVienTDG;
	}

	public LoaiSanPham getLoaiSanPhamTDG() {
		return loaiSanPhamTDG;
	}

	public void setLoaiSanPhamTDG(LoaiSanPham loaiSanPhamTDG) {
		this.loaiSanPhamTDG = loaiSanPhamTDG;
	}

}
