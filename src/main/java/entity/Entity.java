package entity;

import java.util.HashMap;

import org.eclipse.rdf4j.model.impl.TreeModel;

import connection.DataStoreder;

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
	public HashMap<Object, Object> getListProperties() {
		HashMap<Object, Object> properties = new HashMap<Object, Object>();
		properties.put("định_danh", dinhDanh);
		properties.put("thực_thể", this.getClass().getName().replace("entity.", ""));
		properties.put("tên_hiển_thị", tenHienThi);
		properties.put("mô_tả", moTa);
		properties.put("link_nhận_dữ_liệu", link);
		properties.put("ngày_nhận_dữ_liệu", ngayThang);
		return properties;
	}
	
	/**
	 * Store List các thuộc tính vào database
	 * type dùng để xác định kiểu dữ liệu khi store vào DB (person, time ...)
	 */
	public void storeProperties(TreeModel model) {
		DataStoreder dataStoreder = new DataStoreder(model);
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
