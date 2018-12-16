package createdata;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

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
	private TreeModel model = new TreeModel();
	private DataStoreder storeder = new DataStoreder(model);
	private DataReader reader = new DataReader();
	private ArrayList<String>[] entityData = reader.getEntityData();
	private ArrayList<String>[] descriptionData = reader.getDescriptionData();
	private ArrayList<String>[][] relationshipData = reader.getRelationshipData();
	private String[] str = Setting.str;
	private int[] numberStr = Setting.numberStr;

	public DataCreator(DatabaseConnecter databaseConnecter) {
		this.databaseConnecter = databaseConnecter;
		this.conn = databaseConnecter.getConnection();
	}

	/**
	 * Sinh các thực thể có trong tập EntityData
	 * Vòng for đầu để duyệt tất cả các thực thể như Person, Event ...
	 * Vòng for thứ hai duyệt tất cả dữ liệu đã đọc được của mỗi thực thể đang xét
	 * để sinh dữ liệu tương ứng.
	 * Mỗi dữ liệu sau khi sinh được đưa luôn lên server
	 */
	public void createData(){
		EntityCreator creator = new EntityCreator();		
		Random rand = new Random();
		for (int i = 0; i < 6; i++) {
//		for (int j = 0; j < entityData[i].size(); j ++) {
//			int r = rand.nextInt(descriptionData[i].size());
//			String label = str[i] + j;
//			Entity e = creator.creatEntity(label, entityData[i].get(j), descriptionData[i].get(r));
//			storeder.storeEntity(e.getListProperties());
//			e.storeProperties(model);
//			if (model.size() >= 50000*count) {
//				print();
//				conn.add(model);
//				model.clear();
//				count++;
//				print();
//			}
//		}
			for (int count = 0; count < numberStr[i]; count++) {
				String id = str[i] + count++;
				String name = entityData[i].get(rand.nextInt(entityData[i].size()));
				String description = descriptionData[i].get(rand.nextInt(descriptionData[i].size()));
				Entity e = creator.createEntity(id, name, description);
			}
		}
		entityData[5] = new ArrayList<String>(entityData[5].subList(0, 5000));;
		print();
		conn.add(model);
		model.clear();
		print();
	}
	
	/**
	 * Tạo ngẫu nhiên quan hệ bằng cách ghép lần lượt các thực thể với nhau. Có 1
	 * biến xác suất để tạo sự ngẫu nhiên trong dữ liệu
	 * 
	 * @param numberTriple   : số lượng triple cần tạo
	 * @param repositoryName : repository dùng để lưu dữ liệu
	 */
	public void createRelationship(int numberTriple) {
		int coefDiv = (int)Math.sqrt(5000000/numberTriple);
		Random r = new Random();
		int count = 0;
		label: 
		for (int i = 0; i < 6; i++)
		for (int j = 0; j < 6; j++) {
			if (relationshipData[i][j] == null) continue;
			for (int ent1 = 0; ent1 < entityData[i].size()/coefDiv; ent1++) 
			for (int ent2 = 0; ent2 < entityData[j].size()/coefDiv; ent2++) {
				if (count > numberTriple) break label;
				String label1 = str[i]+ent1;
				String label2 = str[j]+ent2;
				if (label1.equals(label2)) continue;
				for (String rel : relationshipData[i][j]) {
					if ((i == 5 || j == 5) && r.nextInt(100) > 2) continue;
					if (r.nextInt(100) > 15) continue;
					storeder.storeRelationship(label1, rel, label2);
					count++;
					if (count % 500000 == 0) {
						print();
						System.out.println(i + " " + j);
						conn.add(model);
						model.clear();
						print();
						System.out.println("__________________________________________");
					}
				}
			}
		}
		print();
		conn.add(model);
		model.clear();
		print();
	}
	
	void print() {
		System.out.println(conn.size());
		System.out.println(model.size());
	}
}
