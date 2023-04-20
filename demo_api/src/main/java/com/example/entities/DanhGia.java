 package com.example.entities;

import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
 @Table(name="DANHGIA")
public class DanhGia {
	@EmbeddedId
	private DanhGiaKeys id;
	private Integer diem;
	private String mota;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@JsonFormat(pattern = "dd-MM-yyyy", timezone = "GMT+7")
	private Date ngaybinhluan;
	
	@ManyToOne
	@MapsId("MALOAI")
	@JsonIgnore
	@JoinColumn(name="MALOAI")
	private LoaiSanPham loaiSanPhamDG;
	
	@ManyToOne
	@MapsId("CMND")
	@JsonIgnore
	@JoinColumn(name="CMND")
	private KhachHang khachHangDG;

	public DanhGiaKeys getId() {
		return id;
	}

	public void setId(DanhGiaKeys id) {
		this.id = id;
	}

	public Integer getDiem() {
		return diem;
	}

	public void setDiem(Integer diem) {
		this.diem = diem;
	}

	public String getMota() {
		return mota;
	}

	public void setMota(String mota) {
		this.mota = mota;
	}

	public Date getNgaybinhluan() {
		return ngaybinhluan;
	}

	public void setNgaybinhluan(Date ngaybinhluan) {
		this.ngaybinhluan = ngaybinhluan;
	}

	public LoaiSanPham getLoaiSanPhamDG() {
		return loaiSanPhamDG;
	}

	public void setLoaiSanPhamDG(LoaiSanPham loaiSanPhamDG) {
		this.loaiSanPhamDG = loaiSanPhamDG;
	}

	public KhachHang getKhachHangDG() {
		return khachHangDG;
	}

	public void setKhachHangDG(KhachHang khachHangDG) {
		this.khachHangDG = khachHangDG;
	}

	public DanhGia() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DanhGia(DanhGiaKeys id, Integer diem, String mota, Date ngaybinhluan, LoaiSanPham loaiSanPhamDG,
			KhachHang khachHangDG) {
		super();
		this.id = id;
		this.diem = diem;
		this.mota = mota;
		this.ngaybinhluan = ngaybinhluan;
		this.loaiSanPhamDG = loaiSanPhamDG;
		this.khachHangDG = khachHangDG;
	}
	
	
	
	
}
