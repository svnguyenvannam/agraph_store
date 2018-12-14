package entity;

import java.time.LocalDate;

import Struct.Dict;
import Struct.RandomProperties;

public class Country extends Entity{
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
	
	protected Dict[] getListProperties() {
		Dict properties[] = super.getListProperties();
		properties[6] = new Dict("dân_số", danSo);
		properties[7] = new Dict("GDP", gdp);
		properties[8] = new Dict("diện_tích", dienTich);
		return properties;
	}
}
