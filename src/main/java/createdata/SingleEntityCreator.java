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
	private String id;	
	private String name;
        private String description;
	private RandomProperties r = new RandomProperties();
	
	public AEntity createEntity(String id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
		return (createEntity());
	}
	
	public AEntity createEntity() {
		AEntity ent;
		if (id.startsWith("Person")) {
			ent = createPerson();
		} else
		if (id.startsWith("Country")) {
			ent = createCountry();
		} else
		if (id.startsWith("Location")) {
			ent = createLocation();
		} else
		if (id.startsWith("Organization")) {
			ent = createOrganization();
		} else
		if (id.startsWith("Time")) {
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
		Person p = new Person (id, name, description);
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
		Organization p = new Organization(id, name, description);
		p = (Organization) create(p);
		p.setNgayThanhLap(r.getRandomDate());
		return p;
	}
	
	/**
	 * @return p : Thực thể Location sau khi được tạo ngẫu nhiên 
	 */
	private Location createLocation() {
		Location p = new Location(id, name, description);
		p = (Location) create(p);
		p.setToaDo(r.getRandomToaDo());
		return p;
	}
	
	/**
	 * @return p : Thực thể Country sau khi được tạo ngẫu nhiên
	 */
	private Country createCountry() {
		Country p = new Country(id, name, description);
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
		Event p = new Event(id, name, description);
		p = (Event) create(p);
		return p;
	}
	
	/**
	 * @return p : Thực thể Time sau khi được tạo ngẫu nhiên
	 */
	private Time createTime() {
		Time p = new Time(id, name, description);
		p = (Time) create(p);
		return p;
	}
}
