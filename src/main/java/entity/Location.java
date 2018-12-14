package entity;

import Struct.Dict;

public class Location extends Entity {
	private String toaDo;

	public String getToaDo() {
		return toaDo;
	}

	public void setToaDo(String toaDo) {
		this.toaDo = toaDo;
	}

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
	
	public Dict[] getListProperties() {
		Dict properties[] = super.getListProperties();
		properties[6] = new Dict ("tọa_độ", toaDo);
		return properties;
	}
	
	
}
