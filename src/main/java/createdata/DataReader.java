package createdata;

import java.util.ArrayList;

import connection.Setting;
import filereader.FileReader;

/**
 * Đọc các Raw Data từ file text.Trả ra các Data đọc được dạng String[]
 * Dữ liệu tất cả các thực thể được đặt vào 1 file riêng, tương tự đối với 
 * miêu tả của chúng.
 */

public class DataReader {
	private ArrayList<String>[] entityData = new ArrayList[6];
	private ArrayList<String>[] descriptionData = new ArrayList[6];
	private ArrayList<String>[][] relationshipData = new ArrayList[6][6];
	private FileReader reader = new FileReader();
	private String[] str = Setting.str;
	
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
			entityData[i] = reader.readFile();
			reader.setPath(Setting.DIR_DATA_PATH + "/Description/" + str[i] + ".txt"); 
			descriptionData[i] = reader.readFile();
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
				relationshipData[i][j] = reader.readFile();
			} else relationshipData[i][j] = null;
		}
	}
	
	public ArrayList<String>[] getEntityData() {return entityData;}
	public ArrayList<String>[] getDescriptionData() {return descriptionData;}
	public ArrayList<String>[][] getRelationshipData() {return relationshipData;}
}
	
