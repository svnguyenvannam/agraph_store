package filereader;

import java.io.File;
import java.util.Scanner;

public class FileReader {
	private String path;
	private File file;
	private Scanner scanner = null;

	/**
	 * Đọc file text lưu dữ liệu vào 1 mảng
	 * 
	 * @return String[] gồm dữ liệu đọc được
	 */
	public String[] readFile(int number) {
		file = new File(path);
		int count = 0;
		String[] listData = new String[number];
		try {
			scanner = new Scanner(file);
			while (scanner.hasNextLine() && count < number) {
				listData[count++] = scanner.nextLine().replace(" ", "_");
			}
		} catch (Exception e) { e.printStackTrace(); }
		return listData;
	}
	
	/**
	 * Kiểm tra tồn tại file 
	 * @return True : file có tồn tại, False : file không tồn tại
	 */
	public boolean exists() {
		file = new File(path);
	    return (file.exists());
	}
	
	public void setPath(String path) {
		this.path = path;
	}
}
