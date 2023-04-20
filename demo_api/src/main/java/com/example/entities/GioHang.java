package com.example.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
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
@Table(name="GIOHANG")
public class GioHang {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="IDGIOHANG")
	private Integer idgiohang;
	
	@OneToMany(mappedBy="gioHangCTGH")
	@JsonIgnore
	private List<CTGH> ctghGH;
	
	@OneToOne(cascade=CascadeType.ALL)
//	@JsonIgnore
	@JoinColumn(name="CMND", referencedColumnName = "cmnd")
	private KhachHang khachHangGH;
	
	public GioHang() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getIdgiohang() {
		return idgiohang;
	}

	public void setIdgiohang(Integer idgiohang) {
		this.idgiohang = idgiohang;
	}

	public List<CTGH> getCtghGH() {
		return ctghGH;
	}

	public void setCtghGH(List<CTGH> ctghGH) {
		this.ctghGH = ctghGH;
	}

	public KhachHang getKhachHangGH() {
		return khachHangGH;
	}

	public void setKhachHangGH(KhachHang khachHangGH) {
		this.khachHangGH = khachHangGH;
	}

	public GioHang(Integer idgiohang, List<CTGH> ctghGH, KhachHang khachHangGH) {
		super();
		this.idgiohang = idgiohang;
		this.ctghGH = ctghGH;
		this.khachHangGH = khachHangGH;
	}

	
}
