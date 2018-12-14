package entity;

import java.time.LocalDate;
import java.util.Random;

import Struct.Dict;
import Struct.RandomProperties;

public class Person extends Entity {
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
	
	protected Dict[] getListProperties() {
		Dict properties[] = super.getListProperties();
		properties[6] = new Dict ("tuổi", tuoi);
		properties[7] = new Dict ("ngày_sinh", ngaySinh);
		properties[8] = new Dict ("giới_tính", gioiTinh);
		return properties;
	}
}
