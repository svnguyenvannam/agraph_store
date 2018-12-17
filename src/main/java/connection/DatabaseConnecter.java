package connection;

import com.franz.agraph.repository.AGCatalog;
import com.franz.agraph.repository.AGRepository;
import com.franz.agraph.repository.AGRepositoryConnection;
import com.franz.agraph.repository.AGServer;

/**
 * Kết nối với server theo server, repository, user, password đã cho trước. Nếu
 * chưa tồn tại repository sẽ tự động tạo repository
 */
public class DatabaseConnecter {
	private String serverURL, catalogID, repositoryID, user, password;
	private static AGServer server;
	private static AGCatalog catalog;
	private static AGRepository myRepository;
	private static DatabaseConnecter databaseConnecter;
	private static AGRepositoryConnection conn;

	// Constructor
	private DatabaseConnecter(String severURL, String repositoryID, String user, String password) {
		this.serverURL = severURL;
		this.repositoryID = repositoryID;
		this.user = user;
		this.password = password;
		this.createConnection();
	}

	// Constructor
	private DatabaseConnecter(String repository) {
		this.serverURL = Setting.SERVER_URL;
		this.catalogID = Setting.CATALOG_ID;
		this.repositoryID = repository;
		this.user = Setting.USERNAME;
		this.password = Setting.PASSWORD;
		this.createConnection();
	}

	// Constructor
	private DatabaseConnecter() {
		this.serverURL = Setting.SERVER_URL;
		this.catalogID = Setting.CATALOG_ID;
		this.repositoryID = Setting.REPOSITORY_ID;
		this.user = Setting.USERNAME;
		this.password = Setting.PASSWORD;
		this.createConnection();
	}

	/**
	 * Kết nối với sever. Nếu chưa tồn tại repository sẽ tự động tạo repository mới.
	 * Tạo kết nối với repository.
	 */
	private void createConnection() {
		server = new AGServer(this.serverURL, this.user, this.password);
		catalog = server.getRootCatalog();
		if (!catalog.hasRepository(this.repositoryID)) {
			myRepository = catalog.createRepository(this.repositoryID);
			myRepository.initialize();
			conn = myRepository.getConnection();
			conn.setNamespace("class", Setting.CLASS_PREFIX);
			conn.setNamespace("ent", Setting.ENTITY_PREFIX);
			conn.setNamespace("prs", Setting.PROPERTIES_PREFIX);
			conn.setNamespace("rel", Setting.RELATIONSHIP_PREFIX);
		}
		else {
			myRepository = catalog.openRepository(this.repositoryID);
			conn = myRepository.getConnection();
		}
	}

	public static DatabaseConnecter getDatabaseConnecter(String severURL, String repositoryID, String user,
			String password) {
		if (databaseConnecter == null) {
			databaseConnecter = new DatabaseConnecter(severURL, repositoryID, user, password);
		}
		return databaseConnecter;
	}

	public static DatabaseConnecter getDatabaseConnecter() {
		databaseConnecter = getDatabaseConnecter(Setting.SERVER_URL, Setting.REPOSITORY_ID, Setting.USERNAME,
				Setting.PASSWORD);
		return databaseConnecter;
	}

	public static DatabaseConnecter getDatabaseConnecter(String repositoryID) {
		if (databaseConnecter == null) {
			databaseConnecter = new DatabaseConnecter(repositoryID);
		}
		return databaseConnecter;
	}

	/**
	 * @return Đối tượng kết nối với DB
	 */
	public AGRepositoryConnection getConnection() {
		return conn;
	}

	/**
	 * Đặt lại repository. Dùng để tạo nhiều repository ứng với nhiều bộ truy vấn
	 * 
	 * @param repositoryName : Tên repository
	 */
	public void createRepository(String repositoryName) {
		databaseConnecter.closeConnection();
		myRepository = catalog.createRepository(repositoryName);
		myRepository.setDuplicateSuppressionPolicy("spo");
		myRepository.initialize();

		conn = myRepository.getConnection();
	}

	/**
	 * Thoát khỏi phiên làm việc, đồng thời đóng repository
	 * 
	 * @param conn : Kết nối với DB hiện tại
	 */
	public void closeConnection() {
		conn.close();
		myRepository.shutDown();
	}
}