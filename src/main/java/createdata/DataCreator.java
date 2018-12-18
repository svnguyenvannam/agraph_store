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
import filereader.DataReader;

/**
 * Class sinh dữ liệu từ dữ liệu đọc ở file data
 */
public class DataCreator {
	private DatabaseConnecter databaseConnecter;
	private AGRepositoryConnection conn;
	private TreeModel model = new TreeModel();
	private DataReader reader = new DataReader();
	private ArrayList<String>[] listEntity = reader.getListEntity();
	private ArrayList<String>[] listDescription = reader.getListDescriptionData();
	private ArrayList<String>[][] listRelationship = reader.getListRelationshipData();
	private String[] str = Setting.str;
	private int[] numberStr = Setting.numberStr;

	public DataCreator(DatabaseConnecter databaseConnecter) {
		this.databaseConnecter = databaseConnecter;
		this.conn = databaseConnecter.getConnection();
	}

	/**
	 * Sinh các thực thể có trong tập EntityData Vòng for đầu để duyệt tất cả các
	 * thực thể như Person, Event ... Vòng for thứ hai duyệt tất cả dữ liệu đã đọc
	 * được của mỗi thực thể đang xét để sinh dữ liệu tương ứng. Mỗi dữ liệu sau khi
	 * sinh được đưa luôn lên server
	 */
	public void createData() {
		EntityCreator creator = new EntityCreator();
		Random rand = new Random();
		for (int i = 0; i < 6; i++)
		for (int count = 0; count < numberStr[i]; count++) {
			String id = str[i] + count;
			String name = listEntity[i].get(rand.nextInt(listEntity[i].size()));
			String description = listDescription[i].get(rand.nextInt(listDescription[i].size()));
			creator.createEntity(id, name, description).storeProperties(model);
			if (model.size() > 250000) store_model();
		}
		store_model();
	}

	/**
	 * Tạo ngẫu nhiên quan hệ bằng cách chọn ngẫu nhiên các thực thể và liên kết 
	 * giữa chúng để tiến hành ghép nối
	 * 
	 * @param numberTriple   : số lượng triple cần tạo
	 * @param repositoryName : repository dùng để lưu dữ liệu
	 */
	public void createRelationship(int numberTriple) {
		DataStoreder storeder = new DataStoreder(model);
		Random rand = new Random();
		int incomplete = numberTriple - (int) conn.size();
		while (incomplete > 0) {
			
			// Chọn 2 loại thực thể để tiến hành ghép nối
			// Nếu không tồn tại liên kết giữa 2 thực thể thì bỏ qua
			int rand_1 = rand.nextInt(6);
			int rand_2 = rand.nextInt(6);
			if (listRelationship[rand_1][rand_2] == null) continue;
			
			// Chọn ngẫu nhiên thực thể trong các thực thể đã chọn để ghép nối
			// Chọn ngẫu nhiên relationship giữa 2 thực thể để ghép nối
			int ent_1 = rand.nextInt(numberStr[rand_1]);
			int ent_2 = rand.nextInt(numberStr[rand_2]);
			int rel_index = rand.nextInt(listRelationship[rand_1][rand_2].size());
			String id1 = str[rand_1] + ent_1;
			String id2 = str[rand_2] + ent_2;
			String rel = listRelationship[rand_1][rand_2].get(rel_index);
			storeder.storeRelationship(id1, rel, id2);
			
			// Kiểm tra nếu model vướt quá 50000 hoặc bằng số triple cần thêm thì add vào repository
			// Cập nhật lại số triple cần thêm
			if (model.size() >= 250000 || model.size() >= incomplete) {
				store_model();
				incomplete = numberTriple - (int) conn.size();	
			}
		}
	}
	
	private void store_model() {
		print();
		conn.add(model);
		model.clear();
		print();
		System.out.println("----------------------------------------------");
	}

	void print() {
		System.out.println(conn.size());
		System.out.println(model.size());
	}
}
