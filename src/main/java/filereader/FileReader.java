package filereader;

import java.io.File;
import java.util.ArrayList;
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
	public ArrayList<String> readFile() {
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
