package com.example.payload;

import java.util.Date;

public class DonHangRequest {
	private String cmnd;
	private Date ngaylap;
	private String tennguoinhan;
	private String diachinhan;
	private String emailnhan;
	private String sdtnguoinhan;
	private float tongtien;
	private Integer matrangthai;
	public Date getNgaylap() {
		return ngaylap;
	}
	public void setNgaylap(Date ngaylap) {
		this.ngaylap = ngaylap;
	}
	public String getTennguoinhan() {
		return tennguoinhan;
	}
	public void setTennguoinhan(String tennguoinhan) {
		this.tennguoinhan = tennguoinhan;
	}
	
	public String getEmailnhan() {
		return emailnhan;
	}
	public void setEmailnhan(String emailchinhan) {
		this.emailnhan = emailchinhan;
	}
	public String getDiachinhan() {
		return diachinhan;
	}
	public void setDiachinhan(String diachinhan) {
		this.diachinhan = diachinhan;
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
	public Integer getMatrangthai() {
		return matrangthai;
	}
	public void setMatrangthai(Integer matrangthai) {
		this.matrangthai = matrangthai;
	}
	
	public String getCmnd() {
		return cmnd;
	}
	public void setCmnd(String cmnd) {
		this.cmnd = cmnd;
	}
	public DonHangRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
