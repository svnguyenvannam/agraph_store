package filereader;

import java.io.File;
import java.util.Scanner;

public abstract class AFileReader {
	protected File file;
	protected Scanner scanner = null;
	
	/**
	 * Kiểm tra tồn tại file 
	 * @return True : file có tồn tại, False : file không tồn tại
	 */
	protected boolean exists(String path) {
		file = new File(path);
	    return (file.exists());
	}
}
