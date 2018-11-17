package createdata;

import java.util.Random;

import com.franz.agraph.repository.AGRepositoryConnection;

import Struct.Dict;
import connection.DataStoreder;
import connection.DatabaseConnecter;
import connection.Query;
import connection.Setting;
import entity.Country;
import entity.Entity;
import entity.Event;
import entity.Location;
import entity.Organization;
import entity.Person;
import entity.Time;

/**
 * Class sinh dữ liệu từ dữ liệu đọc ở file data
 */
public class DataCreator {
	private String[][] entityData;
	private String[][] descriptionData;
	private String[][] relationshipData;
	private String[][] dinhDanh = new String[6][Setting.DEFAULT_INDEX];

	public DataCreator() {
		RawDataReader reader = new RawDataReader();
		reader.readRawData();
		this.entityData = reader.getEntityData();
		this.descriptionData = reader.getDescriptionData();
		this.relationshipData = reader.getRelationshipData();
	}

	/**
	 * Sinh numberTriple Triplle vào repository mới tên là repositryName. Nếu
	 * repository này đã tồn tại, làm mới nó rồi mới thêm triple
	 * 
	 * @param numberTriple   : Số triple mới
	 * @param repositoryName : Tên repository mới
	 */
	public void createData(int numberTriple, String repositoryName) {
		DatabaseConnecter databaseConnecter = DatabaseConnecter.getDatabaseConnecter();
		databaseConnecter.setRepository(repositoryName);
		AGRepositoryConnection conn = databaseConnecter.getConnection();
		conn.clear();
		
		// Duyệt từng phần tử, nối nó với miêu tả tương ứng rồi gọi hàm sinh dữ liệu của
		// class Entity
		// Hàm sinh sẽ gọi các phương thức của lớp con chứ không gọi hàm sinh của Entity
		// : Upcasting
		for (int i = 0; i < Setting.ENTITIES_ARRAY.length; i++) {
			int count = 1;
			for (int j = 0; j < Setting.DEFAULT_INDEX; j++) {
				if (entityData[i][j] == null)
					break;
				Entity entity = null;
				String dinhDanh = Setting.ENTITIES_ARRAY[i] + '_' + count;
				if (i == 0)
					entity = new Person(dinhDanh, entityData[i][j], descriptionData[i][j]);
				if (i == 1)
					entity = new Organization(dinhDanh, entityData[i][j], descriptionData[i][j]);
				if (i == 2)
					entity = new Location(dinhDanh, entityData[i][j], descriptionData[i][j]);
				if (i == 3)
					entity = new Event(dinhDanh, entityData[i][j], descriptionData[i][j]);
				if (i == 4)
					entity = new Country(dinhDanh, entityData[i][j], descriptionData[i][j]);
				if (i == 5)
					entity = new Time(dinhDanh, entityData[i][j], descriptionData[i][j]);
				entity.createEntity();
				entity.storeProperties(databaseConnecter);
				this.dinhDanh[i][j] = dinhDanh;
				count++;
			}
		}
		createRelationship(numberTriple, repositoryName, databaseConnecter);
	}

	/**
	 * Tạo tất cả các thực thể có thể tạo được, sử dụng 4 vòng for, có 1 biến xác
	 * suất để xem triple tạo được có được đưa vào database hay không. Xác suất được
	 * chọn là 85%.
	 * 
	 * @param numberTriple   : số lượng triple cần tạo
	 * @param repositoryName : repository dùng để lưu dữ liệu
	 */
	public void createRelationship(int numberTriple, String repositoryName, DatabaseConnecter databaseConnecter) {
//			// Sinh ngẫu nhiên trạng thái của liên kết, sau đó lấy ngẫu nhiên các định danh
//			Dict entities = createState();
//			Random rand = new Random();
//			String ent1 = null, ent2 = null, relationship = null;
//			while (relationship == null) {
//				relationship = relationshipData[(Integer) entities.V][rand.nextInt(15)];
//			}
//			while (ent1 == null) {
//				ent1 = dinhDanh[(Integer) entities.K][rand.nextInt(Setting.DEFAULT_INDEX)];
//			}
//			while (ent2 == null) {
//				ent2 = dinhDanh[(Integer) entities.V][rand.nextInt(Setting.DEFAULT_INDEX)];
//			}
//			relationship.replace("\n", "");
//
//			DataStoreder dataStoreder = new DataStoreder(databaseConnecter);
//			dataStoreder.storeRelationship(ent1, relationship, ent2);
//			count++;
//			System.out.println(count);
		Random rand = new Random();
		DataStoreder storder = new DataStoreder(databaseConnecter);
		int count = 0;
		for (int ent1_index = 0; ent1_index < Setting.ENTITIES_ARRAY.length - 1; ent1_index++) {
			for (int ent1_index_dd = 0; ent1_index_dd < Setting.DEFAULT_INDEX; ent1_index_dd++) {
				if (dinhDanh[ent1_index][ent1_index_dd] == null) break;
				for (int ent2_index = ent1_index + 1; ent2_index < Setting.ENTITIES_ARRAY.length; ent2_index++) {
					for (int ent2_index_dd = 0; ent2_index_dd < Setting.DEFAULT_INDEX; ent2_index_dd++) {
						if (dinhDanh[ent2_index][ent2_index_dd] == null) break;
						for (int rel_index = 0; rel_index < Setting.DEFAULT_INDEX; rel_index++) {
							if (relationshipData[ent2_index][rel_index] == null) break;
							if (rand.nextInt(1000) > 850) continue;
							String ent1 = dinhDanh[ent1_index][ent1_index_dd];
							String ent2 = dinhDanh[ent2_index][ent2_index_dd];
							String rel = relationshipData[ent2_index][rel_index];
							storder.storeRelationship(ent1, rel, ent2);
							count++;
							System.out.println(count);
							if (count >= numberTriple) return;
						}
					}
				}
			}
		}
	}
}
