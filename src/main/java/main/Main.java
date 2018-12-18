package main;
import com.franz.agraph.repository.AGRepositoryConnection;

import connection.DatabaseConnecter;
import connection.Query;
import createdata.DataCreator;

public class Main {
	public static String user = Setting.USERNAME;
	public static String password = Setting.PASSWORD;
	public static String severURL = Setting.SERVER_URL;
	public static void main(String[] args) {
		DatabaseConnecter databaseConnecter = DatabaseConnecter.getDatabaseConnecter(severURL, user, password);
//		databaseConnecter.createRepository("OOP_5000");
		AGRepositoryConnection conn = databaseConnecter.getConnection("OOP_5000000");
//		AGRepositoryConnection conn = databaseConnecter.getConnection();
//		conn.clear();
//		DataCreator d = new DataCreator();
//		d.createData(15000,"OOP_15000");
//		databaseConnecter.setRepository("OOP_15000");
//		AGRepositoryConnection conn = databaseConnecter.getConnection();
//		AGValueFactory vf = conn.getValueFactory();
				
//		Query query = new Query(databaseConnecter);
//		long startTime= System.currentTimeMillis();
//		query.printResultQuery0();
//	
//		query.printResultQuery7();
//		long endTime=System.currentTimeMillis();
//		long runTime=(endTime-startTime);
//		
//		System.out.print(runTime + "ms");
		long startTime= System.currentTimeMillis();
//		DataCreator creator = new DataCreator(conn);
//		creator.createData();
//		creator.createRelationship(5000000);
		Query q = new Query(conn);
		for (int i = 0; i < 10; i++) {
			q.getResultNormalQuery(i);
		}
		long endTime=System.currentTimeMillis();
		long runTime=(endTime-startTime);
		System.out.print(runTime + "ms");
	}
	
	public void creatRepository(String repositoryName) {
		
	}
	
	public static void calTime(int x, Query query) {
		
		return;
	}
}
