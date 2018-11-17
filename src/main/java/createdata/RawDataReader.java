package createdata;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import connection.Setting;

/**
 * Đọc các Raw Data từ file text.Trả ra các Data đọc được dạng Array
 * 
 * @author toanloi
 */
/*
 * entityData là mảng 2 chiều, lưu các tên hiển thị của các thực thể. Chiều thứ
 * nhất gồm 6 phần từ tương ứng với 6 thực thể. Chiều thứ 2 lưu tên hiển thị của
 * các thực thể
 * 
 * entityDescriptionData là mảng 2 chiều, lưu các mô tả tương ứng của thực thể
 * 
 * entityRelationshipData là mảng 2 chiều, lưu các quan hệ ứng với mỗi thực thể
 */
public class RawDataReader {
	private String pathFile;
	private String[][] entityData = new String[6][Setting.DEFAULT_INDEX];
	private String[][] descriptionData = new String[6][Setting.DEFAULT_INDEX];
	private String[][] relationshipData = new String[6][15];
	private String[] entityPathFile = new String[6];
	private String[] descriptionPathFile = new String[6];
	private String[] relationshipPathFile = new String[6];

	/**
	 * Đọc file text lưu dữ liệu vào 1 mảng
	 * 
	 * @param isReplace : Có thay thể dấu " " bằng "_" không
	 * @return String[] gồm dữ liệu đọc được
	 */
	private String[] readRawFile(boolean isReplace) {
		File rawDataFile = new File(this.pathFile);
		Scanner scanner = null;
		try {
			scanner = new Scanner(rawDataFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String[] listRawData = new String[Setting.DEFAULT_INDEX];
		int count = 0;

		// Đọc đến khi nào hết file relationship
		try {
			System.out.println(pathFile);
			if (isReplace)
				while (scanner.hasNextLine()) {
					listRawData[count] = scanner.nextLine().replace(" ", "_");
					count++;
				}
			else 
				while (scanner.hasNextLine()) {
					listRawData[count] = scanner.nextLine();
					count++;
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listRawData;
	}
	
	/**
	 * Đọc dữ liệu vào các mảng tương ứng.
	 */
	public void readRawData() {
		setupPathFile();
		for (int i = 0; i < 6; i++) {
			pathFile = entityPathFile[i];
			entityData[i] = readRawFile(false);

			pathFile = descriptionPathFile[i];
			descriptionData[i] =readRawFile(false);

			pathFile = relationshipPathFile[i];
			relationshipData[i] = readRawFile(true);
		}
	}

	/**
	 * Cài đặt các path dẫn đến file dữ liệu
	 */
	private void setupPathFile() {
		String[] str = Setting.ENTITIES_ARRAY;
		entityPathFile = new String[6];
		descriptionPathFile = new String[6];
		relationshipPathFile = new String[6];
		for (int i = 0; i < 6; i++) {
			entityPathFile[i] = Setting.DIR_DATA_PATH + "/" + str[i] + ".txt";
			descriptionPathFile[i] = Setting.DIR_DATA_PATH + "/" + str[i] + "Description.txt";
			relationshipPathFile[i] = Setting.DIR_DATA_PATH + "/" + str[i] + "Relationship.txt";
		}
	}

	/**
	 * In tất cả dữ liệu 1 mảng 2 chiều đọc được từ raw data ra console
	 */
	private void printRawData(String[][] str) {
		for (String[] strings : str) {
			for (String string : strings) {
				if (string != null)
					System.out.println(string);
				else
					break;
			}
		}
	}

	public String[][] getEntityData() {return entityData;}
	public String[][] getRelationshipData() {return relationshipData;}
	public String[][] getDescriptionData() {return descriptionData;}
}
