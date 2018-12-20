package entity;

import java.util.HashMap;

public class Location extends AEntity {
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
	
	public HashMap<Object, Object> getListProperties() {
		HashMap<Object, Object> properties = super.getListProperties();
		properties.put("tọa_độ", toaDo);
		return properties;
	}
	
	
}
