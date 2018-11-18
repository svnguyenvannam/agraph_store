package entity;

import Struct.Dict;

public class Person extends Entity {
	// Constructor
	public Person(String dinhDanh, String tenHienThi, String moTa, String link, String ngayThang) {
		super(dinhDanh, tenHienThi, moTa, link, ngayThang);
	}

	// Constructor
	public Person(String dinhDanh, String tenHienThi) {
		super(dinhDanh, tenHienThi);
	}

	// Constructor
	public Person(String dinhDanh, String tenHienThi, String moTa) {
		super(dinhDanh, tenHienThi, moTa);
	}
}
