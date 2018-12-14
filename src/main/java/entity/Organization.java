package entity;

import java.time.LocalDate;

import Struct.Dict;

public class Organization extends Entity {
	private String ngayThanhLap;

	public String getNgayThanhLap() {
		return ngayThanhLap;
	}

	public void setNgayThanhLap(String ngayThanhLap) {
		this.ngayThanhLap = ngayThanhLap;
	}

	// Constructor
	public Organization(String dinhDanh, String tenHienThi, String moTa, String link, String ngayThang) {
		super(dinhDanh, tenHienThi, moTa, link, ngayThang);
	}

	// Constructor
	public Organization(String dinhDanh, String tenHienThi) {
		super(dinhDanh, tenHienThi);
	}

	// Constructor
	public Organization(String dinhDanh, String tenHienThi, String moTa) {
		super(dinhDanh, tenHienThi, moTa);
	}
	
	public Dict[] getListProperties() {
		Dict properties[] = super.getListProperties();
		properties[6] = new Dict ("ngày_thành_lập", ngayThanhLap);
		return properties;
	}
}
