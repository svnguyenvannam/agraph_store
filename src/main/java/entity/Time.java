package entity;

public class Time extends Entity {

	// Constructor
	public Time(String dinhDanh, String tenHienThi, String moTa, String link, String ngayThang) {
		super(dinhDanh, tenHienThi, moTa, link, ngayThang);
	}

	// Constructor
	public Time(String dinhDanh, String tenHienThi) {
		super(dinhDanh, tenHienThi);
	}

	// Constructor
	public Time(String dinhDanh, String tenHienThi, String moTa) {
		super(dinhDanh, tenHienThi, moTa);
	}
}
