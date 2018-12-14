package createdata;

import connection.Setting;
import filereader.FileReader;

/**
 * Đọc các Raw Data từ file text.Trả ra các Data đọc được dạng String[]
 * Dữ liệu tất cả các thực thể được đặt vào 1 file riêng, tương tự đối với 
 * miêu tả của chúng.
 */
public class DataReader {
	private String[][] entityData = new String[6][Setting.nEnt];
	private String[][] descriptionData = new String[6][Setting.nDes];
	private String[][][] relationshipData = new String[6][6][Setting.nRel];
	private FileReader reader = new FileReader();
	private String[] str = { "Person", "Organization", "Location", "Event", "Country", "Time" };
	
	public DataReader() {
		this.readEntity();
		this.readRelationship();
	}
	
	/**
	 * Đọc dữ liệu entity và mô tả
	 * Dữ liệu sẽ được lưu vào 1 mảng 2 chiều
	 * Chiều thứ nhất là 6 loại Entity 
	 * Chiều thứ hai là 150 text tương ứng với mỗi loại 
	 */
	private void readEntity() {
		for (int i = 0; i < 6; i++) {
			reader.setPath(Setting.DIR_DATA_PATH + "/Entity/" + str[i] + ".txt");
			entityData[i] = reader.readFile(Setting.nEnt);
			reader.setPath(Setting.DIR_DATA_PATH + "/Description/" + str[i] + ".txt"); 
			descriptionData[i] = reader.readFile(Setting.nDes);
		}
	}

	/**
	 * Đọc dữ liệu từ file relationship, lưu vào 1 mảng 3 chiều
	 * Chiều thứ nhất và thứ 2 tương ứng với 2 entity
	 * Chiều thứ 3 tương ứng với 10 text relationship mỗi 2 loại tương ứng
	 */
	private void readRelationship() {
		for (int i = 0; i < 6; i++)
		for (int j = 0; j < 6; j++) {
			reader.setPath(Setting.DIR_DATA_PATH + "/Relationship/" + str[i]+str[j] + ".txt");
			if (reader.exists()) {
				relationshipData[i][j] = reader.readFile(Setting.nRel);
			} else relationshipData[i][j] = null;
		}
	}
	
	public String[][] getEntityData() {return entityData;}
	public String[][][] getRelationshipData() {return relationshipData;}
	public String[][] getDescriptionData() {return descriptionData;}
}
