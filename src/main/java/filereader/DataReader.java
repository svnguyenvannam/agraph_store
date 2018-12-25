package filereader;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class DataReader extends AFileReader<String>{

	public DataReader(String path) {
		super(path);
	}

	public DataReader() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Override
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

}
