package createdata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import org.eclipse.rdf4j.model.impl.TreeModel;

import com.franz.agraph.repository.AGRepositoryConnection;

import entity.AEntity;
import filereader.DataReader;
import setting.Config;

/**
 * Class sinh dữ liệu từ dữ liệu đọc ở file data
 */
public class DataCreator {

    private AGRepositoryConnection conn;
    private DataStoreder storeder = new DataStoreder(new TreeModel());

    private ArrayList<String>[] listEntity;
    private ArrayList<String>[] listDescription;
    private ArrayList<String>[][] listRelationship;

    private String[] strNameEntity = Config.strNameEntity;
    private HashMap<Integer, int[]> entityCollection = Config.entityCollection;
    private int numberRelationship, numberEntity;

    public DataCreator(AGRepositoryConnection conn, int numberEntity, int numberRelationship) {
        this.conn = conn;
        this.numberRelationship = numberRelationship;
        this.numberEntity = numberEntity;
        this.getEntityAndDescription();
        this.getRelationship();
    }

    /**
     * Sinh các thực thể có trong tập EntityData Vòng for đầu để duyệt tất cả
     * các thực thể như Person, Event ... Vòng for thứ hai duyệt tất cả dữ liệu
     * đã đọc được của mỗi thực thể đang xét để sinh dữ liệu tương ứng. Mỗi dữ
     * liệu sau khi sinh được đưa vào model. Khi model đủ 250000 triple sẽ đưa
     * lên server
     */
    public void createData() {
        SingleEntityCreator creator = new SingleEntityCreator();
        Random rand = new Random();
        for (int i = 0; i < 6; i++) {
            for (int count = 0; count < entityCollection.get(numberEntity)[i]; count++) {
                String id = strNameEntity[i] + count;
                String name = listEntity[i].get(rand.nextInt(listEntity[i].size()));
                String description = listDescription[i].get(rand.nextInt(listDescription[i].size()));
                AEntity newEntity = creator.createEntity(id, name, description);
                storeder.storeEntity(newEntity.getListProperties());
                if (storeder.getModel().size() > 250000) {
                    storeModel();
                }
            }
        }
        storeModel();
    }

    /**
     * Tạo ngẫu nhiên quan hệ bằng cách chọn ngẫu nhiên các thực thể và liên kết
     * giữa chúng để tiến hành ghép nối
     */
    public void createRelationship() {
        Random rand = new Random();
        int numberTripleEntity = (int) conn.size();
        if (numberRelationship + numberTripleEntity > 5000000) {
            numberRelationship = 5000000 - numberTripleEntity;
        }
        int incomplete = numberRelationship;    // số lượng triple còn phải thêm vào để đủ numberRelationship triple.
        while (incomplete > 0) {

            // Chọn 2 loại thực thể để tiến hành ghép nối
            // Nếu không tồn tại liên kết giữa 2 thực thể thì bỏ qua
            int rand_1 = rand.nextInt(6);
            int rand_2 = rand.nextInt(6);
            if (listRelationship[rand_1][rand_2] == null) {
                continue;
            }

            // Chọn ngẫu nhiên thực thể trong các thực thể đã chọn để ghép nối
            // Chọn ngẫu nhiên relationship giữa 2 thực thể để ghép nối
            // Ex : entityCollection.get(100)= {10, 10, 10, 10, 10, 50};
            int firstEntIndex = rand.nextInt(entityCollection.get(numberEntity)[rand_1]);
            int secondEntIndex = rand.nextInt(entityCollection.get(numberEntity)[rand_2]);
            int relIndex = rand.nextInt(listRelationship[rand_1][rand_2].size());

            String idEnt1 = strNameEntity[rand_1] + firstEntIndex;
            String idEnt2 = strNameEntity[rand_2] + secondEntIndex;
            String rel = listRelationship[rand_1][rand_2].get(relIndex);
            storeder.storeRelationship(idEnt1, rel, idEnt2);

            // Kiểm tra nếu model vướt quá 50000 hoặc bằng số triple cần thêm thì add vào repository
            // Cập nhật lại số triple cần thêm
            if (storeder.getModel().size() >= 250000 || storeder.getModel().size() >= incomplete) {
                storeModel();
                incomplete = numberRelationship + numberTripleEntity - (int) conn.size();
            }
        }
    }

    /**
     * Đọc dữ liệu entity và mô tả Dữ liệu sẽ được lưu vào 1 mảng 2 chiều Chiều
     * thứ nhất là 6 loại Entity Chiều thứ hai là 150 text tương ứng với mỗi
     * loại
     *
     * vd: listEntity[0]="Manh, Nam, Trung...";
     */
    private void getEntityAndDescription() {
        filereader.DataReader reader = new filereader.DataReader();
        for (int i = 0; i < 6; i++) {
            String pathEntity = Config.DIR_DATA_PATH + "/Entity/" + strNameEntity[i] + ".txt";
            reader.setPath(pathEntity);
            listEntity[i] = reader.readFile();

            String pathDescription = Config.DIR_DATA_PATH + "/Description/" + strNameEntity[i] + ".txt";
            reader.setPath(pathDescription);
            listDescription[i] = reader.readFile();
        }
    }

    /**
     * Đọc dữ liệu từ file relationship, lưu vào 1 mảng 3 chiều Chiều thứ nhất
     * và thứ 2 tương ứng với 2 entity Chiều thứ 3 tương ứng với 10 text
     * relationship mỗi 2 loại tương ứng
     *
     * Vd: listRelationship[0][0]="den tham, lam anh huong..."
     */
    private void getRelationship() {
        DataReader reader = new DataReader();
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                String pathRelationship = Config.DIR_DATA_PATH + "/Relationship/" + strNameEntity[i] + strNameEntity[j] + ".txt";
                reader.setPath(pathRelationship);
                if (reader.exists()) {
                    listRelationship[i][j] = reader.readFile();
                } else {
                    listRelationship[i][j] = null;
                }
            }
        }
    }

    private void storeModel() {
        conn.add(storeder.getModel());
        storeder.getModel().clear();

    }
}
