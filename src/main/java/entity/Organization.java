package entity;

public class Organization extends Entity {

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
