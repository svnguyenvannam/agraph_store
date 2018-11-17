package entity;

public class Country extends Entity{
	
	// Constructor
	public Country(String dinhDanh, String tenHienThi, String moTa, String link, String ngayThang) {
		super(dinhDanh, tenHienThi, moTa, link, ngayThang);
	}
	
	// Constructor
	public Country(String dinhDanh, String tenHienThi) {
		super(dinhDanh, tenHienThi);
	}
	
	// Constructor 
	public Country(String dinhDanh, String tenHienThi, String moTa) {
		super(dinhDanh, tenHienThi, moTa);
	}
}
