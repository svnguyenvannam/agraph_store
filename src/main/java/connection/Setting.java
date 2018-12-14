package connection;

public class Setting {
	static final String SERVER_URL = "http://localhost:10035";
	static final String CATALOG_ID = "java-catalog";
	static final String REPOSITORY_ID = "BTL_OOP";
	static final String USERNAME = "toanloi";
	static final String PASSWORD = "20162569";
	
	public static String ENTITY_PREFIX = "http://btl/entity/";
	public static String PROPERTIES_PREFIX = "http://btl/properties/";
	public static String RELATIONSHIP_PREFIX  = "http://btl/relationship/";
	
	public static String DIR_PATH = System.getProperty("user.dir");
	public static String DIR_DATA_PATH = DIR_PATH + "/RawData";
	
	public static int nEnt = 120;
	public static int nDes = 20;
	public static int nRel = 10;
	
	public static String DEFAULT_LINK = "https://vi.wikipedia.org/wiki/";
}