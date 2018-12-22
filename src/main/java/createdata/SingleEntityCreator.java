package createdata;

import entity.Country;
import entity.AEntity;
import entity.Event;
import entity.Location;
import entity.Organization;
import entity.Person;
import entity.Time;
import setting.RandomProperties;

public class SingleEntityCreator {
	private String label;
	private String description;
	private String name;
	private RandomProperties r = new RandomProperties();
	
	public AEntity createEntity(String label, String name, String description) {
		this.label = label;
		this.name = name;
		this.description = description;
		return (createEntity());
	}
	
	public AEntity createEntity() {
		AEntity ent;
		if (label.startsWith("Person")) {
			ent = createPerson();
		} else
		if (label.startsWith("Country")) {
			ent = createConutry();
		} else
		if (label.startsWith("Location")) {
			ent = createLocation();
		} else
		if (label.startsWith("Organization")) {
			ent = createOrganization();
		} else
		if (label.startsWith("Time")) {
			ent = createTime();
		} else
			ent = createEvent();
		return ent;
	}
	
	/**
	 * Tạo ngẫu nhiên ngày tháng và link nhận dữ liệu của thực thể
	 * @param p: 1 thưc thể bất kỳ
	 */
	private AEntity create(AEntity p) {
		p.setNgayThang(r.getRandomDate()); 
		p.setLink(r.getRandomLink(p.getDinhDanh()));
		return p;
	}
	
	/**
	 * @param p : Thực thể Person sau khi được tạo ngẫu nhiên
	 */
	private Person createPerson() {
		Person p = new Person (label, name, description);
		p = (Person) create(p);
		p.setNgaySinh(r.getRandomDate());
		p.setTuoi(r.getRandomInt(100));
		p.setGioiTinh(r.getRandomGioiTinh());
		return p;
	}
	
	/**
	 * @return p : Thực thể Organization sau khi được tạo ngẫu nhiên 
	 */
	private Organization createOrganization() {
		Organization p = new Organization(label, name, description);
		p = (Organization) create(p);
		p.setNgayThanhLap(r.getRandomDate());
		return p;
	}
	
	/**
	 * @return p : Thực thể Location sau khi được tạo ngẫu nhiên 
	 */
	private Location createLocation() {
		Location p = new Location(label, name, description);
		p = (Location) create(p);
		p.setToaDo(r.getRandomToaDo());
		return p;
	}
	
	/**
	 * @return p : Thực thể Country sau khi được tạo ngẫu nhiên
	 */
	private Country createConutry() {
		Country p = new Country(label, name, description);
		p = (Country) create(p);
		p.setDanSo(r.getRandomInt(9000000));
		p.setGdp(r.getRandomInt(50000));
		p.setDienTich(r.getRandomInt(1000000));
		return p;
	}
	
	/**
	 * @return p : Thực thể Event sau khi được tạo ngẫu nhiên 
	 */
	private Event createEvent() {
		Event p = new Event(label, name, description);
		p = (Event) create(p);
		return p;
	}
	
	/**
	 * @return p : Thực thể Time sau khi được tạo ngẫu nhiên
	 */
	private Time createTime() {
		Time p = new Time(label, name, description);
		p = (Time) create(p);
		return p;
	}
}
