package entity;

import java.time.LocalDate;

import Struct.Dict;
import Struct.RandomProperties;
import connection.DataStoreder;
import connection.DatabaseConnecter;

public class Entity {
	private String dinhDanh = null;
	private String tenHienThi = null;
	private String moTa = null;
	private String link = null;
	private String ngayThang = null;
	
	// Constructor
	public Entity(String dinhDanh, String tenHienThi, String moTa, String link, String ngayThang) {
		this.dinhDanh = dinhDanh;
		this.tenHienThi = tenHienThi;
		this.moTa = moTa;
		this.link = link;
		this.ngayThang = ngayThang;
	}
	
	// Constructor 
	public Entity(String dinhDanh, String tenHienThi) {
		this.dinhDanh = dinhDanh;
		this.tenHienThi = tenHienThi;
	}
	
	// Constructor 
	public Entity(String dinhDanh, String tenHienThi, String moTa) {
		this.dinhDanh = dinhDanh;
		this.tenHienThi = tenHienThi;
		this.moTa = moTa;
	}
	
	/**
	 * Lưu các thuộc tính của Entity vào list.
	 * Các lớp thực thể khác muốn thêm các thuộc tính mới có thể kế thừa phương thức này
	 */
	protected Dict[] getListProperties() {
		Dict properties[] = new Dict[30];
		properties[0] = new Dict("định_danh", dinhDanh);
		properties[1] = new Dict("tên_hiển_thị", tenHienThi);
		properties[2] = new Dict("thực_thể", this.getClass().getName().replace("entity.", ""));
		properties[3] = new Dict("mô_tả", moTa);
		properties[4] = new Dict("link_nhận_dữ_liệu", link);
		properties[5] = new Dict("ngày_nhận_dữ_liệu", ngayThang);
		return properties;
	}
	
	/**
	 * Store List các thuộc tính vào database
	 * type dùng để xác định kiểu dữ liệu khi store vào DB (person, time ...)
	 */
	public void storeProperties(DatabaseConnecter databaseConnecter) {
		DataStoreder dataStoreder = new DataStoreder(databaseConnecter);
		dataStoreder.storeEntity(this.getListProperties());
	}
	
	public String getDinhDanh() {
		return dinhDanh;
	}
	
	public void setDinhDanh(String dinhDanh) {
		this.dinhDanh = dinhDanh;
	}
	
	public String getTenHienThi() {
		return tenHienThi;
	}
	
	public void setTenHienThi(String tenHienThi) {
		this.tenHienThi = tenHienThi;
	}
	
	public String getMoTa() {
		return moTa;
	}
	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}
	
	public String getLink() {
		return link;
	}
	
	public void setLink(String link) {
		this.link = link;
	}
	
	public String getNgayThang() {
		return ngayThang;
	}
	
	public void setNgayThang(String ngayThang) {
		this.ngayThang = ngayThang;
	}
}
