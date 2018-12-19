package main;
import com.franz.agraph.repository.AGRepositoryConnection;

import connection.DatabaseConnecter;
import connection.Query;
import createdata.DataCreator;
import time.CalTime;

public class Main {
	public static String user = Setting.USERNAME;
	public static String password = Setting.PASSWORD;
	public static String severURL = Setting.SERVER_URL;
	public static DatabaseConnecter databaseConnecter = DatabaseConnecter.getDatabaseConnecter(severURL, user, password);
	public static AGRepositoryConnection conn;
	public static void main(String[] args) {	
		conn = creatRepository("OOP_100", 100, 200);
		calTime(conn);
		
		conn = creatRepository("OOP_5000", 5000, 7000);
		calTime(conn);
		
		conn = creatRepository("OOP_60000", 60000, 80000);
		calTime(conn);
		
		conn = creatRepository("OOP_100000", 100000, 200000);
		calTime(conn);
		
		conn = creatRepository("OOP_500000", 500000, 3000000);
		calTime(conn);
	}
	
	public static AGRepositoryConnection creatRepository(String repositoryName, int numberEntity, int numberRelationship) {
		databaseConnecter.createRepository(repositoryName);
		AGRepositoryConnection conn = databaseConnecter.getConnection(repositoryName);
		DataCreator creator = new DataCreator(conn, numberEntity, numberRelationship);
		
		long startTime = System.currentTimeMillis();
		creator.createData();
		creator.createRelationship();
		long endTime = System.currentTimeMillis();
		long runtime = endTime - startTime;
		System.out.format("Thoi gian tao repository %s la %d ms\n", repositoryName, runtime);
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
