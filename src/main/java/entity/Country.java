package entity;

import java.util.HashMap;

public class Country extends AEntity{
	private int danSo, gdp, dienTich;
	
	// Constructor
	public Country(String dinhDanh, String tenHienThi, String moTa, String link, String ngayThang) {
		super(dinhDanh, tenHienThi, moTa, link, ngayThang);
	}
	
	public int getDanSo() {
		return danSo;
	}

	public void setDanSo(int danSo) {
		this.danSo = danSo;
	}

	public int getGdp() {
		return gdp;
	}

	public void setGdp(int gdp) {
		this.gdp = gdp;
	}

	public int getDienTich() {
		return dienTich;
	}

	public void setDienTich(int dienTich) {
		this.dienTich = dienTich;
	}

	// Constructor
	public Country(String dinhDanh, String tenHienThi) {
		super(dinhDanh, tenHienThi);
	}
	
	// Constructor 
	public Country(String dinhDanh, String tenHienThi, String moTa) {
		super(dinhDanh, tenHienThi, moTa);
	}
	
	public HashMap<Object, Object> getListProperties() {
		HashMap<Object, Object> properties = super.getListProperties();
		properties.put("dân_số", danSo);
		properties.put("GDP", gdp);
		properties.put("diện_tích", dienTich);
		return properties;
	}
}
