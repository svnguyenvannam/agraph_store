package filereader;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import setting.Setting;
import setting.StructQuery;

public class QueryReader extends AFileReader {
	private ArrayList<StructQuery> listNormalQuery = new ArrayList<StructQuery>(10);
	private ArrayList<StructQuery> listAdvancedQuery = new ArrayList<StructQuery>(10);
	
	public QueryReader() {
		this.getNormalAndAdvancedQuery();
	}
	
	private void getNormalAndAdvancedQuery() {
		String pathNormalQuery = Setting.DIR_QUERY_PATH + "/NormalQuery.txt";
		listNormalQuery = readQuery(pathNormalQuery);
		String pathAdvancedQuery = Setting.DIR_QUERY_PATH + "/AdvancedQuery.txt";
		listAdvancedQuery = readQuery(pathAdvancedQuery);
	}
	
	private ArrayList<StructQuery> readQuery(String path) {
		file = new File(path);
		ArrayList<StructQuery> listData = new ArrayList<StructQuery>(10);
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
				listData.add(new StructQuery(des, quer));
			}
		} catch (Exception e) { e.printStackTrace(); }
		return listData;
	}
	
	public ArrayList<StructQuery> getListNormalQuery() {return listNormalQuery;}
	public ArrayList<StructQuery> getListAdvancedQuery() {return listAdvancedQuery;}
}
