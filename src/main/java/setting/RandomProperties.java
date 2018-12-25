package setting;

import java.time.LocalDate;
import java.util.Random;

public class RandomProperties {
	Random random;
	
	public String getRandomDate() {
		random = new Random();
		int minDay = (int) LocalDate.of(1990,1,1).toEpochDay();
		int maxDay = (int) LocalDate.of(2019, 1, 1).toEpochDay();
		long randomDay = minDay + random.nextInt(maxDay - minDay);
		return LocalDate.ofEpochDay(randomDay).toString();
	}
	
	public String getRandomLink(String dinhDanh) {
		String link = entityCollection.DEFAULT_LINK + dinhDanh;
		return link;
	}
	
	public String getRandomGioiTinh() {
		if (random.nextBoolean())
			return "Nam";
		else return "Nữ";
	}
	
	public int getRandomNumber(int Min, int Max) {
		return Min + random.nextInt(Max - Min);
	}
	
	public int getRandomNumber(int Max) {
		return random.nextInt(Max);
	}
	
	public String getRandomToaDo() {
		return String.format("x = %d,%d  y = %d,%d", getRandomNumber(180), 
				getRandomNumber(100), getRandomNumber(180), getRandomNumber(100));
	}
}
