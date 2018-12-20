package entity;

import java.util.HashMap;

public class Person extends AEntity {
	private int tuoi;
	private String ngaySinh;
	private String gioiTinh;
	
	// Constructor
	public Person(String dinhDanh, String tenHienThi, String moTa, String link, String ngayThang) {
		super(dinhDanh, tenHienThi, moTa, link, ngayThang);
	}

	public int getTuoi() {
		return tuoi;
	}

	public void setTuoi(int tuoi) {
		this.tuoi = tuoi;
	}

	public String getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(String ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	public String getGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	// Constructor
	public Person(String dinhDanh, String tenHienThi) {
		super(dinhDanh, tenHienThi);
	}

	// Constructor
	public Person(String dinhDanh, String tenHienThi, String moTa) {
		super(dinhDanh, tenHienThi, moTa);
	}
	
	public HashMap<Object, Object> getListProperties() {
		HashMap<Object, Object> properties = super.getListProperties();
		properties.put("tuổi", tuoi);
		properties.put("ngày_sinh", ngaySinh);
		properties.put("giới_tính", gioiTinh);
		return properties;
	}
}
