package setting;

import java.util.HashMap;

public class Config {
	public static final String SERVER_URL = "http://localhost:10035";
	public static final String CATALOG_ID = "java-catalog";
	public static final String REPOSITORY_ID = "BTL_OOP";
	public static final String USERNAME = "toanloi";
	public static final String PASSWORD = "20162569";
	
	public static final String CLASS_PREFIX = "http://btl/class/";
	public static final String ENTITY_PREFIX = "http://btl/entity/";
	public static final String PROPERTIES_PREFIX = "http://btl/properties/";
	public static final String RELATIONSHIP_PREFIX  = "http://btl/relationship/";
	
	public static final String DIR_PATH = System.getProperty("user.dir");
	public static final String DIR_DATA_PATH = DIR_PATH + "/RawData";
	public static final String DIR_QUERY_PATH = DIR_PATH + "/Query";
	public static final String DEFAULT_LINK = "https://vi.wikipedia.org/wiki/";
	
	private static final int[] x1 = {10, 10, 10, 10, 10, 50};
	private static final int[] x2 = {100, 100, 100, 100, 100, 4500};
	private static final int[] x3 = {220, 220, 220, 220, 220, 58900};
	private static final int[] x4 = {220, 220, 220, 220, 220, 98900};
	private static final int[] x5 = {220, 220, 220, 220, 220, 498900};

	public static final String[] str = { "Time", "Organization", "Location", "Event", "Country", "Person"};
	public static final HashMap<Integer, int[]> numberStr =  new HashMap<Integer, int[]>(){{
		put(100, x1);
		put(5000, x2);
		put(60000, x3);
		put(100000, x4);
		put(500000, x5);
	}};
}
