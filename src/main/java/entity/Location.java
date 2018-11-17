package entity;

public class Location extends Entity {

	// Constructor
	public Location(String dinhDanh, String tenHienThi, String moTa, String link, String ngayThang) {
		super(dinhDanh, tenHienThi, moTa, link, ngayThang);
	}

	// Constructor
	public Location(String dinhDanh, String tenHienThi) {
		super(dinhDanh, tenHienThi);
	}

	// Constructor
	public Location(String dinhDanh, String tenHienThi, String moTa) {
		super(dinhDanh, tenHienThi, moTa);
	}
}
