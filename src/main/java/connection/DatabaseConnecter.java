package connection;

import com.franz.agraph.repository.AGCatalog;
import com.franz.agraph.repository.AGRepository;
import com.franz.agraph.repository.AGRepositoryConnection;
import com.franz.agraph.repository.AGServer;

import setting.Setting;

/**
 * Kết nối với server theo server, repository, user, password đã cho trước. Nếu
 * chưa tồn tại repository sẽ tự động tạo repository
 */
public class DatabaseConnecter {
	private String serverURL, user, password;
	private AGServer server;
	private AGCatalog catalog;
	private AGRepository myRepository;
	private AGRepositoryConnection conn;
	private static DatabaseConnecter databaseConnecter;

	// Constructor
	private DatabaseConnecter(String severURL, String user, String password) {
		this.serverURL = severURL;
		this.user = user;
		this.password = password;
		this.server = new AGServer(this.serverURL, this.user, this.password);
		this.catalog = this.server.getRootCatalog();
	}

	// Constructor
	private DatabaseConnecter() {
		this.serverURL = Setting.SERVER_URL;
		this.user = Setting.USERNAME;
		this.password = Setting.PASSWORD;
		this.server = new AGServer(Setting.SERVER_URL, Setting.USERNAME, Setting.PASSWORD);
		this.catalog = this.server.getRootCatalog();
	}

	/**
	 * Kết nối với sever. Nếu chưa tồn tại repository sẽ tự động tạo repository mới.
	 * Tạo kết nối với repository.
	 */
	public AGRepositoryConnection getConnection (String repositoryName) {
		if (!catalog.hasRepository(repositoryName)) {
			createRepository(repositoryName);
		}
		else {
			myRepository = catalog.openRepository(repositoryName);
			conn = myRepository.getConnection();
		}
		return conn;
	}
 
	/**
	 * Tạo một kết nối với repository tương ứng nhập vào. Nếu repository này chưa tồn tại,
	 * tự động tạo 1 repository có tên tương ứng và trả ra kết nối
	 * @param repositoryID Tên của repository muốn kết nối
	 * @return Đối tượng databaseConnecter dùng để kết nối với repository vừa nhập
	 */
	public static DatabaseConnecter getDatabaseConnecter(String ServerURL, String user, String password) {
		if (databaseConnecter == null) {
			databaseConnecter = new DatabaseConnecter(ServerURL, user, password);
		} 
		if (!ServerURL.equals(databaseConnecter.serverURL) || !user.equals(databaseConnecter.user) ||
				!password.equals(databaseConnecter.password)) {
			databaseConnecter.server.close();
			databaseConnecter = new DatabaseConnecter(ServerURL, user, password);
		}
		return databaseConnecter;
	}

	/**
	 * Đặt mới lại repository. Dùng để tạo nhiều repository ứng với nhiều bộ truy vấn.
	 * Nếu repository đã tồn tại thì sẽ xóa repository cũ và ghi đè repository mới
	 * @param repositoryName : Tên repository
	 */
	public void createRepository(String repositoryName) {
		databaseConnecter.closeConnection();
		if (catalog.hasRepository(repositoryName))
			catalog.deleteRepository(repositoryName);
		myRepository = catalog.createRepository(repositoryName);
		myRepository.setDuplicateSuppressionPolicy("spo");
		myRepository.initialize();
		conn = myRepository.getConnection();
		conn.setNamespace("class", Setting.CLASS_PREFIX);
		conn.setNamespace("ent", Setting.ENTITY_PREFIX);
		conn.setNamespace("prs", Setting.PROPERTIES_PREFIX);
		conn.setNamespace("rel", Setting.RELATIONSHIP_PREFIX);
	}

	/**
	 * Thoát khỏi phiên làm việc, đồng thời đóng repository
	 * @param conn : Kết nối với DB hiện tại
	 */
	public void closeConnection() {
		if (conn == null) return;
		conn.close();
		myRepository.shutDown();
	}
}