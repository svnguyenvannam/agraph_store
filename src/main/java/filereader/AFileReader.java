package filereader;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class AFileReader<E> {
	protected File file;
	protected Scanner scanner = null;
	protected String path;
	
	public AFileReader(String path) {
		this.path = path;
	}
	
	public AFileReader(){
		
	}
	
	/**
	 * Đặt lại thuộc tính file 
	 * @param path
	 */
	public void setPath (String path) {
		this.path = path;
	}
	
	/**
	 * Kiểm tra tồn tại file 
	 * @return True : file có tồn tại, False : file không tồn tại
	 */
	public boolean exists() {
		file = new File(path);
	    return (file.exists());
	}
	
	public abstract ArrayList<E> readFile ();
}
