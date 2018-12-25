package createdata;

import java.util.ArrayList;

import setting.Config;

/**
 * Đọc các Raw Data từ file text.Trả ra các Data đọc được dạng String[]
 * Dữ liệu tất cả các thực thể được đặt vào 1 file riêng, tương tự đối với 
 * miêu tả của chúng.
 */

public class DataReader{
	private ArrayList<String>[] listEntity = new ArrayList[6];
	private ArrayList<String>[] listDescription = new ArrayList[6];
	private ArrayList<String>[][] listRelationship = new ArrayList[6][6];
	private String[] str = Config.str;
	
	public DataReader() {
		this.getEntityAndDescription();
		this.getRelationship();
	}
	
	/**
	 * Đọc dữ liệu entity và mô tả
	 * Dữ liệu sẽ được lưu vào 1 mảng 2 chiều
	 * Chiều thứ nhất là 6 loại Entity 
	 * Chiều thứ hai là 150 text tương ứng với mỗi loại 
         * 
         * vd: listEntity[0]="Manh, Nam, Trung...";
	 */
	private void getEntityAndDescription() {
		filereader.DataReader reader = new filereader.DataReader();
		for (int i = 0; i < 6; i++) {
			String pathEntity = Config.DIR_DATA_PATH + "/Entity/" + str[i] + ".txt";
			reader.setPath(pathEntity);
			listEntity[i]= reader.readFile();
			
			String pathDescription = Config.DIR_DATA_PATH + "/Description/" + str[i] + ".txt";
			reader.setPath(pathDescription);
			listDescription[i] = reader.readFile();
		}
	}

	/**
	 * Đọc dữ liệu từ file relationship, lưu vào 1 mảng 3 chiều
	 * Chiều thứ nhất và thứ 2 tương ứng với 2 entity
	 * Chiều thứ 3 tương ứng với 10 text relationship mỗi 2 loại tương ứng
         * 
         * Vd: listRelationship[0][0]="den tham, lam anh huong..."
	 */
	private void getRelationship() {
		filereader.DataReader reader = new filereader.DataReader();
		for (int i = 0; i < 6; i++)
		for (int j = 0; j < 6; j++) {
			String pathRelationship = Config.DIR_DATA_PATH + "/Relationship/" + str[i]+str[j] + ".txt";
			reader.setPath(pathRelationship);
			if (reader.exists()) { 
				listRelationship[i][j] = reader.readFile();
			} else listRelationship[i][j] = null;
		}
	}
	
	public ArrayList<String>[] getListEntity() {return listEntity;}
	public ArrayList<String>[] getListDescriptionData() {return listDescription;}
	public ArrayList<String>[][] getListRelationshipData() {return listRelationship;}
}