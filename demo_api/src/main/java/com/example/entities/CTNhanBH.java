package com.example.entities;

import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name="CTNHANBH")
public class CTNhanBH {
	@EmbeddedId
	private CTNhanBHKeys id;
	private String trangthaitruocbh;
	private Date ngaytra;
	private String chitietsaubh;
	
	@ManyToOne
	@MapsId("masobhct")
	@JoinColumn(name="masobh")
	private PhieuBH phieuBHCT;
	
	@ManyToOne
	@MapsId("manvnhan")
	@JoinColumn(name="manv")
	private NhanVien nhanVienNhanBH;

	public CTNhanBH() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CTNhanBHKeys getId() {
		return id;
	}

	public void setId(CTNhanBHKeys id) {
		this.id = id;
	}

	public String getTrangthaitruocbh() {
		return trangthaitruocbh;
	}

	public void setTrangthaitruocbh(String trangthaitruocbh) {
		this.trangthaitruocbh = trangthaitruocbh;
	}

	public Date getNgaytra() {
		return ngaytra;
	}

	public void setNgaytra(Date ngaytra) {
		this.ngaytra = ngaytra;
	}

	public String getChitietsaubh() {
		return chitietsaubh;
	}

	public void setChitietsaubh(String chitietsaubh) {
		this.chitietsaubh = chitietsaubh;
	}

	public PhieuBH getPhieuBHCT() {
		return phieuBHCT;
	}

	public void setPhieuBHCT(PhieuBH phieuBHCT) {
		this.phieuBHCT = phieuBHCT;
	}

	public NhanVien getNhanVienNhan() {
		return nhanVienNhanBH;
	}

	public void setNhanVienNhan(NhanVien nhanVienNhan) {
		this.nhanVienNhanBH = nhanVienNhan;
	}
	
	
}
