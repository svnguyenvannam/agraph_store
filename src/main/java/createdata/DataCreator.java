package createdata;
import java.util.Random;

import org.eclipse.rdf4j.model.impl.TreeModel;

import com.franz.agraph.repository.AGRepositoryConnection;

import connection.DataStoreder;
import connection.DatabaseConnecter;
import connection.Setting;
import entity.Entity;

/**
 * Class sinh dữ liệu từ dữ liệu đọc ở file data
 */
public class DataCreator {
	private DatabaseConnecter databaseConnecter;
	private AGRepositoryConnection conn;
	private TreeModel model;
	private DataReader reader = new DataReader();
	private String[][] entityData = reader.getEntityData();
	private String[][] descriptionData = reader.getDescriptionData();
	private String[][][] relationshipData = reader.getRelationshipData();
	private String[] str = { "Person", "Organization", "Location", "Event", "Country", "Time" };

	public DataCreator(DatabaseConnecter databaseConnecter) {
		this.databaseConnecter = databaseConnecter;
	}

	/**
	 * Sinh các thực thể có trong tập EntityData
	 * Vòng for đầu để duyệt tất cả các thực thể như Person, Event ...
	 * Vòng for thứ hai duyệt tất cả dữ liệu đã đọc được của mỗi thực thể đang xét
	 * để sinh dữ liệu tương ứng.
	 * Mỗi dữ liệu sau khi sinh được đưa luôn lên server
	 */
	public void createData(String repositoryName) {
		EntityCreator creator = new EntityCreator();
		databaseConnecter = DatabaseConnecter.getDatabaseConnecter();
		databaseConnecter.setRepository(repositoryName);
		conn = databaseConnecter.getConnection();
		conn.clear();
		
		Random rand = new Random();
		for (int i = 0; i < 6; i++) 
		for (int j = 0; j < Setting.nEnt; j ++) {
			int r = rand.nextInt(Setting.nDes);
			String label = str[i] + j;
			Entity e = creator.creatEntity(label, entityData[i][j], descriptionData[i][r]);
			e.storeProperties(databaseConnecter);
		}
	}
	
	/**
	 * Tạo ngẫu nhiên quan hệ bằng cách ghép lần lượt các thực thể với nhau. Có 1
	 * biến xác suất để tạo sự ngẫu nhiên trong dữ liệu
	 * 
	 * @param numberTriple   : số lượng triple cần tạo
	 * @param repositoryName : repository dùng để lưu dữ liệu
	 */
	public void createRelationship(int numberTriple, String repositoryName) {
		Random r = new Random();
		DataStoreder storeder = new DataStoreder(databaseConnecter);
		int count = 0;
		label: 
		for (int i = 0; i < 6; i++)
		for (int j = 0; j < 6; j++) {
			if (relationshipData[i][j] == null) continue;
			for (int ent1 = 0; ent1 < Setting.nEnt; ent1++) 
			for (int ent2 = 0; ent2 < Setting.nEnt; ent2++) {
				if (count > numberTriple) break label;
				String label1 = str[i]+ent1;
				String label2 = str[j]+ent2;
				if (label1.equals(label2)) continue;
				for (String rel : relationshipData[i][j]) {
					if (r.nextInt(100) > 3) continue;
					storeder.storeRelationship(label1, rel, label2);
				}
			}
		}
	}
}
