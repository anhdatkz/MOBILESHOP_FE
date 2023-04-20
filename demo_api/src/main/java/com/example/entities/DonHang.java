package com.example.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="DONHANG")
public class DonHang {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="MADH")
	private Integer madh;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@JsonFormat(pattern = "dd-MM-yyyy", timezone = "GMT+7")
	private Date ngaylap;
	private String tennguoinhan;
	private String diachinhan;
	private String emailnguoinhan;
	private String sdtnguoinhan;
	private float tongtien;
	
	@OneToMany(mappedBy="gioHang")
	@JsonIgnore
	private List<SanPham> sanPhams;
	
	@OneToOne(mappedBy="donHang")
	@JsonIgnore
	private HoaDon hoaDon;
	
	@OneToMany(mappedBy="donHangCTDH")
	@JsonIgnore
	private List<CTDH> ctdhs;
	
	@ManyToOne
	@JoinColumn(name="matrangthai")
	private TrangThai trangThai;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="cmnd")
	private KhachHang khachHang;
	
	@ManyToOne
	@JoinColumn(name="manvduyet")
	@JsonIgnore
	private NhanVien nhanVienDuyet;
	
	@ManyToOne
	@JoinColumn(name = "manvgiao")
	@JsonIgnore
	private NhanVien nhanVienGiao;

	public Integer getMadh() {
		return madh;
	}

	public void setMadh(Integer madh) {
		this.madh = madh;
	}

	public Date getNgayLap() {
		return ngaylap;
	}

	public void setNgayLap(Date ngaylap) {
		this.ngaylap = ngaylap;
	}

	public String getTennguoinhan() {
		return tennguoinhan;
	}

	public void setTennguoinhan(String tennguoinhan) {
		this.tennguoinhan = tennguoinhan;
	}

	public String getDiachinhan() {
		return diachinhan;
	}

	public void setDiachinhan(String diachinhan) {
		this.diachinhan = diachinhan;
	}
	
	public Date getNgaylap() {
		return ngaylap;
	}

	public void setNgaylap(Date ngaylap) {
		this.ngaylap = ngaylap;
	}

	public String getEmailnguoinhan() {
		return emailnguoinhan;
	}

	public void setEmailnguoinhan(String emailnguoinhan) {
		this.emailnguoinhan = emailnguoinhan;
	}

	public List<SanPham> getSanPhams() {
		return sanPhams;
	}

	public void setSanPhams(List<SanPham> sanPhams) {
		this.sanPhams = sanPhams;
	}

	public String getSdtnguoinhan() {
		return sdtnguoinhan;
	}

	public void setSdtnguoinhan(String sdtnguoinhan) {
		this.sdtnguoinhan = sdtnguoinhan;
	}

	public float getTongtien() {
		return tongtien;
	}

	public void setTongtien(float tongtien) {
		this.tongtien = tongtien;
	}

	public HoaDon getHoaDon() {
		return hoaDon;
	}

	public void setHoaDon(HoaDon hoaDon) {
		this.hoaDon = hoaDon;
	}

	public List<CTDH> getCtdhs() {
		return ctdhs;
	}

	public void setCtdhs(List<CTDH> ctdhs) {
		this.ctdhs = ctdhs;
	}

	public TrangThai getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(TrangThai trangThai) {
		this.trangThai = trangThai;
	}

	public KhachHang getKhachHang() {
		return khachHang;
	}

	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}

	public NhanVien getNhanVienDuyet() {
		return nhanVienDuyet;
	}

	public void setNhanVienDuyet(NhanVien nhanVienDuyet) {
		this.nhanVienDuyet = nhanVienDuyet;
	}

	public NhanVien getNhanVienGiao() {
		return nhanVienGiao;
	}

	public void setNhanVienGiao(NhanVien nhanVienGiao) {
		this.nhanVienGiao = nhanVienGiao;
	}

	

	public DonHang(Integer madh, Date ngaylap, String tennguoinhan, String diachinhan, String emailnguoinhan,
			String sdtnguoinhan, float tongtien, List<SanPham> sanPhams, HoaDon hoaDon, List<CTDH> ctdhs,
			TrangThai trangThai, KhachHang khachHang, NhanVien nhanVienDuyet, NhanVien nhanVienGiao) {
		super();
		this.madh = madh;
		this.ngaylap = ngaylap;
		this.tennguoinhan = tennguoinhan;
		this.diachinhan = diachinhan;
		this.emailnguoinhan = emailnguoinhan;
		this.sdtnguoinhan = sdtnguoinhan;
		this.tongtien = tongtien;
		this.sanPhams = sanPhams;
		this.hoaDon = hoaDon;
		this.ctdhs = ctdhs;
		this.trangThai = trangThai;
		this.khachHang = khachHang;
		this.nhanVienDuyet = nhanVienDuyet;
		this.nhanVienGiao = nhanVienGiao;
	}

	public DonHang() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
