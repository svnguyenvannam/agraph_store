package filereader;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import setting.Query;

public class QueryReader extends AFileReader<Query> {

	public QueryReader(String path) {
		super(path);
		// TODO Auto-generated constructor stub
	}

	public QueryReader() {
		super();
	}
	
	@Override
	public ArrayList<Query> readFile() {
		file = new File(path);
		ArrayList<Query> listData = new ArrayList<Query>(10);
		try {
			scanner = new Scanner(file);
			while (scanner.hasNextLine())  {
				String des = scanner.nextLine();
				String quer = "";
				while (true) {
					String line = scanner.nextLine();
					if (line.replaceAll(" ", "").replaceAll("\t", "").equals("***")) break;
					quer += line+"\n";
				}
				listData.add(new Query(des, quer));
			}
		} catch (Exception e) { e.printStackTrace(); }
		return listData;
	}

}
