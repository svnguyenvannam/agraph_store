package main;
import com.franz.agraph.repository.AGRepositoryConnection;

import connection.DatabaseConnecter;
import createdata.DataCreator;
import setting.Setting;
import time.CalTime;

public class Main {
	public static String user = Setting.USERNAME;
	public static String password = Setting.PASSWORD;
	public static String severURL = Setting.SERVER_URL;
	public static DatabaseConnecter databaseConnecter = DatabaseConnecter.getDatabaseConnecter(severURL, user, password);
	public static AGRepositoryConnection conn;
	public static void main(String[] args) {
		createAndGetQuery();
	}
	
	public static void createAndGetQuery() {
		conn = creatRepository("100_entity_200_relation", 100, 200);
		calTime(conn);
		databaseConnecter.closeConnection();
		
		conn = creatRepository("5000_entity_7000_relation", 5000, 7000);
		calTime(conn);
		databaseConnecter.closeConnection();
		
		conn = creatRepository("60000_entity_80000_relation", 60000, 80000);
		calTime(conn);
		databaseConnecter.closeConnection();
		
		conn = creatRepository("100000_entity_200000_relation", 100000, 200000);
		calTime(conn);
		databaseConnecter.closeConnection();
		
		conn = creatRepository("500000_entity_1000000_relation", 500000, 3000000);
		calTime(conn);
		databaseConnecter.closeConnection();
	}
	
	public static AGRepositoryConnection creatRepository(String repositoryName, int numberEntity, int numberRelationship) {
		databaseConnecter.createRepository(repositoryName);
		AGRepositoryConnection conn = databaseConnecter.getConnection(repositoryName);
		DataCreator creator = new DataCreator(conn, numberEntity, numberRelationship);
		
		creator.createData();
		creator.createRelationship();
		System.out.format("Da tao xong repostisoty %s\n", repositoryName);
		return conn;
	}
	
	public static void calTime(AGRepositoryConnection conn) {
		CalTime calTime = new CalTime(conn);
		for (int i = 0; i < 10; i++) 
			calTime.calTimeNormalQuery(i);
		System.out.println("______________________________________________________________________________");
		for (int i = 0; i < 10; i++) 
			calTime.calTimeAdvancedQuery(i);
	}
}
