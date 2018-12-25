package query;

import java.util.ArrayList;

import setting.Config;

public class QueryReader {
	private ArrayList<Query> listNormalQuery = new ArrayList<Query>(10);
	private ArrayList<Query> listAdvancedQuery = new ArrayList<Query>(10);
	
        //constructor
	public QueryReader() {
		this.getNormalAndAdvancedQuery();
	}
	
	public void getNormalAndAdvancedQuery() {
		filereader.QueryReader reader = new filereader.QueryReader();
		
		String pathNormalQuery = Config.DIR_QUERY_PATH + "/NormalQuery.txt";
		reader.setPath(pathNormalQuery);
		listNormalQuery = reader.readFile();
		
		String pathAdvancedQuery = Config.DIR_QUERY_PATH + "/AdvancedQuery.txt";
		reader.setPath(pathAdvancedQuery);
		listAdvancedQuery = reader.readFile();
	}	
	
	public ArrayList<Query> getListNormalQuery() {return listNormalQuery;}
	public ArrayList<Query> getListAdvancedQuery() {return listAdvancedQuery;}
}
