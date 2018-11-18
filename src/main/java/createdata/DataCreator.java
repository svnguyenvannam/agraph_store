package createdata;

import java.util.Random;

import com.franz.agraph.repository.AGRepositoryConnection;

import connection.DataStoreder;
import connection.DatabaseConnecter;
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
	RawDataReader reader;
	DatabaseConnecter databaseConnecter;
	AGRepositoryConnection conn;
	private String[] entityData;
	private String[] descriptionData;
	private String[] relationshipData;
	private String[] dinhDanh = new String[6*Setting.DEFAULT_INDEX];

	public DataCreator() {
		reader = new RawDataReader();
		reader.readRawData();
		this.entityData = reader.getEntityData();
		this.descriptionData = reader.getDescriptionData();
		this.relationshipData = reader.getRelationshipData();
	}

	/**
	 * Sinh numberTriple Triplle vào repository mới tên là repositryName. Nếu
	 * repository này đã tồn tại, làm mới nó rồi mới thêm triple.
	 * Duyệt lần lượt từng phần tử trong entityData, do số phần tử của các thực thể 
	 * là bằng nhau, lại được xắp xếp trước. Nên ta có thể tính được thực thể đang 
	 * xét là dạng nào (Person, Time ...).
	 * Thứ tự tập phần tử là { "Person", "Organization", "Location", "Event", "Country", "Time" }.
	 * Mỗi tập phần tử có Setting.DEFAULT_INDEX phần tử.
	 * Các phép toán như i < 5*Setting.DEFAULT_INDEX là dùng để xác định xem phần tử đang
	 * xét hiện có dạng nào. Từ đó sinh định danh cho nó. Trong ví dụ trên, phần tử đang xét 
	 * có thuộc tập phần tử Country
	 * 
	 * @param numberTriple   : Số triple mới
	 * @param repositoryName : Tên repository mới
	 */
	public void createData(int numberTriple, String repositoryName) {
		databaseConnecter = DatabaseConnecter.getDatabaseConnecter();
		databaseConnecter.setRepository(repositoryName);
		conn = databaseConnecter.getConnection();
		conn.clear();
		
		// Duyệt từng phần tử, nối nó với miêu tả tương ứng rồi gọi hàm sinh dữ liệu của
		// class Entity
		// Hàm sinh sẽ gọi các phương thức của lớp con chứ không gọi hàm sinh của Entity
		// : Upcasting
		for (int i = 0; i < 6*Setting.DEFAULT_INDEX; i++) {
			Entity entity = null;
			String dinhDanh;
			if (i < Setting.DEFAULT_INDEX) {
				dinhDanh = "Person"+i;
				entity = new Person(dinhDanh, entityData[i], descriptionData[i]);
			}
			else if (i < 2*Setting.DEFAULT_INDEX) {
				dinhDanh = "Organization"+(i-Setting.DEFAULT_INDEX);
				entity = new Organization(dinhDanh, entityData[i], descriptionData[i]);
			}
			else if (i < 3*Setting.DEFAULT_INDEX) {
				dinhDanh = "Location" + (i-2*Setting.DEFAULT_INDEX);
				entity = new Location(dinhDanh, entityData[i], descriptionData[i]);
			}
			else if (i < 4*Setting.DEFAULT_INDEX) {
				dinhDanh = "Even"+(i-3*Setting.DEFAULT_INDEX);
				entity = new Event(dinhDanh, entityData[i], descriptionData[i]);
			}
			else if (i < 5*Setting.DEFAULT_INDEX) {
				dinhDanh = "Country"+(i-4*Setting.DEFAULT_INDEX);
				entity = new Country(dinhDanh, entityData[i], descriptionData[i]);
			}
			else {
				dinhDanh = "Time"+(i-5*Setting.DEFAULT_INDEX);
				entity = new Time(dinhDanh, entityData[i], descriptionData[i]);
			}
			
			entity.createEntity();
			entity.storeProperties(databaseConnecter);
			this.dinhDanh[i] = dinhDanh;
		}
		for (String string : dinhDanh) {
			System.out.println(string);
		}
//		if (this.dinhDanh.length < 2000) return;
		createRelationship(numberTriple, repositoryName, databaseConnecter);
	}

	/**
	 * Tạo ngẫu nhiên quan hệ bằng cách ghép lần lượt các thực thể với nhau. 
	 * Có 1 biến xác suất để tạo sự ngẫu nhiên trong dữ liệu
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
		
//		
//		Random rand = new Random();
//		DataStoreder storder = new DataStoreder(databaseConnecter);
//		int count = 0;
//		for (int ent1_index = 0; ent1_index < 5; ent1_index++) {
//			for (int ent1_index_dd = 0; ent1_index_dd < 10; ent1_index_dd++) {
//				if (dinhDanh[ent1_index][ent1_index_dd] == null) break;
//				for (int ent2_index = ent1_index + 1; ent2_index < 5; ent2_index++) {
//					for (int ent2_index_dd = 0; ent2_index_dd < 10; ent2_index_dd++) {
//						if (dinhDanh[ent2_index][ent2_index_dd] == null) break;
//						for (int rel_index = 0; rel_index < 10; rel_index++) {
//							if (relationshipData[ent2_index][rel_index] == null) break;
//							if (rand.nextInt(1000) > 850) continue;
//							String ent1 = dinhDanh[ent1_index][ent1_index_dd];
//							String ent2 = dinhDanh[ent2_index][ent2_index_dd];
//							String rel = relationshipData[ent2_index][rel_index];
//							storder.storeRelationship(dinhDanh[ent1_index][ent1_index_dd], 
//									dinhDanh[ent2_index][ent2_index_dd], 
//									relationshipData[ent2_index][rel_index]);
//							count++;
//							System.out.println(count);
//							if (count >= numberTriple) return;
//						}
//					}
//				}
//			}
//		}
		DataStoreder storeder = new DataStoreder(databaseConnecter);
		Random rand = new Random();
		int count = 0;
		
		int Max = 6*Setting.DEFAULT_INDEX;
		int MaxRel = relationshipData.length;
		label1:
		for (int i = 0; i < Max; i++) 
			label2:
			for (int j = 0; j < Max; j++) {
				if (i == j) continue label2;
				label3:
				for (int k = 0; k < MaxRel; k++) {
					if (rand.nextInt(100)  > 75) continue label3;
					storeder.storeRelationship(dinhDanh[i], relationshipData[k], dinhDanh[j]);
					count ++;
					System.out.println(count);
					if (count == numberTriple) break label1;
				}
			}
	}
}
