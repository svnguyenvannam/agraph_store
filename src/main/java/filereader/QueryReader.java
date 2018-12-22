package filereader;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import query.Query;
import setting.Config;

public class QueryReader extends AFileReader {
	private ArrayList<Query> listNormalQuery = new ArrayList<Query>(10);
	private ArrayList<Query> listAdvancedQuery = new ArrayList<Query>(10);
	
	public QueryReader() {
		this.getNormalAndAdvancedQuery();
	}
	
	private void getNormalAndAdvancedQuery() {
		String pathNormalQuery = Config.DIR_QUERY_PATH + "/NormalQuery.txt";
		listNormalQuery = readQuery(pathNormalQuery);
		String pathAdvancedQuery = Config.DIR_QUERY_PATH + "/AdvancedQuery.txt";
		listAdvancedQuery = readQuery(pathAdvancedQuery);
	}
	
	private ArrayList<Query> readQuery(String path) {
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
	
	public ArrayList<Query> getListNormalQuery() {return listNormalQuery;}
	public ArrayList<Query> getListAdvancedQuery() {return listAdvancedQuery;}
}
