package setting;

import java.util.HashMap;

public class Setting {
	public static final String SERVER_URL = "http://localhost:10035";
	public static final String CATALOG_ID = "java-catalog";
	public static final String REPOSITORY_ID = "BTL_OOP";
	public static final String USERNAME = "manhronado";
	public static final String PASSWORD = "manhthinh98";
	
	public static String CLASS_PREFIX = "http://btl/class/";
	public static String ENTITY_PREFIX = "http://btl/entity/";
	public static String PROPERTIES_PREFIX = "http://btl/properties/";
	public static String RELATIONSHIP_PREFIX  = "http://btl/relationship/";
	
	public static String DIR_PATH = System.getProperty("user.dir");
	public static String DIR_DATA_PATH = DIR_PATH + "/RawData";
	public static String DIR_QUERY_PATH = DIR_PATH + "/Query";
	public static String DEFAULT_LINK = "https://vi.wikipedia.org/wiki/";
	
	private static int[] x1 = {10, 10, 10, 10, 10, 50};
	private static int[] x2 = {100, 100, 100, 100, 100, 4500};
	private static int[] x3 = {220, 220, 220, 220, 220, 58900};
	private static int[] x4 = {220, 220, 220, 220, 220, 98900};
	private static int[] x5 = {220, 220, 220, 220, 220, 498900};

	public static String[] str = { "Time", "Organization", "Location", "Event", "Country", "Person"};
	public static HashMap<Integer, int[]> numberStr =  new HashMap<Integer, int[]>(){{
		put(100, x1);
		put(5000, x2);
		put(60000, x3);
		put(100000, x4);
		put(500000, x5);
	}};
}
