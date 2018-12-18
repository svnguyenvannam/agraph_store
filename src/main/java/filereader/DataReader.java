package filereader;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import connection.Setting;

/**
 * Đọc các Raw Data từ file text.Trả ra các Data đọc được dạng String[]
 * Dữ liệu tất cả các thực thể được đặt vào 1 file riêng, tương tự đối với 
 * miêu tả của chúng.
 */

public class DataReader extends FileReader{
	private ArrayList<String>[] listEntity = new ArrayList[6];
	private ArrayList<String>[] listDescription = new ArrayList[6];
	private ArrayList<String>[][] listRelationship = new ArrayList[6][6];
	private FileReader reader = new FileReader();
	private String[] str = Setting.str;
	
	public DataReader() {
		this.getEntityAndDescription();
		this.getRelationship();
	}
	
	/**
	 * Đọc dữ liệu entity và mô tả
	 * Dữ liệu sẽ được lưu vào 1 mảng 2 chiều
	 * Chiều thứ nhất là 6 loại Entity 
	 * Chiều thứ hai là 150 text tương ứng với mỗi loại 
	 */
	private void getEntityAndDescription() {
		for (int i = 0; i < 6; i++) {
			String pathEntity = Setting.DIR_DATA_PATH + "/Entity/" + str[i] + ".txt";
			String pathDescription = Setting.DIR_DATA_PATH + "/Description/" + str[i] + ".txt";
			listEntity[i] = readEntityOrDescriptionOrRelationship(pathEntity);
			listDescription[i] = readEntityOrDescriptionOrRelationship(pathDescription);
		}
	}

	/**
	 * Đọc dữ liệu từ file relationship, lưu vào 1 mảng 3 chiều
	 * Chiều thứ nhất và thứ 2 tương ứng với 2 entity
	 * Chiều thứ 3 tương ứng với 10 text relationship mỗi 2 loại tương ứng
	 */
	private void getRelationship() {
		for (int i = 0; i < 6; i++)
		for (int j = 0; j < 6; j++) {
			String pathRelationship = Setting.DIR_DATA_PATH + "/Relationship/" + str[i]+str[j] + ".txt";
			if (reader.exists(pathRelationship)) {
				listRelationship[i][j] = readEntityOrDescriptionOrRelationship(pathRelationship);
			} else listRelationship[i][j] = null;
		}
	}
	
	/**
	 * Đọc file Entity, Description hoặc Relationship
	 * @param path Đường dẫn đến file cần đọc
	 * @return ArrayList chứa dữ liệu đọc được
	 */
	private ArrayList<String> readEntityOrDescriptionOrRelationship(String path) {
		file = new File(path);
		ArrayList<String> listData = new ArrayList<String>();
		try {
			scanner = new Scanner(file);
			while (scanner.hasNextLine())  {
				listData.add(scanner.nextLine().replace(" ", "_"));
			}
		} catch (Exception e) { e.printStackTrace(); }
		return listData;
	}
	
	public ArrayList<String>[] getListEntity() {return listEntity;}
	public ArrayList<String>[] getListDescriptionData() {return listDescription;}
	public ArrayList<String>[][] getListRelationshipData() {return listRelationship;}
}