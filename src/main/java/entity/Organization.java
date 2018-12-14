package entity;

import java.time.LocalDate;

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
}
