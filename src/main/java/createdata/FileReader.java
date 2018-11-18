package createdata;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import connection.Setting;

public class FileReader {
	private String pathFile;
	private File file;
	private int number;
	private Scanner scanner;
	
	public FileReader(String pathFile, int number) {
		this.pathFile = pathFile;
		this.number = number;
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
		String[] listData = new String[number];
		int count = 0;

		// Đọc đến khi nào hết file relationship
		try {
			while (scanner.hasNextLine() && count < number) {
				listData[count] = scanner.nextLine().replace(" ", "_");
				count++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listData;
	}
}
