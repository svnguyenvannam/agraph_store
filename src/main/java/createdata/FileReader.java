package createdata;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import connection.Setting;

public class FileReader {
	private String pathFile;
	private File file;
	private Scanner scanner;
	
	public FileReader(String pathFile) {
		this.pathFile = pathFile;
	}
	
	/**
	 * Đọc file text lưu dữ liệu vào 1 mảng
	 * 
	 * @return String[] gồm dữ liệu đọc được
	 */
	public String[] readFile() {
		file = new File(pathFile);
		scanner = null;
		try {
			scanner = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String[] listData = new String[Setting.DEFAULT_INDEX];
		int count = 0;

		// Đọc đến khi nào hết file relationship
		try {
			while (scanner.hasNextLine()) {
				listData[count] = scanner.nextLine().replace(" ", "_");
				count++;
				if (count == 10) return listData;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listData;
	}
}
